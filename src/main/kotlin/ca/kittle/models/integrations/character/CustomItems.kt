package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomItems (

  @SerialName("id"          ) var id          : Int?    = null,
  @SerialName("name"        ) var name        : String? = null,
  @SerialName("description" ) var description : String? = null,
  @SerialName("weight"      ) var weight      : String? = null,
  @SerialName("cost"        ) var cost        : String? = null,
  @SerialName("quantity"    ) var quantity    : Int?    = null,
  @SerialName("notes"       ) var notes       : String? = null

)
