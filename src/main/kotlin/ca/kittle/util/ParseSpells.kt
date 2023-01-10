package ca.kittle.util

import ca.kittle.integrations.Database
import ca.kittle.models.*
import java.io.File
import ca.kittle.models.spell.DdbSpells
import ca.kittle.repositories.ItemDao
import ca.kittle.repositories.SpellDao
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

suspend fun main() {

    Database.init()

    val JSON = Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    val fileText = File("samples/spells.json").readText(Charsets.UTF_8)
    val spells = JSON.decodeFromString(fileText) as DdbSpells

    val start = System.currentTimeMillis()

    spells.spells.forEach {
        it.definition.description = it.definition.description?.stripHtml()?.stripNewLine()?.trim()
        SpellDao.saveSpell(it.definition, 1)
        println(it.definition.name)
    }

    val end = System.currentTimeMillis()

    println("Finished in ${end-start}ms")

}
