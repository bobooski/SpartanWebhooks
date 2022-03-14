package net.ecoporium.webhooks.manager

import club.minnced.discord.webhook.WebhookClient
import club.minnced.discord.webhook.send.WebhookEmbed
import java.io.IOException

class WebhookManager {

    fun send(webhook: WebhookEmbed, content: String, url: String) {
        try {
            val client = WebhookClient.withUrl(url)
            client.send(content)
            client.send(webhook)
        } catch (exception: IOException) {
            exception.printStackTrace()
        }
    }

}