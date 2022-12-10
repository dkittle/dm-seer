package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomBackground (

  @SerialName("id"                                    ) var id                                    : Int?    = null,
  @SerialName("entityTypeId"                          ) var entityTypeId                          : Int?    = null,
  @SerialName("name"                                  ) var name                                  : String? = null,
  @SerialName("description"                           ) var description                           : String? = null,
  @SerialName("featuresBackground"                    ) var featuresBackground                    : String? = null,
  @SerialName("characteristicsBackground"             ) var characteristicsBackground             : String? = null,
  @SerialName("featuresBackgroundDefinitionId"        ) var featuresBackgroundDefinitionId        : String? = null,
  @SerialName("characteristicsBackgroundDefinitionId" ) var characteristicsBackgroundDefinitionId : String? = null,
  @SerialName("backgroundType"                        ) var backgroundType                        : String? = null

)
