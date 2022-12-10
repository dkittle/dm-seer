package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Decorations (

    @SerialName("avatarUrl"                            ) var avatarUrl                            : String?          = null,
    @SerialName("frameAvatarUrl"                       ) var frameAvatarUrl                       : String?          = null,
    @SerialName("backdropAvatarUrl"                    ) var backdropAvatarUrl                    : String?          = null,
    @SerialName("smallBackdropAvatarUrl"               ) var smallBackdropAvatarUrl               : String?          = null,
    @SerialName("largeBackdropAvatarUrl"               ) var largeBackdropAvatarUrl               : String?          = null,
    @SerialName("thumbnailBackdropAvatarUrl"           ) var thumbnailBackdropAvatarUrl           : String?          = null,
    @SerialName("defaultBackdrop"                      ) var defaultBackdrop                      : ca.kittle.models.integrations.character.DefaultBackdrop? = ca.kittle.models.integrations.character.DefaultBackdrop(),
    @SerialName("avatarId"                             ) var avatarId                             : Int?             = null,
    @SerialName("portraitDecorationKey"                ) var portraitDecorationKey                : String?          = null,
    @SerialName("frameAvatarDecorationKey"             ) var frameAvatarDecorationKey             : String?          = null,
    @SerialName("frameAvatarId"                        ) var frameAvatarId                        : Int?             = null,
    @SerialName("backdropAvatarDecorationKey"          ) var backdropAvatarDecorationKey          : String?          = null,
    @SerialName("backdropAvatarId"                     ) var backdropAvatarId                     : Int?             = null,
    @SerialName("smallBackdropAvatarDecorationKey"     ) var smallBackdropAvatarDecorationKey     : String?          = null,
    @SerialName("smallBackdropAvatarId"                ) var smallBackdropAvatarId                : Int?             = null,
    @SerialName("largeBackdropAvatarDecorationKey"     ) var largeBackdropAvatarDecorationKey     : String?          = null,
    @SerialName("largeBackdropAvatarId"                ) var largeBackdropAvatarId                : Int?             = null,
    @SerialName("thumbnailBackdropAvatarDecorationKey" ) var thumbnailBackdropAvatarDecorationKey : String?          = null,
    @SerialName("thumbnailBackdropAvatarId"            ) var thumbnailBackdropAvatarId            : Int?             = null,
    @SerialName("themeColor"                           ) var themeColor                           : ca.kittle.models.integrations.character.ThemeColor?      = ca.kittle.models.integrations.character.ThemeColor()

)
