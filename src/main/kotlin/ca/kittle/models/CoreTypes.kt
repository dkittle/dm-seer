package ca.kittle.models

class CharacterSpeciess {
    companion object {
        fun getCharacterSpeciesById(id: Int): CharacterSpecies? {
            return when (id) {
                in 1.. CharacterSpecies.values().size -> CharacterSpecies.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class CharacterSpecies(val id: Int, val label: String, val avatarUrl: String?) {
    HUMAN(1, "Human", "https://www.dndbeyond.com/avatars/316/819/636620994416798583.jpeg"),
    HALFORC(2, "Half-Orc", "https://www.dndbeyond.com/avatars/316/817/636620994330373054.jpeg"),
    ELF(3, "Elf", "https://www.dndbeyond.com/avatars/316/807/636620993866733375.jpeg"),
    AARAKOCRA(4, "Aarakocra", "https://www.dndbeyond.com/avatars/316/802/636620993394513358.jpeg"),
    TIEFLING(5, "Tiefling", "https://www.dndbeyond.com/avatars/316/825/636620994695575306.jpeg"),
    DWARF(6, "Dwarf", "https://www.dndbeyond.com/avatars/316/806/636620993762055484.jpeg"),
    HALFLING(7, "Halfling", "https://www.dndbeyond.com/avatars/316/816/636620994265008086.jpeg"),
    DRAGONBORN(8, "Dragonborn", "https://www.dndbeyond.com/avatars/316/805/636620993696690483.jpeg"),
    GNOME(9, "Gnome", "https://www.dndbeyond.com/avatars/316/811/636620994035528201.jpeg"),
    HALFELF(10, "Half-Elf", "https://www.dndbeyond.com/avatars/316/814/636620994184198696.jpeg"),
    GOLIATH(11, "Goliath", "https://www.dndbeyond.com/avatars/316/813/636620994135369890.jpeg"),
    GENASI(12, "Genasi", "https://www.dndbeyond.com/avatars/316/809/636620993978899042.jpeg"),
    AASIMAR(13, "Aasimar", "https://www.dndbeyond.com/avatars/316/803/636620993510579220.jpeg"),
    FIRBOLG(14, "Firbolg", "https://www.dndbeyond.com/avatars/316/808/636620993924142291.jpeg"),
    KENKU(15, "Kenku", "https://www.dndbeyond.com/avatars/316/820/636620994458763373.jpeg"),
    LIZARDFOLK(16, "Lizardfolk", "https://www.dndbeyond.com/avatars/316/822/636620994561880989.jpeg"),
    TABAXI(17, "Tabaxi", "https://www.dndbeyond.com/avatars/316/824/636620994652518517.jpeg"),
    TRITON(18, "Triton", "https://www.dndbeyond.com/avatars/316/827/636620994792452860.jpeg"),
    BUGBEAR(19, "Bugbear", "https://www.dndbeyond.com/avatars/316/804/636620993577660502.jpeg"),
    GOBLIN(20, "Goblin", "https://www.dndbeyond.com/avatars/316/812/636620994081080899.jpeg"),
    HOBGOBLIN(21, "Hobgoblin", "https://www.dndbeyond.com/avatars/316/818/636620994370309752.jpeg"),
    KOBOLD(22, "Kobold", "https://www.dndbeyond.com/avatars/316/821/636620994500727959.jpeg"),
    ORC(23, "Orc", "https://www.dndbeyond.com/avatars/316/823/636620994608993950.jpeg"),
    YUANTIPUREBLOOD(24, "Yuan-ti Pureblood", "https://www.dndbeyond.com/avatars/316/828/636620994846117422.jpeg"),
    TORTLE(25, "Tortle", "https://www.dndbeyond.com/avatars/316/826/636620994747056231.jpeg"),
    GITH(27, "Gith", "https://www.dndbeyond.com/avatars/318/759/636621911421792248.jpeg"),
    WARFORGED(28, "Warforged", "https://www.dndbeyond.com/avatars/2490/554/636680421772857650.jpeg"),
    SHIFTER(29, "Shifter", "https://www.dndbeyond.com/avatars/2490/562/636680421866916694.jpeg"),
    DRACONICRACES(30, "Draconic Races", null),
    LINEAGES(31, "Lineages", null)
}

class SpellRangeTypes {
    companion object {
        fun getSpellRangeTypeById(id: Int): SpellRangeType? {
            return when (id) {
                in 1.. SpellRangeType.values().size -> SpellRangeType.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class SpellRangeType(val id: Int, val label: String) {
    SELF(1, "Self"),
    TOUCH(2, "Touch"),
    RANGED(3, "Ranged"),
    SIGHT(4, "Sight"),
    UNLIMITED(9, "Unlimited")
}

class AttackTypes {
    companion object {
        fun getAttackTypeById(id: Int): AttackType? {
            return when (id) {
                in 1.. AttackType.values().size -> AttackType.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class AttackType(val id: Int, val label: String) {
    MELEE(1, "Melee"),
    RANGED(2, "Ranged")
}

class DamageTypes {
    companion object {
        fun getDamageTypeById(id: Int): DamageType {
            return when (id) {
                in 1.. DamageType.values().size -> DamageType.values()[id - 1]
                else -> DamageType.UNKNOWN
            }
        }
        fun getDamageTypeByName(input: String): DamageType {
            return DamageType.values().filter { it.name.equals(input.uppercase()) }.getOrNull(0) ?: DamageType.UNKNOWN
        }
    }
}

enum class DamageType(val id: Int, val label: String) {
    BLUDGEONING(1, "Bludgeoning"),
    PIERCING(2, "Piercing"),
    SLASHING(3, "Slashing"),
    NECROTIC(4, "Necrotic"),
    ACID(5, "Acid"),
    COLD(6, "Cold"),
    FIRE(7, "Fire"),
    LIGHTNING(8, "Lightning"),
    THUNDER(9, "Thunder"),
    POISON(10, "Poison"),
    PSYCHIC(11, "Psychic"),
    RADIANT(12, "Radiant"),
    FORCE(13, "Force"),
    MAGICAL(20, "Magical"),
    SILVERED(21, "Silvered"),
    ADAMANTINE(22, "Adamantine"),
    NONMAGICAL(30, "Non-magical"),
    NONSILVERED(31, "Non-silvered, non-magical"),
    NONADAMANTINE(33, "Non-adamantine, non-magical"),
    MAGICALGOOD(34, "Magical wielded by good creatures"),
    INDARKNESS(35, "While in dim light or darkness"),
    TRAPS(40, "Traps"),
    RANGED(41, "Ranged attacks"),
    WEAPONS(42, "Weapons"),
    SPELLS(43, "Spells"),
    ALL(98, "All"),
    UNKNOWN(99, "Unknown")
}

class CreatureSubTypes {
    companion object {
        fun getCreatureSubTypeById(id: Int): CreatureSubType? {
            return when (id) {
                in 1.. CreatureSubType.values().size -> CreatureSubType.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class CreatureSubType(val id: Int, val label: String) {
    AARAKOCRA(1, "aarakocra"),
    GNOME(2, "gnome"),
    GNOLL(3, "gnoll"),
    GRIMLOCK(4, "grimlock"),
    ANYRACE(5, "any race"),
    ELF(6, "elf"),
    TORTLE(7, "tortle"),
    YUGOLOTH(8, "yugoloth"),
    DEMON(9, "demon"),
    DEVIL(10, "devil"),
    SHAPECHANGER(11, "shapechanger"),
    NAGPA(12, "nagpa"),
    MEAZEL(13, "meazel"),
    INEVITABLE(14, "inevitable"),
    GITH(15, "gith"),
    DWARF(16, "dwarf"),
    DERRO(17, "derro"),
    KUOTOA(18, "kuo-toa"),
    TITAN(19, "titan"),
    YUANTI(20, "yuan-ti"),
    XVART(21, "xvart"),
    ORC(22, "orc"),
    GOBLINOID(23, "goblinoid"),
    KOBOLD(24, "kobold"),
    GRUNG(25, "grung"),
    KENKU(26, "kenku"),
    FIRENEWT(27, "firenewt"),
    TROGLODYTE(28, "troglodyte"),
    THRIKREEN(29, "thri-kreen"),
    SAHUAGIN(30, "sahuagin"),
    QUAGGOTH(31, "quaggoth"),
    MERFOLK(32, "merfolk"),
    HUMAN(33, "human"),
    LIZARDFOLK(34, "lizardfolk"),
    CLOUDGIANT(35, "cloud giant"),
    FIREGIANT(36, "fire giant"),
    FROSTGIANT(37, "frost giant"),
    HILLGIANT(38, "hill giant"),
    STONEGIANT(39, "stone giant"),
    STORMGIANT(40, "storm giant"),
    BULLYWUG(41, "bullywug"),
    SAURIAL(42, "saurial"),
    TABAXI(43, "tabaxi"),
    HALFELF(44, "half-elf"),
    HALFDRAGON(45, "half-dragon"),
    SIMICHYBRID(46, "simic hybrid"),
    ANGEL(47, "angel"),
    KRAUL(48, "kraul"),
    TIEFLING(49, "tiefling"),
    HALFORC(50, "half-orc"),
    HALFLING(51, "halfling"),
    LOCATHAH(52, "locathah"),
    TRITON(53, "triton"),
    DRAGONBORN(54, "dragonborn"),
    WARFORGED(55, "warforged"),
    WATERGENASI(56, "water genasi"),
    FIREGENASI(57, "fire genasi"),
    EARTHGENASI(58, "earth genasi"),
    AIRGENASI(59, "air genasi"),
    CHANGELING(60, "changeling"),
    KALASHTAR(61, "kalashtar"),
    SHIFTER(62, "shifter"),
    BLINDHEIM(63, "blindheim"),
    DIRECORBY(64, "dire corby"),
    JERMLAINE(65, "jermlaine"),
    GOLIATH(66, "goliath"),
    GIFF(67, "Giff"),
    SKULK(68, "Skulk"),
    LEONIN(69, "Leonin"),
    GRIPPLI(70, "grippli"),
    MONGRELFOLK(71, "mongrelfolk"),
    HARENGON(72, "Harengon"),
    HAG(73, "Hag"),
    WIZARD(74, "Wizard"),
    SORCERER(75, "Sorcerer"),
    WARLOCK(76, "Warlock"),
    CLERIC(77, "Cleric"),
    SHADARKAI(78, "Shadar-Kai"),
    PALADIN(79, "Paladin"),
    GEM(80, "Gem"),
    METALLIC(81, "Metallic"),
    CHROMATIC(82, "Chromatic"),
    HIGHELF(83, "High-Elf"),
    HALFBLACKDRAGON(84, "half-black dragon"),
    DRUID(85, "Druid"),
    BARD(89, "Bard"),
    SHIELDDWARF(90, "shield dwarf"),
    RANGER(91, "Ranger"),
    GOBLIN(92, "Goblin"),
    DROW(93, "Drow"),
    OGRE(94, "Ogre"),
    MONK(95, "Monk"),
    MINDFLAYER(96, "Mind Flayer"),
    CATTLE(97, "Cattle"),
    DINOSAUR(98, "Dinosaur"),
    BEHOLDER(99, "Beholder"),
    MYCONID(100, "Myconid"),
    KENDER(101, "Kender"),
    ADULTCHROMATIC(102, "Adult Chromatic"),
    UNICORN(103, "Unicorn")
}

class CreatureGroupFlags {
    companion object {
        fun getCreatureGroupFlagById(id: Int): CreatureGroupFlag? {
            return when (id) {
                in 1.. CreatureGroupFlag.values().size -> CreatureGroupFlag.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class CreatureGroupFlag(val id: Int, val label: String, val key: String, val value: Int?, val valueContextId: Int?) {
    ARMORADDPROFICIENCYBONUS(1, "Armor Add Proficiency Bonus", "ACPB", null, null),
    ATTACKROLLSADDPROFICIENCYBONUS(2, "Attack Rolls Add Proficiency Bonus", "ARPB", null, null),
    DAMAGEROLLSADDPROFICIENCYBONUS(3, "Damage Rolls Add Proficiency Bonus", "DRPB", null, null),
    PROFICIENTSKILLSADDPROFICIENCYBONUS(4, "Proficient Skills Add Proficiency Bonus", "PSPB", null, null),
    PROFICIENTSAVINGTHROWSADDPROFICIENCYBONUS(5, "Proficient Saving Throws Add Proficiency Bonus", "STPB", null, null),
    MAXHITPOINTSLEVELMULTIPLIEROPTION(6, "Max Hit Points Level Multiplier Option", "HPLM", 4, 5),
    EVALUATEOWNERSKILLPROFICIENCIES(7, "Evaluate Owner Skill Proficiencies", "EOSKP", null, null),
    EVALUATEOWNERSAVEPROFICIENCIES(8, "Evaluate Owner Save Proficiencies", "EOSVP", null, null),
    CANNOTBESWARM(9, "Cannot Be Swarm", "CBS", null, null),
    CANNOTUSELEGENDARYACTIONS(10, "Cannot Use Legendary Actions", "CULGA", null, null),
    CANNOTUSELAIRACTIONS(11, "Cannot Use Lair Actions", "CULRA", null, null),
    EVALUATEUPDATEDPASSIVEPERCEPTION(12, "Evaluate_Updated_Passive_Perception", "EUPP", null, null),
    EVALUATEOWNERPASSIVEPERCEPTION(13, "Evaluate Owner Passive Perception", "EOPP", null, null),
    ARTIFICERHPMULTIPLIER(14, "Artificer HP Multiplier", "AHM", 5, 252717),
    MAXHITPOINTSADDINTMODIFIER(15, "Max Hit Points Add Int Modifier", "MHPAIM", null, 4),
    MAXHITPOINTSADDMONSTERCONMODIFIER(16, "Max Hit Points Add Monster CON Modifier", "MHPAMCM", null, 3),
    USECHALLENGERATINGASLEVEL(17, "Use Challenge Rating As Level", "UCRAL", null, null),
    MAXHITPOINTSBASEARTIFICERLEVEL(18, "Max Hit Points Base Artificer Level", "MHPBAL", null, 252717)
}

class CreatureGroupCategories {
    companion object {
        fun getCreatureGroupCategoryById(id: Int): CreatureGroupCategory? {
            return when (id) {
                in 1.. CreatureGroupCategory.values().size -> CreatureGroupCategory.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class CreatureGroupCategory(val id: Int, val label: String) {
    COMPANION(1, "Companion"),
    SHAPECHANGE(2, "Shapechange"),
    OTHER(3, "Other")
}

class CreatureTypes {
    companion object {
        fun getCreatureTypeById(id: Int): CreatureType? {
            return CreatureType.values().filter { it.id == id }.getOrNull(0)
        }
    }
}

enum class CreatureType(val id: Int, val label: String, val pluralizedName: String, val avatarUrl: String, val description: String) {
    ABERRATION(1, "Aberration", "aberrations", "https://www.dndbeyond.com/avatars/4675/665/636747837392078487.jpeg", "Aberrations are utterly alien beings. Many of them have innate magical abilities drawn from the creature's alien mind rather than the mystical forces of the world. The quintessential aberrations are aboleths, and slaadi."),
    BEAST(2, "Beast", "beasts", "https://www.dndbeyond.com/avatars/4675/664/636747837303835953.jpeg", "Beasts are nonhumanoid creatures that are a natural part of the fantasy ecology. Some of them have magical powers, but most are unintelligent and lack any society or language. Beasts include all varieties of ordinary animals, dinosaurs, and giant versions of animals."),
    CELESTIAL(3, "Celestial", "celestials", "https://www.dndbeyond.com/avatars/4675/666/636747837434463638.jpeg", "Celestials are creatures native to the Upper Planes. Many of them are the servants of deities, employed as messengers or agents in the mortal realm and throughout the planes. Celestials are good by nature, so the exceptional celestial who strays from a good alignment is a horrifying rarity."),
    CONSTRUCT(4, "Construct", "constructs", "https://www.dndbeyond.com/avatars/4675/667/636747837482013331.jpeg", "Constructs include angels, couatls, and pegasi. Constructs are made, not born. Some are programmed by their creators to follow a simple set of instructions, while others are imbued with sentience and capable of independent thought. Golems are the iconic constructs. Many creatures native to the outer plane of Mechanus, such as modrons, are constructs shaped from the raw material of the plane by the will of more powerful creatures."),
    DRAGON(6, "Dragon", "dragons", "https://www.dndbeyond.com/avatars/4675/668/636747837521115242.jpeg", "Dragons are large reptilian creatures of ancient origin and tremendous power. True dragons, including the good metallic dragons and the evil chromatic dragons, are highly intelligent and have innate magic. Also in this category are creatures distantly related to true dragons, but less powerful, less intelligent, and less magical, such as wyverns and pseudodragons."),
    ELEMENTAL(7, "Elemental", "elementals", "https://www.dndbeyond.com/avatars/4675/669/636747837569942785.jpeg", "Elementals are creatures native to the elemental planes. Some creatures of this type are little more than animate masses of their respective elements, including the creatures simply called elementals. Others have biological forms infused with elemental energy. The races of genies, including djinn and efreet, form the most important civilizations on the elemental planes. Other elemental creatures include azers, invisible stalkers, and water weirds."),
    FEY(8, "Fey", "fey", "https://www.dndbeyond.com/avatars/4675/671/636747837638112910.jpeg", "Fey are magical creatures closely tied to the forces of nature. They dwell in twilight groves and misty forests. In some worlds, they are closely tied to the Feywild, also called the Plane of Faerie. Some are also found in the Outer Planes, particularly the planes of Arborea and the Beastlands. Fey include dryads, pixies, and satyrs."),
    FIEND(9, "Fiend", "fiends", "https://www.dndbeyond.com/avatars/4675/672/636747837699453839.jpeg", "Fiends are creatures of wickedness that are native to the Lower Planes. A few are the servants of deities, but many more labor under the leadership of archdevils and demon princes. Evil priests and mages sometimes summon fiends to the material world to do their bidding. If an evil celestial is a rarity, a good fiend is almost inconceivable. Fiends include demons, devils, hell hounds, rakshasas, and yugoloths."),
    GIANT(10, "Giant", "giants", "https://www.dndbeyond.com/avatars/4675/674/636747837751071918.jpeg", "Giants tower over humans and their kind. They are humanlike in shape, though some have multiple heads (ettins) or deformities (fomorians). The six varieties of true giant are hill giants, stone giants, frost giants, fire giants, cloud giants, and storm giants. Besides these, creatures such as ogres and trolls are giants."),
    HUMANOID(11, "Humanoid", "humanoids", "https://www.dndbeyond.com/avatars/4675/675/636747837794884984.jpeg", "Humanoids are the main peoples of a fantasy gaming world, both civilized and savage, including humans and a tremendous variety of other species. They have language and culture, few if any innate magical abilities (though most humanoids can learn spellcasting), and a bipedal form. The most common humanoid races are the ones most suitable as player characters: humans, dwarves, elves, and halflings. Almost as numerous but far more savage and brutal, and almost uniformly evil, are the races of goblinoids (goblins, hobgoblins, and bugbears), orcs, gnolls, lizardfolk, and kobolds."),
    MONSTROSITY(13, "Monstrosity", "monstrosities", "https://www.dndbeyond.com/avatars/4675/676/636747837839875603.jpeg", "Monstrosities are monsters in the strictest sense--frightening creatures that are not ordinary, not truly natural, and almost never benign. Some are the results of magical experimentation gone awry (such as owlbears), and others are the product of terrible curses (including minotaurs). They defy categorization, and in some sense serve as a catch-all category for creatures that don't fit into any other type."),
    OOZE(14, "Ooze", "oozes", "https://www.dndbeyond.com/avatars/4675/678/636747837893364274.jpeg", "Oozes are gelatinous creatures that rarely have a fixed shape. They are mostly subterranean, dwelling in caves and dungeons and feeding on refuse, carrion, or creatures unlucky enough to get in their way. Black puddings and gelatinous cubes are among the most recognizable oozes."),
    PLANT(15, "Plant", "plants", "https://www.dndbeyond.com/avatars/4675/679/636747837952193011.jpeg", "Plants in this context are vegetable creatures, not ordinary flora. Most of them are ambulatory, and some are carnivorous. The quintessential plants are the shambling mound and the treant. Fungal creatures such as the gas spore and the myconid also fall into this category."),
    UNDEAD(16, "Undead", "undead", "https://www.dndbeyond.com/avatars/4675/680/636747837998336262.jpeg", "Undead are once-living creatures brought to a horrifying state of undeath through the practice of necromantic magic or some unholy curse. Undead include walking corpses, such as vampires and zombies, as well as bodiless spirits, such as ghosts and specters.")
}

class Environments {
    companion object {
        fun getEnvironmentById(id: Int): Environment? {
            return when (id) {
                in 1.. Environment.values().size -> Environment.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class Environment(val id: Int, val label: String) {
    ARCTIC(1, "Arctic"),
    COASTAL(2, "Coastal"),
    DESERT(3, "Desert"),
    FOREST(4, "Forest"),
    GRASSLAND(5, "Grassland"),
    HILL(6, "Hill"),
    MOUNTAIN(7, "Mountain"),
    SWAMP(8, "Swamp"),
    UNDERDARK(9, "Underdark"),
    UNDERWATER(10, "Underwater"),
    URBAN(11, "Urban")
}

class ArmorTypes {
    companion object {
        fun getArmorTypeById(id: Int): ArmorType? {
            return when (id) {
                in 1.. ArmorType.values().size -> ArmorType.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class ArmorType(val id: Int, val label: String) {
    LIGHTARMOR(1, "Light Armor"),
    MEDIUMARMOR(2, "Medium Armor"),
    HEAVYARMOR(3, "Heavy Armor"),
    SHIELD(4, "Shield")
}

class GearTypes {
    companion object {
        fun getGearTypeById(id: Int): GearType? {
            return when (id) {
                in 1.. GearType.values().size -> GearType.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class GearType(val id: Int, val label: String) {
    ADVENTURINGGEAR(1, "Adventuring Gear"),
    ARMOR(2, "Armor"),
    ARTIFACT(3, "Artifact"),
    MOUNT(4, "Mount"),
    POISON(5, "Poison"),
    POTION(6, "Potion"),
    RING(7, "Ring"),
    ROD(8, "Rod"),
    SCROLL(9, "Scroll"),
    STAFF(10, "Staff"),
    TOOL(11, "Tool"),
    VEHICLE_LAND(12, "Vehicle (Land)"),
    WAND(13, "Wand"),
    WEAPON(14, "Weapon"),
    WONDROUSITEM(15, "Wondrous Item"),
    EQUIPMENTPACK(16, "Equipment Pack"),
    VEHICLE_WATER(17, "Vehicle (Water)"),
    GEMSTONE(18, "Gemstone"),
    VEHICLE_SPACE(19, "Vehicle (Space)")
}

class Languages {
    companion object {
        fun getLanguageById(id: Int): Language {
            return Language.values().filter { it.id == id }.getOrNull(0) ?: Language.UNKNOWN
        }
    }
}

enum class Language(val id: Int, val label: String) {
    COMMON(1, "Common"),
    DWARVISH(2, "Dwarvish"),
    ELVISH(3, "Elvish"),
    GIANT(4, "Giant"),
    GNOMISH(5, "Gnomish"),
    GOBLIN(6, "Goblin"),
    HALFLING(7, "Halfling"),
    ORC(8, "Orc"),
    ABYSSAL(9, "Abyssal"),
    CELESTIAL(10, "Celestial"),
    DRACONIC(11, "Draconic"),
    DEEPSPEECH(12, "Deep Speech"),
    INFERNAL(13, "Infernal"),
    PRIMORDIAL(14, "Primordial"),
    SYLVAN(15, "Sylvan"),
    UNDERCOMMON(16, "Undercommon"),
    TELEPATHY(18, "Telepathy"),
    AQUAN(19, "Aquan"),
    AURAN(20, "Auran"),
    IGNAN(21, "Ignan"),
    TERRAN(22, "Terran"),
    DRUIDIC(23, "Druidic"),
    GIANTEAGLE(24, "Giant Eagle"),
    GIANTELK(25, "Giant Elk"),
    GIANTOWL(26, "Giant Owl"),
    GNOLL(27, "Gnoll"),
    OTYUGH(28, "Otyugh"),
    SAHUAGIN(29, "Sahuagin"),
    SPHINX(30, "Sphinx"),
    WINTERWOLF(31, "Winter Wolf"),
    WORG(32, "Worg"),
    BLINKDOG(33, "Blink Dog"),
    YETI(34, "Yeti"),
    ALL(35, "All"),
    AARAKOCRA(36, "Aarakocra"),
    SLAAD(37, "Slaad"),
    BULLYWUG(38, "Bullywug"),
    GITH(39, "Gith"),
    GRELL(40, "Grell"),
    HOOKHORROR(41, "Hook Horror"),
    MODRON(42, "Modron"),
    THRIKREEN(43, "Thri-kreen"),
    TROGLODYTE(44, "Troglodyte"),
    UMBERHULK(45, "Umber Hulk"),
    THIEVESCANT(46, "Thieves' Cant"),
    GRUNG(47, "Grung"),
    TLINCALLI(48, "Tlincalli"),
    VEGEPYGMY(49, "Vegepygmy"),
    YIKARIA(50, "Yikaria"),
    BOTHII(51, "Bothii"),
    IXITXACHITL(52, "Ixitxachitl"),
    THAYAN(53, "Thayan"),
    NETHERESE(54, "Netherese"),
    ICETOAD(55, "Ice Toad"),
    OLMAN(56, "Olman"),
    QUORI(57, "Quori"),
    MINOTAUR(58, "Minotaur"),
    LOXODON(59, "Loxodon"),
    KRAUL(60, "Kraul"),
    VEDALKEN(61, "Vedalken"),
    DAELKYR(62, "Daelkyr"),
    RIEDRAN(64, "Riedran"),
    ZEMNIAN(66, "Zemnian"),
    MARQUESIAN(67, "Marquesian"),
    NAUSH(68, "Naush"),
    LEONIN(69, "Leonin"),
    GRIPPLI(70, "Grippli"),
    SKITTERWIDGET(71, "Skitterwidget"),
    ZIKLIGHT(72, "Ziklight"),
    KRUTHIK(73, "Kruthik"),
    CITLANÉS(74, "Citlanés"),
    DJAYNAIAN(75, "Djaynaian"),
    GODSTONGUE(76, "Godstongue"),
    HALRI(77, "Halri"),
    MAYNAH(78, "Maynah"),
    NWARIAN(79, "N’warian"),
    QUIRAPU(80, "Quirapu"),
    SENSAN(81, "Sensan"),
    SHANKHI(82, "Shankhi"),
    TLETLAHTOLLI(83, "Tletlahtolli"),
    XINGYU(84, "Xingyu"),
    ZABAANI(85, "Zabaani"),
    DOHWAR(86, "Dohwar"),
    HADOZEE(87, "Hadozee"),
    AARTUK(88, "Aartuk"),
    ABANASINIAN(89, "Abanasinian"),
    ERGOT(90, "Ergot"),
    ISTARIAN(91, "Istarian"),
    KENDERSPEAK(92, "Kenderspeak"),
    KHAROLIAN(93, "Kharolian"),
    KHUR(94, "Khur"),
    KOTHIAN(95, "Kothian"),
    NERAKESE(96, "Nerakese"),
    NORDMAARIAN(97, "Nordmaarian"),
    OGRE(98, "Ogre"),
    SOLAMNIC(99, "Solamnic"),
    UNKNOWN(0, "Unknown")
}

class WeaponCategories {
    companion object {
        fun getWeaponCategoryById(id: Int): WeaponCategory? {
            return when (id) {
                in 1.. WeaponCategory.values().size -> WeaponCategory.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class WeaponCategory(val id: Int, val label: String, val entityTypeId: Int) {
    SIMPLE(1, "Simple", 660121713),
    MARTIAL(2, "Martial", 660121713),
    FIREARMS(3, "Firearms", 660121713)
}

class SpellComponents {
    companion object {
        fun getSpellComponentById(id: Int): SpellComponent? {
            return when (id) {
                in 1.. SpellComponent.values().size -> SpellComponent.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class SpellComponent(val id: Int, val label: String, val shortName: String, val description: String) {
    VERBAL(1, "Verbal", "V", "Most spells require the chanting of mystic words. The words themselves aren't the source of the spell's power; rather, the particular combination of sounds, with specific pitch and resonance, sets the threads of magic in motion. Thus, a character who is gagged or in an area of silence, such as one created by the silence spell, can't cast a spell with a verbal component."),
    SOMATIC(2, "Somatic", "S", "Spellcasting gestures might include a forceful gesticulation or an intricate set of gestures. If a spell requires a somatic component, the caster must have free use of at least one hand to perform these gestures."),
    MATERIAL(3, "Material", "M", "Casting some spells requires particular objects, specified in parentheses in the component entry. A character can use a component pouch or a spellcasting focus (found in “Equipment”) in place of the components specified for a spell. But if a cost is indicated for a component, a character must have that specific component before he or she can cast the spell. If a spell states that a material component is consumed by the spell, the caster must provide this component for each casting of the spell. A spellcaster must have a hand free to access a spell's material components--or to hold a spellcasting focus--but it can be the same hand that he or she uses to perform somatic components."),
    ROYALTY(4, "Royalty", "R", "To cast a spell that employs a royalty component (including using a spell scroll or other magic item that stores such a spell), a caster must have sufficient funds on their person. The cost of the casting is set by the caster who creates the spell, but is typically 1 gp per spell slot level. When the spell is cast, the royalty is magically transported to a coffer or other object designated by the creating spellcaster. This payment is made whether the caster using the spell is aware of the royalty component or not. If the caster does not have sufficient funds, the spell is not lost but it cannot be cast.Though many casters have tried to circumvent the royalty component, none have ever fully succeeded. However, it is said that a character can attempt a DC 15 Intelligence (Arcana) check while casting a spell with a royalty component. With a successful check, the payment is taken from a random creature within 10 feet of the caster, without that creature’s knowledge.")
}

class ActivationTypes {
    companion object {
        fun getActivationTypeById(id: Int): ActivationType? {
            return when (id) {
                in 1.. ActivationType.values().size -> ActivationType.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class ActivationType(val id: Int, val label: String, val prerequisite: Int?, val description: String, val requiredLevel: Int?, val displayOrder: Int?) {
    ACTION(1, "Action", null, "", null, null),
    NOACTION(2, "No Action", null, "", null, null),
    BONUSACTION(3, "Bonus Action", null, "", null, null),
    REACTION(4, "Reaction", null, "", null, null),
    MINUTE(6, "Minute", null, "", null, null),
    HOUR(7, "Hour", null, "", null, null),
    SPECIAL(8, "Special", null, "", null, null)
}

class BasicActions {
    companion object {
        fun getBasicActionById(id: Int): BasicAction? {
            return when (id) {
                in 1.. BasicAction.values().size -> BasicAction.values()[id - 1]
                else -> null
            }
        }
    }
}

data class Activation(val time: Int, val type: Int)

enum class BasicAction(val id: Int, val label: String, val description: String, val activation: Activation?) {
    ATTACK(1, "Attack", "The most common action to take in combat is the Attack action, whether you are swinging a sword, firing an arrow from a bow, or brawling with your fists.With this action, you make one melee or ranged attack. See the \"Making an Attack\" section for the rules that govern attacks.Certain features, such as the Extra Attack feature of the fighter, allow you to make more than one attack with this action.", Activation(0, 1)),
    CASTASPELL(2, "Cast a Spell", "Spellcasters such as wizards and clerics, as well as many monsters, have access to spells and can use them to great effect in combat. Each spell has a casting time, which specifies whether the caster must use an action, a reaction, minutes, or even hours to cast the spell. Casting a spell is, therefore, not necessarily an action. Most spells do have a casting time of 1 action, so a spellcaster often uses his or her action in combat to cast such a spell.", Activation(0, 1)),
    DASH(4, "Dash", "When you take the Dash action, you gain extra movement for the current turn. The increase equals your speed, after applying any modifiers. With a speed of 30 feet, for example, you can move up to 60 feet on your turn if you dash.Any increase or decrease to your speed changes this additional movement by the same amount. If your speed of 30 feet is reduced to 15 feet, for instance, you can move up to 30 feet this turn if you dash.", Activation(0, 1)),
    DISENGAGE(5, "Disengage", "If you take the Disengage action, your movement doesn't provoke opportunity attacks for the rest of the turn.", Activation(0, 1)),
    DODGE(6, "Dodge", "When you take the Dodge action, you focus entirely on avoiding attacks. Until the start of your next turn, any attack roll made against you has disadvantage if you can see the attacker, and you make Dexterity saving throws with advantage. You lose this benefit if you are incapacitated or if your speed drops to 0.", Activation(0, 1)),
    HELP(7, "Help", "You can lend your aid to another creature in the completion of a task. When you take the Help action, the creature you aid gains advantage on the next ability check it makes to perform the task you are helping with, provided that it makes the check before the start of your next turn.Alternatively, you can aid a friendly creature in attacking a creature within 5 feet of you. You feint, distract the target, or in some other way team up to make your ally's attack more effective. If your ally attacks the target before your next turn, the first attack roll is made with advantage.", Activation(0, 1)),
    HIDE(8, "Hide", "When you take the Hide action, you make a Dexterity (Stealth) check in an attempt to hide, following the rules for hiding. If you succeed, you gain certain benefits, as described in the \"Unseen Attackers and Targets\" section later in this chapter.", Activation(0, 1)),
    READY(9, "Ready", "Sometimes you want to get the jump on a foe or wait for a particular circumstance before you act. To do so, you can take the Ready action on your turn, which lets you act using your reaction before the start of your next turn.First, you decide what perceivable circumstance will trigger your reaction. Then, you choose the action you will take in response to that trigger, or you choose to move up to your speed in response to it. Examples include \"If the cultist steps on the trapdoor, I'll pull the lever that opens it,\" and \"If the goblin steps next to me, I move away.\"When the trigger occurs, you can either take your reaction right after the trigger finishes or ignore the trigger. Remember that you can take only one reaction per round.When you ready a spell, you cast it as normal but hold its energy, which you release with your reaction when the trigger occurs. To be readied, a spell must have a casting time of 1 action, and holding onto the spell's magic requires concentration. If your concentration is broken, the spell dissipates without taking effect. For example, if you are concentrating on the web spell and ready magic missile, your web spell ends, and if you take damage before you release magic missile with your reaction, your concentration might be broken.", Activation(0, 1)),
    SEARCH(10, "Search", "When you take the Search action, you devote your attention to finding something. Depending on the nature of your search, the GM might have you make a Wisdom (Perception) check or an Intelligence (Investigation) check.", Activation(0, 1)),
    USEANOBJECT(11, "Use an Object", "You normally interact with an object while doing something else, such as when you draw a sword as part of an attack. When an object requires your action for its use, you take the Use an Object action. This action is also useful when you want to interact with more than one object on your turn.", Activation(0, 1)),
    OPPORTUNITYATTACK(1001, "Opportunity Attack", "You can make an opportunity attack when a hostile creature that you can see moves out of your reach. To make the opportunity attack, you use your reaction to make one melee attack against the provoking creature. The attack occurs right before the creature leaves your reach", Activation(0, 4)),
    GRAPPLE(1002, "Grapple", "When you want to grab a creature or wrestle with it, you can use the Attack action to make a special melee attack, a grapple. If you're able to make multiple attacks with the Attack action, this attack replaces one of them.The target of your grapple must be no more than one size larger than you and must be within your reach. Using at least one free hand, you try to seize the target by making a grapple check instead of an attack roll: a Strength (Athletics) check contested by the target's Strength (Athletics) or Dexterity (Acrobatics) check (the target chooses the ability to use). If you succeed, you subject the target to the grappled condition. The condition specifies the things that end it, and you can release the target whenever you like (no action required).Escaping a Grapple. A grappled creature can use its action to escape. To do so, it must succeed on a Strength (Athletics) or Dexterity (Acrobatics) check contested by your Strength (Athletics) check.Moving a Grappled Creature. When you move, you can drag or carry the grappled creature with you, but your speed is halved, unless the creature is two or more sizes smaller than you.", Activation(0, 1)),
    SHOVE(1003, "Shove", "Using the Attack action, you can make a special melee attack to shove a creature, either to knock it prone or push it away from you. If you're able to make multiple attacks with the Attack action, this attack replaces one of them.The target must be no more than one size larger than you and must be within your reach. Instead of making an attack roll, you make a Strength (Athletics) check contested by the target's Strength (Athletics) or Dexterity (Acrobatics) check (the target chooses the ability to use). You succeed automatically if the target is incapacitated. If you succeed, you either knock the target prone or push it 5 feet away from you.", Activation(0, 1)),
    IMPROVISE(1004, "Improvise", "Your character can do things not covered by the actions in this chapter, such as breaking down doors, intimidating enemies, sensing weaknesses in magical defenses, or calling for a parley with a foe. The only limits to the actions you can attempt are your imagination and your character's ability scores. See the descriptions of the ability scores in chapter 7 for inspiration as you improvise.When you describe an action not detailed elsewhere in the rules, the DM tells you whether that action is possible and what kind of roll you need to make, if any, to determine success or failure.", Activation(0, 1)),
    TWOWEAPONFIGHTING(1005, "Two-Weapon Fighting", "When you take the Attack action and attack with a light melee weapon that you're holding in one hand, you can use a bonus action to attack with a different light melee weapon that you're holding in the other hand. You don't add your ability modifier to the damage of the bonus attack, unless that modifier is negative.If either weapon has the thrown property, you can throw the weapon, instead of making a melee attack with it.", Activation(0, 3)),
    INTERACTWITHANOBJECT(1006, "Interact with an Object", "Here are a few examples of the sorts of thing you can do in tandem with your movement and action:draw or sheathe a swordopen or close a doorwithdraw a potion from your backpackpick up a dropped axetake a bauble from a tableremove a ring from your fingerstuff some food into your mouthplant a banner in the groundfish a few coins from your belt pouchdrink all the ale in a flagonthrow a lever or a switchpull a torch from a sconcetake a book from a shelf you can reachextinguish a small flamedon a maskpull the hood of your cloak up and over your headput your ear to a doorkick a small stoneturn a key in a locktap the floor with a 10-foot polehand an item to another character", Activation(0, 8))
}

class Rules {
    companion object {
        fun getRuleById(id: Int): Rule? {
            return when (id) {
                in 1.. Rule.values().size -> Rule.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class Rule(val id: Int, val label: String, val description: String) {
    RACE(34, "Race", "Your choice of race affects many different aspects of your character. It establishes fundamental qualities that exist throughout your character’s adventuring career. When making this decision, keep in mind the kind of character you want to play. For example, a halfling could be a good choice for a sneaky rogue, a dwarf makes a tough warrior, and an elf can be a master of arcane magic."),
    CLASS(35, "Class", "Class is the primary definition of what your character can do. It’s more than a profession; it’s your character’s calling. Class shapes the way you think about the world and interact with it and your relationship with other people and powers in the multiverse. A fighter, for example, might view the world in pragmatic terms of strategy and maneuvering, and see herself as just a pawn in a much larger game. A cleric, by contrast, might see himself as a willing servant in a god’s unfolding plan or a conflict brewing among various deities. While the fighter has contacts in a mercenary company or army, the cleric might know a number of priests, paladins, and devotees who share his faith."),
    LEVEL(36, "Level", "Starting off at 1st level marks your character’s entry into the adventuring life. As your character goes on adventures and overcomes challenges, he or she gains experience, represented by experience points. A character who reaches a specified experience point total advances in capability. This advancement is called gaining a level."),
    EXPERIENCEPOINTS(37, "Experience Points", "As your character goes on adventures and overcomes challenges, he or she gains experience, represented by experience points. A character who reaches a specified experience point total advances in capability. This advancement is called gaining a level."),
    HITPOINTS(38, "Hit Points", "Hit points represent a combination of physical and mental durability, the will to live, and luck. Creatures with more hit points are more difficult to kill. Those with fewer hit points are more fragile. A creature's current hit points (usually just called hit points) can be any number from the creature's hit point maximum down to 0. This number changes frequently as a creature takes damage or receives healing. Whenever a creature takes damage, that damage is subtracted from its hit points. The loss of hit points has no effect on a creature's capabilities until the creature drops to 0 hit points."),
    HITDICE(39, "Hit Dice", "Your hit points are determined by your Hit Dice (short for Hit Point Dice).At 1st level, your character has 1 Hit Die, and the die type is determined by your class. You start with hit points equal to the highest roll of that die, as indicated in your class description. (You also add your Constitution modifier.)"),
    PROFICIENCYBONUS(40, "Proficiency Bonus", "Characters have a proficiency bonus determined by level, as detailed in chapter 1. Monsters also have this bonus, which is incorporated in their stat blocks. The bonus is used in the rules on ability checks, saving throws, and attack rolls.Your proficiency bonus can’t be added to a single die roll or other number more than once. For example, if two different rules say you can add your proficiency bonus to a Wisdom saving throw, you nevertheless add the bonus only once when you make the save.Occasionally, your proficiency bonus might be multiplied or divided (doubled or halved, for example) before you apply it. For example, the rogue’s Expertise feature doubles the proficiency bonus for certain ability checks. If a circumstance suggests that your proficiency bonus applies more than once to the same roll, you still add it only once and multiply or divide it only once.By the same token, if a feature or effect allows you to multiply your proficiency bonus when making an ability check that wouldn’t normally benefit from your proficiency bonus, you still don’t add the bonus to the check. For that check your proficiency bonus is 0, given the fact that multiplying 0 by any number is still 0. For instance, if you lack proficiency in the History skill, you gain no benefit from a feature that lets you double your proficiency bonus when you make Intelligence (History) checks.In general, you don’t multiply your proficiency bonus for attack rolls or saving throws. If a feature or effect allows you to do so, these same rules apply."),
    ABILITYSCORES(41, "Ability Scores", "Each of a creature's abilities has a score, a number that defines the magnitude of that ability. An ability score is not just a measure of innate capabilities, but also encompasses a creature's training and competence in activities related to that ability. A score of 10 or 11 is the normal human average, but adventurers and many monsters are a cut above average in most abilities. A score of 18 is the highest that a person usually reaches. Adventurers can have scores as high as 20, and monsters and divine beings can have scores as high as 30. Each ability also has a modifier, derived from the score and ranging from -5 (for an ability score of 1) to +10 (for a score of 30). The Ability Scores and Modifiers table notes the ability modifiers for the range of possible ability scores, from 1 to 30."),
    ALIGNMENT(42, "Alignment", "A typical creature in the game world has an alignment, which broadly describes its moral and personal attitudes. Alignment is a combination of two factors: one identifies morality (good, evil, or neutral), and the other describes attitudes toward society and order (lawful, chaotic, or neutral). Thus, nine distinct alignments define the possible combinations."),
    LAWFULGOOD(43, "Lawful Good", "Lawful good (LG) creatures can be counted on to do the right thing as expected by society. Gold dragons, paladins, and most dwarves are lawful good."),
    NEUTRALGOOD(44, "Neutral Good", "Neutral good (NG) folk do the best they can to help others according to their needs. Many celestials, some cloud giants, and most gnomes are neutral good."),
    CHAOTICGOOD(45, "Chaotic Good", "Chaotic good (CG) creatures act as their conscience directs, with little regard for what others expect. Copper dragons, many elves, and unicorns are chaotic good."),
    NEUTRAL(46, "Neutral", "Neutral (N) is the alignment of those who prefer to steer clear of moral questions and don’t take sides, doing what seems best at the time. Lizardfolk, most druids, and many humans are neutral."),
    LAWFULEVIL(47, "Lawful Evil", "Lawful evil (LE) creatures methodically take what they want, within the limits of a code of tradition, loyalty, or order. Devils, blue dragons, and hobgoblins are lawful evil."),
    NEUTRALEVIL(48, "Neutral Evil", "Neutral evil (NE) is the alignment of those who do whatever they can get away with, without compassion or qualms. Many drow, some cloud giants, and goblins are neutral evil."),
    CHAOTICEVIL(49, "Chaotic Evil", "Chaotic evil (CE) creatures act with arbitrary violence, spurred by their greed, hatred, or bloodlust. Demons, red dragons, and orcs are chaotic evil."),
    LAWFULNEUTRAL(50, "Lawful Neutral", "Lawful neutral (LN) individuals act in accordance with law, tradition, or personal codes. Many monks and some wizards are lawful neutral."),
    CHAOTICNEUTRAL(51, "Chaotic Neutral", "Chaotic neutral (CN) creatures follow their whims, holding their personal freedom above all else. Many barbarians and rogues, and some bards, are chaotic neutral."),
    BACKGROUND(52, "Background", "Every story has a beginning. Your character’s background reveals where you came from, how you became an adventurer, and your place in the world. Your fighter might have been a courageous knight or a grizzled soldier. Your wizard could have been a sage or an artisan. Your rogue might have gotten by as a guild thief or commanded audiences as a jester."),
    PERSONALITYTRAITS(53, "Personality Traits", "Personality traits are small, simple ways to help you set your character apart from every other character. Your personality traits should tell you something interesting and fun about your character. They should be self-descriptions that are specific about what makes your character stand out.Personality traits might describe the things your character likes, his or her past accomplishments, things your character dislikes or fears, your character’s self-attitude or mannerisms, or the influence of his or her ability scores."),
    BONDS(54, "Bonds", "Bonds represent a character’s connections to people, places, and events in the world. They tie you to things from your background. They might inspire you to heights of heroism, or lead you to act against your own best interests if they are threatened. They can work very much like ideals, driving a character’s motivations and goals. Bonds might answer any of these questions: Whom do you care most about? To what place do you feel a special connection? What is your most treasured possession?"),
    IDEALS(55, "Ideals", "Your ideals are the things that you believe in most strongly, the fundamental moral and ethical principles that compel you to act as you do. Ideals encompass everything from your life goals to your core belief system. Ideals might answer any of these questions: What are the principles that you will never betray? What would prompt you to make sacrifices? What drives you to act and guides your goals and ambitions? What is the single most important thing you strive for?"),
    FLAWS(56, "Flaws", "Your character’s flaw represents some vice, compulsion, fear, or weakness—in particular, anything that someone else could exploit to bring you to ruin or cause you to act against your best interests. More significant than negative personality traits, a flaw might answer any of these questions: What enrages you? What’s the one person, concept, or event that you are terrified of? What are your vices?"),
    ARMORCLASS(57, "Armor Class", "Your Armor Class (AC) represents how well your character avoids being wounded in battle. Things that contribute to your AC include the armor you wear, the shield you carry, and your Dexterity modifier. Not all characters wear armor or carry shields, however. Without armor or a shield, your character’s AC equals 10 + his or her Dexterity modifier. If your character wears armor, carries a shield, or both, calculate your AC using the rules in the Equipment section. Record your AC on your character sheet."),
    ACIDDAMAGE(58, "Acid Damage", "The corrosive spray of an adult black dragon's breath and the dissolving enzymes secreted by a black pudding deal acid damage."),
    BLUDGEONINGDAMAGE(59, "Bludgeoning Damage", "Blunt force attacks--hammers, falling, constriction, and the like--deal bludgeoning damage."),
    COLDDAMAGE(60, "Cold Damage", "The infernal chill radiating from an ice devil's spear and the frigid blast of a young white dragon's breath deal cold damage."),
    FIREDAMAGE(61, "Fire Damage", "Ancient red dragons breathe fire, and many spells conjure flames to deal fire damage."),
    FORCEDAMAGE(62, "Force Damage", "Force is pure magical energy focused into a damaging form. Most effects that deal force damage are spells, including magic missile and spiritual weapon."),
    LIGHTNINGDAMAGE(63, "Lightning Damage", "A lightning bolt spell and a blue dragon wyrmling's breath deal lightning damage."),
    NECROTICDAMAGE(64, "Necrotic Damage", "Necrotic damage, dealt by certain undead and a spell such as chill touch, withers matter and even the soul."),
    PIERCINGDAMAGE(65, "Piercing Damage", "Puncturing and impaling attacks, including spears and monsters' bites, deal piercing damage."),
    POISONDAMAGE(66, "Poison Damage", "Venomous stings and the toxic gas of an adult green dragon's breath deal poison damage."),
    PSYCHICDAMAGE(67, "Psychic Damage", "Mental abilities such as a psionic blast deal psychic damage."),
    RADIANTDAMAGE(68, "Radiant Damage", "Radiant damage, dealt by a cleric's flame strike spell or an angel's smiting weapon, sears the flesh like fire and overloads the spirit with power."),
    SLASHINGDAMAGE(69, "Slashing Damage", "Swords, axes, and monsters' claws deal slashing damage."),
    THUNDERDAMAGE(70, "Thunder Damage", "A concussive burst of sound, such as the effect of the thunderwave spell, deals thunder damage."),
    CRITICALHITS(71, "Critical Hits", "When you score a critical hit, you get to roll extra dice for the attack's damage against the target. Roll all of the attack's damage dice twice and add them together. Then add any relevant modifiers as normal. To speed up play, you can roll all the damage dice at once."),
    DAMAGERESISTANCE(72, "Damage Resistance", "Some creatures and objects are exceedingly difficult to hurt with certain types of damage.If a creature or an object has resistance to a damage type, damage of that type is halved against it.Resistance is applied after all other modifiers to damage. For example, a creature has resistance to bludgeoning damage and is hit by an attack that deals 25 bludgeoning damage. The creature is also within a magical aura that reduces all damage by 5. The 25 damage is first reduced by 5 and then halved, so the creature takes 10 damage.Multiple instances of resistance that affect the same damage type count as only one instance. For example, if a creature has resistance to fire damage as well as resistance to all nonmagical damage, the damage of a nonmagical fire is reduced by half against the creature, not reduced by three-quarters."),
    DAMAGEVULNERABILITY(73, "Damage Vulnerability", "Some creatures and objects are unusually easy to hurt with certain types of damage. If a creature or an object has vulnerability to a damage type, damage of that type is doubled against it. Multiple instances of vulnerability that affect the same damage type count as only one instance."),
    INSTANTDEATH(74, "Instant Death", "Massive damage can kill you instantly. When damage reduces you to 0 hit points and there is damage remaining, you die if the remaining damage equals or exceeds your hit point maximum. For example, a cleric with a maximum of 12 hit points currently has 6 hit points. If she takes 18 damage from an attack, she is reduced to 0 hit points, but 12 damage remains. Because the remaining damage equals her hit point maximum, the cleric dies."),
    DEATHSAVINGTHROWS(75, "Death Saving Throws", "Whenever you start your turn with 0 hit points, you must make a special saving throw, called a death saving throw, to determine whether you creep closer to death or hang onto life. Unlike other saving throws, this one isn't tied to any ability score. You are in the hands of fate now, aided only by spells and features that improve your chances of succeeding on a saving throw. Roll a d20. If the roll is 10 or higher, you succeed. Otherwise, you fail. A success or failure has no effect by itself. On your third success, you become stable (see below). On your third failure, you die. The successes and failures don't need to be consecutive; keep track of both until you collect three of a kind. The number of both is reset to zero when you regain any hit points or become stable."),
    TEMPORARYHITPOINTS(76, "Temporary Hit Points", "Some spells and special abilities confer temporary hit points to a creature. Temporary hit points aren't actual hit points; they are a buffer against damage, a pool of hit points that protect you from injury. When you have temporary hit points and take damage, the temporary hit points are lost first, and any leftover damage carries over to your normal hit points. For example, if you have 5 temporary hit points and take 7 damage, you lose the temporary hit points and then take 2 damage. Because temporary hit points are separate from your actual hit points, they can exceed your hit point maximum. A character can, therefore, be at full hit points and receive temporary hit points."),
    SAVINGTHROWS(77, "Saving Throws", "A saving throw — also called a save — represents an attempt to resist a spell, a trap, a poison, a disease, or a similar threat. You don’t normally decide to make a saving throw; you are forced to make one because your character or monster is at risk of harm.To make a saving throw, roll a d20 and add the appropriate ability modifier. For example, you use your Dexterity modifier for a Dexterity saving throw.A saving throw can be modified by a situational bonus or penalty and can be affected by advantage and disadvantage, as determined by the DM.Each class gives proficiency in at least two saving throws. The wizard, for example, is proficient in Intelligence saves. As with skill proficiencies, proficiency in a saving throw lets a character add his or her proficiency bonus to saving throws made using a particular ability score. Some monsters have saving throw proficiencies as well.The Difficulty Class for a saving throw is determined by the effect that causes it. For example, the DC for a saving throw allowed by a spell is determined by the caster’s spellcasting ability and proficiency bonus.The result of a successful or failed saving throw is also detailed in the effect that allows the save. Usually, a successful save means that a creature suffers no harm, or reduced harm, from an effect."),
    SENSES_PASSIVECHECKS(78, "Senses (Passive Checks)", "Passive ChecksA passive check is a special kind of ability check that doesn't involve any die rolls. Such a check can represent the average result for a task done repeatedly, such as searching for secret doors over and over again, or can be used when the DM wants to secretly determine whether the characters succeed at something without rolling dice, such as noticing a hidden monster.Here's how to determine a character's total for a passive check:10 + all modifiers that normally apply to the checkIf the character has advantage on the check, add 5. For disadvantage, subtract 5. The game refers to a passive check total as a score.For example, if a 1st-level character has a Wisdom of 15 and proficiency in Perception, he or she has a passive Wisdom (Perception) score of 14.The rules on hiding in the “Dexterity” section below rely on passive checks, as do the exploration rules in chapter 8, “Adventuring.”"),
    INITIATIVE(79, "Initiative", "Initiative determines the order of turns during combat. When combat starts, every participant makes a Dexterity check to determine their place in the initiative order. The DM makes one roll for an entire group of identical creatures, so each member of the group acts at the same time.The DM ranks the combatants in order from the one with the highest Dexterity check total to the one with the lowest. This is the order (called the initiative order) in which they act during each round. The initiative order remains the same from round to round.If a tie occurs, the DM decides the order among tied DM-controlled creatures, and the players decide the order among their tied characters. The DM can decide the order if the tie is between a monster and a player character. Optionally, the DM can have the tied characters and monsters each roll a d20 to determine the order, highest roll going first."),
    INSPIRATION(80, "Inspiration", "Inspiration is a rule the game master can use to reward you for playing your character in a way that’s true to his or her personality traits, ideal, bond, and flaw. By using inspiration, you can draw on your personality trait of compassion for the downtrodden to give you an edge in negotiating with the Beggar Prince. Or inspiration can let you call on your bond to the defense of your home village to push past the effect of a spell that has been laid on you.Gaining InspirationYour DM can choose to give you inspiration for a variety of reasons. Typically, DMs award it when you play out your personality traits, give in to the drawbacks presented by a flaw or bond, and otherwise portray your character in a compelling way. Your DM will tell you how you can earn inspiration in the game.You either have inspiration or you don’t - you can’t stockpile multiple “inspirations” for later use.Using InspirationIf you have inspiration, you can expend it when you make an attack roll, saving throw, or ability check. Spending your inspiration gives you advantage on that roll.Additionally, if you have inspiration, you can reward another player for good roleplaying, clever thinking, or simply doing something exciting in the game. When another player character does something that really contributes to the story in a fun and interesting way, you can give up your inspiration to give that character inspiration."),
    SPEED(81, "Speed", "Every character has a speed, which is the distance in feet that the character can walk in 1 round. This number assumes short bursts of energetic movement in the midst of a life-threatening situation.While climbing or swimming, each foot of movement costs 1 extra foot (2 extra feet in difficult terrain), unless a creature has a climbing or swimming speed. At the DM’s option, climbing a slippery vertical surface or one with few handholds requires a successful Strength (Athletics) check. Similarly, gaining any distance in rough water might require a successful Strength (Athletics) check.Your Strength determines how far you can jump.Long Jump.&nbsp;When you make a long jump, you cover a number of feet up to your Strength score if you move at least 10 feet on foot immediately before the jump. When you make a standing long jump, you can leap only half that distance. Either way, each foot you clear on the jump costs a foot of movement.This rule assumes that the height of your jump doesn't matter, such as a jump across a stream or chasm. At your DM's option, you must succeed on a DC 10 Strength (Athletics) check to clear a low obstacle (no taller than a quarter of the jump's distance), such as a hedge or low wall. Otherwise, you hit it.When you land in difficult terrain, you must succeed on a DC 10 Dexterity (Acrobatics) check to land on your feet. Otherwise, you land prone.High Jump.&nbsp;When you make a high jump, you leap into the air a number of feet equal to 3 + your Strength modifier (minimum of 0 feet) if you move at least 10 feet on foot immediately before the jump. When you make a standing high jump, you can jump only half that distance. Either way, each foot you clear on the jump costs a foot of movement. In some circumstances, your DM might allow you to make a Strength (Athletics) check to jump higher than you normally can.You can extend your arms half your height above yourself during the jump. Thus, you can reach above you a distance equal to the height of the jump plus 1 1/2 times your height."),
    SENSES(82, "Senses", "Passive ChecksA passive check is a special kind of ability check that doesn't involve any die rolls. Such a check can represent the average result for a task done repeatedly, such as searching for secret doors over and over again, or can be used when the DM wants to secretly determine whether the characters succeed at something without rolling dice, such as noticing a hidden monster.Special senses are described below.BlindsightA monster with blindsight can perceive its surroundings without relying on sight, within a specific radius.Creatures without eyes, such as grimlocks and gray oozes, typically have this special sense, as do creatures with echolocation or heightened senses, such as bats and true dragons.If a monster is naturally blind, it has a parenthetical note to this effect, indicating that the radius of its blindsight defines the maximum range of its perception.DarkvisionA monster with darkvision can see in the dark within a specific radius. The monster can see in dim light within the radius as if it were bright light, and in darkness as if it were dim light. The monster can't discern color in darkness, only shades of gray. Many creatures that live underground have this special sense.TremorsenseA monster with tremorsense can detect and pinpoint the origin of vibrations within a specific radius, provided that the monster and the source of the vibrations are in contact with the same ground or substance.Tremorsense can't be used to detect flying or incorporeal creatures. Many burrowing creatures, such as ankhegs, have this special sense.TruesightA monster with truesight can, out to a specific range, see in normal and magical darkness, see invisible creatures and objects, automatically detect visual illusions and succeed on saving throws against them, and perceive the original form of a shapechanger or a creature that is transformed by magic. Furthermore, the monster can see into the Ethereal Plane within the same range."),
    HALFCOVER(83, "Half Cover", "A target with half cover has a +2 bonus to AC and Dexterity saving throws. A target has half cover if an obstacle blocks at least half of its body. The obstacle might be a low wall, a large piece of furniture, a narrow tree trunk, or a creature, whether that creature is an enemy or a friend."),
    THREEQUARTERSCOVER(84, "Three-Quarters Cover", "A target with three-quarters cover has a +5 bonus to AC and Dexterity saving throws. A target has three-quarters cover if about three-quarters of it is covered by an obstacle. The obstacle might be a portcullis, an arrow slit, or a thick tree trunk."),
    TOTALCOVER(85, "Total Cover", "A target with total cover cannot be targeted directly by an attack or a spell, although some spells can reach such a target by including it in an area of effect. A target has total cover if it is completely concealed by an obstacle."),
    DIFFICULTTERRAIN(86, "Difficult Terrain", "You move at half speed in difficult terrain--moving 1 foot in difficult terrain costs 2 feet of speed--so you can cover only half the normal distance in a minute, an hour, or a day."),
    FALLING(87, "Falling", "A fall from a great height is one of the most common hazards facing an adventurer. At the end of a fall, a creature takes 1d6 bludgeoning damage for every 10 feet it fell, to a maximum of 20d6. The creature lands prone, unless it avoids taking damage from the fall."),
    SUFFOCATING(88, "Suffocating", "A creature can hold its breath for a number of minutes equal to 1 + its Constitution modifier (minimum of 30 seconds). When a creature runs out of breath or is choking, it can survive for a number of rounds equal to its Constitution modifier (minimum of 1 round). At the start of its next turn, it drops to 0 hit points and is dying, and it cannot regain hit points or be stabilized until it can breathe again."),
    LIGHTLYOBSCURED(89, "Lightly Obscured", "In a lightly obscured area, such as dim light, patchy fog, or moderate foliage, creatures have disadvantage on Wisdom (Perception) checks that rely on sight."),
    HEAVILYOBSCURED(90, "Heavily Obscured", "A heavily obscured area--such as darkness, opaque fog, or dense foliage--blocks vision entirely. A creature effectively suffers from the blinded condition when trying to see something in that area."),
    CLIMBINGSWIMMINGCRAWLING(91, "Climbing Swimming Crawling", "Each foot of movement costs 1 extra foot (2 extra feet in difficult terrain) when you’re climbing, swimming, or crawling. You ignore this extra cost if you have a climbing speed and use it to climb or a swimming speed and use it to swim. At the DM’s option, climbing a slippery vertical surface or one with few handholds requires a successful Strength (Athletics) check. Similarly, gaining any distance in rough water might require a successful Strength (Athletics) check."),
    SURPRISE(92, "Surprise", "If you are surprised, you cannot move or take an action on your first turn of the combat, and you cannot take a reaction until that turn ends. A member of a group can be surprised even if the other members are not."),
    FLYING(93, "Flying", "Flying creatures enjoy many benefits of mobility, but they must also deal with the danger of falling. If a flying creature is knocked prone, has its speed reduced to 0, or is otherwise deprived of the ability to move, the creature falls, unless it has the ability to hover or it is being held aloft by magic, such as by the fly spell."),
    UNDERWATER(94, "Underwater", "When making a melee weapon attack, a creature that does not have a swimming speed (either natural or granted by magic) has disadvantage on the attack roll unless the weapon is a dagger, javelin, shortsword, spear, or trident. A ranged weapon attack automatically misses a target beyond the weapon's normal range. Even against a target within normal range, the attack roll has disadvantage unless the weapon is a crossbow, a net, or a weapon that is thrown like a javelin (including a spear, trident, or dart). Creatures and objects that are fully immersed in water have resistance to fire damage."),
    ADVANTAGE(96, "Advantage", "Sometimes a special ability or spell tells you that you have advantage or disadvantage on an ability check, a saving throw, or an attack roll. When that happens, you roll a second d20 when you make the roll. Use the higher of the two rolls if you have advantage."),
    DISADVANTAGE(98, "Disadvantage", "Sometimes a special ability or spell tells you that you have advantage or disadvantage on an ability check, a saving throw, or an attack roll. When that happens, you roll a second d20 when you make the roll. Use the lower roll if you have disadvantage.")
}

class Armors {
    companion object {
        fun getArmorById(id: Int): Armor? {
            return when (id) {
                in 1.. Armor.values().size -> Armor.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class Armor(val id: Int, val label: String, val entityTypeId: Int, val description: String, val categoryId: Int) {
    STUDDEDLEATHER(3, "Studded Leather", 701257905, "Made from tough but flexible leather, studded leather is reinforced with close-set rivets or spikes.", 1),
    SCALEMAIL(6, "Scale Mail", 701257905, "This armor consists of a coat and leggings (and perhaps a separate skirt) of leather covered with overlapping pieces of metal, much like the scales of a fish. The suit includes gauntlets.", 2),
    SHIELD(8, "Shield", 701257905, "A shield is made from wood or metal and is carried in one hand. Wielding a shield increases your Armor Class by 2. You can benefit from only one shield at a time.", 4),
    PADDED(9, "Padded", 701257905, "Padded armor consists of quilted layers of cloth and batting.", 1),
    LEATHER(10, "Leather", 701257905, "The breastplate and shoulder protectors of this armor are made of leather that has been stiffened by being boiled in oil. The rest of the armor is made of softer and more flexible materials.", 1),
    HIDE(11, "Hide", 701257905, "This crude armor consists of thick furs and pelts. It is commonly worn by barbarian tribes, evil humanoids, and other folk who lack access to the tools and materials needed to create better armor.", 2),
    CHAINSHIRT(12, "Chain Shirt", 701257905, "Made of interlocking metal rings, a chain shirt is worn between layers of clothing or leather. This armor offers modest protection to the wearer's upper body and allows the sound of the rings rubbing against one another to be muffled by outer layers.", 2),
    BREASTPLATE(13, "Breastplate", 701257905, "This armor consists of a fitted metal chest piece worn with supple leather. Although it leaves the legs and arms relatively unprotected, this armor provides good protection for the wearer's vital organs while leaving the wearer relatively unencumbered.", 2),
    HALFPLATE(14, "Half Plate", 701257905, "Half plate consists of shaped metal plates that cover most of the wearer's body. It does not include leg protection beyond simple greaves that are attached with leather straps.", 2),
    RINGMAIL(15, "Ring Mail", 701257905, "This armor is leather armor with heavy rings sewn into it. The rings help reinforce the armor against blows from swords and axes. Ring mail is inferior to chain mail, and it's usually worn only by those who can't afford better armor.", 3),
    CHAINMAIL(16, "Chain Mail", 701257905, "Made of interlocking metal rings, chain mail includes a layer of quilted fabric worn underneath the mail to prevent chafing and to cushion the impact of blows. The suit includes gauntlets.", 3),
    SPLINT(17, "Splint", 701257905, "This armor is made of narrow vertical strips of metal riveted to a backing of leather that is worn over cloth padding. Flexible chain mail protects the joints.", 3),
    PLATE(18, "Plate", 701257905, "Plate consists of shaped, interlocking metal plates to cover the entire body. A suit of plate includes gauntlets, heavy leather boots, a visored helmet, and thick layers of padding underneath the armor. Buckles and straps distribute the weight over the body.", 3),
    SPIKEDARMOR(19, "Spiked Armor", 701257905, "Spiked armor is a rare type of medium armor made by dwarves. It consists of a leather coat and leggings covered with spikes that are usually made of metal.", 2),
    PRIDESILKOUTFIT(20, "Pride Silk Outfit", 701257905, "An outfit made of [item]pride silk[/item] weighs 4 pounds and costs 500 gp. If you aren’t wearing armor, your base Armor Class is 11 + your Dexterity modifier while wearing it.", 1)
}

class Tools {
    companion object {
        fun getToolById(id: Int): Tool? {
            return when (id) {
                in 1.. Tool.values().size -> Tool.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class Tool(val id: Int, val label: String, val description: String) {
    ALCHEMISTSSUPPLIES(102, "Alchemist's Supplies", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    BREWERSSUPPLIES(103, "Brewer's Supplies", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    CALLIGRAPHERSSUPPLIES(104, "Calligrapher's Supplies", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    CARPENTERSTOOLS(105, "Carpenter's Tools", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    CARTOGRAPHERSTOOLS(106, "Cartographer's Tools", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    COBBLERSTOOLS(107, "Cobbler's Tools", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    COOKSUTENSILS(108, "Cook's Utensils", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    GLASSBLOWERSTOOLS(109, "Glassblower's Tools", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    JEWELERSTOOLS(110, "Jeweler's Tools", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    LEATHERWORKERSTOOLS(111, "Leatherworker's Tools", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    MASONSTOOLS(112, "Mason's Tools", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    PAINTERSSUPPLIES(113, "Painter's Supplies", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    POTTERSTOOLS(114, "Potter's Tools", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    SMITHSTOOLS(115, "Smith's Tools", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    TINKERSTOOLS(116, "Tinker's Tools", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    WEAVERSTOOLS(117, "Weaver's Tools", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    WOODCARVERSTOOLS(118, "Woodcarver's Tools", "These special tools include the items needed to pursue a craft or trade. Proficiency with a set of artisan's tools lets you add your proficiency bonus to any ability checks you make using the tools in your craft. Each type of artisan's tools requires a separate proficiency."),
    DISGUISEKIT(119, "Disguise Kit", "This pouch of cosmetics, hair dye, and small props lets you create disguises that change your physical appearance. Proficiency with this kit lets you add your proficiency bonus to any ability checks you make to create a visual disguise."),
    FORGERYKIT(120, "Forgery Kit", "This small box contains a variety of papers and parchments, pens and inks, seals and sealing wax, gold and silver leaf, and other supplies necessary to create convincing forgeries of physical documents. Proficiency with this kit lets you add your proficiency bonus to any ability checks you make to create a physical forgery of a document."),
    DICESET(121, "Dice Set", "If you are proficient with a gaming set, you can add your proficiency bonus to ability checks you make to play a game with that set."),
    PLAYINGCARDSET(122, "Playing Card Set", "If you are proficient with a gaming set, you can add your proficiency bonus to ability checks you make to play a game with that set."),
    HERBALISMKIT(123, "Herbalism Kit", "This kit contains a variety of instruments such as clippers, mortar and pestle, and pouches and vials used by herbalists to create remedies and potions. Proficiency with this kit lets you add your proficiency bonus to any ability checks you make to identify or apply herbs. Also, proficiency with this kit is required to create [item]antitoxin[/item] and any [magicitem]potion of healing[/magicitem]."),
    NAVIGATORSTOOLS(124, "Navigator's Tools", "This set of instruments is used for navigation at sea. Proficiency with navigator's tools lets you chart a ship's course and follow navigation charts. In addition, these tools allow you to add your proficiency bonus to any ability check you make to avoid getting lost at sea."),
    POISONERSKIT(125, "Poisoner's Kit", "A poisoner's kit includes the vials, chemicals, and other equipment necessary for the creation of poisons. Proficiency with this kit lets you add your proficiency bonus to any ability checks you make to craft or use poisons."),
    THIEVESTOOLS(126, "Thieves' Tools", "This set of tools includes a small file, a set of lock picks, a small mirror mounted on a metal handle, a set of narrow-bladed scissors, and a pair of pliers. Proficiency with these tools lets you add your proficiency bonus to any ability checks you make to disarm traps or open locks."),
    BAGPIPES(127, "Bagpipes", "If you have proficiency with a given musical instrument, you can add your proficiency bonus to any ability checks you make to play music with the instrument. A bard can use bagpipes&nbsp;as a spellcasting focus."),
    DRUM(128, "Drum", "If you have proficiency with a given musical instrument, you can add your proficiency bonus to any ability checks you make to play music with the instrument. A bard can use a musical instrument as a spellcasting focus."),
    DULCIMER(129, "Dulcimer", "If you have proficiency with a given musical instrument, you can add your proficiency bonus to any ability checks you make to play music with the instrument. A bard can use a musical instrument as a spellcasting focus."),
    FLUTE(130, "Flute", "If you have proficiency with a given musical instrument, you can add your proficiency bonus to any ability checks you make to play music with the instrument. A bard can use a musical instrument as a spellcasting focus."),
    LUTE(131, "Lute", "If you have proficiency with a given musical instrument, you can add your proficiency bonus to any ability checks you make to play music with the instrument. A bard can use a musical instrument as a spellcasting focus."),
    HORN(132, "Horn", "If you have proficiency with a given musical instrument, you can add your proficiency bonus to any ability checks you make to play music with the instrument. A bard can use a musical instrument as a spellcasting focus."),
    PANFLUTE(133, "Pan Flute", "If you have proficiency with a given musical instrument, you can add your proficiency bonus to any ability checks you make to play music with the instrument.&nbsp;A bard can use a musical instrument as a spellcasting focus."),
    SHAWM(134, "Shawm", "If you have proficiency with a given musical instrument, you can add your proficiency bonus to any ability checks you make to play music with the instrument. A bard can use a musical instrument as a spellcasting focus."),
    LYRE(135, "Lyre", "If you have proficiency with a given musical instrument, you can add your proficiency bonus to any ability checks you make to play music with the instrument. A bard can use a musical instrument as a spellcasting focus."),
    VIOL(136, "Viol", "If you have proficiency with a given musical instrument, you can add your proficiency bonus to any ability checks you make to play music with the instrument. A bard can use a musical instrument as a spellcasting focus."),
    THREEDRAGONANTESET(189, "Three-Dragon Ante Set", "If you are proficient with a gaming set, you can add your proficiency bonus to ability checks you make to play a game with that set."),
    DRAGONCHESSSET(190, "Dragonchess Set", "If you are proficient with a gaming set, you can add your proficiency bonus to ability checks you make to play a game with that set."),
    BIRDPIPES(232, "Birdpipes", "Pan pipes or satyr pipes, also known as the shalm, these are sacred to Lliira and popular with wood elf and wild elf bards."),
    GLAUR(233, "Glaur", "Short, curved horns like a cornucopia. Played with valves, glaur sound like trumpets, while those without valves, known as gloon, have a more mournful sound."),
    HANDDRUM(234, "Hand Drum", "A double-headed skin drum fitted with handles along its side."),
    LONGHORN(235, "Longhorn", "A Faerûnian flute of sophisticated make, found only in areas with skilled artisans, as in great cities or elven enclaves."),
    SONGHORN(236, "Songhorn", "A recorder, a simple type of flute, usually carved from wood."),
    TANTAN(237, "Tantan", "A tambourine, a popular instrument with halflings and humans south of the Dalelands."),
    THELARR(238, "Thelarr", "Also known as a whistlecane, a simple and easy-to-make wind instrument cut from a reed. They are so simple, in fact, that skilled bards frequently make and give them away to children — to the parents’ delight or regret."),
    TOCKEN(239, "Tocken", "A hanging set of carved oval bells, usually played with a pair of light wooden hammers (or open handed). They are most common in underground cultures, where the resonant tones can carry."),
    WARGONG(240, "Wargong", "A metal gong, traditionally made from a shield, particularly the shield of an enemy. Both goblins and dwarves make and play wargongs, their sound echoing through tunnels in the Underdark."),
    YARTING(241, "Yarting", "A southern instrument from Amn and Calimshan that is a Faerûnian analog to the guitar. Numerous variations have spread across the continent."),
    ZULKOON(242, "Zulkoon", "A complex pump organ that originated with the zulkirs of Thay, who use it in the casting of their spells. It is considered to have a dramatic, but sinister, sound."),
    WHISTLESTICK(301, "Whistle-Stick", "The grung of One&nbsp;Grung Above&nbsp;are trained to use this new musical instrument. This is a hollow tube with holes cut throughout, much like a flute. You can play music with it for entertainment, but the grung also swing it about by a sturdy cord (attached) to create a sound recognizable by other grung, so they know each other’s approximate location. Additionally, grung that know Thieves’ Cant can use a whistle stick in this manner to communicate over distance.")
}

class Weapons {
    companion object {
        fun getWeaponById(id: Int): Weapon? {
            return when (id) {
                in 1.. Weapon.values().size -> Weapon.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class Weapon(val id: Int, val label: String, val entityTypeId: Int, val description: String, val categoryId: Int) {
    CROSSBOWHAND(1, "Crossbow, hand", 1782728300, "Proficiency with a hand crossbow allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    GLAIVE(2, "Glaive", 1782728300, "Proficiency with a glaive allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    DAGGER(3, "Dagger", 1782728300, "Proficiency with a dagger allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    LONGSWORD(4, "Longsword", 1782728300, "Proficiency with a longsword allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    CLUB(5, "Club", 1782728300, "Proficiency with a club allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    GREATCLUB(6, "Greatclub", 1782728300, "Proficiency with a greatclub allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    HANDAXE(7, "Handaxe", 1782728300, "Proficiency with a handaxe allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    JAVELIN(8, "Javelin", 1782728300, "Proficiency with a javelin allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    LIGHTHAMMER(10, "Light Hammer", 1782728300, "Proficiency with a light hammer allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    MACE(11, "Mace", 1782728300, "Proficiency with a mace allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    QUARTERSTAFF(12, "Quarterstaff", 1782728300, "Proficiency with a quarterstaff allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    SICKLE(13, "Sickle", 1782728300, "Proficiency with a sickle allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    SPEAR(14, "Spear", 1782728300, "Proficiency with a spear allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    CROSSBOWLIGHT(15, "Crossbow, light", 1782728300, "Proficiency with a light crossbow allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    DART(16, "Dart", 1782728300, "Proficiency with a dart allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    SHORTBOW(17, "Shortbow", 1782728300, "Proficiency with a shortbow allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    SLING(18, "Sling", 1782728300, "Proficiency with a sling allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 1),
    BATTLEAXE(19, "Battleaxe", 1782728300, "Proficiency with a battleaxe allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    FLAIL(20, "Flail", 1782728300, "Proficiency with a flail allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    GREATAXE(21, "Greataxe", 1782728300, "Proficiency with a greataxe allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    GREATSWORD(22, "Greatsword", 1782728300, "Proficiency with a greatsword allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    HALBERD(23, "Halberd", 1782728300, "Proficiency with a halberd allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    LANCE(24, "Lance", 1782728300, "Proficiency with a lance allows you to add your proficiency bonus to the attack roll for any attack you make with it.You have disadvantage when you use a lance to attack a target within 5 feet of you. Also, a lance requires two hands to wield when you aren't mounted.", 2),
    MAUL(25, "Maul", 1782728300, "Proficiency with a maul allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    MORNINGSTAR(26, "Morningstar", 1782728300, "Proficiency with a morningstar allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    PIKE(27, "Pike", 1782728300, "Proficiency with a pike allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    RAPIER(28, "Rapier", 1782728300, "Proficiency with a rapier allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    SCIMITAR(29, "Scimitar", 1782728300, "Proficiency with a scimitar allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    SHORTSWORD(30, "Shortsword", 1782728300, "Proficiency with a shortsword allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    TRIDENT(31, "Trident", 1782728300, "Proficiency with a trident allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    WARPICK(32, "War Pick", 1782728300, "Proficiency with a war pick allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    WARHAMMER(33, "Warhammer", 1782728300, "Proficiency with a warhammer allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    WHIP(34, "Whip", 1782728300, "Proficiency with a whip allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    BLOWGUN(35, "Blowgun", 1782728300, "Proficiency with a blowgun allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    CROSSBOWHEAVY(36, "Crossbow, heavy", 1782728300, "Proficiency with a heavy crossbow allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    LONGBOW(37, "Longbow", 1782728300, "Proficiency with a longbow allows you to add your proficiency bonus to the attack roll for any attack you make with it.", 2),
    NET(38, "Net", 1782728300, "Proficiency with a net allows you to add your proficiency bonus to the attack roll for any attack you make with it.A Large or smaller creature hit by a net is [condition]restrained[/condition] until it is freed. A net has no effect on creatures that are formless, or creatures that are Huge or larger. A creature can use its action to make a DC 10 Strength check, freeing itself or another creature within its reach on a success. Dealing 5 slashing damage to the net (AC 10) also frees the creature without harming it, ending the effect and destroying the net.When you use an action, bonus action, or reaction to attack with a net, you can make only one attack regardless of the number of attacks you can normally make.", 2),
    BOOMERANG(40, "Boomerang", 1782728300, "The boomerang is a ranged weapon, and any creature proficient with the javelin is also proficient with this weapon.On a miss, a boomerang returns to the thrower's hand.", 1),
    YKLWA(41, "Yklwa", 1782728300, "A yklwa (pronounced YICK-ul-wah) is a simple melee weapon that is the traditional weapon of Chultan warriors. A yklwa consists of a 3-foot wooden shaft with a steel or stone blade up to 18 inches long. It costs 1 gp, and it deals 1d8 piercing damage on a hit. Although it has the [wprop]thrown[/wprop] weapon property, the yklwa is not well balanced for throwing (range 10/30 ft.).", 1),
    PISTOL(42, "Pistol", 1782728300, "It’s up to you to decide whether a character has proficiency with a firearm. Characters in most D&amp;D worlds wouldn’t have such proficiency. During their downtime, characters can use the training rules in the&nbsp;Player’s Handbook&nbsp;to acquire proficiency, assuming that they have enough ammunition to keep the weapons working while mastering&nbsp;their use.", 3),
    MUSKET(43, "Musket", 1782728300, "It’s up to you to decide whether a character has proficiency with a firearm. Characters in most D&amp;D worlds wouldn’t have such proficiency. During their downtime, characters can use the training rules in the&nbsp;Player’s Handbook&nbsp;to acquire proficiency, assuming that they have enough ammunition to keep the weapons working while mastering&nbsp;their use.", 3),
    PISTOLAUTOMATIC(44, "Pistol, Automatic", 1782728300, "It’s up to you to decide whether a character has proficiency with a firearm. Characters in most D&amp;D worlds wouldn’t have such proficiency. During their downtime, characters can use the training rules in the&nbsp;Player’s Handbook&nbsp;to acquire proficiency, assuming that they have enough ammunition to keep the weapons working while mastering&nbsp;their use.", 3),
    REVOLVER(45, "Revolver", 1782728300, "It’s up to you to decide whether a character has proficiency with a firearm. Characters in most D&amp;D worlds wouldn’t have such proficiency. During their downtime, characters can use the training rules in the&nbsp;Player’s Handbook&nbsp;to acquire proficiency, assuming that they have enough ammunition to keep the weapons working while mastering&nbsp;their use.", 3),
    RIFLEHUNTING(46, "Rifle, Hunting", 1782728300, "It’s up to you to decide whether a character has proficiency with a firearm. Characters in most D&amp;D worlds wouldn’t have such proficiency. During their downtime, characters can use the training rules in the&nbsp;Player’s Handbook&nbsp;to acquire proficiency, assuming that they have enough ammunition to keep the weapons working while mastering&nbsp;their use.", 3),
    RIFLEAUTOMATIC(47, "Rifle, Automatic", 1782728300, "It’s up to you to decide whether a character has proficiency with a firearm. Characters in most D&amp;D worlds wouldn’t have such proficiency. During their downtime, characters can use the training rules in the&nbsp;Player’s Handbook&nbsp;to acquire proficiency, assuming that they have enough ammunition to keep the weapons working while mastering&nbsp;their use.", 3),
    SHOTGUN(48, "Shotgun", 1782728300, "It’s up to you to decide whether a character has proficiency with a firearm. Characters in most D&amp;D worlds wouldn’t have such proficiency. During their downtime, characters can use the training rules in the&nbsp;Player’s Handbook&nbsp;to acquire proficiency, assuming that they have enough ammunition to keep the weapons working while mastering&nbsp;their use.", 3),
    LASERPISTOL(49, "Laser Pistol", 1782728300, "It’s up to you to decide whether a character has proficiency with a firearm. Characters in most D&amp;D worlds wouldn’t have such proficiency. During their downtime, characters can use the training rules in the&nbsp;Player’s Handbook&nbsp;to acquire proficiency, assuming that they have enough ammunition to keep the weapons working while mastering&nbsp;their use.", 3),
    ANTIMATTERRIFLE(50, "Antimatter Rifle", 1782728300, "It’s up to you to decide whether a character has proficiency with a firearm. Characters in most D&amp;D worlds wouldn’t have such proficiency. During their downtime, characters can use the training rules in the Player’s Handbook to acquire proficiency, assuming that they have enough ammunition to keep the weapons working while mastering their use.", 3),
    LASERRIFLE(51, "Laser Rifle", 1782728300, "It’s up to you to decide whether a character has proficiency with a firearm. Characters in most D&amp;D worlds wouldn’t have such proficiency. During their downtime, characters can use the training rules in the&nbsp;Player’s Handbook&nbsp;to acquire proficiency, assuming that they have enough ammunition to keep the weapons working while mastering&nbsp;their use.", 3),
    PALMPISTOL_EXANDRIA(52, "Palm Pistol (Exandria)", 1782728300, "Firearms are a new and volatile technology, and as such bring their own unique set of weapon properties. Some properties are followed by a number, and this number signifies an element of that property (outlined below). These properties replace the optional ones presented in the Dungeon Master’s Guide. Firearms are ranged weapons.Reload. The weapon can be fired a number of times equal to its Reload score before you must spend 1 attack or 1 action to reload. You must have one free hand to reload a firearm.Misfire. Whenever you make an attack roll with a firearm, and the dice roll is equal to or lower than the weapon’s Misfire score, the weapon misfires. The attack misses, and the weapon cannot be used again until you spend an action to try and repair it. To repair your firearm, you must make a successful Tinker’s Tools check (DC equal to 8 + misfire score). If your check fails, the weapon is broken and must be mended out of combat at a quarter of the cost of the firearm. Creatures who use a firearm without being proficient increase the weapon’s misfire score by 1.Explosive. Upon a hit, everything within 5 ft of the target must make a Dexterity saving throw (DC equal to 8 + your proficiency bonus + your Dexterity modifier) or suffer 1d8 fire damage. If the weapon misses, the ammunition fails to detonate, or bounces away harmlessly before doing so.AmmunitionAll firearms require ammunition to make an attack, and due to their rare nature, ammunition may be near impossible to find or purchase. However, if materials are gathered, you can craft ammunition yourself using your Tinker’s Tools at half the cost. Each firearm uses its own unique ammunition and is generally sold or crafted in batches listed below next to the price.THIS IS UNOFFICIAL MATERIALThese game mechanics are usable in your campaign if your DM allows them but not refined by final game design and editing. They aren’t officially part of the Dungeons &amp; Dragons game and aren’t permitted in D&amp;D Adventurers League events unless otherwise stated.", 3),
    PEPPERBOX_EXANDRIA(53, "Pepperbox (Exandria)", 1782728300, "Firearms are a new and volatile technology, and as such bring their own unique set of weapon properties. Some properties are followed by a number, and this number signifies an element of that property (outlined below). These properties replace the optional ones presented in the Dungeon Master’s Guide. Firearms are ranged weapons.Reload.&nbsp;The weapon can be fired a number of times equal to its Reload score before you must spend 1 attack or 1 action to reload. You must have one free hand to reload a firearm.Misfire.&nbsp;Whenever you make an attack roll with a firearm, and the dice roll is equal to or lower than the weapon’s Misfire score, the weapon misfires. The attack misses, and the weapon cannot be used again until you spend an action to try and repair it. To repair your firearm, you must make a successful Tinker’s Tools check (DC equal to 8 + misfire score). If your check fails, the weapon is broken and must be mended out of combat at a quarter of the cost of the firearm. Creatures who use a firearm without being proficient increase the weapon’s misfire score by 1.AmmunitionAll firearms require ammunition to make an attack, and due to their rare nature, ammunition may be near impossible to find or purchase. However, if materials are gathered, you can craft ammunition yourself using your Tinker’s Tools at half the cost. Each firearm uses its own unique ammunition and is generally sold or crafted in batches listed below next to the price.THIS IS UNOFFICIAL MATERIALThese game mechanics are usable in your campaign if your DM allows them but not refined by final game design and editing. They aren’t officially part of the Dungeons &amp; Dragons game and aren’t permitted in D&amp;D Adventurers League events unless otherwise stated.", 3),
    PISTOL_EXANDRIA(54, "Pistol (Exandria)", 1782728300, "Firearms are a new and volatile technology, and as such bring their own unique set of weapon properties. Some properties are followed by a number, and this number signifies an element of that property (outlined below). These properties replace the optional ones presented in the Dungeon Master’s Guide. Firearms are ranged weapons.Reload. The weapon can be fired a number of times equal to its Reload score before you must spend 1 attack or 1 action to reload. You must have one free hand to reload a firearm.Misfire. Whenever you make an attack roll with a firearm, and the dice roll is equal to or lower than the weapon’s Misfire score, the weapon misfires. The attack misses, and the weapon cannot be used again until you spend an action to try and repair it. To repair your firearm, you must make a successful Tinker’s Tools check (DC equal to 8 + misfire score). If your check fails, the weapon is broken and must be mended out of combat at a quarter of the cost of the firearm. Creatures who use a firearm without being proficient increase the weapon’s misfire score by 1.Explosive. Upon a hit, everything within 5 ft of the target must make a Dexterity saving throw (DC equal to 8 + your proficiency bonus + your Dexterity modifier) or suffer 1d8 fire damage. If the weapon misses, the ammunition fails to detonate, or bounces away harmlessly before doing so.AmmunitionAll firearms require ammunition to make an attack, and due to their rare nature, ammunition may be near impossible to find or purchase. However, if materials are gathered, you can craft ammunition yourself using your Tinker’s Tools at half the cost. Each firearm uses its own unique ammunition and is generally sold or crafted in batches listed below next to the price.THIS IS UNOFFICIAL MATERIALThese game mechanics are usable in your campaign if your DM allows them but not refined by final game design and editing. They aren’t officially part of the Dungeons &amp; Dragons game and aren’t permitted in D&amp;D Adventurers League events unless otherwise stated.", 3),
    BLUNDERBUSS_EXANDRIA(55, "Blunderbuss (Exandria)", 1782728300, "Firearms are a new and volatile technology, and as such bring their own unique set of weapon properties. Some properties are followed by a number, and this number signifies an element of that property (outlined below). These properties replace the optional ones presented in the Dungeon Master’s Guide. Firearms are ranged weapons.Reload. The weapon can be fired a number of times equal to its Reload score before you must spend 1 attack or 1 action to reload. You must have one free hand to reload a firearm.Misfire. Whenever you make an attack roll with a firearm, and the dice roll is equal to or lower than the weapon’s Misfire score, the weapon misfires. The attack misses, and the weapon cannot be used again until you spend an action to try and repair it. To repair your firearm, you must make a successful Tinker’s Tools check (DC equal to 8 + misfire score). If your check fails, the weapon is broken and must be mended out of combat at a quarter of the cost of the firearm. Creatures who use a firearm without being proficient increase the weapon’s misfire score by 1.Explosive. Upon a hit, everything within 5 ft of the target must make a Dexterity saving throw (DC equal to 8 + your proficiency bonus + your Dexterity modifier) or suffer 1d8 fire damage. If the weapon misses, the ammunition fails to detonate, or bounces away harmlessly before doing so.AmmunitionAll firearms require ammunition to make an attack, and due to their rare nature, ammunition may be near impossible to find or purchase. However, if materials are gathered, you can craft ammunition yourself using your Tinker’s Tools at half the cost. Each firearm uses its own unique ammunition and is generally sold or crafted in batches listed below next to the price.THIS IS UNOFFICIAL MATERIALThese game mechanics are usable in your campaign if your DM allows them but not refined by final game design and editing. They aren’t officially part of the Dungeons &amp; Dragons game and aren’t permitted in D&amp;D Adventurers League events unless otherwise stated.", 3),
    BADNEWS_EXANDRIA(56, "Bad News (Exandria)", 1782728300, "Firearms are a new and volatile technology, and as such bring their own unique set of weapon properties. Some properties are followed by a number, and this number signifies an element of that property (outlined below). These properties replace the optional ones presented in the Dungeon Master’s Guide. Firearms are ranged weapons.Reload. The weapon can be fired a number of times equal to its Reload score before you must spend 1 attack or 1 action to reload. You must have one free hand to reload a firearm.Misfire. Whenever you make an attack roll with a firearm, and the dice roll is equal to or lower than the weapon’s Misfire score, the weapon misfires. The attack misses, and the weapon cannot be used again until you spend an action to try and repair it. To repair your firearm, you must make a successful Tinker’s Tools check (DC equal to 8 + misfire score). If your check fails, the weapon is broken and must be mended out of combat at a quarter of the cost of the firearm. Creatures who use a firearm without being proficient increase the weapon’s misfire score by 1.Explosive. Upon a hit, everything within 5 ft of the target must make a Dexterity saving throw (DC equal to 8 + your proficiency bonus + your Dexterity modifier) or suffer 1d8 fire damage. If the weapon misses, the ammunition fails to detonate, or bounces away harmlessly before doing so.AmmunitionAll firearms require ammunition to make an attack, and due to their rare nature, ammunition may be near impossible to find or purchase. However, if materials are gathered, you can craft ammunition yourself using your Tinker’s Tools at half the cost. Each firearm uses its own unique ammunition and is generally sold or crafted in batches listed below next to the price.THIS IS UNOFFICIAL MATERIALThese game mechanics are usable in your campaign if your DM allows them but not refined by final game design and editing. They aren’t officially part of the Dungeons &amp; Dragons game and aren’t permitted in D&amp;D Adventurers League events unless otherwise stated.", 3),
    HANDMORTAR_EXANDRIA(57, "Hand Mortar (Exandria)", 1782728300, "Firearms are a new and volatile technology, and as such bring their own unique set of weapon properties. Some properties are followed by a number, and this number signifies an element of that property (outlined below). These properties replace the optional ones presented in the Dungeon Master’s Guide. Firearms are ranged weapons.Reload.&nbsp;The weapon can be fired a number of times equal to its Reload score before you must spend 1 attack or 1 action to reload. You must have one free hand to reload a firearm.Misfire.&nbsp;Whenever you make an attack roll with a firearm, and the dice roll is equal to or lower than the weapon’s Misfire score, the weapon misfires. The attack misses, and the weapon cannot be used again until you spend an action to try and repair it. To repair your firearm, you must make a successful Tinker’s Tools check (DC equal to 8 + misfire score). If your check fails, the weapon is broken and must be mended out of combat at a quarter of the cost of the firearm. Creatures who use a firearm without being proficient increase the weapon’s misfire score by 1.Explosive.&nbsp;Upon a hit, everything within 5 ft of the target must make a Dexterity saving throw (DC equal to 8 + your proficiency bonus + your Dexterity modifier) or suffer 1d8 fire damage. If the weapon misses, the ammunition fails to detonate, or bounces away harmlessly before doing so.AmmunitionAll firearms require ammunition to make an attack, and due to their rare nature, ammunition may be near impossible to find or purchase. However, if materials are gathered, you can craft ammunition yourself using your Tinker’s Tools at half the cost. Each firearm uses its own unique ammunition and is generally sold or crafted in batches listed below next to the price.THIS IS UNOFFICIAL MATERIALThese game mechanics are usable in your campaign if your DM allows them but not refined by final game design and editing. They aren’t officially part of the Dungeons &amp; Dragons game and aren’t permitted in D&amp;D Adventurers League events unless otherwise stated.", 3),
    MUSKET_EXANDRIA(58, "Musket (Exandria)", 1782728300, "Firearms are a new and volatile technology, and as such bring their own unique set of weapon properties. Some properties are followed by a number, and this number signifies an element of that property (outlined below). These properties replace the optional ones presented in the Dungeon Master’s Guide. Firearms are ranged weapons.Reload. The weapon can be fired a number of times equal to its Reload score before you must spend 1 attack or 1 action to reload. You must have one free hand to reload a firearm.Misfire. Whenever you make an attack roll with a firearm, and the dice roll is equal to or lower than the weapon’s Misfire score, the weapon misfires. The attack misses, and the weapon cannot be used again until you spend an action to try and repair it. To repair your firearm, you must make a successful Tinker’s Tools check (DC equal to 8 + misfire score). If your check fails, the weapon is broken and must be mended out of combat at a quarter of the cost of the firearm. Creatures who use a firearm without being proficient increase the weapon’s misfire score by 1.Explosive. Upon a hit, everything within 5 ft of the target must make a Dexterity saving throw (DC equal to 8 + your proficiency bonus + your Dexterity modifier) or suffer 1d8 fire damage. If the weapon misses, the ammunition fails to detonate, or bounces away harmlessly before doing so.AmmunitionAll firearms require ammunition to make an attack, and due to their rare nature, ammunition may be near impossible to find or purchase. However, if materials are gathered, you can craft ammunition yourself using your Tinker’s Tools at half the cost. Each firearm uses its own unique ammunition and is generally sold or crafted in batches listed below next to the price.THIS IS UNOFFICIAL MATERIALThese game mechanics are usable in your campaign if your DM allows them but not refined by final game design and editing. They aren’t officially part of the Dungeons &amp; Dragons game and aren’t permitted in D&amp;D Adventurers League events unless otherwise stated.", 3),
    DOUBLEBLADEDSCIMITAR(59, "Double-Bladed Scimitar", 1782728300, "The double-bladed scimitar is the signature weapon of Valenar elves. A haft of fine wood supports a long, curving blade on either end. Forged with techniques honed over centuries, these blades are strong, sharp, and remarkably light. Each scimitar is a masterpiece, and as a result the double-bladed scimitar is an expensive weapon (100 gp) — few though ever have the opportunity to purchase one. A Valenar blade in the hands of a non-elf is generally assumed to have been stolen or looted from a fallen foe, and a Valenar elf might feel entitled to demand its return or challenge the bearer to prove they’re worthy to wield it.Special. If you attack with a double-bladed scimitar as part of the [action]Attack[/action] action on your turn, you can use a bonus action immediately after to make a melee attack with it. This attack deals 1d4 slashing damage on a hit, instead of 2d4.", 2),
    HOOPAK_RANGED(60, "Hoopak (Ranged)", 1782728300, "A hoopak is a sturdy stick with a sling at one end and a pointed tip at the other.Special. When you make a melee attack with this weapon, you ignore its [wprop]ammunition[/wprop] property. You can use the hoopak as a martial ranged weapon. If you do, it uses the [wprop]ammunition[/wprop] property, uses [item]sling bullets[/item], and deals 1d4 bludgeoning damage on a hit.", 2),
    HOOPAK(61, "Hoopak", 1782728300, "A hoopak is a sturdy stick with a sling at one end and a pointed tip at the other.Special. When you make a melee attack with this weapon, you ignore its [wprop]ammunition[/wprop] property. You can use the hoopak as a martial ranged weapon. If you do, it uses the [wprop]ammunition[/wprop] property, uses [item]sling bullets[/item], and deals 1d4 bludgeoning damage on a hit.", 2),
    FIREARM(62, "Firearm", 1782728300, "", 2)
}

class WeaponProperties {
    companion object {
        fun getWeaponPropertyById(id: Int): WeaponProperty? {
            return when (id) {
                in 1.. WeaponProperty.values().size -> WeaponProperty.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class WeaponProperty(val id: Int, val label: String, val prerequisite: Int?, val description: String, val requiredLevel: Int?, val displayOrder: Int?) {
    AMMUNITION(1, "Ammunition", null, "You can use a weapon that has the ammunition property to make a ranged attack only if you have ammunition to fire from the weapon. Each time you attack with the weapon, you expend one piece of ammunition. Drawing the ammunition from a quiver, case, or other container is part of the attack (you need a free hand to load a one-handed weapon). At the end of the battle, you can recover half your expended ammunition by taking a minute to search the battlefield. If you use a weapon that has the ammunition property to make a melee attack, you treat the weapon as an improvised weapon (see \"Improvised Weapons\" later in the section). A sling must be loaded to deal any damage when used in this way.", null, null),
    FINESSE(2, "Finesse", null, "When making an attack with a finesse weapon, you use your choice of your Strength or Dexterity modifier for the attack and damage rolls. You must use the same modifier for both rolls.", null, null),
    HEAVY(3, "Heavy", null, "Creatures that are Small or Tiny have disadvantage on attack rolls with heavy weapons. A heavy weapon's size and bulk make it too large for a Small or Tiny creature to use effectively.", null, null),
    LIGHT(4, "Light", null, "A light weapon is small and easy to handle, making it ideal for use when fighting with two weapons.", null, null),
    LOADING(5, "Loading", null, "Because of the time required to load this weapon, you can fire only one piece of ammunition from it when you use an action, bonus action, or reaction to fire it, regardless of the number of attacks you can normally make.", null, null),
    RANGE(7, "Range", null, "A weapon that can be used to make a ranged attack has a range in parentheses after the ammunition or thrown property. The range lists two numbers. The first is the weapon's normal range in feet, and the second indicates the weapon's long range. When attacking a target beyond normal range, you have disadvantage on the attack roll. You can't attack a target beyond the weapon's long range.", null, null),
    REACH(8, "Reach", null, "This weapon adds 5 feet to your reach when you attack with it, as well as when determining your reach for opportunity attacks with it.", null, null),
    SPECIAL(9, "Special", null, "A weapon with the special property has unusual rules governing its use, explained in the weapon's description .", null, null),
    THROWN(10, "Thrown", null, "If a weapon has the thrown property, you can throw the weapon to make a ranged attack. If the weapon is a melee weapon, you use the same ability modifier for that attack roll and damage roll that you would use for a melee attack with the weapon. For example, if you throw a handaxe, you use your Strength, but if you throw a dagger, you can use either your Strength or your Dexterity, since the dagger has the finesse property.", null, null),
    TWOHANDED(11, "Two-Handed", null, "This weapon requires two hands when you attack with it.", null, null),
    VERSATILE(12, "Versatile", null, "This weapon can be used with one or two hands. A damage value in parentheses appears with the property--the damage when the weapon is used with two hands to make a melee attack.", null, null),
    AMMUNITION_FIREARMS(13, "Ammunition (Firearms)", null, "The ammunition of a firearm is destroyed upon use. Renaissance and modern firearms use bullets. Futuristic firearms are powered by a special type of ammunition called energy cells. An energy cell contains enough power for all the shots its firearm can make.", null, null),
    BURSTFIRE(14, "Burst Fire", null, "A weapon that has the burst fire property can make a normal single-target attack, or it can spray a 10-foot-cube area within normal range with shots. Each creature in the area must succeed on a DC 15 Dexterity saving throw or take the weapon’s normal damage. This action uses ten pieces of ammunition.", null, null),
    RELOAD(15, "Reload", null, "A limited number of shots can be made with a weapon that has the reload property. A character must then reload it using an action or a bonus action (the character’s choice).", null, null),
    MISFIRE(16, "Misfire", null, "", null, null),
    EXPLOSIVE(17, "Explosive", null, "", null, null)
}

class AreaOfEffects {
    companion object {
        fun getAreaOfEffectById(id: Int): AreaOfEffect? {
            return when (id) {
                in 1.. AreaOfEffect.values().size -> AreaOfEffect.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class AreaOfEffect(val id: Int, val label: String, val prerequisite: Int?, val description: String, val requiredLevel: Int?, val displayOrder: Int?) {
    CONE(1, "Cone", null, "A cone extends in a direction you choose from its point of origin. A cone's width at a given point along its length is equal to that point's distance from the point of origin. A cone's area of effect specifies its maximum length. A cone's point of origin is not included in the cone's area of effect, unless you decide otherwise.", null, null),
    CUBE(2, "Cube", null, "You select a cube's point of origin, which lies anywhere on a face of the cubic effect. The cube's size is expressed as the length of each side. A cube's point of origin is not included in the cube's area of effect, unless you decide otherwise.", null, null),
    CYLINDER(3, "Cylinder", null, "A cylinder's point of origin is the center of a circle of a particular radius, as given in the spell description. The circle must either be on the ground or at the height of the spell effect. The energy in a cylinder expands in straight lines from the point of origin to the perimeter of the circle, forming the base of the cylinder. The spell's effect then shoots up from the base or down from the top, to a distance equal to the height of the cylinder. A cylinder's point of origin is included in the cylinder's area of effect.", null, null),
    LINE(4, "Line", null, "A line extends from its point of origin in a straight path up to its length and covers an area defined by its width. A line's point of origin is not included in the line's area of effect, unless you decide otherwise.", null, null),
    SPHERE(5, "Sphere", null, "You select a sphere's point of origin, and the sphere extends outward from that point. The sphere's size is expressed as a radius in feet that extends from the point. A sphere's point of origin is included in the sphere's area of effect.", null, null),
    SQUARE(9, "Square", null, "", null, null),
    SQUAREFEET(13, "Square Feet", null, "", null, null)
}

class Lifestyles {
    companion object {
        fun getLifestyleById(id: Int): Lifestyle? {
            return when (id) {
                in 1.. Lifestyle.values().size -> Lifestyle.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class Lifestyle(val id: Int, val label: String, val description: String, val cost: String) {
    WRETCHED(1, "Wretched", "You live in inhumane conditions. With no place to call home, you shelter wherever you can, sneaking into barns, huddling in old crates, and relying on the good graces of people better off than you. A wretched lifestyle presents abundant dangers. Violence, disease, and hunger follow you wherever you go. Other wretched people covet your armor, weapons, and adventuring gear, which represent a fortune by their standards. You are beneath the notice of most people.", "-"),
    SQUALID(3, "Squalid", "You live in a leaky stable, a mud-floored hut just outside town, or a vermin-infested boarding house in the worst part of town. You have shelter from the elements, but you live in a desperate and often violent environment, in places rife with disease, hunger, and misfortune. You are beneath the notice of most people, and you have few legal protections. Most people at this lifestyle level have suffered some terrible setback. They might be disturbed, marked as exiles, or suffer from disease.", "1SP"),
    POOR(4, "Poor", "A poor lifestyle means going without the comforts available in a stable community. Simple food and lodgings, threadbare clothing, and unpredictable conditions result in a sufficient, though probably unpleasant, experience. Your accommodations might be a room in a flophouse or in the common room above a tavern. You benefit from some legal protections, but you still have to contend with violence, crime, and disease. People at this lifestyle level tend to be unskilled laborers, costermongers, peddlers, thieves, mercenaries, and other disreputable types.", "2SP"),
    MODEST(5, "Modest", "A modest lifestyle keeps you out of the slums and ensures that you can maintain your equipment. You live in an older part of town, renting a room in a boarding house, inn, or temple. You don't go hungry or thirsty, and your living conditions are clean, if simple. Ordinary people living modest lifestyles include soldiers with families, laborers, students, priests, hedge wizards, and the like.", "1GP"),
    COMFORTABLE(6, "Comfortable", "Choosing a comfortable lifestyle means that you can afford nicer clothing and can easily maintain your equipment. You live in a small cottage in a middle-class neighborhood or in a private room at a fine inn. You associate with merchants, skilled tradespeople, and military officers.", "2GP"),
    WEALTHY(7, "Wealthy", "Choosing a wealthy lifestyle means living a life of luxury, though you might not have achieved the social status associated with the old money of nobility or royalty. You live a lifestyle comparable to that of a highly successful merchant, a favored servant of the royalty, or the owner of a few small businesses. You have respectable lodgings, usually a spacious home in a good part of town or a comfortable suite at a fine inn. You likely have a small staff of servants.", "4GP"),
    ARISTOCRATIC(8, "Aristocratic", "You live a life of plenty and comfort. You move in circles populated by the most powerful people in the community. You have excellent lodgings, perhaps a townhouse in the nicest part of town or rooms in the finest inn. You dine at the best restaurants, retain the most skilled and fashionable tailor, and have servants attending to your every need. You receive invitations to the social gatherings of the rich and powerful, and spend evenings in the company of politicians, guild leaders, high priests, and nobility. You must also contend with the highest levels of deceit and treachery. The wealthier you are, the greater the chance you will be drawn into political intrigue as a pawn or participant.", "10GP minimum")
}

class DamageAdjustments {
    companion object {
        fun getDamageAdjustmentById(id: Int): DamageAdjustment? {
            return when (id) {
                in 1.. DamageAdjustment.values().size -> DamageAdjustment.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class DamageAdjustment(val id: Int, val label: String, val type: DamageAdjustmentType, val damage: DamageType, val isMulti: Boolean, val displayOrder: Int) {
    DA1(1, "Bludgeoning",     DamageAdjustmentType.RESISTANCE, DamageType.BLUDGEONING, false, 2),
    DA2(2, "Piercing",     DamageAdjustmentType.RESISTANCE, DamageType.PIERCING, false, 8),
    DA3(3, "Slashing",     DamageAdjustmentType.RESISTANCE, DamageType.SLASHING, false, 12),
    DA4(4, "Lightning",     DamageAdjustmentType.RESISTANCE, DamageType.LIGHTNING, false, 6),
    DA5(5, "Thunder",     DamageAdjustmentType.RESISTANCE, DamageType.THUNDER, false, 13),
    DA6(6, "Poison",     DamageAdjustmentType.RESISTANCE, DamageType.POISON, false, 9),
    DA7(7, "Cold",     DamageAdjustmentType.RESISTANCE, DamageType.COLD, false, 3),
    DA8(8, "Radiant",     DamageAdjustmentType.RESISTANCE, DamageType.RADIANT, false, 11),
    DA9(9, "Fire",     DamageAdjustmentType.RESISTANCE, DamageType.FIRE, false, 4),
    DA10(10, "Necrotic",     DamageAdjustmentType.RESISTANCE, DamageType.NECROTIC, false, 7),
    DA11(11, "Acid",     DamageAdjustmentType.RESISTANCE, DamageType.ACID, false, 1),
    DA12(12, "Psychic",     DamageAdjustmentType.RESISTANCE, DamageType.PSYCHIC, false, 10),
    DA13(13, "Bludgeoning, Piercing, and Slashing from Nonmagical Attacks",     DamageAdjustmentType.RESISTANCE, DamageType.NONMAGICAL, true, 14),
    DA14(14, "Bludgeoning, Piercing, and Slashing from Nonmagical Attacks that aren't Silvered",     DamageAdjustmentType.RESISTANCE, DamageType.NONSILVERED, true, 15),
    DA15(15, "Bludgeoning, Piercing, and Slashing from Nonmagical Attacks that aren't Adamantine",     DamageAdjustmentType.RESISTANCE, DamageType.NONADAMANTINE, true, 16),
    DA16(16, "Piercing and Slashing from Nonmagical Attacks that aren't Adamantine",     DamageAdjustmentType.RESISTANCE, DamageType.NONADAMANTINE, true, 17),
    DA17(17, "Bludgeoning", DamageAdjustmentType.IMMUNITY, DamageType.BLUDGEONING, false, 2),
    DA18(18, "Piercing", DamageAdjustmentType.IMMUNITY, DamageType.PIERCING, false, 8),
    DA19(19, "Slashing", DamageAdjustmentType.IMMUNITY, DamageType.SLASHING, false, 12),
    DA20(20, "Lightning", DamageAdjustmentType.IMMUNITY, DamageType.LIGHTNING, false, 6),
    DA21(21, "Thunder", DamageAdjustmentType.IMMUNITY, DamageType.THUNDER, false, 13),
    DA22(22, "Poison", DamageAdjustmentType.IMMUNITY, DamageType.POISON, false, 9),
    DA23(23, "Cold", DamageAdjustmentType.IMMUNITY, DamageType.COLD, false, 3),
    DA24(24, "Radiant", DamageAdjustmentType.IMMUNITY, DamageType.RADIANT, false, 11),
    DA25(25, "Fire", DamageAdjustmentType.IMMUNITY, DamageType.FIRE, false, 4),
    DA26(26, "Necrotic", DamageAdjustmentType.IMMUNITY, DamageType.NECROTIC, false, 7),
    DA27(27, "Acid", DamageAdjustmentType.IMMUNITY,  DamageType.ACID, false, 1),
    DA28(28, "Psychic", DamageAdjustmentType.IMMUNITY, DamageType.PSYCHIC, false, 10),
    DA29(29, "Bludgeoning, Piercing, and Slashing from Nonmagical Attacks", DamageAdjustmentType.IMMUNITY, DamageType.NONMAGICAL, true, 14),
    DA30(30, "Bludgeoning, Piercing, and Slashing from Nonmagical Attacks that aren't Silvered", DamageAdjustmentType.IMMUNITY, DamageType.NONSILVERED, true, 16),
    DA31(31, "Bludgeoning, Piercing, and Slashing from Nonmagical Attacks that aren't Adamantine", DamageAdjustmentType.IMMUNITY, DamageType.NONADAMANTINE, true, 15),
    DA32(32, "Piercing and Slashing from Nonmagical Attacks that aren't Adamantine", DamageAdjustmentType.IMMUNITY, DamageType.NONADAMANTINE, true, 17),
    DA33(33, "Bludgeoning", DamageAdjustmentType.VULNERABLE, DamageType.BLUDGEONING, false, 2),
    DA34(34, "Piercing", DamageAdjustmentType.VULNERABLE, DamageType.PIERCING, false, 8),
    DA35(35, "Slashing", DamageAdjustmentType.VULNERABLE, DamageType.SLASHING, false, 12),
    DA36(36, "Lightning", DamageAdjustmentType.VULNERABLE, DamageType.LIGHTNING, false, 6),
    DA37(37, "Thunder", DamageAdjustmentType.VULNERABLE, DamageType.THUNDER, false, 13),
    DA38(38, "Poison", DamageAdjustmentType.VULNERABLE, DamageType.POISON, false, 9),
    DA39(39, "Cold", DamageAdjustmentType.VULNERABLE, DamageType.COLD, false, 3),
    DA40(40, "Radiant", DamageAdjustmentType.VULNERABLE, DamageType.RADIANT, false, 11),
    DA41(41, "Fire", DamageAdjustmentType.VULNERABLE, DamageType.FIRE, false, 4),
    DA42(42, "Necrotic", DamageAdjustmentType.VULNERABLE, DamageType.NECROTIC, false, 7),
    DA43(43, "Acid", DamageAdjustmentType.VULNERABLE, DamageType.ACID, false, 1),
    DA44(44, "Psychic", DamageAdjustmentType.VULNERABLE, DamageType.PSYCHIC, false, 10),
    DA45(45, "Piercing from Magic Weapons Wielded by Good Creatures", DamageAdjustmentType.VULNERABLE, DamageType.MAGICAL, true, 14),
    DA46(46, "Bludgeoning, Piercing, and Slashing from Magic Weapons", DamageAdjustmentType.RESISTANCE, DamageType.MAGICAL, true, 18),
    DA47(47, "Force", DamageAdjustmentType.RESISTANCE, DamageType.FORCE, false, 5),
    DA48(48, "Force", DamageAdjustmentType.IMMUNITY, DamageType.FORCE, false, 5),
    DA49(49, "Force", DamageAdjustmentType.VULNERABLE, DamageType.FORCE, false, 5),
    DA50(50, "Bludgeoning, Piercing, and Slashing from Nonmagical Attacks while in Dim Light or Darkness", DamageAdjustmentType.RESISTANCE, DamageType.NONMAGICAL, true, 19),
    DA51(51, "Ranged Attacks", DamageAdjustmentType.RESISTANCE, DamageType.RANGED, false, 20),
    DA52(52, "Damage Dealt By Traps", DamageAdjustmentType.RESISTANCE, DamageType.TRAPS, false, 21),
    DA53(53, "All", DamageAdjustmentType.RESISTANCE, DamageType.ALL, true, 22),
    DA54(54, "Bludgeoning from non magical attacks", DamageAdjustmentType.RESISTANCE, DamageType.NONMAGICAL, false, 23),
    DA55(55, "Bludgeoning, Piercing, and Slashing from Metal Weapons", DamageAdjustmentType.IMMUNITY, DamageType.WEAPONS, true, 18),
    DA56(56, "Bludgeoning, Piercing, and Slashing while in Dim Light or Darkness", DamageAdjustmentType.RESISTANCE, DamageType.INDARKNESS, true, 24),
    DA57(57, "Damage from Spells", DamageAdjustmentType.RESISTANCE, DamageType.SPELLS, false, 25),
    DA60(60, "Bludgeoning, Piercing, and Slashing from Nonmagical Attacks that aren't Adamantine or Silvered", DamageAdjustmentType.IMMUNITY, DamageType.NONMAGICAL, true, 19),
    DA61(61, "Nonmagical Bludgeoning, Piercing, and Slashing (from Stoneskin)", DamageAdjustmentType.RESISTANCE, DamageType.NONMAGICAL, true, 27),
    DA62(62, "All damage but Force, Radiant, and Psychic", DamageAdjustmentType.RESISTANCE, DamageType.NONMAGICAL, true, 29),
    DA63(63, "Petrified (Aberrant Armor Only)", DamageAdjustmentType.IMMUNITY, DamageType.NONMAGICAL, false, 28),
    DA64(64, "Slashing from a Vorpal Sword", DamageAdjustmentType.VULNERABLE, DamageType.NONMAGICAL, false, 30),
    DA65(65, "Damage of the type matching the animated breath's form (acid, cold, fire, lightning, or poison)", DamageAdjustmentType.RESISTANCE, DamageType.NONMAGICAL, false, 31),
    DA66(66, "Psychic (granted by Ruidium Armor)", DamageAdjustmentType.RESISTANCE, DamageType.NONMAGICAL, false, 32),
    DA67(67, "Bludgeoning, Piercing, and Slashing that is Nonmagical", DamageAdjustmentType.IMMUNITY, DamageType.NONMAGICAL, true, 29)
}

class Alignments {
    companion object {
        fun getAlignmentById(id: Int): Alignment? {
            return Alignment.values().filter { it.id == id}.getOrNull(0)
        }
    }
}

enum class Alignment(val id: Int, val label: String, val description: String?, val availableToCharacter: Boolean) {
    LAWFULGOOD(1, "Lawful Good", "Lawful good (LG) creatures can be counted on to do the right thing as expected by society. Gold dragons, paladins, and most dwarves are lawful good.", true),
    NEUTRALGOOD(2, "Neutral Good", "Neutral good (NG) folk do the best they can to help others according to their needs. Many celestials, some cloud giants, and most gnomes are neutral good.", true),
    CHAOTICGOOD(3, "Chaotic Good", "Chaotic good (CG) creatures act as their conscience directs, with little regard for what others expect. Copper dragons, many elves, and unicorns are chaotic good.", true),
    LAWFULNEUTRAL(4, "Lawful Neutral", "Lawful neutral (LN) individuals act in accordance with law, tradition, or personal codes. Many monks and some wizards are lawful neutral.", true),
    NEUTRAL(5, "Neutral", "Neutral (N) is the alignment of those who prefer to steer clear of moral questions and don't take sides, doing what seems best at the time. Lizardfolk, most druids, and many humans are neutral.", true),
    CHAOTICNEUTRAL(6, "Chaotic Neutral", "Chaotic neutral (CN) creatures follow their whims, holding their personal freedom above all else. Many barbarians and rogues, and some bards, are chaotic neutral.", true),
    LAWFULEVIL(7, "Lawful Evil", "Lawful evil (LE) creatures methodically take what they want, within the limits of a code of tradition, loyalty, or order. Devils, blue dragons, and hobgoblins are lawful evil.", true),
    NEUTRALEVIL(8, "Neutral Evil", "Neutral evil (NE) is the alignment of those who do whatever they can get away with, without compassion or qualms. Many drow, some cloud giants, and goblins are neutral evil.", true),
    CHAOTICEVIL(9, "Chaotic Evil", "Chaotic evil (CE) creatures act with arbitrary violence, spurred by their greed, hatred, or bloodlust. Demons, red dragons, and orcs are chaotic evil.", true),
    UNALIGNED(10, "Unaligned", null, false),
    ANYALIGNMENT(11, "Any Alignment", "Any Alignment", false),
    ANYEVILALIGNMENT(13, "Any Evil Alignment", "Any Evil Alignment", false),
    ANYGOODALIGNMENT(14, "Any Good Alignment", "Any Good Alignment", false),
    ANYCHAOTICALIGNMENT(15, "Any Chaotic Alignment", "Any Chaotic Alignment", false),
    ANYLAWFULALIGNMENT(16, "Any Lawful Alignment", "Any Lawful Alignment", false),
    ANYNONGOODALIGNMENT(18, "Any Non-Good Alignment", "Any Non-Good Alignment", false),
    ANYNONLAWFULALIGNMENT(19, "Any Non-Lawful Alignment", "Any Non-Lawful Alignment", false),
    TYPICALLYCHAOTICNEUTRAL(20, "Typically Chaotic Neutral", "Typically Chaotic Neutral", false),
    TYPICALLYNEUTRALGOOD(21, "Typically Neutral Good", "Typically Neutral Good", false),
    TYPICALLYLAWFULGOOD(22, "Typically Lawful Good", "Typically Lawful Good", false),
    TYPICALLYCHAOTICEVIL(23, "Typically Chaotic Evil", "Typically Chaotic Evil", false),
    TYPICALLYNEUTRALEVIL(24, "Typically Neutral Evil", "Typically Neutral Evil", false),
    TYPICALLYCHAOTICGOOD(25, "Typically Chaotic Good", "Typically Chaotic Good", false),
    TYPICALLYNEUTRAL(26, "Typically Neutral", "Typically Neutral", false),
    TYPICALLYLAWFULEVIL(27, "Typically Lawful Evil", "Typically Lawful Evil", false),
    TYPICALLYLAWFULNEUTRAL(28, "Typically Lawful Neutral", "Typically Lawful Neutral", false)
}

class Sources {
    companion object {
        fun getSourceById(id: Int): Source {
            return when (id) {
                in 1.. Source.values().size -> Source.values()[id - 1]
                else -> Source.UNKNOWN
            }
        }
    }
}

enum class Source(val id: Int, val label: String, val description: String, val sourceCategoryId: Int, val isReleased: Boolean, val avatarURL: String, val sourceURL: String) {
    BR(1, "BR", "Basic Rules", 1, true, "https://www.dndbeyond.com/avatars/10434/136/637248073409717512.jpeg", "sources/basic-rules"),
    PHB(2, "PHB", "Player's Handbook", 1, true, "https://www.dndbeyond.com/avatars/10435/389/637248131811862290.jpeg", "sources/phb"),
    DMG(3, "DMG", "Dungeon Master's Guide", 1, true, "https://www.dndbeyond.com/avatars/10367/593/637245347063211867.jpeg", "sources/dmg"),
    EE(4, "EE", "Elemental Evil Player's Companion", 1, true, "https://www.dndbeyond.com/avatars/", ""),
    MM(5, "MM", "Monster Manual", 1, true, "https://www.dndbeyond.com/avatars/10434/816/637248105832999293.jpeg", "sources/mm"),
    COS(6, "CoS", "Curse of Strahd", 1, true, "https://www.dndbeyond.com/avatars/10349/296/637244603965977140.jpeg", "sources/cos"),
    HOTDQ(7, "HotDQ", "Hoard of the Dragon Queen", 1, true, "https://www.dndbeyond.com/avatars/10432/68/637247937818392417.jpeg", "sources/hotdq"),
    LMOP(8, "LMoP", "Lost Mine of Phandelver", 1, true, "https://www.dndbeyond.com/avatars/10434/616/637248096401764265.jpeg", "sources/lmop"),
    OOTA(9, "OotA", "Out of the Abyss", 1, true, "https://www.dndbeyond.com/avatars/19/735/636383500945700817.jpeg", "sources/oota"),
    POTA(10, "PotA", "Princes of the Apocalypse", 1, true, "https://www.dndbeyond.com/avatars/10435/524/637248137744435932.jpeg", "sources/pota"),
    ROT(11, "RoT", "Rise of Tiamat", 1, true, "https://www.dndbeyond.com/avatars/10435/605/637248141604547323.jpeg", "sources/rot"),
    SKT(12, "SKT", "Storm King's Thunder", 1, true, "https://www.dndbeyond.com/avatars/19/740/636383501361665378.jpeg", "sources/skt"),
    SCAG(13, "SCAG", "Sword Coast Adventurer's Guide", 1, true, "https://www.dndbeyond.com/avatars/10435/793/637248149475504723.jpeg", "sources/scag"),
    TFTYP(14, "TftYP", "Tales from the Yawning Portal", 1, true, "https://www.dndbeyond.com/avatars/10449/177/637248652153094716.jpeg", "sources/tftyp"),
    VGTM(15, "VGtM", "Volo's Guide to Monsters", 1, true, "https://www.dndbeyond.com/avatars/10449/464/637248679743732719.jpeg", "sources/vgtm"),
    TSC(16, "TSC", "The Sunless Citadel", 1, true, "https://www.dndbeyond.com/avatars/10449/402/637248674372576676.jpeg", "sources/tftyp/a1"),
    TFOF(17, "TFoF", "The Forge of Fury", 1, true, "https://www.dndbeyond.com/avatars/10436/4/637248156999902689.jpeg", "sources/tftyp/a2"),
    THSOT(18, "THSoT", "The Hidden Shrine of Tamoachan", 1, true, "https://www.dndbeyond.com/avatars/10449/236/637248657347161458.jpeg", "sources/tftyp/a3"),
    WPM(19, "WPM", "White Plume Mountain", 1, true, "https://www.dndbeyond.com/avatars/10449/751/637248705560259195.jpeg", "sources/tftyp/a4"),
    DIT(20, "DiT", "Dead in Thay", 1, true, "https://www.dndbeyond.com/avatars/10434/246/637248079254127234.jpeg", "sources/tftyp/a5"),
    ATG(21, "AtG", "Against the Giants", 1, true, "https://www.dndbeyond.com/avatars/10433/315/637248029897296032.jpeg", "sources/tftyp/a6"),
    TOH(22, "ToH", "Tomb of Horrors", 1, true, "https://www.dndbeyond.com/avatars/10449/371/637248671854035769.jpeg", "sources/tftyp/a7"),
    TOA(25, "ToA", "Tomb of Annihilation", 1, true, "https://www.dndbeyond.com/avatars/10449/339/637248669136195626.jpeg", "sources/toa"),
    COSCO(26, "CoSCO", "Curse of Strahd: Character Options", 1, true, "https://www.dndbeyond.com/avatars/10349/289/637244603748885696.jpeg", ""),
    XGTE(27, "XGtE", "Xanathar's Guide to Everything", 1, true, "https://www.dndbeyond.com/avatars/10449/803/637248709455777906.jpeg", "sources/xgte"),
    TTP(28, "TTP", "The Tortle Package", 1, true, "https://www.dndbeyond.com/avatars/39/300/636411199124473334.png", "sources/ttp"),
    UA(29, "UA", "Unearthed Arcana", 3, true, "https://www.dndbeyond.com/avatars/100/464/636506973225556542.png", ""),
    CR(31, "CR", "Critical Role", 2, true, "https://www.dndbeyond.com/avatars/", ""),
    MTOF(33, "MToF", "Mordenkainen’s Tome of Foes", 1, true, "https://www.dndbeyond.com/avatars/10434/949/637248111148617766.jpeg", "sources/mtof"),
    DDIAMORD(34, "DDIA-MORD", "Rrakkma", 12, true, "https://www.dndbeyond.com/avatars/319/345/636622116959280867.jpeg", "sources/ddia-mord"),
    WDH(35, "WDH", "Waterdeep: Dragon Heist", 1, true, "https://www.dndbeyond.com/avatars/343/499/636632335939805190.jpeg", "sources/wdh"),
    WDOTMM(36, "WDotMM", "Waterdeep: Dungeon of the Mad Mage", 1, true, "https://www.dndbeyond.com/avatars/10449/493/637248684031810278.jpeg", "sources/wdotmm"),
    WGTE(37, "WGtE", "Wayfinder's Guide to Eberron", 8, true, "https://www.dndbeyond.com/avatars/10449/715/637248702538222765.jpeg", "sources/wgte"),
    GGTR(38, "GGtR", "Guildmasters' Guide to Ravnica", 7, true, "https://www.dndbeyond.com/avatars/10369/823/637245482341163840.jpeg", "sources/ggtr"),
    LLOK(40, "LLoK", "Lost Laboratory of Kwalish", 12, true, "https://www.dndbeyond.com/avatars/10434/498/637248091075319276.jpeg", "sources/llok"),
    DOIP(41, "DoIP", "Dragon of Icespire Peak", 1, true, "https://www.dndbeyond.com/avatars/10350/957/637244676648122088.jpeg", "sources/doip"),
    TMR(42, "TMR", "Tactical Maps Reincarnated", 1, true, "https://www.dndbeyond.com/avatars/5336/630/636850745475942698.jpeg", ""),
    GOS(43, "GoS", "Ghosts of Saltmarsh", 1, true, "https://www.dndbeyond.com/avatars/10370/66/637245493047936420.jpeg", "sources/gos"),
    AI(44, "AI", "Acquisitions Incorporated", 1, true, "https://www.dndbeyond.com/avatars/10350/905/637244674570907870.jpeg", "sources/ai"),
    HFTT(47, "HftT", "Hunt for the Thessalhydra", 1, true, "https://www.dndbeyond.com/avatars/10432/12/637247932786703735.jpeg", "sources/hftt"),
    BGDIA(48, "BGDiA", "Baldur's Gate: Descent into Avernus", 1, true, "https://www.dndbeyond.com/avatars/10350/927/637244675832719441.jpeg", "sources/bgdia"),
    ERFTLW(49, "ERftLW", "Eberron: Rising from the Last War", 8, true, "https://www.dndbeyond.com/avatars/10368/6/637245381196842264.jpeg", "sources/erftlw"),
    SLW(50, "SLW", "Storm Lord’s Wrath", 1, true, "https://www.dndbeyond.com/avatars/10350/964/637244676927254855.jpeg", "sources/slw"),
    SDW(51, "SDW", "Sleeping Dragon’s Wake", 1, true, "https://www.dndbeyond.com/avatars/10350/959/637244676820916158.jpeg", "sources/sdw"),
    DC(52, "DC", "Divine Contention", 1, true, "https://www.dndbeyond.com/avatars/10350/951/637244676535367295.jpeg", "sources/dc"),
    SAC(53, "SAC", "Sage Advice Compendium", 1, true, "https://www.dndbeyond.com/avatars/10435/702/637248145947271474.jpeg", "sources/sac"),
    DDVRAM(54, "DDvRaM", "Dungeons &amp; Dragons vs. Rick and Morty", 10, true, "https://www.dndbeyond.com/avatars/10367/229/637245316031917098.jpeg", "sources/ddvram"),
    LR(55, "LR", "Locathah Rising", 12, true, "https://www.dndbeyond.com/avatars/10434/650/637248098360957592.jpeg", "sources/lr"),
    IMR(56, "IMR", "Infernal Machine Rebuild", 12, true, "https://www.dndbeyond.com/avatars/10434/395/637248086063224834.jpeg", "sources/imr"),
    MFFV1(57, "MFFV1", "Mordenkainen's Fiendish Folio Volume 1", 12, true, "https://www.dndbeyond.com/avatars/10434/743/637248102793792401.jpeg", "sources/mffv1"),
    SD(58, "SD", "Sapphire Dragon", 1, true, "https://www.dndbeyond.com/avatars/10435/899/637248153278056972.jpeg", ""),
    EGTW(59, "EGtW", "Explorer's Guide to Wildemount", 2, true, "https://www.dndbeyond.com/avatars/10367/769/637245363413951140.jpeg", "sources/egtw"),
    OGA(60, "OGA", "One Grung Above", 12, true, "https://www.dndbeyond.com/avatars/10435/68/637248116464990081.jpeg", "sources/oga"),
    MOOT(61, "MOoT", "Mythic Odysseys of Theros", 7, true, "https://www.dndbeyond.com/avatars/10434/885/637248108609488365.jpeg", "sources/moot"),
    WA(62, "WA", "Frozen Sick", 2, true, "https://www.dndbeyond.com/avatars/9193/755/637200909525723425.jpeg", "sources/wa"),
    IDROTF(66, "IDRotF", "Icewind Dale: Rime of the Frostmaiden", 1, true, "https://www.dndbeyond.com/avatars/11095/550/637278965847502335.jpeg", "sources/idrotf"),
    TCOE(67, "TCoE", "Tasha’s Cauldron of Everything", 1, true, "https://www.dndbeyond.com/avatars/13665/613/637400361423035085.jpeg", "sources/tcoe"),
    CM(68, "CM", "Candlekeep Mysteries", 1, true, "https://www.dndbeyond.com/avatars/14917/783/637456355214291364.jpeg", "sources/cm"),
    VRGTR(69, "VRGtR", "Van Richten’s Guide to Ravenloft", 1, true, "https://www.dndbeyond.com/avatars/15973/81/637496917952314322.jpeg", "sources/vrgtr"),
    TWBTW(79, "TWBtW", "The Wild Beyond the Witchlight", 1, true, "https://www.dndbeyond.com/avatars/18223/997/637587419509160992.jpeg", "sources/twbtw"),
    SACOC(80, "SACoC", "Strixhaven: A Curriculum of Chaos", 7, true, "https://www.dndbeyond.com/avatars/18228/52/637587668398315568.jpeg", "sources/sacoc"),
    FTOD(81, "FToD", "Fizban's Treasury of Dragons", 1, true, "https://www.dndbeyond.com/avatars/19075/983/637620380256293999.jpeg", "sources/ftod"),
    COTN(83, "CotN", "Critical Role: Call of the Netherdeep", 2, true, "https://www.dndbeyond.com/avatars/20906/943/637695655261542821.jpeg", "sources/cotn"),
    MOTM(85, "MotM", "Mordenkainen Presents: Monsters of the Multiverse", 1, true, "https://www.dndbeyond.com/avatars/22937/354/637776964748720726.jpeg", "sources/motm"),
    JTTRC(87, "JttRC", "Journeys through the Radiant Citadel", 1, true, "https://www.dndbeyond.com/avatars/24454/511/637830510509865265.jpeg", "sources/jttrc"),
    MCV1(89, "MCv1", "Monstrous Compendium Volume One: Spelljammer Creatures", 1, true, "https://www.dndbeyond.com/avatars/25098/972/637854763136224645.jpeg", "sources/mcv1"),
    SAIS(90, "SAiS", "Spelljammer: Adventures in Space", 1, true, "https://www.dndbeyond.com/avatars/25228/876/637859890823057854.jpeg", "sources/sais"),
    TVD(91, "TVD", "The Vecna Dossier", 1, true, "https://www.dndbeyond.com/avatars/26305/340/637901114717317528.jpeg", "sources/tvd"),
    TRC(92, "TRC", "The Radiant Citadel", 1, true, "https://www.dndbeyond.com/avatars/26479/568/637907273106559243.jpeg", "sources/trc"),
    SJA(93, "SJA", "Spelljammer Academy", 1, true, "https://www.dndbeyond.com/avatars/26848/192/637920417931102595.jpeg", "sources/sja"),
    DOSI(94, "DoSI", "Dragons of Stormwreck Isle", 1, true, "https://www.dndbeyond.com/avatars/26865/226/637921086362458107.jpeg", "sources/dosi"),
    SOTDQ(95, "SotDQ", "Dragonlance: Shadow of the Dragon Queen", 14, true, "https://www.dndbeyond.com/avatars/27777/666/637951679601337771.jpeg", "sources/sotdq"),
    ONEDND(100, "One-DnD", "One D&amp;D Playtest", 3, true, "https://www.dndbeyond.com/avatars/28133/674/637963577590732420.jpeg", "sources/one-dnd"),
    MCV2(101, "MCv2", "Monstrous Compendium Volume Two: Dragonlance Creatures", 14, true, "https://www.dndbeyond.com/avatars/30591/814/638054153540284547.jpeg", "sources/mcv2"),
    UNKNOWN(0, "Unknown", "", 1, false, "", "")
}

class Stats {
    companion object {
        fun getStatById(id: Int): Stat? {
            return when (id) {
                in 1.. Stat.values().size -> Stat.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class Stat(val id: Int, val label: String, val entityTypeId: Int, val key: String, val compendiumText: String) {
    STRENGTH(1, "Strength", 1472902489, "STR", "Strength measures bodily power, athletic training, and the extent to which you can exert raw physical force.Strength ChecksA Strength check can model any attempt to lift, push, pull, or break something, to force your body through a space, or to otherwise apply brute force to a situation. The Athletics skill reflects aptitude in certain kinds of Strength checks.AthleticsYour Strength (Athletics) check covers difficult situations you encounter while climbing, jumping, or swimming. Examples include the following activities:You attempt to climb a sheer or slippery cliff, avoid hazards while scaling a wall, or cling to a surface while something is trying to knock you off.You try to jump an unusually long distance or pull off a stunt midjump.You struggle to swim or stay afloat in treacherous currents, storm-tossed waves, or areas of thick seaweed. Or another creature tries to push or pull you underwater or otherwise interfere with your swimming.Other Strength ChecksThe DM might also call for a Strength check when you try to accomplish tasks like the following:Force open a stuck, locked, or barred doorBreak free of bondsPush through a tunnel that is too smallHang on to a wagon while being dragged behind itTip over a statueKeep a boulder from rollingAttack Rolls and DamageYou add your Strength modifier to your attack roll and your damage roll when attacking with a melee weapon such as a mace, a battleaxe, or a javelin. You use melee weapons to make melee attacks in hand-to-hand combat, and some of them can be thrown to make a ranged attack.Lifting and CarryingYour Strength score determines the amount of weight you can bear. The following terms define what you can lift or carry.Carrying Capacity. Your carrying capacity is your Strength score multiplied by 15. This is the weight (in pounds) that you can carry, which is high enough that most characters don't usually have to worry about it.Push, Drag, or Lift. You can push, drag, or lift a weight in pounds up to twice your carrying capacity (or 30 times your Strength score). While pushing or dragging weight in excess of your carrying capacity, your speed drops to 5 feet.Size and Strength. Larger creatures can bear more weight, whereas Tiny creatures can carry less. For each size category above Medium, double the creature's carrying capacity and the amount it can push, drag, or lift. For a Tiny creature, halve these weights.Variant: EncumbranceThe rules for lifting and carrying are intentionally simple. Here is a variant if you are looking for more detailed rules for determining how a character is hindered by the weight of equipment. When you use this variant, ignore the Strength column of the Armor table in chapter 5.If you carry weight in excess of 5 times your Strength score, you are encumbered, which means your speed drops by 10 feet.If you carry weight in excess of 10 times your Strength score, up to your maximum carrying capacity, you are instead heavily encumbered, which means your speed drops by 20 feet and you have disadvantage on ability checks, attack rolls, and saving throws that use Strength, Dexterity, or Constitution."),
    DEXTERITY(2, "Dexterity", 1472902489, "DEX", "Dexterity measures agility, reflexes, and balance.Dexterity ChecksA Dexterity check can model any attempt to move nimbly, quickly, or quietly, or to keep from falling on tricky footing. The Acrobatics, Sleight of Hand, and Stealth skills reflect aptitude in certain kinds of Dexterity checks.AcrobaticsYour Dexterity (Acrobatics) check covers your attempt to stay on your feet in a tricky situation, such as when you're trying to run across a sheet of ice, balance on a tightrope, or stay upright on a rocking ship's deck. The DM might also call for a Dexterity (Acrobatics) check to see if you can perform acrobatic stunts, including dives, rolls, somersaults, and flips.Sleight of HandWhenever you attempt an act of legerdemain or manual trickery, such as planting something on someone else or concealing an object on your person, make a Dexterity (Sleight of Hand) check. The DM might also call for a Dexterity (Sleight of Hand) check to determine whether you can lift a coin purse off another person or slip something out of another person's pocket.StealthMake a Dexterity (Stealth) check when you attempt to conceal yourself from enemies, slink past guards, slip away without being noticed, or sneak up on someone without being seen or heard.Other Dexterity ChecksThe DM might call for a Dexterity check when you try to accomplish tasks like the following:Control a heavily laden cart on a steep descentSteer a chariot around a tight turnPick a lockDisable a trapSecurely tie up a prisonerWriggle free of bondsPlay a stringed instrumentCraft a small or detailed objectAttack Rolls and DamageYou add your Dexterity modifier to your attack roll and your damage roll when attacking with a ranged weapon, such as a sling or a longbow. You can also add your Dexterity modifier to your attack roll and your damage roll when attacking with a melee weapon that has the finesse property, such as a dagger or a rapier.Armor ClassDepending on the armor you wear, you might add some or all of your Dexterity modifier to your Armor Class, as described in chapter 5, \"Equipment.\"InitiativeAt the beginning of every combat, you roll initiative by making a Dexterity check. Initiative determines the order of creatures' turns in combat, as described in chapter 9, \"Combat.\"HIDINGThe DM decides when circumstances are appropriate for hiding. When you try to hide, make a Dexterity (Stealth) check. Until you are discovered or you stop hiding, that check's total is contested by the Wisdom (Perception) check of any creature that actively searches for signs of your presence.You can't hide from a creature that can see you clearly, and you give away your position if you make noise, such as shouting a warning or knocking over a vase. An invisible creature can always try to hide. Signs of its passage might still be noticed, and it does have to stay quiet.In combat, most creatures stay alert for signs of danger all around, so if you come out of hiding and approach a creature, it usually sees you. However, under certain circumstances, the DM might allow you to stay hidden as you approach a creature that is distracted, allowing you to gain advantage on an attack roll before you are seen.Passive Perception. When you hide, there's a chance someone will notice you even if they aren't searching. To determine whether such a creature notices you, the DM compares your Dexterity (Stealth) check with that creature's passive Wisdom (Perception) score, which equals 10 + the creature's Wisdom modifier, as well as any other bonuses or penalties. If the creature has advantage, add 5. For disadvantage, subtract 5.For example, if a 1st-level character (with a proficiency bonus of +2) has a Wisdom of 15 (a +2 modifier) and proficiency in Perception, he or she has a passive Wisdom (Perception) of 14.What Can You See? One of the main factors in determining whether you can find a hidden creature or object is how well you can see in an area, which might be lightly or heavily obscured&nbsp;as explained in chapter 8, “Adventuring.”"),
    CONSTITUTION(3, "Constitution", 1472902489, "CON", "Constitution measures health, stamina, and vital force.Constitution ChecksConstitution checks are uncommon, and no skills apply to Constitution checks, because the endurance this ability represents is largely passive rather than involving a specific effort on the part of a character or monster. A Constitution check can model your attempt to push beyond normal limits, however.The DM might call for a Constitution check when you try to accomplish tasks like the following:Hold your breathMarch or labor for hours without restGo without sleepSurvive without food or waterQuaff an entire stein of ale in one goHit PointsYour Constitution modifier contributes to your hit points. Typically, you add your Constitution modifier to each Hit Die you roll for your hit points.If your Constitution modifier changes, your hit point maximum changes as well, as though you had the new modifier from 1st level. For example, if you raise your Constitution score when you reach 4th level and your Constitution modifier increases from +1 to +2, you adjust your hit point maximum as though the modifier had always been +2. So you add 3 hit points for your first three levels, and then roll your hit points for 4th level using your new modifier. Or if you're 7th level and some effect lowers your Constitution score so as to reduce your Constitution modifier by 1, your hit point maximum is reduced by 7."),
    INTELLIGENCE(4, "Intelligence", 1472902489, "INT", "Intelligence measures mental acuity, accuracy of recall, and the ability to reason.Intelligence ChecksAn Intelligence check comes into play when you need to draw on logic, education, memory, or deductive reasoning. The Arcana, History, Investigation, Nature, and Religion skills reflect aptitude in certain kinds of Intelligence checks.ArcanaYour Intelligence (Arcana) check measures your ability to recall lore about spells, magic items, eldritch symbols, magical traditions, the planes of existence, and the inhabitants of those planes.HistoryYour Intelligence (History) check measures your ability to recall lore about historical events, legendary people, ancient kingdoms, past disputes, recent wars, and lost civilizations.InvestigationWhen you look around for clues and make deductions based on those clues, you make an Intelligence (Investigation) check. You might deduce the location of a hidden object, discern from the appearance of a wound what kind of weapon dealt it, or determine the weakest point in a tunnel that could cause it to collapse. Poring through ancient scrolls in search of a hidden fragment of knowledge might also call for an Intelligence (Investigation) check.NatureYour Intelligence (Nature) check measures your ability to recall lore about terrain, plants and animals, the weather, and natural cycles.ReligionYour Intelligence (Religion) check measures your ability to recall lore about deities, rites and prayers, religious hierarchies, holy symbols, and the practices of secret cults.Other Intelligence ChecksThe DM might call for an Intelligence check when you try to accomplish tasks like the following:Communicate with a creature without using wordsEstimate the value of a precious itemPull together a disguise to pass as a city guardForge a documentRecall lore about a craft or tradeWin a game of skillSpellcasting AbilityWizards use Intelligence as their spellcasting ability, which helps determine the saving throw DCs of spells they cast."),
    WISDOM(5, "Wisdom", 1472902489, "WIS", "Wisdom reflects how attuned you are to the world around you and represents perceptiveness and intuition.Wisdom ChecksA Wisdom check might reflect an effort to read body language, understand someone’s feelings, notice things about the environment, or care for an injured person. The Animal Handling, Insight, Medicine, Perception, and Survival skills reflect aptitude in certain kinds of Wisdom checks.Animal HandlingWhen there is any question whether you can calm down a domesticated animal, keep a mount from getting spooked, or intuit an animal’s intentions, the DM might call for a Wisdom (Animal Handling) check. You also make a Wisdom (Animal Handling) check to control your mount when you attempt a risky maneuver.InsightYour Wisdom (Insight) check decides whether you can determine the true intentions of a creature, such as when searching out a lie or predicting someone’s next move. Doing so involves gleaning clues from body language, speech habits, and changes in mannerisms.MedicineA Wisdom (Medicine) check lets you try to stabilize a dying companion or diagnose an illness.PerceptionYour Wisdom (Perception) check lets you spot, hear, or otherwise detect the presence of something. It measures your general awareness of your surroundings and the keenness of your senses. For example, you might try to hear a conversation through a closed door, eavesdrop under an open window, or hear monsters moving stealthily in the forest. Or you might try to spot things that are obscured or easy to miss, whether they are orcs lying in ambush on a road, thugs hiding in the shadows of an alley, or candlelight under a closed secret door.FINDING A HIDDEN OBJECTWhen your character searches for a hidden object such as a secret door or a trap, the DM typically asks you to make a Wisdom (Perception) check. Such a check can be used to find hidden details or other information and clues that you might otherwise overlook.In most cases, you need to describe where you are looking in order for the DM to determine your chance of success. For example, a key is hidden beneath a set of folded clothes in the top drawer of a bureau. If you tell the DM that you pace around the room, looking at the walls and furniture for clues, you have no chance of finding the key, regardless of your Wisdom (Perception) check result. You would have to specify that you were opening the drawers or searching the bureau in order to have any chance of success.SurvivalThe DM might ask you to make a Wisdom (Survival) check to follow tracks, hunt wild game, guide your group through frozen wastelands, identify signs that owlbears live nearby, predict the weather, or avoid quicksand and other natural hazards.Other Wisdom ChecksThe DM might call for a Wisdom check when you try to accomplish tasks like the following:Get a gut feeling about what course of action to followDiscern whether a seemingly dead or living creature is undeadSpellcasting AbilityClerics, druids, and rangers use Wisdom as their spellcasting ability, which helps determine the saving throw DCs of spells they cast."),
    CHARISMA(6, "Charisma", 1472902489, "CHA", "Charisma measures your ability to interact effectively with others. It includes such factors as confidence and eloquence, and it can represent a charming or commanding personality.Charisma ChecksA Charisma check might arise when you try to influence or entertain others, when you try to make an impression or tell a convincing lie, or when you are navigating a tricky social situation. The Deception, Intimidation, Performance, and Persuasion skills reflect aptitude in certain kinds of Charisma checks.DeceptionYour Charisma (Deception) check determines whether you can convincingly hide the truth, either verbally or through your actions. This deception can encompass everything from misleading others through ambiguity to telling outright lies. Typical situations include trying to fast-talk a guard, con a merchant, earn money through gambling, pass yourself off in a disguise, dull someone's suspicions with false assurances, or maintain a straight face while telling a blatant lie.IntimidationWhen you attempt to influence someone through overt threats, hostile actions, and physical violence, the DM might ask you to make a Charisma (Intimidation) check. Examples include trying to pry information out of a prisoner, convincing street thugs to back down from a confrontation, or using the edge of a broken bottle to convince a sneering vizier to reconsider a decision.PerformanceYour Charisma (Performance) check determines how well you can delight an audience with music, dance, acting, storytelling, or some other form of entertainment.PersuasionWhen you attempt to influence someone or a group of people with tact, social graces, or good nature, the DM might ask you to make a Charisma (Persuasion) check. Typically, you use persuasion when acting in good faith, to foster friendships, make cordial requests, or exhibit proper etiquette. Examples of persuading others include convincing a chamberlain to let your party see the king, negotiating peace between warring tribes, or inspiring a crowd of townsfolk.Other Charisma ChecksThe DM might call for a Charisma check when you try to accomplish tasks like the following:Find the best person to talk to for news, rumors, and gossipBlend into a crowd to get the sense of key topics of conversationSpellcasting AbilityBards, paladins, sorcerers, and warlocks use Charisma as their spellcasting ability, which helps determine the saving throw DCs of spells they cast.")
}

class Skills {
    companion object {
        fun getSkillById(id: Int): Skill {
            return Skill.values().filter { it.id == id }.getOrNull(0) ?: Skill.UNKNOWN
        }
    }
}

enum class Skill(val id: Int, val label: String, val entityTypeId: Int, val stat: Int, val description: String) {
    ATHLETICS(2, "Athletics", 1958004211, 1, "Your Strength (Athletics) check covers difficult situations you encounter while climbing, jumping, or swimming. Examples include the following activities:You attempt to climb a sheer or slippery cliff, avoid hazards while scaling a wall, or cling to a surface while something is trying to knock you off.You try to jump an unusually long distance or pull off a stunt midjump.You struggle to swim or stay afloat in treacherous currents, storm-tossed waves, or areas of thick seaweed. Or another creature tries to push or pull you underwater or otherwise interfere with your swimming."),
    ACROBATICS(3, "Acrobatics", 1958004211, 2, "Your Dexterity (Acrobatics) check covers your attempt to stay on your feet in a tricky situation, such as when you're trying to run across a sheet of ice, balance on a tightrope, or stay upright on a rocking ship's deck. The GM might also call for a Dexterity (Acrobatics) check to see if you can perform acrobatic stunts, including dives, rolls, somersaults, and flips."),
    SLEIGHTOFHAND(4, "Sleight of Hand", 1958004211, 2, "Whenever you attempt an act of legerdemain or manual trickery, such as planting something on someone else or concealing an object on your person, make a Dexterity (Sleight of Hand) check. The GM might also call for a Dexterity (Sleight of Hand) check to determine whether you can lift a coin purse off another person or slip something out of another person's pocket."),
    STEALTH(5, "Stealth", 1958004211, 2, "Make a Dexterity (Stealth) check when you attempt to conceal yourself from enemies, slink past guards, slip away without being noticed, or sneak up on someone without being seen or heard."),
    ARCANA(6, "Arcana", 1958004211, 4, "Your Intelligence (Arcana) check measures your ability to recall lore about spells, magic items, eldritch symbols, magical traditions, the planes of existence, and the inhabitants of those planes."),
    HISTORY(7, "History", 1958004211, 4, "Your Intelligence (History) check measures your ability to recall lore about historical events, legendary people, ancient kingdoms, past disputes, recent wars, and lost civilizations."),
    INVESTIGATION(8, "Investigation", 1958004211, 4, "When you look around for clues and make deductions based on those clues, you make an Intelligence (Investigation) check. You might deduce the location of a hidden object, discern from the appearance of a wound what kind of weapon dealt it, or determine the weakest point in a tunnel that could cause it to collapse. Poring through ancient scrolls in search of a hidden fragment of knowledge might also call for an Intelligence (Investigation) check."),
    NATURE(9, "Nature", 1958004211, 4, "Your Intelligence (Nature) check measures your ability to recall lore about terrain, plants and animals, the weather, and natural cycles."),
    RELIGION(10, "Religion", 1958004211, 4, "Your Intelligence (Religion) check measures your ability to recall lore about deities, rites and prayers, religious hierarchies, holy symbols, and the practices of secret cults."),
    ANIMALHANDLING(11, "Animal Handling", 1958004211, 5, "When there is any question whether you can calm down a domesticated animal, keep a mount from getting spooked, or intuit an animal’s intentions, the GM might call for a Wisdom (Animal Handling) check. You also make a Wisdom (Animal Handling) check to control your mount when you attempt a risky maneuver."),
    INSIGHT(12, "Insight", 1958004211, 5, "Your Wisdom (Insight) check decides whether you can determine the true intentions of a creature, such as when searching out a lie or predicting someone’s next move. Doing so involves gleaning clues from body language, speech habits, and changes in mannerisms."),
    MEDICINE(13, "Medicine", 1958004211, 5, "A Wisdom (Medicine) check lets you try to stabilize a dying companion or diagnose an illness."),
    PERCEPTION(14, "Perception", 1958004211, 5, "Your Wisdom (Perception) check lets you spot, hear, or otherwise detect the presence of something. It measures your general awareness of your surroundings and the keenness of your senses. For example, you might try to hear a conversation through a closed door, eavesdrop under an open window, or hear monsters moving stealthily in the forest. Or you might try to spot things that are obscured or easy to miss, whether they are orcs lying in ambush on a road, thugs hiding in the shadows of an alley, or candlelight under a closed secret door."),
    SURVIVAL(15, "Survival", 1958004211, 5, "The GM might ask you to make a Wisdom (Survival) check to follow tracks, hunt wild game, guide your group through frozen wastelands, identify signs that owlbears live nearby, predict the weather, or avoid quicksand and other natural hazards."),
    DECEPTION(16, "Deception", 1958004211, 6, "Your Charisma (Deception) check determines whether you can convincingly hide the truth, either verbally or through your actions. This deception can encompass everything from misleading others through ambiguity to telling outright lies. Typical situations include trying to fast-talk a guard, con a merchant, earn money through gambling, pass yourself off in a disguise, dull someone's suspicions with false assurances, or maintain a straight face while telling a blatant lie."),
    INTIMIDATION(17, "Intimidation", 1958004211, 6, "When you attempt to influence someone through overt threats, hostile actions, and physical violence, the GM might ask you to make a Charisma (Intimidation) check. Examples include trying to pry information out of a prisoner, convincing street thugs to back down from a confrontation, or using the edge of a broken bottle to convince a sneering vizier to reconsider a decision."),
    PERFORMANCE(18, "Performance", 1958004211, 6, "Your Charisma (Performance) check determines how well you can delight an audience with music, dance, acting, storytelling, or some other form of entertainment."),
    PERSUASION(19, "Persuasion", 1958004211, 6, "When you attempt to influence someone or a group of people with tact, social graces, or good nature, the GM might ask you to make a Charisma (Persuasion) check. Typically, you use persuasion when acting in good faith, to foster friendships, make cordial requests, or exhibit proper etiquette. Examples of persuading others include convincing a chamberlain to let your party see the king, negotiating peace between warring tribes, or inspiring a crowd of townsfolk."),
    UNKNOWN(99, "Unknown", 0, 0, "")
}

class Senses {
    companion object {
        fun getSenseById(id: Int): Sense? {
            return when (id) {
                in 1.. Sense.values().size -> Sense.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class Sense(val id: Int, val label: String, val entityTypeId: Int) {
    BLINDSIGHT(1, "Blindsight", 668550506),
    DARKVISION(2, "Darkvision", 668550506),
    TREMORSENSE(3, "Tremorsense", 668550506),
    TRUESIGHT(4, "Truesight", 668550506),
    UNKNOWN(5, "Unknown", 668550506)
}

class Sizes {
    companion object {
        fun getSizeById(id: Int): Size? {
            return Size.values().filter { it.id == id }.getOrNull(0)
        }
    }
}

enum class Size(val id: Int, val label: String, val entityTypeId: Int, val weightType: Int) {
    TINY(2, "Tiny", 127108918, 1),
    SMALL(3, "Small", 127108918, 2),
    MEDIUM(4, "Medium", 127108918, 2),
    LARGE(5, "Large", 127108918, 3),
    HUGE(6, "Huge", 127108918, 3),
    GARGANTUAN(7, "Gargantuan", 127108918, 3),
    MEDIUMORSMALL(10, "Medium or Small", 127108918, 2)
}

class ResetTypes {
    companion object {
        fun getResetTypeById(id: Int): ResetType? {
            return when (id) {
                in 1.. ResetType.values().size -> ResetType.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class ResetType(val id: Int, val label: String) {
    SHORTREST(1, "Short Rest"),
    LONGREST(2, "Long Rest"),
    DAWN(3, "Dawn"),
    OTHER(4, "Other")
}

class SourceCategories {
    companion object {
        fun getSourceCategoryById(id: Int): SourceCategory? {
            return when (id) {
                in 1.. SourceCategory.values().size -> SourceCategory.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class SourceCategory(val id: Int, val label: String, val description: String?, val isHideable: Boolean, val isEnabledByDefault: Boolean, val isToggleable: Boolean, val avatarUrl: String) {
    COREDND(1, "Core DD", null, false, true, false, ""),
    CRITICALROLE(2, "Critical Role", null, true, false, true, "https://www.dndbeyond.com/avatars/105/174/636512853628516966.png"),
    PLAYTEST(3, "Playtest", "THIS IS UNOFFICIAL MATERIALThe material here is presented for playtesting and to spark your imagination. These game mechanics are in draft form, usable in your campaign but not refined by&nbsp;full game design and editing. They aren’t officially part of the game and aren’t permitted in D&amp;D Adventurers League events.If this material is made official, it will be refined based on your feedback, and then it will appear in a D&amp;D product that you can unlock on DDB.If this material is not made official, it will be removed from D&amp;D Beyond following the playtest period and you will need to replace it with another option.", true, false, false, "https://www.dndbeyond.com/avatars/110/171/636516074887091041.png"),
    DNDBEYOND(6, "D&amp;D Beyond", "D&amp;D Beyond", false, false, false, ""),
    MAGICTHEGATHERING(7, "Magic: The Gathering", "Magic: The Gathering content for fifth edition Dungeons &amp; Dragons", false, true, true, ""),
    EBERRON(8, "Eberron", "The Eberron campaign setting for&nbsp;fifth edition Dungeons &amp; Dragons", false, true, true, "https://www.dndbeyond.com/avatars/2588/861/636681435608150525.png"),
    ARCHIVED(9, "Archived", "The Graveyard", false, false, false, ""),
    RICKANDMORTY(10, "Rick and Morty", "Source category for Rick and Morty products.", true, false, true, ""),
    RUNETERRA(11, "Runeterra", "THIS IS UNOFFICIAL MATERIALThese game mechanics are usable in your campaign if your DM allows them but not refined by final game design and editing. They aren’t officially part of the Dungeons &amp; Dragons game and aren’t permitted in D&amp;D Adventurers League events unless otherwise stated.", false, false, false, "https://www.dndbeyond.com/avatars/11008/904/637274855809570341.png"),
    NONCOREDND(12, "Noncore D&amp;D", "THIS IS NONCORE D&amp;D MATERIALThese game mechanics are usable in your campaign if your DM allows them but not refined by final game design and editing.", false, false, true, ""),
    ADVENTURERSLEAGUE(13, "Adventurers League", "Adventurers League content for fifth edition Dungeons &amp; Dragons", false, false, false, ""),
    DRAGONLANCE(14, "Dragonlance", "The Dragonlance campaign setting for fifth edition Dungeons &amp; Dragons.", false, true, true, "")
}

class Movements {
    companion object {
        fun getMovementById(id: Int): Movement? {
            return when (id) {
                in 1.. Movement.values().size -> Movement.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class Movement(val id: Int, val label: String, val description: String) {
    WALK(1, "Walk", "Walking"),
    BURROW(2, "Burrow", "Burrowing"),
    CLIMB(3, "Climb", "Climbing"),
    FLY(4, "Fly", "Flying"),
    SWIM(5, "Swim", "Swimming")
}

class ProficiencyGroups {
    companion object {
        fun getProficiencyGroupById(id: Int): ProficiencyGroup? {
            return when (id) {
                in 1.. ProficiencyGroup.values().size -> ProficiencyGroup.values()[id - 1]
                else -> null
            }
        }
    }
}

enum class ProficiencyGroup(val label: String, val customProficiencyGroup: Int, val customAdjustments: List<Int>, val entityTypeIds: List<Int>) {
    ARMOR("Armor", 4, listOf(32), listOf(701257905, 174869515)),
    WEAPON("Weapons", 5, listOf(33), listOf(1782728300, 660121713)),
    TOOL("Tools", 2, listOf(34), listOf(2103445194, 1452973421)),
    LANUAGE("Languages", 3, listOf(35), listOf(906033267))
}

