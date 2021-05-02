package lee.garden

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.jackson.*
import com.fasterxml.jackson.databind.*
import io.ktor.features.*
import org.slf4j.event.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import kotlin.test.*
import io.ktor.server.testing.*
import lee.garden.router.baseRouter

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ baseRouter() }) {
            handleRequest(HttpMethod.Get, "/board").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("this is board api", response.content)
            }
        }
    }
}