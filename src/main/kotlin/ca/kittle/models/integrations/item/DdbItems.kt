package ca.kittle.integrations.spells

import ca.kittle.models.integrations.item.Item
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DdbItems (

  @SerialName("id"         ) var id         : Int?            = null,
  @SerialName("success"    ) var success    : Boolean?        = null,
  @SerialName("message"    ) var message    : String?         = null,
  @SerialName("data"       ) var items       : ArrayList<Item> = arrayListOf(),
  @SerialName("pagination" ) var pagination : String?         = null

)
