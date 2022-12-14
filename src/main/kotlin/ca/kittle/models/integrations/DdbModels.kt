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
    val splashUrl: String = "",
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

@Serializable
data class DdbUserCharactersResponse (
    val id: Long,
    val success: Boolean,
    val message: String,
    val data: DdbUserCharactersSettings
)

@Serializable
data class DdbUserCharactersSettings (
    val characters: List<DdbCharacterHeadline>
)

@Serializable
data class DdbCharacterHeadline (
    var id: Long?,
    var level: Int?,
    var name: String?,
    var status: Int?,
    var statusSlug: String?,
    var isAssigned: Boolean?,
    @SerialName("classDescription") var charClass: String?,
    @SerialName("raceName") var species: String?,
    var avatarUrl: String?,
    var backdropUrl: String?,
    var coverImageUrl: String?,
    var characterSecondaryInfo: String?,
    var campaignId: Long?,
    var campaignName: String?,
    var createdDate: Long?,
    var lastModifiedDate: Long?,
    var isReady: Boolean?
)
