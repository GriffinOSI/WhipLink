package dao

import model.ShortLink
import org.litote.kmongo.KMongo
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

class ShortLinkDAO {
    private val client = KMongo.createClient(System.getenv("DB_URL"))
    private val database = client.getDatabase("Production")
    private val col = database.getCollection<ShortLink>("shortUrls")

    fun findByDestinationURL(destinationUrl: String): ShortLink? {
        return col.findOne(ShortLink::destinationURL eq destinationUrl)
    }

    fun findByShortURL(shortUrl: String): ShortLink? {
        return col.findOne(ShortLink::generatedURL eq shortUrl)
    }

    fun addNewShortLink(newLink: ShortLink) {
        col.insertOne(newLink)
    }
}
