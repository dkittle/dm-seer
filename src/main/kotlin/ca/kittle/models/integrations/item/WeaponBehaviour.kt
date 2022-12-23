package ca.kittle.models.integrations.item

import ca.kittle.models.Dice
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeaponBehavior (

    @SerialName("baseItemId"   ) var baseItemId   : Int?                  = null,
    @SerialName("baseTypeId"   ) var baseTypeId   : Int?                  = null,
    @SerialName("type"         ) var type         : String?               = null,
    @SerialName("attackType"   ) var attackType   : Int?                  = null,
    @SerialName("categoryId"   ) var categoryId   : Int?                  = null,
    @SerialName("properties"   ) var properties   : ArrayList<Property> = arrayListOf(),
    @SerialName("damage"       ) var damage       : Dice?               = null,
    @SerialName("damageType"   ) var damageType   : String?               = null,
    @SerialName("range"        ) var range        : Int?                  = null,
    @SerialName("longRange"    ) var longRange    : Int?                  = null,
    @SerialName("isMonkWeapon" ) var isMonkWeapon : Boolean?              = null

)
