package lee.garden

import io.ktor.application.*
import lee.garden.config.database
import lee.garden.plugins.*
import lee.garden.router.baseRouter

/**
 * conf 파일의 변수를 읽어들이기 위해서 EngineMain으로 서버를 기동 한다.
 * ref: https://ktor.io/docs/configurations.html#hocon-overview
 * */
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    baseRouter()
    database()
    configureSerialization()
    configureMonitoring()
}