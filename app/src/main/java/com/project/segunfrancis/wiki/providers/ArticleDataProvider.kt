package com.project.segunfrancis.wiki.providers

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.project.segunfrancis.wiki.models.WikiResult
import java.io.Reader
import com.project.segunfrancis.wiki.models.Urls
import java.lang.Exception

/**
 * Created by SegunFrancis
 */
class ArticleDataProvider {

    // Set user agent data
    init {
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "My Wiki")
    }

    fun search(term: String, skip: Int, take: Int, responseHandler: (result: WikiResult) -> Unit?) {
        Urls.getSearchUrl(term, skip, take).httpGet()
            .responseObject(WikipediaDataDeserializer()) { _, response, result ->
                if (response.statusCode != 200) {
                    throw Exception("Unable to get articles")
                }
                val (data, _) = result
                responseHandler.invoke(data as WikiResult)
            }
    }

    fun getRandom(take: Int, responseHandler: (result: WikiResult) -> Unit?) {
        Urls.getRandomUrl(take).httpGet()
            .responseObject(WikipediaDataDeserializer()) { _, response, result ->
                if (response.statusCode != 200) {
                    throw Exception("Unable to get articles")
                }
                val (data, _) = result
                responseHandler.invoke(data as WikiResult)
            }
    }

    class WikipediaDataDeserializer : ResponseDeserializable<WikiResult> {
        override fun deserialize(reader: Reader): WikiResult? {
            return Gson().fromJson(reader, WikiResult::class.java)
        }
    }
}