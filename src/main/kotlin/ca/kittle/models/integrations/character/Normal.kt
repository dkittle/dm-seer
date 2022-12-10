package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Normal (

  @SerialName("walk"   ) var walk   : Int? = null,
  @SerialName("fly"    ) var fly    : Int? = null,
  @SerialName("burrow" ) var burrow : Int? = null,
  @SerialName("swim"   ) var swim   : Int? = null,
  @SerialName("climb"  ) var climb  : Int? = null

)
