package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Currencies (

  @SerialName("cp" ) var cp : Int? = null,
  @SerialName("sp" ) var sp : Int? = null,
  @SerialName("gp" ) var gp : Int? = null,
  @SerialName("ep" ) var ep : Int? = null,
  @SerialName("pp" ) var pp : Int? = null

)
