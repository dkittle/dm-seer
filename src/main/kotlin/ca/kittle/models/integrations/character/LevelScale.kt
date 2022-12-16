package ca.kittle.models.integrations.character

import ca.kittle.models.Dice
import kotlinx.serialization.Serializable

@Serializable
data class LevelScale(
    var id : Long,
    var description : String?,
    var dice: Dice?,
    var fixedValue: Int?
)
