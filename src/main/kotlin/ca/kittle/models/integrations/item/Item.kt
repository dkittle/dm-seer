package ca.kittle.models.integrations.item

import ca.kittle.models.Dice
import ca.kittle.models.integration.item.GrantedModifier
import ca.kittle.models.integrations.Source
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Item (

  @SerialName("id"                    ) var id                    : Int?                        = null,
  @SerialName("baseTypeId"            ) var baseTypeId            : Int?                        = null,
  @SerialName("entityTypeId"          ) var entityTypeId          : Int?                        = null,
  @SerialName("definitionKey"         ) var definitionKey         : String?                     = null,
  @SerialName("canEquip"              ) var canEquip              : Boolean?                    = null,
  @SerialName("magic"                 ) var magic                 : Boolean?                    = null,
  @SerialName("name"                  ) var name                  : String?                     = null,
  @SerialName("snippet"               ) var snippet               : String?                     = null,
  @SerialName("weight"                ) var weight                : Float?                        = null,
  @SerialName("weightMultiplier"      ) var weightMultiplier      : Float?                        = null,
  @SerialName("capacity"              ) var capacity              : String?                     = null,
  @SerialName("capacityWeight"        ) var capacityWeight        : Float?                        = null,
  @SerialName("type"                  ) var type                  : String?                     = null,
  @SerialName("description"           ) var description           : String?                     = null,
  @SerialName("canAttune"             ) var canAttune             : Boolean?                    = null,
  @SerialName("attunementDescription" ) var attunementDescription : String?                     = null,
  @SerialName("rarity"                ) var rarity                : String?                     = null,
  @SerialName("isHomebrew"            ) var isHomebrew            : Boolean?                    = null,
  @SerialName("version"               ) var version               : String?                     = null,
  @SerialName("sourceId"              ) var sourceId              : String?                     = null,
  @SerialName("sourcePageNumber"      ) var sourcePageNumber      : String?                     = null,
  @SerialName("stackable"             ) var stackable             : Boolean?                    = null,
  @SerialName("bundleSize"            ) var bundleSize            : Int?                        = null,
  @SerialName("avatarUrl"             ) var avatarUrl             : String?                     = null,
  @SerialName("largeAvatarUrl"        ) var largeAvatarUrl        : String?                     = null,
  @SerialName("filterType"            ) var filterType            : String?                     = null,
  @SerialName("cost"                  ) var cost                  : String?                     = null,
  @SerialName("isPack"                ) var isPack                : Boolean?                    = null,
  @SerialName("tags"                  ) var tags                  : ArrayList<String>           = arrayListOf(),
  @SerialName("grantedModifiers"      ) var grantedModifiers      : ArrayList<GrantedModifier> = arrayListOf(),
  @SerialName("subType"               ) var subType               : String?                     = null,
  @SerialName("isConsumable"          ) var isConsumable          : Boolean?                    = null,
  @SerialName("weaponBehaviors"       ) var weaponBehaviors       : ArrayList<WeaponBehavior>           = arrayListOf(),
  @SerialName("baseItemId"            ) var baseItemId            : String?                     = null,
  @SerialName("baseArmorName"         ) var baseArmorName         : String?                     = null,
  @SerialName("strengthRequirement"   ) var strengthRequirement   : String?                     = null,
  @SerialName("armorClass"            ) var armorClass            : String?                     = null,
  @SerialName("stealthCheck"          ) var stealthCheck          : String?                     = null,
  @SerialName("damage"                ) var damage                : Dice?                     = null,
  @SerialName("damageType"            ) var damageType            : String?                     = null,
  @SerialName("fixedDamage"           ) var fixedDamage           : String?                     = null,
  @SerialName("properties"            ) var properties            : List<Property>?                     = arrayListOf(),
  @SerialName("attackType"            ) var attackType            : String?                     = null,
  @SerialName("categoryId"            ) var categoryId            : String?                     = null,
  @SerialName("range"                 ) var range                 : String?                     = null,
  @SerialName("longRange"             ) var longRange             : String?                     = null,
  @SerialName("isMonkWeapon"          ) var isMonkWeapon          : Boolean?                    = null,
  @SerialName("levelInfusionGranted"  ) var levelInfusionGranted  : Int?                        = null,
  @SerialName("sources"               ) var sources               : ArrayList<Source>          = arrayListOf(),
  @SerialName("armorTypeId"           ) var armorTypeId           : String?                     = null,
  @SerialName("gearTypeId"            ) var gearTypeId            : Int?                        = null,
  @SerialName("groupedId"             ) var groupedId             : String?                     = null,
  @SerialName("canBeAddedToInventory" ) var canBeAddedToInventory : Boolean?                    = null,
  @SerialName("isContainer"           ) var isContainer           : Boolean?                    = null,
  @SerialName("isCustomItem"          ) var isCustomItem          : Boolean?                    = null

)
