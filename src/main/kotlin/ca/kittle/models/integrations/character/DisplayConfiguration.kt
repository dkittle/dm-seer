package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DisplayConfiguration (

  @SerialName("RACIALTRAIT"  ) var RACIALTRAIT  : Int? = null,
  @SerialName("ABILITYSCORE" ) var ABILITYSCORE : Int? = null,
  @SerialName("LANGUAGE"     ) var LANGUAGE     : Int? = null,
  @SerialName("CLASSFEATURE" ) var CLASSFEATURE : Int? = null

)
