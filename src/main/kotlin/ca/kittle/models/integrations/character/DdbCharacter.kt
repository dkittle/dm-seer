package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DdbCharacter(

    @SerialName("id") val id: Int?,
    @SerialName("userId") val userId: Int?,
    @SerialName("username") val username: String?,
    @SerialName("isAssignedToPlayer") val isAssignedToPlayer: Boolean?,
    @SerialName("readonlyUrl") val readonlyUrl: String?,
    @SerialName("decorations") val decorations: ca.kittle.models.integrations.character.Decorations?,
    @SerialName("name") val name: String?,
    @SerialName("socialName") val socialName: String?,
    @SerialName("gender") val gender: String?,
    @SerialName("faith") val faith: String?,
    @SerialName("age") val age: String?,
    @SerialName("hair") val hair: String?,
    @SerialName("eyes") val eyes: String?,
    @SerialName("skin") val skin: String?,
    @SerialName("height") val height: String?,
    @SerialName("weight") val weight: String?,
    @SerialName("inspiration") val inspiration: Boolean?,
    @SerialName("baseHitPoints") val baseHitPoints: Int?,
    @SerialName("bonusHitPoints") val bonusHitPoints: String?,
    @SerialName("overrideHitPoints") val overrideHitPoints: String?,
    @SerialName("removedHitPoints") val removedHitPoints: Int?,
    @SerialName("temporaryHitPoints") val temporaryHitPoints: Int?,
    @SerialName("currentXp") val currentXp: Int?,
    @SerialName("alignmentId") val alignmentId: String?,
    @SerialName("lifestyleId") val lifestyleId: String?,
    @SerialName("stats") val stats: List<Stat>,
    @SerialName("bonusStats") val bonusStats: List<Stat>? = null,
    @SerialName("overrideStats") val overrideStats: List<ca.kittle.models.integrations.character.OverrideStats>,
    @SerialName("background") val background: ca.kittle.models.integrations.character.Background?,
    @SerialName("race") val species: ca.kittle.models.integrations.character.Species?,
    @SerialName("raceDefinitionId") val raceDefinitionId: String?,
    @SerialName("raceDefinitionTypeId") val raceDefinitionTypeId: String?,
    @SerialName("notes") val notes: ca.kittle.models.integrations.character.Notes? = ca.kittle.models.integrations.character.Notes(),
    @SerialName("traits") val traits: ca.kittle.models.integrations.character.Traits? = ca.kittle.models.integrations.character.Traits(),
    @SerialName("preferences") val preferences: ca.kittle.models.integrations.character.Preferences? = ca.kittle.models.integrations.character.Preferences(),
    @SerialName("configuration") val configuration: ca.kittle.models.integrations.character.Configuration? = ca.kittle.models.integrations.character.Configuration(),
    @SerialName("lifestyle") val lifestyle: String?,
    @SerialName("inventory") val inventory: List<ca.kittle.models.integrations.character.Inventory>,
    @SerialName("currencies") val currencies: ca.kittle.models.integrations.character.Currencies? = ca.kittle.models.integrations.character.Currencies(),
    @SerialName("classes") val classes: List<ca.kittle.models.integrations.character.Classes>,
    @SerialName("feats") val feats: List<Feat>,
    @SerialName("features") val features: String?,
    @SerialName("customDefenseAdjustments") val customDefenseAdjustments: List<String>,
    @SerialName("customSenses") val customSenses: List<String>,
    @SerialName("customSpeeds") val customSpeeds: List<String>,
    @SerialName("customProficiencies") val customProficiencies: List<String>,
    @SerialName("customActions") val customActions: List<ca.kittle.models.integrations.character.CustomActions>,
    @SerialName("characterValues") val characterValues: List<Valuable>?,
    @SerialName("conditions") val conditions: List<ca.kittle.models.integrations.character.Conditions>,
    @SerialName("deathSaves") val deathSaves: ca.kittle.models.integrations.character.DeathSaves? = ca.kittle.models.integrations.character.DeathSaves(),
    @SerialName("adjustmentXp") val adjustmentXp: String?,
    @SerialName("spellSlots") val spellSlots: List<ca.kittle.models.integrations.character.SpellSlots>,
    @SerialName("pactMagic") val pactMagic: List<ca.kittle.models.integrations.character.PactMagic>,
    @SerialName("activeSourceCategories") val activeSourceCategories: List<Int>,
    @SerialName("spells") val spells: ca.kittle.models.integrations.character.Spells? = ca.kittle.models.integrations.character.Spells(),
    @SerialName("options") val options: ca.kittle.models.integrations.character.Option? = ca.kittle.models.integrations.character.Option(),
    @SerialName("choices") val choices: ca.kittle.models.integrations.character.Choices? = ca.kittle.models.integrations.character.Choices(),
    @SerialName("actions") val actions: ca.kittle.models.integrations.character.Actions?,
    @SerialName("modifiers") val modifiers: ca.kittle.models.integrations.character.Modifiers? = ca.kittle.models.integrations.character.Modifiers(),
    @SerialName("classSpells") val classSpells: List<ca.kittle.models.integrations.character.ClassSpells>,
    @SerialName("customItems") val customItems: List<ca.kittle.models.integrations.character.CustomItems>,
    @SerialName("campaign") val campaign: ca.kittle.models.integrations.character.Campaign? = ca.kittle.models.integrations.character.Campaign(),
    @SerialName("creatures") val creatures: List<Creature>? = null,
    @SerialName("optionalOrigins") val optionalOrigins: List<OptionalOrigin>,
    @SerialName("optionalClassFeatures") val optionalClassFeatures: List<ca.kittle.models.integrations.character.OptionalClassFeature>,
    @SerialName("dateModified") val dateModified: String?,
    @SerialName("providedFrom") val providedFrom: String?,
    @SerialName("canEdit") val canEdit: Boolean?,
    @SerialName("campaignSetting") val campaignSetting: String?

)

