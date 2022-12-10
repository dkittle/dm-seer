package ca.kittle.models.integrations.tersecharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attack (

  @SerialName("name"     ) var name     : String? = null,
  @SerialName("range"    ) var range    : Range?  = Range(),
//  @SerialName("damage"   ) var damage   : Damage? = Damage(),
  @SerialName("saveInfo" ) var saveInfo : String? = null,
  @SerialName("toHit"    ) var toHit    : Int?    = null

)
