package ru.lavafrai.exler.mai

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class ExlerNetworkHelper {
    companion object {
        private const val EXLER_DOMAIN = "https://mai-exler.ru/"


        fun getPage(path: String, attemptsLeft: Int = 5): Document {
            if (attemptsLeft < 0) throw IOException()
            val url = "$EXLER_DOMAIN/$path"

            return try {
                Jsoup
                    .connect(url)
                    .get()
            } catch (e: Exception) {
                e.printStackTrace()
                Thread.sleep(100)
                getPage(path, attemptsLeft - 1)
            }
        }
    }
}


/*
var builtUrl = url
    if (!builtUrl.endsWith("?")) builtUrl += "?"
    args.forEach {
        builtUrl += "&${it.key}=${it.value}"
    }
    if (attemptsLeft < 0) throw IOException()

    try {
        return Jsoup
            .connect(builtUrl)
            .header("Cookie", COOKIES_CONSTANT)
            .validateTLSCertificates(false)
            .get()
    } catch (e: Exception) {
        e.printStackTrace()
        Thread.sleep(100)
        return getPage(url, args, attemptsLeft - 1)
 */