package ca.kittle.routes.support

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterIds(
    @SerialName("characterIds") var characterids: List<Long>
)
