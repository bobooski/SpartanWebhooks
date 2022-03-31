package net.ecoporium.webhooks.manager

import club.minnced.discord.webhook.WebhookClient
import club.minnced.discord.webhook.send.WebhookEmbed
import club.minnced.discord.webhook.send.WebhookMessage
import net.ecoporium.webhooks.SpartanWebhooks
import java.io.IOException
import java.util.concurrent.LinkedBlockingQueue

class WebhookManager(private val plugin: SpartanWebhooks) {

    val embeds by lazy { LinkedBlockingQueue<WebhookEmbed>() }

    private fun send(message: WebhookMessage) {
        try {
            val content = plugin.getWebhookConfig().content.trim()
            val client = WebhookClient.withUrl(plugin.getSettingsConfig().webhookUrl)
            if (content.isNotEmpty()) client.send(content)
            client.send(message)
        } catch (exception: IOException) {
            if(plugin.getSettingsConfig().debug) exception.printStackTrace()
        }
    }

    fun run(): () -> Unit = {
        if (embeds.isNotEmpty()) {
            val embed = embeds.poll()
            if (embed != null) send(WebhookMessage.embeds(embed))
        }
    }

}