package ca.kittle.models.integrations.encounter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Pagination (

  @SerialName("take"        ) var take        : Int? = null,
  @SerialName("skip"        ) var skip        : Int? = null,
  @SerialName("currentPage" ) var currentPage : Int? = null,
  @SerialName("pages"       ) var pages       : Int? = null,
  @SerialName("total"       ) var total       : Int? = null

)
