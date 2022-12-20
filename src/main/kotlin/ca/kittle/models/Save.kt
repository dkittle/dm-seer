package ca.kittle.models

data class Save(
    val id: Long,
    val name: String,
    val modifier: Int = 0,
    val proficient: Boolean = false,
    val expert: Boolean = false,
    val semiExpert: Boolean = false
) {
    companion object {
        fun getSaveById(id: Long): String? {
            for (save in Saves.values()) {
                if (save.id == id)
                    return save.name.lowercase()
            }
            return null
        }
    }
}

enum class Saves(val id: Long) {
    STRENGTH(1),
    DEXTERITY(2),
    CONSTITUTION(3),
    INTELLIGENCE(4),
    WISDOM(5),
    CHARISMA(6)
}
