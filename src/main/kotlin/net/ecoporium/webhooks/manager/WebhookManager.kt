package net.ecoporium.webhooks.manager

import club.minnced.discord.webhook.WebhookClient
import club.minnced.discord.webhook.send.WebhookEmbed
import club.minnced.discord.webhook.send.WebhookEmbedBuilder
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
            val toAdd = mutableListOf<WebhookEmbed>()
            for (i in 0 until 10) {
                val embed = embeds.poll()
                if (embed != null) {
                    val title = embed.title
                    if (title != null) {
                        val url = if (title.url != null) "${title.url}?embed=${i}" else null
                        toAdd.add(embed.builder().setTitle(WebhookEmbed.EmbedTitle(title.text, url)).build())
                    } else {
                        toAdd.add(embed)
                    }
                }
                else break
            }
            send(WebhookMessage.embeds(toAdd))
        }
    }

    /**
     * Copies all the fields from the given [WebhookEmbed] to a new embed, and returns a [WebhookEmbedBuilder] for it
     */
    private fun WebhookEmbed.builder(): WebhookEmbedBuilder {
        val builder = WebhookEmbedBuilder()
            .setTitle(this.title)
            .setDescription(this.description)
            .setAuthor(this.author)
            .setColor(this.color)
            .setFooter(this.footer)
            .setImageUrl(this.imageUrl)
            .setThumbnailUrl(this.thumbnailUrl)
            .setTimestamp(this.timestamp)
        this.fields.forEach { builder.addField(it) }
        return builder
    }

}