package net.ecoporium.webhooks.config

import club.minnced.discord.webhook.send.WebhookEmbed
import club.minnced.discord.webhook.send.WebhookEmbed.EmbedField
import club.minnced.discord.webhook.send.WebhookEmbedBuilder
import com.electronwill.nightconfig.core.file.FileConfig
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.entity.Player
import java.util.stream.Collectors

class WebhookConfig(private val config: FileConfig) {

    val content: String = config["content"]

    fun embedFor(player: Player): WebhookEmbed {

        val rawFields: List<FileConfig> = config["embed.fields"]

        return WebhookEmbedBuilder().build()

    }

}