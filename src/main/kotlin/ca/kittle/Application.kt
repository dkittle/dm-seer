package ca.kittle

import ca.kittle.plugins.configureRouting
import ca.kittle.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main() {

    logger.info { "Starting up" }
    var port = 5000
    val env = System.getenv("LOCAL_PORT")
    if (env != null) {
        try { port = env.toInt()
        } catch (ignored: NumberFormatException) { }
    }
    DiscordListener.startDiscordListener()
    embeddedServer(Netty, port = port, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

//fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureRouting()
}
