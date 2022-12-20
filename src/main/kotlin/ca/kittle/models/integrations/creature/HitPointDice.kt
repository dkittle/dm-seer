package ca.kittle.models.integrations.creature

import kotlinx.serialization.Serializable

@Serializable
data class HitPointDice (

	val diceCount : Int,
	val diceValue : Int,
	val diceMultiplier : Int,
	val fixedValue : Int,
	val diceString : String
)
