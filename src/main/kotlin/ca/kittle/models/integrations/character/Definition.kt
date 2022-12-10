package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Definition (

  @SerialName("id"               ) var id               : Int?,
  @SerialName("entityTypeId"     ) var entityTypeId     : Int?              ,
  @SerialName("name"             ) var name             : String?           ,
  @SerialName("description"      ) var description      : String?           ,
  @SerialName("snippet"          ) var snippet          : String?           ,
  @SerialName("activation"       ) var activation       : String?           ,
  @SerialName("sourceId"         ) var sourceId         : Int?              ,
  @SerialName("sourcePageNumber" ) var sourcePageNumber : String?           ,
  @SerialName("creatureRules"    ) var creatureRules    : List<String> = ArrayList(),
  @SerialName("spellListIds"     ) var spellListIds     : List<String> = ArrayList()

)
