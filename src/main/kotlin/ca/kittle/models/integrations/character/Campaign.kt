package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Campaign(

    @SerialName("id") var id: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("description") var description: String? = null,
    @SerialName("link") var link: String? = null,
    @SerialName("publicNotes") var publicNotes: String? = null,
    @SerialName("dmUserId") var dmUserId: Int? = null,
    @SerialName("dmUsername") var dmUsername: String? = null,
    @SerialName("characters") var characters: List<CampaignCharacter>? = null

)
