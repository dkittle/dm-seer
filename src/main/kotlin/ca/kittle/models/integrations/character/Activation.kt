package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Activation(

    @SerialName("activationTime") var activationTime: Int? = null,
    @SerialName("activationType") var activationType: String? = null

)
