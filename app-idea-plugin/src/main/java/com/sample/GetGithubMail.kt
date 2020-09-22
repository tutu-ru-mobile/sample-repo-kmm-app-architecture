package com.sample

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

/**
 * Тут страшный код, написанный на скорую руку.
 * Смысл такой:
 * Открываем браузер на Github авторизации,
 * ждём когда на localhost прилетит авторизационный код,
 * получаем почту через GitHub Api, и закрываем браузер.
 */
@OptIn(EngineAPI::class)
fun getGithubMail(
    port: Int = 55555,
    callback: (mail: String) -> Unit
) {
    var mutableCallbackPipe: (mail: String) -> Unit = {
        callback(it)
    }
    var server: BaseApplicationEngine? = null
    server = GlobalScope.embeddedServer(if (true) Netty else CIO, port, configure = {
        //конфигурация может быть специфичная для Netty или CIO
        connectionGroupSize
        workerGroupSize
        callGroupSize
        //requestQueueLimit
        //runningLimit
        //shareWorkGroup
    }) {
        routing {
            get("/") {
                val githubAuthCode = context.parameters["code"]
                if (githubAuthCode != null) {
                    println("githubAuthCode: $githubAuthCode")
                    val client: HttpClient = HttpClient(Apache)
                    val tokenResponse: String = client.post("https://github.com/login/oauth/access_token") {
                        body = TextContent(
                            """
                                  {
                                    "client_id": "${BuildConfig.GITHUB_CLIENT_ID}",
                                    "client_secret": "${BuildConfig.GITHUB_CLIENT_SECRET}",
                                    "code": "$githubAuthCode"
                                  }              
                                """.trimIndent(),
                            ContentType.Application.Json
                        )
                    }
                    val resultParams: Map<String, String> = tokenResponse.split("&").associate {
                        val split = it.split("=")
                        val key = split[0]
                        val value = split[1]
                        key to value
                    }

                    val token = resultParams.get("access_token")
                    if (token != null) {
                        val mail = client.getGithubMail(token)
                        mutableCallbackPipe(mail)
                    }
                }
            }
        }
    }
    server.start(wait = false)
    MainScope().launch {
        val authHref = authHref("user:email")
        val closable = openBrowserJBCeffOrDefault(authHref)
        val oldCallback = mutableCallbackPipe
        mutableCallbackPipe = {
            oldCallback(it)
            closable.close()
        }
    }
}

suspend fun HttpClient.getGithubMail(token: String): String {
    val json = request<String>(
        url = Url("https://api.github.com/user/emails")
    ) {
        method = HttpMethod.Get
        header("Authorization", "bearer $token")
        header("Accept", "*/*")
//        contentType(/**/)
    }
    val jsonToGithubMails = json.jsonToGithubMails()
    val mail: String = jsonToGithubMails.firstOrNull()?.email ?: "no visible mails"
    return mail
}

fun String.jsonToGithubMails(): List<GitHubMail> =
    Json.decodeFromString(ListSerializer(GitHubMail.serializer()), this)

@Serializable
class GitHubMail(
    val email: String?,
    val primary: Boolean,
    val verified: Boolean,
    val visibility: String
)

/**
 * https://developer.github.com/apps/building-oauth-apps/understanding-scopes-for-oauth-apps/
 */
private fun authHref(tokenScope: String): String =
    Url("https://github.com/login/oauth/authorize/")
        .copy(
            parameters = parametersOf(
                "client_id" to listOf(BuildConfig.GITHUB_CLIENT_ID),
                "scope" to listOf(tokenScope)
            )
        ).toString()
