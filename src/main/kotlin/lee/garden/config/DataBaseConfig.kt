package lee.garden.config

import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.database() {

    val DATABASE_NAME: String = environment.config.property("database.name").getString()
    val DATABASE_USER: String = environment.config.property("database.username").getString()
    val DATABASE_PASSWORD: String = environment.config.property("database.password").getString()

    Database.connect("jdbc:mysql://localhost:3306/$DATABASE_NAME", "org.mariadb.jdbc.Driver", user=DATABASE_USER, password = DATABASE_PASSWORD)

    transaction {
        addLogger(StdOutSqlLogger)
    }

}