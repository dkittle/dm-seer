package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrerequisiteMappings (

  @SerialName("id"                  ) var id                  : Int?    = null,
  @SerialName("entityId"            ) var entityId            : Int?    = null,
  @SerialName("entityTypeId"        ) var entityTypeId        : Int?    = null,
  @SerialName("type"                ) var type                : String? = null,
  @SerialName("subType"             ) var subType             : String? = null,
  @SerialName("value"               ) var value               : Int?    = null,
  @SerialName("friendlyTypeName"    ) var friendlyTypeName    : String? = null,
  @SerialName("friendlySubTypeName" ) var friendlySubTypeName : String? = null

)
