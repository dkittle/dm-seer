package ca.kittle.models.integrations.encounter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DdbEncounters (

//  @SerialName("editable"   ) var editable   : List<Editable>?   = null,
  @SerialName("config"     ) var config     : Config?         = null,
  @SerialName("pagination" ) var pagination : Pagination?     = null,
  @SerialName("stats"      ) var stats      : Stats?          = null,
  @SerialName("metaData"   ) var metaData   : MetaData?       = null,
  @SerialName("data"       ) var encounters       : List<Encounter> = arrayListOf()

)
