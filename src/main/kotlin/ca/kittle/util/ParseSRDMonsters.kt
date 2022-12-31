package ca.kittle.util

import ca.kittle.integrations.Database
import ca.kittle.integrations.DdbProxy
import ca.kittle.models.*
import com.nfeld.jsonpathkt.JsonPath
import java.io.File
import ca.kittle.models.integrations.creature.Creature
import ca.kittle.models.integrations.creature.Stat
import ca.kittle.repositories.CreatureDao
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

suspend fun main () {

    Database.init()

    val JSON = Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    val fileText = File("samples/monsters/basic-monsters.json").readText(Charsets.UTF_8)
    val creatures = JSON.decodeFromString(fileText) as List<Creature>

    println(creatures.size)
    val start = System.currentTimeMillis()

    val ddbProxy = DdbProxy(107326383, "")
    creatures.forEach {
        CreatureDao.cacheCreatureFromDdb(it, 1)
    }

    val end = System.currentTimeMillis()

    println("Finished in ${end-start}ms")

//    val path = JsonPath("$")
//    var result = path.readFromJson<List<Map<String, Any>>>(fileText)
//    println("Results ${result?.size}")
//    result?.forEach {
//        println("movements ${it["movements"]}")
//    }
//    result?.forEach {
//        Creature(0, true,
//            it["url"].toString(),
//            "",
//            "",
//            "",
//            parseStats(it["stats"] ?: listOf<Any>())
//                    it["name"].toString(),
//            parseIntArray(it["subTypes"].toString()).map {
//                CreatureSubTypes.getCreatureSubTypeById(it)?.label ?: ""
//            },
//            Sizes.getSizeById(it["sizeId"].toString().toInt()).label,
//            Alignments.getAlignmentById(it["alignmentId"].toString().toInt())?.label ?: ""
//        )
//    }
}

//fun parseIntArray(input: String): List<Int> {
//    val elements = input.split(",")
//    return elements.map {
//        it.replace("[", "")
//        .replace("]", "")
//        .trim() }.map { it.toInt() }
//}
//
//fun parseStats(input: Any): List<Stat> {
//
//}
