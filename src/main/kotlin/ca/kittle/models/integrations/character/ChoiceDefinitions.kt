package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChoiceDefinitions(

    @SerialName("id") var id: String? = null,
    @SerialName("options") var options: ArrayList<Option>? = null

)
