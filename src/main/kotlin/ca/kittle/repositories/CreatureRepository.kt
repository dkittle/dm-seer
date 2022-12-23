package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.models.Creature
import mu.KotlinLogging

class CreatureRepository {


//    fun createCreature(c: Creature, origin: String, originId: Int): Int? {
//        var exists = 0
//        db.connect().use { conn ->
//            conn.prepareStatement(CREATURE_ORIGIN).use { stmt ->
//                stmt.setString(1, origin)
//                stmt.setInt(2, originId)
//                stmt.executeQuery().use { rs ->
//                    if (rs.next())
//                      exists = rs.getInt("creature_id")
//                }
//            }
//        }
//        if (exists != 0) {
//            logger.debug { "Creature already imported from $origin $originId" }
//            return exists
//        }
//        logger.debug { "Storing a creature" }
//        var newId = 0
//        db.connect().use { conn ->
//            var returnId = arrayOf("id")
//            conn.prepareStatement(NEW_CREATURE_SQL, returnId).use { stmt ->
//                stmt.setString(1, c.species)
//                stmt.setString(2, c.subSpecies.toString())
//                stmt.setString(3, c.size)
//                stmt.setString(4, c.alignment)
//                stmt.setInt(5, c.str)
//                stmt.setInt(6, c.dex)
//                stmt.setInt(7, c.con)
//                stmt.setInt(8, c.int)
//                stmt.setInt(9, c.wis)
//                stmt.setInt(10, c.cha)
//                stmt.setInt(11, c.averageHitPoints)
//                stmt.setString(12, c.hpDice.diceString)
//                stmt.setString(13, c.challengeRating)
//                stmt.setString(14, c.swarm?.name)
//                stmt.setInt(15, c.swarm?.sizeId ?: 0)
//                stmt.setInt(16, c.ac)
//                stmt.setString(17, c.armor)
//                stmt.setString(18, c.speeds.toString())
//                stmt.setString(19, c.senses.toString())
//                stmt.setString(20, c.savingThrows.toString())
//                stmt.setString(21, c.skills.toString())
//                stmt.setString(22, c.languages.toString())
//                stmt.setString(23, c.creatureType)
//                stmt.setInt(24, c.passivePerception)
//                stmt.setString(25, c.isHomebrew.toString())
//                stmt.setString(26, c.isLegacy.toString())
//                stmt.setString(27, c.isLegendary.toString())
//                stmt.setString(28, c.isMythic.toString())
//                stmt.setString(29, c.hasLair.toString())
//                val rows = stmt.executeUpdate()
//                val rs = stmt.generatedKeys
//                if (rs.next())
//                    newId = rs.getInt(1)
//                if (rows != 1)
//                    logger.warn { "Creature ${c.species} could not be saved" }
//                else
//                    logger.debug { "Create ${c.species} saved as new id $newId." }
//            }
//        }
//        db.connect().use { conn ->
//            conn.prepareStatement(NEW_CREATURE_ORIGIN).use { stmt ->
//                stmt.setInt(1, newId)
//                stmt.setString(2, origin)
//                stmt.setInt(3, originId)
//                val rows = stmt.executeUpdate()
//                if (rows != 1)
//                    logger.warn { "Creature origin $newId/$originId could not be saved" }
//            }
//        }
//        db.connect().use { conn ->
//            for (s in c.sources) {
//                conn.prepareStatement(NEW_CREATURE_SOURCES).use { stmt ->
//                    stmt.setInt(1, newId)
//                    stmt.setInt(2, s.sourceId)
//                    stmt.setInt(3, s.pageNumber)
//                    val rows = stmt.executeUpdate()
//                    if (rows != 1)
//                        logger.warn { "Creature source ${s.sourceId} could not be saved" }
//                }
//            }
//        }
//        db.connect().use { conn ->
//            for (url in c.avatarUrl) {
//                conn.prepareStatement(NEW_CREATURE_AVATARS).use { stmt ->
//                    stmt.setInt(1, newId)
//                    stmt.setString(2, url)
//                    val rows = stmt.executeUpdate()
//                    if (rows != 1)
//                        logger.warn { "Creature avatar $url could not be saved" }
//                }
//            }
//        }
//        return newId
//    }
//
//
//
//    companion object {
//        private val logger = KotlinLogging.logger {}
//
//        val CREATURE_ORIGIN = "SELECT creature_id FROM creature_origins WHERE origin=? AND origin_id=?"
//
////        val NEW_CREATURE_SQL = "INSERT INTO creatures (species, subSpecies, size, alignment, str, dex, con, int, wis, cha, averageHitPoints, hpDice, challengeRating, swarm, ac, armor, speeds, senses, savingThrows, skills, languages, creatureType, passivePerception, isHomebrew, sources, isLegacy, isLegendary, isMythic, hasLair, avatarUrl, environments, immunities, resistances, vulnerabilities, conditionImmunities, traits, actions, bonusActions, reactions, mythicActions, legendaryActions, mythicDescription, legendaryDescription, tags)".trimIndent()
//        val NEW_CREATURE_SQL = ("INSERT INTO creatures (species, subSpecies, size, alignment, strength, dexterity, constitution, intelligence, wisdom, charisma, averageHitPoints, hitDiceString, challenge_rating, swarmName, swarmSize, armorClass, armor, speeds, senses, savingThrows, skills, languages, creatureType, passivePerception, isHomebrew, isLegacy, isLegendary, isMythic, hasLair)" +
//        " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)").trimIndent()
//
//        val NEW_CREATURE_ORIGIN = "INSERT INTO creature_origins (creature_id, origin, origin_id) VALUES (?, ?, ?)"
//
//        val NEW_CREATURE_SOURCES = "INSERT INTO creature_sources (creature_id, source_id, page_number) VALUES (?, ?, ?)"
//
//        val NEW_CREATURE_AVATARS = "INSERT INTO creature_avatars (creature_id, url) VALUES (?, ?)"
//
//    }
}
