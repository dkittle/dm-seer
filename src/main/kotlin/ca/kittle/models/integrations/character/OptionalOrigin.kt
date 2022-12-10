package ca.kittle.models.integrations.character

import kotlinx.serialization.Serializable

@Serializable
data class OptionalOrigin(
    var racialTraitId: Long?,
    var affectedRacialTraitId: Long?,
    var racialTraitDefinitionKey: String?,
    var affectedRacialTraitDefinitionKey: String?
)
