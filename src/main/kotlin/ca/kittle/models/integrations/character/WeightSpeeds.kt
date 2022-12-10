package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeightSpeeds (

  @SerialName("normal"            ) var normal            : ca.kittle.models.integrations.character.Normal? = ca.kittle.models.integrations.character.Normal(),
  @SerialName("encumbered"        ) var encumbered        : String? = null,
  @SerialName("heavilyEncumbered" ) var heavilyEncumbered : String? = null,
  @SerialName("pushDragLift"      ) var pushDragLift      : String? = null,
  @SerialName("override"          ) var override          : String? = null

)
