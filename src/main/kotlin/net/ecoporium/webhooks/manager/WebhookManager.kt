package net.ecoporium.webhooks.manager

import club.minnced.discord.webhook.WebhookClient
import club.minnced.discord.webhook.send.WebhookEmbed
import club.minnced.discord.webhook.send.WebhookMessage
import club.minnced.discord.webhook.send.WebhookMessageBuilder
import net.ecoporium.webhooks.SpartanWebhooks
import java.io.IOException

class WebhookManager(private val plugin: SpartanWebhooks) {

    val webhooks by lazy { mutableListOf<Pair<String, WebhookEmbed>>() }

    fun load() {
        webhooks.clear()
        val period = if (plugin.getWebhookConfig().contentFor(null).isEmpty()) 40L else 80L
        plugin.server.scheduler.runTaskTimerAsynchronously(plugin, this::runQueue, 0L, period)
    }

    private fun send(content: String, message: WebhookMessage) {
        try {
            val client = WebhookClient.withUrl(plugin.getSettingsConfig().webhookUrl)
            if (content.isNotEmpty()) client.send(content)
            client.send(message)
        } catch (exception: IOException) {
            if(plugin.getSettingsConfig().debug) exception.printStackTrace()
        }
    }

    private fun runQueue(): () -> Unit = {
        if (webhooks.isNotEmpty()) {
            val messageBuilder = WebhookMessageBuilder()
            val amount: Int = if (webhooks.size > 10) 10 else webhooks.size
            val content = webhooks.first().first
            for (i in 0 until amount) {
                messageBuilder.addEmbeds(webhooks.removeAt(i).second)
            }
            send(content, messageBuilder.build())
        }
    }

}