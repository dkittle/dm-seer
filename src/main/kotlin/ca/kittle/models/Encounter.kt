package ca.kittle.models

import kotlinx.serialization.Serializable

@Serializable
data class Encounter(
    var id: Long,
    var name: String,
    var dmName: String,
    var campaignName: String,
    var locationId: Long,
    var location: String,
    var source: String,
    var suggestedAcl: Int
)
