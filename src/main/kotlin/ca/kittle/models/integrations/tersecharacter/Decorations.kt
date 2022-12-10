package ca.kittle.models.integrations.tersecharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Decorations (

  @SerialName("backdrop"       ) var backdrop       : Backdrop?       = Backdrop(),
  @SerialName("characterTheme" ) var characterTheme : CharacterTheme? = CharacterTheme(),
  @SerialName("avatar"         ) var avatar         : Avatar?         = Avatar()

)
