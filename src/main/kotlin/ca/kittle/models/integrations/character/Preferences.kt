package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Preferences (

  @SerialName("useHomebrewContent"          ) var useHomebrewContent          : Boolean? = null,
  @SerialName("progressionType"             ) var progressionType             : Int?     = null,
  @SerialName("encumbranceType"             ) var encumbranceType             : Int?     = null,
  @SerialName("ignoreCoinWeight"            ) var ignoreCoinWeight            : Boolean? = null,
  @SerialName("hitPointType"                ) var hitPointType                : Int?     = null,
  @SerialName("showUnarmedStrike"           ) var showUnarmedStrike           : Boolean? = null,
  @SerialName("showScaledSpells"            ) var showScaledSpells            : Boolean? = null,
  @SerialName("primarySense"                ) var primarySense                : Int?     = null,
  @SerialName("primaryMovement"             ) var primaryMovement             : Int?     = null,
  @SerialName("privacyType"                 ) var privacyType                 : Int?     = null,
  @SerialName("sharingType"                 ) var sharingType                 : Int?     = null,
  @SerialName("abilityScoreDisplayType"     ) var abilityScoreDisplayType     : Int?     = null,
  @SerialName("enforceFeatRules"            ) var enforceFeatRules            : Boolean? = null,
  @SerialName("enforceMulticlassRules"      ) var enforceMulticlassRules      : Boolean? = null,
  @SerialName("enableOptionalClassFeatures" ) var enableOptionalClassFeatures : Boolean? = null,
  @SerialName("enableOptionalOrigins"       ) var enableOptionalOrigins       : Boolean? = null,
  @SerialName("enableDarkMode"              ) var enableDarkMode              : Boolean? = null,
  @SerialName("enableContainerCurrency"     ) var enableContainerCurrency     : Boolean? = null

)
