package ca.kittle.models.integrations.character

import kotlinx.serialization.Serializable

@Serializable
data class Creature(
    var id: Long?,
    var entityTypeId: Long?,
    var name: String?,
    var description: String?,
    var isActive: Boolean?,
    var removedHitPoints: Int?,
    var temporaryHitPoints: Int?,
    var groupId: Long?,
    var definition: CreatureDefinition?,
//    var movements: List<Movement>? = null,
    var passivePerception: Int?,
    var isHomebrew: Boolean?,
    var challengeRatingId: Long?,
    var sourceId: Long?,
    var sourcePageNumber: Int?,
    var isLegendary: Boolean?,
    var isMythic: Boolean?,
    var hasLair: Boolean?,
    var avatarUrl: String?,
    var largeAvatarUrl: String?,
    var basicAvatarUrl: String?,
    var version: String?,
    var swarm: String?,
    var subTypes: List<String>? = null,
    var environments: List<Long>? = null,
    var tags: List<String>? = null,
    var sources: List<Source>? = null,
    var stats: List<Stat>? = null,
    var damageAdjustments: List<String>? = null,
    var conditionImmunities: List<String>? = null,
    var savingThrows: List<String>? = null,
    var skills: List<KnownSkill>? = null,
    var languages: List<String>? = null,
    var specialTraitsDescription: String?,
    var actionsDescription: String?,
    var reactionsDescription: String?,
    var legendaryActionsDescription: String?,
    var mythicActionsDescription: String?,
    var bonusActionsDescription: String?,
    var characteristicsDescription: String?,
    var lairDescription: String?,
    var languageDescription: String?,
    var languageNote: String?,
    var hideCr: Boolean?,
    var isLegacy: Boolean?
)


