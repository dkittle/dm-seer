package ca.kittle

import ca.kittle.integrations.mapping.DdbCreature
import kotlin.test.Test
import kotlin.test.assertEquals

class TestFixCreatureUrls {

    @Test
    fun testFixSolarUrls() {
        val url = "https://www.dndbeyond.com/avatars/0/120/636252748079195305.jpeg"
        assertEquals("https://www.dndbeyond.com/avatars/0/120/636252748079195305.jpeg", DdbCreature.fixCreatureUrl(url))
        val large = "https://www.dndbeyond.com/avatars/thumbnails/30761/809/400/271/638061094428241214.png"
        assertEquals("https://www.dndbeyond.com/avatars/thumbnails/30761/809/1000/1000/638061094428241214.png", DdbCreature.fixCreatureUrl(large))
        val basic = "https://www.dndbeyond.com.com/avatars/30761/810/638061094430881219.png"
        assertEquals("https://www.dndbeyond.com/avatars/30761/810/638061094430881219.png", DdbCreature.fixCreatureUrl(basic))
    }
}
