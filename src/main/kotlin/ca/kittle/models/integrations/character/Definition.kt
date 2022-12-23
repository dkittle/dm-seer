package ca.kittle.models.integrations.character

import ca.kittle.models.integrations.Source
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Definition (

  @SerialName("id"               ) var id               : Int?,
  @SerialName("entityTypeId"     ) var entityTypeId     : Int?,
  @SerialName("name"             ) var name             : String?,
  @SerialName("description"      ) var description      : String?,
  @SerialName("snippet"          ) var snippet          : String?,
  @SerialName("activation"       ) var activation       : Activation? = null,
  @SerialName("sourceId"         ) var sourceId         : Int?,
  @SerialName("sourcePageNumber" ) var sourcePageNumber : String?,
  @SerialName("creatureRules"    ) var creatureRules    : List<CreatureRule>? = null,
  @SerialName("prerequisites") var prerequisites: List<Prerequisite>? = null,
  val sources: List<Source>? = null,
  val isHomebrew: Boolean?,
  @SerialName("spellListIds"     ) var spellListIds     : List<String> = ArrayList()

)
