package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Inventory (

    @SerialName("id"                     ) var id                     : Int?        = null,
    @SerialName("entityTypeId"           ) var entityTypeId           : Int?        = null,
    @SerialName("definition"             ) var definition             : ca.kittle.models.integrations.character.Definition?,
    @SerialName("definitionId"           ) var definitionId           : Int?        = null,
    @SerialName("definitionTypeId"       ) var definitionTypeId       : Int?        = null,
    @SerialName("displayAsAttack"        ) var displayAsAttack        : String?     = null,
    @SerialName("quantity"               ) var quantity               : Int?        = null,
    @SerialName("isAttuned"              ) var isAttuned              : Boolean?    = null,
    @SerialName("equipped"               ) var equipped               : Boolean?    = null,
    @SerialName("equippedEntityTypeId"   ) var equippedEntityTypeId   : Int?        = null,
    @SerialName("equippedEntityId"       ) var equippedEntityId       : Int?        = null,
    @SerialName("chargesUsed"            ) var chargesUsed            : Int?        = null,
    @SerialName("limitedUse"             ) var limitedUse             : String?     = null,
    @SerialName("containerEntityId"      ) var containerEntityId      : Int?        = null,
    @SerialName("containerEntityTypeId"  ) var containerEntityTypeId  : Int?        = null,
    @SerialName("containerDefinitionKey" ) var containerDefinitionKey : String?     = null,
    @SerialName("currency"               ) var currency               : String?     = null

)
