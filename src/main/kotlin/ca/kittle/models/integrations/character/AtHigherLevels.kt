package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AtHigherLevels(

    @SerialName("higherLevelDefinitions") var higherLevelDefinitions: List<String>? = null,
    @SerialName("additionalAttacks") var additionalAttacks: List<String>? = null,
    @SerialName("additionalTargets") var additionalTargets: List<String>? = null,
    @SerialName("areaOfEffect") var areaOfEffect: List<String>? = null,
    @SerialName("duration") var duration: List<String>? = null,
    @SerialName("creatures") var creatures: List<String>? = null,
    @SerialName("special") var special: List<String>? = null,
    @SerialName("points") var points: List<String>? = null,
    @SerialName("range") var range: List<String>? = null

)
