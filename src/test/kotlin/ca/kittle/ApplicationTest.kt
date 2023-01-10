package ca.kittle


import ca.kittle.plugins.configureRouting
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
//    @Test
//    fun testRoot() = testApplication {
//        application {
//            configureRouting()
//        }
//        client.get("/").apply {
//            assertEquals(HttpStatusCode.NotFound, status)
//            assertEquals("", bodyAsText())
//        }
//        client.get("/api/health") {
//            header(HttpHeaders.Accept, ContentType.Application.Json)
//        }.apply {
//            assertEquals(HttpStatusCode.OK, status)
//            assertEquals(OK.toString(), bodyAsText())
//        }
//    }
}
