package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClassFeatures (

    @SerialName("definition" ) var definition : Definition?,
    @SerialName("levelScale" ) var levelScale : LevelScale?

)
