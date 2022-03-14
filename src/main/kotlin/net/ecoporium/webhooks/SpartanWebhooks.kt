package net.ecoporium.webhooks

import net.ecoporium.webhooks.config.SettingsConfig
import net.ecoporium.webhooks.config.WebhookConfig
import net.ecoporium.webhooks.manager.FileManager
import org.bukkit.plugin.java.JavaPlugin

class SpartanWebhooks : JavaPlugin() {

    private val fileManager: FileManager by lazy { FileManager(this) }

    override fun onEnable() {

        fileManager.reload()

    }

    fun getWebhookConfig() = WebhookConfig(fileManager.webhookConfig)
    fun getSettingsConfig() = SettingsConfig(fileManager.settingsConfig)

}