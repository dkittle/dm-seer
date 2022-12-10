package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpellSlots (

  @SerialName("level"     ) var level     : Int? = null,
  @SerialName("used"      ) var used      : Int? = null,
  @SerialName("available" ) var available : Int? = null

)
