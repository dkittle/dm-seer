package ca.kittle.models.integrations.character

import ca.kittle.models.Dice
import kotlinx.serialization.Serializable

@Serializable
data class CreatureDefinition(
    var id: Long?,
    var entityTypeId: Long?,
    var name: String?,
    var alignmentId: Long?,
    var sizeId: Long?,
    var typeId: Long?,
    var armorClass: Int?,
    var armorClassDescription: String?,
    var averageHitPoints: Int?,
    var hitPointDice: Dice?
)
