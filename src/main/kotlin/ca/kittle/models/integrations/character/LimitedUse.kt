package ca.kittle.models.integrations.character

import ca.kittle.models.Dice
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LimitedUse(

    @SerialName("name") var name: String? = null,
    @SerialName("statModifierUsesId") var statModifierUsesId: String? = null,
    @SerialName("resetType") var resetType: String? = null,
    @SerialName("numberUsed") var numberUsed: Int? = null,
    @SerialName("minNumberConsumed") var minNumberConsumed: Int? = null,
    @SerialName("maxNumberConsumed") var maxNumberConsumed: Int? = null,
    @SerialName("maxUses") var maxUses: Int? = null,
    @SerialName("operator") var operator: Int? = null,
    @SerialName("useProficiencyBonus") var useProficiencyBonus: Boolean? = null,
    @SerialName("proficiencyBonusOperator") var proficiencyBonusOperator: Int? = null,
    @SerialName("resetDice") var resetDice: Dice? = null,
    var resetTypeDescription: String?
)

