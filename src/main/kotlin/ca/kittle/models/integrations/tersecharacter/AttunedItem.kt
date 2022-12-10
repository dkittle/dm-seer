package ca.kittle.models.integrations.tersecharacter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AttunedItem (

  @SerialName("name"          ) var name          : String? = null,
  @SerialName("definitionKey" ) var definitionKey : String? = null,
  @SerialName("type"          ) var type          : String? = null,
  @SerialName("avatarUrl"     ) var avatarUrl     : String? = null

)
