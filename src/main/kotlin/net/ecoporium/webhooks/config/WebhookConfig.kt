package net.ecoporium.webhooks.config

import club.minnced.discord.webhook.send.WebhookEmbed
import club.minnced.discord.webhook.send.WebhookEmbed.EmbedAuthor
import club.minnced.discord.webhook.send.WebhookEmbed.EmbedField
import club.minnced.discord.webhook.send.WebhookEmbed.EmbedFooter
import com.electronwill.nightconfig.core.file.FileConfig
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.entity.Player
import java.time.OffsetDateTime
import java.util.stream.Collectors

class WebhookConfig(private val config: FileConfig) {

    fun contentFor(player: Player): String {
        val content: String = config["content"]
        return PlaceholderAPI.setPlaceholders(player, content)
    }

    fun embedFor(player: Player): WebhookEmbed {
        val rawFields: List<FileConfig> = config["embed.fields"]
        val fields: List<EmbedField> = rawFields.stream()
            .map { embedField(it, player) }.collect(Collectors.toList())


        return WebhookEmbed(
            OffsetDateTime.now(),
            config["embed.color"],
            basicParse("embed.description", player),
            basicParse("embed.thumbnail.url", player),
            basicParse("embed.image.url", player),
            embedFooter(player),
            embedTitle(player),
            embedAuthor(player),
            fields
        )
    }

    private fun embedField(config: FileConfig, player: Player): EmbedField {
        val inline: Boolean? = config["inline"]
        val name: String = config["name"]
        val value: String = config["value"]

        return EmbedField(
            inline ?: false,
            PlaceholderAPI.setPlaceholders(player, name),
            PlaceholderAPI.setPlaceholders(player, value)
        )
    }

    private fun embedTitle(player: Player): WebhookEmbed.EmbedTitle {
        val title: String = config["embed.title"]
        val url: String? = config["embed.url"]

        return WebhookEmbed.EmbedTitle(
            PlaceholderAPI.setPlaceholders(player, title),
            url?.let { PlaceholderAPI.setPlaceholders(player, it) }
        )
    }

    private fun embedAuthor(player: Player): EmbedAuthor {
        val name: String = config["embed.author.name"]
        val iconUrl: String? = config["embed.author.icon_url"]
        val url: String? = config["embed.author.url"]

        return EmbedAuthor(
            PlaceholderAPI.setPlaceholders(player, name),
            iconUrl?.let { PlaceholderAPI.setPlaceholders(player, it) },
            url?.let { PlaceholderAPI.setPlaceholders(player, it) }
        )
    }

    private fun embedFooter(player: Player): EmbedFooter {
        val text: String = config["embed.footer.text"]
        val iconUrl: String? = config["embed.footer.icon_url"]

        return EmbedFooter(
            PlaceholderAPI.setPlaceholders(player, text),
            iconUrl?.let { PlaceholderAPI.setPlaceholders(player, it) }
        )
    }

    private fun basicParse(path: String, player: Player): String? {
        val str: String? = config[path]
        return str?.let { PlaceholderAPI.setPlaceholders(player, it) }
    }

}