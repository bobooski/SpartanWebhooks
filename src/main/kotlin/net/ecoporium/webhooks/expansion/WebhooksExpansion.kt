package net.ecoporium.webhooks.expansion

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import me.vagdedes.spartan.api.API
import net.ecoporium.webhooks.SpartanWebhooks
import net.ecoporium.webhooks.listener.ViolationHandler
import org.bukkit.entity.Player
import java.math.RoundingMode
import java.text.DecimalFormat

class WebhooksExpansion(private val plugin: SpartanWebhooks, private val handler: ViolationHandler) : PlaceholderExpansion() {

    override fun getIdentifier() = "spartanwebhooks"
    override fun getAuthor(): String = plugin.description.authors[0]
    override fun getVersion(): String = plugin.description.version

    override fun onPlaceholderRequest(player: Player?, params: String): String? {

        val format = DecimalFormat("#.##")
        format.roundingMode = RoundingMode.CEILING

        return when (params) {
            "violator_name" -> player?.name
            "violator_uuid" -> player?.uniqueId?.toString()
            "violator_information" -> handler.violationData[player?.uniqueId]?.map { "${it.key} - ${it.value}x" }?.joinToString("\n")
            "violator_latency" -> API.getPing(player!!).toString()
            "server_tps" -> format.format(API.getTPS()).toString()
            else -> "Placeholder not found"
        }
    }

}