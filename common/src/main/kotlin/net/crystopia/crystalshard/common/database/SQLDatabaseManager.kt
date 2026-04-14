package net.crystopia.crystalshard.common.database

import org.ktorm.database.Database
import org.ktorm.database.SqlDialect

/**
 *
 * Small Database Manager to create Database connections with the use of ktorm.
 *
 */
object SQLDatabaseManager {

    private var url: String = "jdbc:postgresql://localhost:5432/postgres"
    private var username: String? = null
    private var password: String? = null
    var driverClassName: String? = "org.postgresql.Driver"
    var dialect: SqlDialect? = null

    fun init(
        url: String = "jdbc:postgresql://localhost:5432/postgres",
        username: String,
        password: String,
        db: SQLDatabaseManager.() -> Unit
    ): SQLDatabaseManager {
        this.url = url
        this.username = username
        this.password = password
        db(this)
        return this
    }

    val database = Database.connect(
        url = url,
        driver = driverClassName,
        user = username,
        password = password,
        dialect = dialect!!
    )

    /**
     * Setup Method to execute SQL Queries
     */
    fun command(command: String): SQLDatabaseManager {
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