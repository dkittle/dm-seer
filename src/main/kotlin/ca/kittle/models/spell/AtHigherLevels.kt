package ca.kittle.models.spell

import kotlinx.serialization.Serializable

@Serializable
data class AtHigherLevelsList (

    var higherLevelDefinitions : List<HigherLevelDefinitions> = listOf(),

)
