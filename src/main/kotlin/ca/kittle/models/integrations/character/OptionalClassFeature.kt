package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OptionalClassFeature (

  @SerialName("classFeatureId"                    ) var classFeatureId                    : Int?    = null,
  @SerialName("affectedClassFeatureId"            ) var affectedClassFeatureId            : String? = null,
  @SerialName("classFeatureDefinitionKey"         ) var classFeatureDefinitionKey         : String? = null,
  @SerialName("affectedClassFeatureDefinitionKey" ) var affectedClassFeatureDefinitionKey : String? = null

)
