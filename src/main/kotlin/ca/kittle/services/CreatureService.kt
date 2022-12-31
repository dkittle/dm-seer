package ca.kittle.services

import ca.kittle.integrations.DdbProxy
import ca.kittle.models.CoreCreature
import ca.kittle.models.CreatureType
import ca.kittle.models.Environment
import ca.kittle.repositories.CreatureDao
import java.util.regex.Pattern

object CreatureService {
    suspend fun findCreatures(originalSearch: String, accountId: Int): List<CoreCreature> {
        val search = originalSearch.lowercase()
        val m = Pattern.compile("cr\\s*([\\d]+[/]*[\\d]*)").matcher(search)
        val crs = buildList() { while(m.find()) add(m.group().replace(" ", "")) }
        val split = search.split(" ")
        val words = split.filter { !it.startsWith("cr") }.filter{ !it.all { c -> c.isDigit() || c == '/'} } as MutableList<String>
        val environments = words.filter{ Environment.values().map { i -> i.name.lowercase()}.contains(it) }
        val types = words.filter{ CreatureType.values().map { i -> i.name.lowercase()}.contains(it) }
        words.removeAll(environments)
        words.removeAll(types)
        return CreatureDao.findCreatures(words, crs, environments, types, accountId)
    }

    suspend fun getCachedCreatureId(ddbProxy: DdbProxy, vttId: Int, accountId: Int): Int? {
        var id = CreatureDao.getCachedCreatureId(vttId) ?: run {
            ddbProxy.creature(vttId)
                ?.let { it1 -> CreatureDao.cacheCreatureFromDdb(it1, accountId) }
        }

        return id
    }
}
