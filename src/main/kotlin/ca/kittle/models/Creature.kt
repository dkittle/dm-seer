package ca.kittle.models

import kotlinx.serialization.Serializable

//@Serializable
//data class Creature (
//    val id: Long,
//    val species: String,
//    val subSpecies: List<String>,
//    val size: String,
//    val alignment: String,
//    val str: Int,
//    val dex: Int,
//    val con: Int,
//    val int: Int,
//    val wis: Int,
//    val cha: Int,
//    val health: Health = Health(),
//    val challengeRating: String,
//    val ac: Int,
//    val armor: String,
//    val hpDice: Dice,
//    val speeds: List<String> = arrayListOf(),
//    val senses: List<String> = arrayListOf(),
//    val savingThrows: List<String> = arrayListOf(),
//    val skills: List<Skill> = arrayListOf(),
//    val languages: List<String>,
//    val creatureType: String,
//    val passivePerception: Int,
//    val isHomebrew: Boolean,
//    val sources: List<CreatureReference>,
//    val isLegacy: Boolean,
//    val isLegendary: Boolean,
//    val isMythic: Boolean,
//    val hasLair: Boolean,
//    val avatarUrl: List<String>,
//    val environments: List<String> = arrayListOf(),
//    val immunities: List<DamageType> = arrayListOf(),
//    val resistances: List<DamageType> = arrayListOf(),
//    val vulnerabilities: List<DamageType> = arrayListOf(),
//    val conditionImmunities: List<Condition> = arrayListOf(),
//    val traits: List<BasicTrait> = arrayListOf(),
//    val actions: List<BasicAction> = arrayListOf(),
//    val bonusActions: List<BasicAction> = arrayListOf(),
//    val reactions: List<BasicAction> = arrayListOf(),
//    val mythicActions: List<BasicAction> = arrayListOf(),
//    val legendaryActions: List<BasicAction> = arrayListOf(),
//    val mythicDescription: String,
//    val legendaryDescription: String
//) {
//    companion object {
//        fun proficiencyBonus(cr: String): Int {
//            val id = ChallengeRating.getChallengeRatingByLabel(cr)?.id ?: 0
//            return when (id) {
//                in 1..8 -> 2
//                in 9..12 -> 3
//                in 13..16 -> 4
//                in 17..20 -> 5
//                in 21..24 -> 6
//                in 25..28 -> 7
//                in 29..32 -> 8
//                in 33..34 -> 9
//                else -> 0
//            }
//        }
//        fun experiencePoints(cr: String): Int {
//            val id = ChallengeRating.getChallengeRatingByLabel(cr)?.id ?: 0
//            return when (id) {
//                1 -> 10
//                2 -> 25
//                3 -> 50
//                4 -> 10
//                5 -> 200
//                6 -> 450
//                7 -> 700
//                8 -> 1100
//                9 -> 1800
//                10 -> 2300
//                11 -> 2900
//                12 -> 3900
//                13 -> 5000
//                14 -> 5900
//                15 -> 7200
//                16 -> 8400
//                17 -> 1000
//                18 -> 11500
//                19 -> 13000
//                20 -> 15000
//                21 -> 18000
//                22 -> 20000
//                23 -> 22000
//                24 -> 25000
//                25 -> 33000
//                26 -> 41000
//                27 -> 50000
//                28 -> 62000
//                29 -> 75000
//                30 -> 90000
//                31 -> 105000
//                32 -> 120000
//                33 -> 135000
//                34 -> 155000
//                else -> 0
//            }
//        }
//    }
//}
