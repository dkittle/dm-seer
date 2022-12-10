package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CampaignCharacter(

    @SerialName("userId") var userId: Int? = null,
    @SerialName("username") var username: String? = null,
    @SerialName("characterId") var characterId: Int? = null,
    @SerialName("characterName") var characterName: String? = null,
    @SerialName("characterUrl") var characterUrl: String? = null,
    @SerialName("avatarUrl") var avatarUrl: String? = null,
    @SerialName("privacyType") var privacyType: Int? = null,
    @SerialName("campaignId") var campaignId: String? = null,
    @SerialName("isAssigned") var isAssigned: Boolean? = null

)
