package net.ecoporium.webhooks.manager

import com.electronwill.nightconfig.core.file.CommentedFileConfig
import com.electronwill.nightconfig.core.file.FileConfig
import net.ecoporium.webhooks.SpartanWebhooks
import net.ecoporium.webhooks.config.SettingsConfig
import net.ecoporium.webhooks.config.WebhookConfig
import java.io.File

class FileManager(private val plugin: SpartanWebhooks) {

    private val settingsConfig: CommentedFileConfig by lazy {
        CommentedFileConfig.builder(File(plugin.dataFolder.path, "settings.toml"))
            .defaultResource("settings.toml")
            .autosave()
            .build()
    }

    private val webhookConfig: FileConfig by lazy {
        FileConfig.builder(File(plugin.dataFolder.path, "webhook.json"))
            .autosave()
            .build()
    }

    init {
        if (!plugin.dataFolder.exists()) plugin.dataFolder.createNewFile()
        reload()

        SettingsConfig(settingsConfig)
        WebhookConfig(webhookConfig)
    }

    fun reload() {
        settingsConfig.load()
        webhookConfig.load()
    }

}