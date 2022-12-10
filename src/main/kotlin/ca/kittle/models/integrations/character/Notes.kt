package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Notes (

  @SerialName("allies"              ) var allies              : String? = null,
  @SerialName("personalPossessions" ) var personalPossessions : String? = null,
  @SerialName("otherHoldings"       ) var otherHoldings       : String? = null,
  @SerialName("organizations"       ) var organizations       : String? = null,
  @SerialName("enemies"             ) var enemies             : String? = null,
  @SerialName("backstory"           ) var backstory           : String? = null,
  @SerialName("otherNotes"          ) var otherNotes          : String? = null

)
