package ca.kittle.models.integrations.encounter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Player (

  @SerialName("id"                 ) var id                 : String,
  @SerialName("count"              ) var count              : Int?     = null,
  @SerialName("level"              ) var level              : Int,
  @SerialName("type"               ) var type               : String?  = null,
  @SerialName("hidden"             ) var hidden             : Boolean? = null,
  @SerialName("race"               ) var species               : String,
  @SerialName("gender"             ) var gender             : String?  = null,
  @SerialName("name"               ) var name               : String,
  @SerialName("userName"           ) var userName           : String,
  @SerialName("isReady"            ) var isReady            : Boolean? = null,
  @SerialName("avatarUrl"          ) var avatarUrl          : String?  = null,
  @SerialName("classByLine"        ) var classByLine        : String,
  @SerialName("initiative"         ) var initiative         : Int?     = null,
  @SerialName("currentHitPoints"   ) var currentHitPoints   : Int,
  @SerialName("temporaryHitPoints" ) var temporaryHitPoints : Int?     = null,
  @SerialName("maximumHitPoints"   ) var maximumHitPoints   : Int?     = null

)
