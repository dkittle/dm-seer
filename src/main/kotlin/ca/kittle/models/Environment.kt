package ca.kittle.models

import kotlinx.serialization.Serializable

@Serializable
data class Environment(
    val id: Long,
    val name: String
)

enum class Environments(val id: Int) {
    ARCTIC(1),
    COSTAL(2),
    DESERT(3),
    FOREST(4),
    GRASSLAND(5),
    HILL(6),
    MOUNTAIN(7),
    SWAMP(8),
    UNDERDARK(9),
    UNDERWATER(10),
    URBAN(11),
}
