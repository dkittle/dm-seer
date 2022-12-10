package ca.kittle.models.integrations.character

import kotlinx.serialization.Serializable

@Serializable
data class SpellModifier(
    val fixedValue: Int,
    val id: String?,
    val entityId: Long?,
    val entityTypeId: Long?,
    val type: String?,
    val subType: String?,
    val dice: String?,
    val restriction: String?,
    val statId: Long?,
    val requiresAttunement: Boolean?,
    val duration: Int?,
    val friendlyTypeName: String?,
    val friendlySubtypeName: String?,
    val isGranted: Boolean?,
    val bonusTypes: List<String>?,
    val value: Int?,
    val availableToMulticlass: Boolean?,
    val modifierTypeId: Long?,
    val modifierSubTypeId: Long?,
    val componentId: Long?,
    val componentTypeId: Long?,
    val die: Dice?,
    val count: Int?,
    val durationUnit: String?,
    val usePrimaryStat: Boolean?,
    val atHigherLevels: AtHigherLevels?
    )


