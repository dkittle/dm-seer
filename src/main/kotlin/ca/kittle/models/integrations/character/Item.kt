package ca.kittle.models.integrations.character

import ca.kittle.models.Dice
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val fixedValue: Int?,
    val id: String?,
    val entityId: String?,
    val entityTypeId: String?,
    val type: String?,
    val subType: String?,
    val dice: Dice?,
    val restriction: String?,
    val statId: String?,
    val requiresAttunement: Boolean?,
    val duration: Duration?,
    val friendlyTypeName: String?,
    val friendlySubtypeName: String?,
    val isGranted: Boolean?,
    val bonusTypes: List<String>?,
    val availableToMulticlass: Boolean?,
    val modifierTypeId: Long?,
    val modifierSubTypeId: Long?,
    val componentId: Long?,
    val componentTypeId: Long?
)

