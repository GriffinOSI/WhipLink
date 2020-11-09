package model

import org.bson.codecs.pojo.annotations.BsonId

data class ShortLink (@BsonId val destinationURL: String,
                      val generatedURL: String)