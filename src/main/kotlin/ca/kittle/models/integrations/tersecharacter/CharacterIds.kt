package ca.kittle.models.integrations.tersecharacter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterIds(
    @SerialName("characterIds") var characterids: List<Long>
)
