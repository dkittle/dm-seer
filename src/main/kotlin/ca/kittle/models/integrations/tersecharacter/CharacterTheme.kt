package ca.kittle.models.integrations.tersecharacter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterTheme (

  @SerialName("name"            ) var name            : String?  = null,
  @SerialName("isDarkMode"      ) var isDarkMode      : Boolean? = null,
  @SerialName("isDefault"       ) var isDefault       : Boolean? = null,
  @SerialName("backgroundColor" ) var backgroundColor : String?  = null,
  @SerialName("themeColor"      ) var themeColor      : String?  = null

)
