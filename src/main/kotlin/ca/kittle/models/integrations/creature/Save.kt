package ca.kittle.models.integrations.creature

import kotlinx.serialization.Serializable

@Serializable
data class Save (

    val statId : Int,
    val bonusModifier : Int?
)
