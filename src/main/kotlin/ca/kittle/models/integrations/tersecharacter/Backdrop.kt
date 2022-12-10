package ca.kittle.models.integrations.tersecharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Backdrop (

  @SerialName("largeBackdropAvatarUrl"     ) var largeBackdropAvatarUrl     : String? = null,
  @SerialName("smallBackdropAvatarUrl"     ) var smallBackdropAvatarUrl     : String? = null,
  @SerialName("backdropAvatarUrl"          ) var backdropAvatarUrl          : String? = null,
  @SerialName("thumbnailBackdropAvatarUrl" ) var thumbnailBackdropAvatarUrl : String? = null

)
