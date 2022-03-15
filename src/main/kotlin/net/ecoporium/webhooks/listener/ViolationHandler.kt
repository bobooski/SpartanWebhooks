package net.ecoporium.webhooks.listener

import me.vagdedes.spartan.api.API
import me.vagdedes.spartan.api.PlayerViolationEvent
import me.vagdedes.spartan.system.Enums.HackType
import net.ecoporium.webhooks.SpartanWebhooks
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.util.UUID

class ViolationHandler(private val plugin: SpartanWebhooks) : Listener {

    val violationData = mutableMapOf<UUID, HashMap<HackType, Int>>()

    @EventHandler
    fun onViolation(event: PlayerViolationEvent) {
        if (event.isFalsePositive) return
        if (event.violation < API.getCancelViolation(event.hackType)) return

        violationData[event.player.uniqueId]?.let {
            it[event.hackType] = it[event.hackType]?.plus(1) ?: 1
        } ?: run {
            violationData[event.player.uniqueId] = hashMapOf(event.hackType to 1)
        }

        if ((violationData[event.player.uniqueId]?.values?.sum() ?: 0) >= plugin.getSettingsConfig().violationThreshold) {
            plugin.webhookManager.webhooks.add(Pair(
                plugin.getWebhookConfig().contentFor(event.player),
                plugin.getWebhookConfig().embedFor(event.player)
            ))

            violationData.remove(event.player.uniqueId)
        }
    }

}