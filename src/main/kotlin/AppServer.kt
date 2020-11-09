@file:JvmName("AppServer")
import service.ShortLinkService
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val shortLinkService = ShortLinkService()
    val port = System.getenv("PORT")?.toInt() ?: 8080
    embeddedServer(Netty, port) {
        install(ContentNegotiation) {
            register(ContentType.Application.Json, JacksonConverter())
        }
        routing {
            // Redirect to front end if no parameters
            get("/") {
                call.respondRedirect("https://whiplink.xyz/")
            }
            // If path has a value, attempt to validate it and redirect
            get("/{shortLink}") {
                val requestedLink = call.parameters["shortLink"]

                if(requestedLink != null && shortLinkService.shortLinkExists(requestedLink)) {
                    call.respondRedirect(shortLinkService.getDestinationForShortLink(requestedLink).destinationURL)
                } else {
                    call.respondText("No Link Found", ContentType.Text.Html)
                }
            }
            post("/create") {
                var postToCreate = call.receive<ShortLinkRequest>()

                if(postToCreate != null) {
                    call.respond(HttpStatusCode.Created, shortLinkService.createLink(postToCreate.destinationUrl))
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Bad Request")
                }
            }
        }
    }.start(wait = true)
}

data class ShortLinkRequest(val destinationUrl: String)