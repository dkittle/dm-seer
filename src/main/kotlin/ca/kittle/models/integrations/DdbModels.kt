package ca.kittle.models.integrations

import ca.kittle.models.integrations.character.DdbCharacter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DdbCampaign(
    val id: Long,
    val name: String,
    val dmUsername: String,
    val dmId: Long,
    val dateCreated: String,
    val playerCount: Int
)


@Serializable
data class DdbAuthResponse(
    @SerialName("token")
    val token: String,
    @SerialName("ttl")
    val ttl: Int)

@Serializable
data class DdbCampaignResponse(
    val status: String,
    val data: List<DdbCampaign>
)

@Serializable
data class DdbCharacterResponse(
    val success: Boolean,
    val message: String,
    val data: DdbCharacter
)
