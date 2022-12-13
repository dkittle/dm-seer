package ca.kittle.models

data class Health(
    val hitPoints: Int,
    val maximumHitPoints: Int,
    val temporaryHitPoints: Int,
    val temporaryMaximum: Int
)
