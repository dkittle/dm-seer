package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Classes (

  @SerialName("id"                   ) var id                   : Int?                     ,
  @SerialName("entityTypeId"         ) var entityTypeId         : Int?                     ,
  @SerialName("level"                ) var level                : Int?                     ,
  @SerialName("isStartingClass"      ) var isStartingClass      : Boolean?                 ,
  @SerialName("hitDiceUsed"          ) var hitDiceUsed          : Int?                     ,
  @SerialName("definitionId"         ) var definitionId         : Int?                     ,
  @SerialName("subclassDefinitionId" ) var subclassDefinitionId : String?                  ,
  @SerialName("definition"           ) var definition           : ca.kittle.models.integrations.character.Definition?,
  @SerialName("subclassDefinition"   ) var subclassDefinition   : ca.kittle.models.integrations.character.SubclassDefinition?,
  @SerialName("classFeatures"        ) var classFeatures        : ArrayList<ca.kittle.models.integrations.character.ClassFeatures>

)
