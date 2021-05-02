package lee.garden.config

import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import com.zaxxer.hikari.HikariDataSource
import com.zaxxer.hikari.HikariConfig

fun Application.databaseInit() {

    val DATABASE_NAME: String = environment.config.property("database.name").getString()
    val DATABASE_USER: String = environment.config.property("database.username").getString()
    val DATABASE_PASSWORD: String = environment.config.property("database.password").getString()

    Database.connect(HikariDataSource(hikariConfig(DATABASE_NAME, DATABASE_USER, DATABASE_PASSWORD)))

    transaction {
        addLogger(StdOutSqlLogger)
    }

}

private fun hikariConfig(dbName: String, dbUser: String, dbPw: String) =
    HikariConfig().apply {
        driverClassName = "org.mariadb.jdbc.Driver"
        jdbcUrl = "jdbc:mysql://localhost:3306/${dbName}"
        maximumPoolSize = 30
        isAutoCommit = false
        username = dbUser
        password = dbPw
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    }