package com.example.travelwise.utils

import com.example.travelwise.BuildConfig
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

class AiService {

    private val apiKey: String = BuildConfig.GEMINI_API_KEY
    private val gson = Gson()
    private val client: OkHttpClient = OkHttpClient.Builder()
        .callTimeout(40, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(40, TimeUnit.SECONDS)
        .build()

    data class Part(val text: String?)
    data class Content(val parts: List<Part>?)
    data class Candidate(val content: Content?)
    data class GeminiResponse(val candidates: List<Candidate>?)

    suspend fun generateTripPlan(prompt: String): String {
        if (apiKey.isBlank()) return "API key missing. Set BuildConfig.GEMINI_API_KEY."
        if (apiKey.length < 30) return "API key looks invalid. Please paste a full Gemini key."

        val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=$apiKey"
        val json = gson.toJson(
            mapOf(
                "contents" to listOf(
                    mapOf(
                        "role" to "user",
                        "parts" to listOf(mapOf("text" to prompt))
                    )
                )
            )
        )
        val body = json.toRequestBody("application/json; charset=utf-8".toMediaType())
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        return try {
            client.newCall(request).execute().use { resp: Response ->
                if (!resp.isSuccessful) {
                    val code = resp.code
                    return when (code) {
                        400 -> "Bad request (400)."
                        401 -> "Invalid API key (401 UNAUTHENTICATED). Check your GEMINI_API_KEY."
                        403 -> "Access denied (403). Enable the Generative Language API and billing."
                        429 -> "Rate limited (429). Try again later."
                        500, 502, 503 -> "Gemini service error ($code). Try again."
                        else -> "HTTP error $code."
                    }
                }
                val bodyStr = resp.body?.string() ?: return "Empty response body."
                val parsed = gson.fromJson(bodyStr, GeminiResponse::class.java)
                val text = parsed.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                text ?: "No candidates returned."
            }
        } catch (e: IOException) {
            "Network error. Check your connection. (${e.message})"
        } catch (e: Exception) {
            "AI error: ${e.message ?: "Unknown error"}"
        }
    }
}


