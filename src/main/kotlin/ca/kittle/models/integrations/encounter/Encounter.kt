package ca.kittle.models.integrations.encounter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Encounter (

  @SerialName("id"             ) var id             : String,
  @SerialName("copiedFromId"   ) var copiedFromId   : String?             = null,
  @SerialName("userId"         ) var userId         : Int,
  @SerialName("name"           ) var name           : String,
  @SerialName("map"            ) var map            : String?             = null,
  @SerialName("room"           ) var room           : String?             = null,
  @SerialName("source"         ) var source         : String?             = null,
  @SerialName("inProgress"     ) var inProgress     : Boolean?            = null,
  @SerialName("roundNum"       ) var roundNum       : Int?                = null,
  @SerialName("turnNum"        ) var turnNum        : Int?                = null,
  @SerialName("notes"          ) var notes          : String?             = null,
  @SerialName("monsters"       ) var monsters       : ArrayList<Monster> = arrayListOf(),
  @SerialName("groups"         ) var groups         : ArrayList<Group>   = arrayListOf(),
  @SerialName("players"        ) var players        : ArrayList<Player>  = arrayListOf(),
  @SerialName("manualEntries"  ) var manualEntries  : ArrayList<ManualEntry>?   = null,
  @SerialName("difficulty"     ) var difficulty     : Int,
  @SerialName("dateCreated"    ) var dateCreated    : Long?                = null,
  @SerialName("dateModified"   ) var dateModified   : Long?                = null,
  @SerialName("versionNumber"  ) var versionNumber  : Int?                = null,
  @SerialName("status"         ) var status         : Int?                = null,
  @SerialName("campaign"       ) var campaign       : Campaign?           = Campaign(),
  @SerialName("flavorText"     ) var flavorText     : String?             = null,
  @SerialName("description"    ) var description    : String?             = null,
  @SerialName("rewards"        ) var rewards        : String?             = null,
  @SerialName("compendiumLink" ) var compendiumLink : String?             = null

)

