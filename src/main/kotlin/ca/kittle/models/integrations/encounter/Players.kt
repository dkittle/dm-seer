package ca.kittle.models.integrations.encounter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Players (

  @SerialName("id"                 ) var id                 : String?  = null,
  @SerialName("count"              ) var count              : Int?     = null,
  @SerialName("level"              ) var level              : Int?     = null,
  @SerialName("type"               ) var type               : String?  = null,
  @SerialName("hidden"             ) var hidden             : Boolean? = null,
  @SerialName("race"               ) var species               : String?  = null,
  @SerialName("gender"             ) var gender             : String?  = null,
  @SerialName("name"               ) var name               : String?  = null,
  @SerialName("userName"           ) var userName           : String?  = null,
  @SerialName("isReady"            ) var isReady            : Boolean? = null,
  @SerialName("avatarUrl"          ) var avatarUrl          : String?  = null,
  @SerialName("classByLine"        ) var classByLine        : String?  = null,
  @SerialName("initiative"         ) var initiative         : Int?     = null,
  @SerialName("currentHitPoints"   ) var currentHitPoints   : Int?     = null,
  @SerialName("temporaryHitPoints" ) var temporaryHitPoints : Int?     = null,
  @SerialName("maximumHitPoints"   ) var maximumHitPoints   : Int?     = null

)
