package ca.kittle.models.integrations.encounter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Monster (

  @SerialName("groupId"            ) var groupId            : String,
  @SerialName("id"                 ) var id                 : Int,
  @SerialName("uniqueId"           ) var uniqueId           : String?,
  @SerialName("name"               ) var name               : String?,
  @SerialName("order"              ) var order              : Int,
  @SerialName("quantity"           ) var quantity           : Int,
  @SerialName("notes"              ) var notes              : String? = null,
  @SerialName("index"              ) var index              : Int?,
  @SerialName("currentHitPoints"   ) var currentHitPoints   : Int,
  @SerialName("temporaryHitPoints" ) var temporaryHitPoints : Int,
  @SerialName("maximumHitPoints"   ) var maximumHitPoints   : Int,
  @SerialName("initiative"         ) var initiative         : Int?,

)

