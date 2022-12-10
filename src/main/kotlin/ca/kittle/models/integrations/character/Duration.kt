package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Duration(

    @SerialName("durationInterval") var durationInterval: Int? = null,
    @SerialName("durationUnit") var durationUnit: String? = null,
    @SerialName("durationType") var durationType: String? = null

)
