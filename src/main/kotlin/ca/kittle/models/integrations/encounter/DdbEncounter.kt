package ca.kittle.models.integrations.encounter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DdbEncounter (

    @SerialName("stats"    ) var stats    : String?  = null,
    @SerialName("editable" ) var editable : Boolean? = null,
    @SerialName("data"     ) var encounter     : Encounter? = null

)
