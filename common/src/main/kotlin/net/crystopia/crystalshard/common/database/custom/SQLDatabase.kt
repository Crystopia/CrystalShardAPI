package net.crystopia.crystalshard.common.database.custom

import org.ktorm.database.Database
import org.ktorm.database.SqlDialect
import org.ktorm.support.postgresql.PostgreSqlDialect
import java.sql.SQLException

/**
 * Build a Mysql Database instance to connect to a MySql/MariaDB/Postgres Database.
 */
fun database(url: String, username: String, password: String, database: SQLDatabase.() -> Unit): SQLDatabase {
    val db = SQLDatabase(url, username, password)
    database(db)
    return db
}

class SQLDatabase(
    url: String = "jdbc:postgresql://localhost:5432/public",
    username: String? = null,
    password: String? = null,
    driverClassName: String? = "org.postgresql.Driver",
    dialect: SqlDialect? = PostgreSqlDialect()
) {

    var database = Database.connect(
        url = url,
        driver = driverClassName,
        user = username,
        password = password,
        dialect = dialect!!
    )

    fun connect(): SQLDatabase {
        try {
            database.useConnection { _ -> }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return this
    }

    /**
     * Setup Method to execute SQL Queries
     */
    fun command(command: String): SQLDatabase {
        try {
            database.useConnection { conn ->
                conn.createStatement().use { statement ->
                    statement.executeUpdate(command)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return this
    }
}