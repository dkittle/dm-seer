package ca.kittle.integrations.spells

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DdbSpells (

  @SerialName("id"         ) var id         : Int?            = null,
  @SerialName("success"    ) var success    : Boolean?        = null,
  @SerialName("message"    ) var message    : String?         = null,
  @SerialName("data"       ) var spells       : ArrayList<Spell> = arrayListOf(),
  @SerialName("pagination" ) var pagination : String?         = null

)
