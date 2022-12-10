package ca.kittle.models.integrations.tersecharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Skill (

  @SerialName("isExpert"         ) var isExpert         : Boolean? = null,
  @SerialName("isHalfProficient" ) var isHalfProficient : Boolean? = null,
  @SerialName("modifier"         ) var modifier         : Int?     = null,
  @SerialName("name"             ) var name             : String?  = null,
  @SerialName("isProficient"     ) var isProficient     : Boolean? = null,
  @SerialName("isAdvantage"      ) var isAdvantage      : Boolean? = null,
  @SerialName("isDisadvantage"   ) var isDisadvantage   : Boolean? = null

)
