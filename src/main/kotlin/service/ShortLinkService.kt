package service

import dao.ShortLinkDAO
import model.ShortLink

class ShortLinkService {
    // Define acceptable characters for generated urls, in this case alphanumeric
    private val allowedCharacters = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private val defaultShortenedLength = 6

    private val dao = ShortLinkDAO();

    fun createLink(originalURL: String): ShortLink {
        // If link already exists with the same destination then return that value
        if (entryExistsForDestinationURL(originalURL)) {
            return dao.findByDestinationURL(validateUrl(originalURL))!!
        }
        // If not, create a new link and return it
        val newShortLink = ShortLink(validateUrl(originalURL), generateShortenedURL())
        dao.addNewShortLink(newShortLink)
        return newShortLink
    }

    fun getDestinationForShortLink(shortURL: String): ShortLink {
        return dao.findByShortURL(shortURL)!!
    }

    private fun entryExistsForDestinationURL(destinationURL: String): Boolean {
        return dao.findByDestinationURL(validateUrl(destinationURL)) != null
    }

    fun shortLinkExists(shortLink: String): Boolean {
        return dao.findByShortURL(shortLink) != null
    }

    // Generate a short collection of random characters for the links
    private fun generateShortenedURL(): String {
        var newShortLink: String

        do {
            newShortLink = (1..defaultShortenedLength)
                .map { allowedCharacters.random() }
                .joinToString("")
        } while (shortLinkExists(newShortLink)) // Keep generating links until there is no collision

        return newShortLink
    }

    // Redirect URLs must contain http for redirecting to absolute values
    // Otherwise the application attempts a relative redirect so add http if its not present
    private fun validateUrl(url: String): String {
        if (!url.contains("http://", ignoreCase = true)
            && !url.contains("https://", ignoreCase = true)
        ) {
            return "http://$url";
        } else {
            return url
        }
    }
}