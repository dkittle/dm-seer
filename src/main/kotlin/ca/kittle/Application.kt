package ca.kittle

import ca.kittle.integrations.Database
import ca.kittle.models.UserSession
import ca.kittle.services.IdentityAuth
import ca.kittle.plugins.configureAuthentication
import ca.kittle.plugins.configureRouting
import ca.kittle.plugins.configureSerialization
import ca.kittle.util.EnvUtil
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


//fun main() {
//
//    logger.info { "Starting up" }
//    var port = 5000
//    val env = System.getenv("LOCAL_PORT")
//    if (env != null) {
//        try { port = env.toInt()
//        } catch (ignored: NumberFormatException) { }
//    }
//    logger.info { "Binding to port $port"}
//    DiscordListener.startDiscordListener()
//    embeddedServer(Netty, port = port, host = "0.0.0.0", module = Application::module)
//        .start(wait = true)
//}

//fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val identityAuth = IdentityAuth(EnvUtil.stringFromEnvironment("JWT_SECRET", ""))

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    configureSerialization()
    configureAuthentication(identityAuth)
    configureRouting(identityAuth)
    install(Sessions) {
        val secretEncryptKey = hex("0116283ba45866878cc9aa9b4c0d0eff")
        val secretSignKey = hex("6319b67a626945a1908f48236b89")
        header<UserSession>("User-Session") {
            transform(SessionTransportTransformerEncrypt(secretEncryptKey, secretSignKey))
        }
    }

    Database.init()

}
