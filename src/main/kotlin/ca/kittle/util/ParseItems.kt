package ca.kittle.util

import ca.kittle.integrations.Database
import ca.kittle.models.*
import java.io.File
import ca.kittle.repositories.ItemDao
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

suspend fun main() {

    Database.init()

    val JSON = Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    val fileText = File("samples/items.json").readText(Charsets.UTF_8)
    val items = JSON.decodeFromString(fileText) as DdbItems

    val start = System.currentTimeMillis()

    items.items.forEach {
        val item = it.copy(description = it.description.stripHtml().stripNewLine().trim())
        ItemDao.saveItem(item, 1)
        println(it.name)
    }

    val end = System.currentTimeMillis()

    println("Finished in ${end-start}ms")

}
