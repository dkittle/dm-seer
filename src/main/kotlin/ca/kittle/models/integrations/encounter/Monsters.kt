package ca.kittle.models.integrations.encounter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Monsters (

  @SerialName("groupId"            ) var groupId            : String? = null,
  @SerialName("id"                 ) var id                 : Int?    = null,
  @SerialName("uniqueId"           ) var uniqueId           : String? = null,
  @SerialName("name"               ) var name               : String? = null,
  @SerialName("order"              ) var order              : Int?    = null,
  @SerialName("quantity"           ) var quantity           : Int?    = null,
  @SerialName("notes"              ) var notes              : String? = null,
  @SerialName("index"              ) var index              : Int?    = null,
  @SerialName("currentHitPoints"   ) var currentHitPoints   : Int?    = null,
  @SerialName("temporaryHitPoints" ) var temporaryHitPoints : Int?    = null,
  @SerialName("maximumHitPoints"   ) var maximumHitPoints   : Int?    = null,
  @SerialName("initiative"         ) var initiative         : Int?    = null

)

