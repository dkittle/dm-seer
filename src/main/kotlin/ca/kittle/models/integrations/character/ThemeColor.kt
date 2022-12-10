package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ThemeColor (

  @SerialName("themeColorId"    ) var themeColorId    : Int?              = null,
  @SerialName("themeColor"      ) var themeColor      : String?           = null,
  @SerialName("backgroundColor" ) var backgroundColor : String?           = null,
  @SerialName("name"            ) var name            : String?           = null,
  @SerialName("raceId"          ) var raceId          : String?           = null,
  @SerialName("subRaceId"       ) var subRaceId       : String?           = null,
  @SerialName("classId"         ) var classId         : String?           = null,
  @SerialName("tags"            ) var tags            : ArrayList<String> = arrayListOf(),
  @SerialName("decorationKey"   ) var decorationKey   : String?           = null

)
