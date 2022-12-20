package ca.kittle.models.integrations.creature

import kotlinx.serialization.Serializable

@Serializable
data class Swarm (

    val name : String,
    val sizeId : Long,
    val typeId : Long
)
