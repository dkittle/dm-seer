package ca.kittle.models.integrations.encounter

import kotlinx.serialization.Serializable

@Serializable
data class ManualEntry(
    var id: String,
    var order: Int,
    var quantity: Int,
    var armorClass: Int,
    var initiative: Int,
    var name: String,
    var currentHitPoints: Int,
    var temporaryHitPoints: Int,
    var maximumHitPoints: Int
)
