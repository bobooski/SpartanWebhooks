package net.ecoporium.webhooks

import dev.triumphteam.cmd.bukkit.BukkitCommandManager
import net.ecoporium.webhooks.command.WebhookCommand
import net.ecoporium.webhooks.config.SettingsConfig
import net.ecoporium.webhooks.config.WebhookConfig
import net.ecoporium.webhooks.expansion.WebhooksExpansion
import net.ecoporium.webhooks.listener.ViolationHandler
import net.ecoporium.webhooks.manager.FileManager
import net.ecoporium.webhooks.manager.WebhookManager
import org.bukkit.plugin.java.JavaPlugin

class SpartanWebhooks : JavaPlugin() {

    private val fileManager: FileManager by lazy { FileManager(this) }
    val webhookManager: WebhookManager by lazy { WebhookManager(this) }

    override fun onEnable() {

        val handler = ViolationHandler(this)
        fileManager.reload()
        if (server.pluginManager.getPlugin("PlaceholderAPI") != null) WebhooksExpansion(this, handler).register()
        server.pluginManager.registerEvents(handler, this)

        BukkitCommandManager.create(this).registerCommand(WebhookCommand(this, handler))
        webhookManager.load()

    }

    override fun onDisable() {
        fileManager.webhookConfig.close()
        fileManager.settingsConfig.close()
    }

    fun reloadConfigurations() = fileManager.reload()
    fun getWebhookConfig() = WebhookConfig(fileManager.webhookConfig)
    fun getSettingsConfig() = SettingsConfig(fileManager.settingsConfig)

}