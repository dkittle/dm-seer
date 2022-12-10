package ca.kittle.models.integrations.character

import kotlinx.serialization.Serializable

@Serializable
data class SpellRange(
    val origin: String?,
    val rangeValue: Int?,
    val aoeType: String?,
    val aoeValue: Int?
)
