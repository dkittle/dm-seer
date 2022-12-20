package ca.kittle.models

data class ChallengeRating(val id: Int, val name: String, val cr: Float) {
    companion object {
        fun getChallengeRatingById(id: Int): ChallengeRating? {
            for (cr in ChallengeRatings.values()) {
                if (cr.id == id)
                    return ChallengeRating(cr.id, cr.label, cr.cr)
            }
            return null
        }
        fun getChallengeRatingByLabel(label: String): ChallengeRating? {
            for (cr in ChallengeRatings.values()) {
                if (cr.label.equals(label))
                    return ChallengeRating(cr.id, cr.label, cr.cr)
            }
            return null
        }
    }
}

enum class ChallengeRatings(val id: Int, val label: String, val cr: Float) {
    ZERO(1, "0", 0f),
    ONEEIGHTH(2, "1/8", 0.125f),
    ONEQUARTER(3, "1/4", 0.25f),
    ONEHALF(4, "1/2", 0.5f),
    CR1(5, "1", 1f),
    CR2(6, "2", 2f),
    CR3(7, "3", 3f),
    CR4(8, "4", 4f),
    CR5(9, "5", 5f),
    CR6(10, "6", 6f),
    CR7(11, "7", 7f),
    CR8(12, "8", 8f),
    CR9(13, "9", 9f),
    CR10(14, "10", 10f),
    CR11(15, "11", 11f),
    CR12(16, "12", 12f),
    CR13(17, "13", 13f),
    CR14(18, "14", 14f),
    CR15(19, "15", 15f),
    CR16(20, "16", 16f),
    CR17(21, "17", 17f),
    CR18(22, "18", 18f),
    CR19(23, "19", 19f),
    CR20(24, "20", 20f),
    CR21(25, "21", 21f),
    CR22(26, "22", 22f),
    CR23(27, "23", 23f),
    CR24(28, "24", 24f),
    CR25(29, "25", 25f),
    CR26(30, "26", 26f),
    CR27(31, "27", 27f),
    CR28(32, "28", 28f),
    CR29(33, "29", 29f),
    CR30(34, "30", 30f)
}

