package ca.kittle.models.integrations.tersecharacter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProficiencyGroup (

  @SerialName("group"  ) var group  : String? = null,
  @SerialName("values" ) var values : String? = null

)
