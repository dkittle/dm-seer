package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultBackdrop (

  @SerialName("backdropAvatarUrl"          ) var backdropAvatarUrl          : String? = null,
  @SerialName("smallBackdropAvatarUrl"     ) var smallBackdropAvatarUrl     : String? = null,
  @SerialName("largeBackdropAvatarUrl"     ) var largeBackdropAvatarUrl     : String? = null,
  @SerialName("thumbnailBackdropAvatarUrl" ) var thumbnailBackdropAvatarUrl : String? = null

)
