package ca.kittle.models

import kotlinx.serialization.Serializable

@Serializable
data class Creature (
    val id: Long,
    val name: String,
    val species: String,
    val subSpecies: String,
    val size: String,
    val alignment: String,
    val str: Int,
    val dex: Int,
    val con: Int,
    val int: Int,
    val wis: Int,
    val cha: Int,
    val health: Health = Health(),
    val challengeRating: Int,
    val ac: Int,
    val armor: String,
    val hpDice: Dice,
    val speeds: List<Movement> = arrayListOf()
//    val senses: List<Sense> = arrayListOf()
//    val immunities: List<Condition> = arrayListOf(),
//    val resistances: List<DamageTypes> = arrayListOf(),
//    val languages: List<Language> = arrayListOf(),
//    val features: List<Feature> = arrayListOf(),
//    val actions: List<Action> = arrayListOf(),
//    val environments: List<Environment> = arrayListOf(),
//    val sources: List<Source> = arrayListOf()
)
