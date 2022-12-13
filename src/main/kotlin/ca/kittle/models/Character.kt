package ca.kittle.models




data class Character (
    val name: String,
    val level: Int,
    val inspiration: Boolean,
    val species: String,
    val subSpecies: String,
    val classes: List<CharClass>,
    val health: Health,
    val deathSaves: DeathSaves,
    val abilities: List<Ability>,
//    val skills: List<Skill>,
//    val saves: List<Save>,
//    val background: Background,
//    val experiencePoints: Int,
//    val magic: Magic,
//    val inventory: List<Item>
)
