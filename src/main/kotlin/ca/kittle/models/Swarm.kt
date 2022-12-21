package ca.kittle.models

import kotlinx.serialization.Serializable

@Serializable
data class Swarm (

    val name : String,
    val sizeId : Int,
    val typeId : Int
)
