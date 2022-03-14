package net.ecoporium.webhooks.manager

import com.electronwill.nightconfig.core.file.CommentedFileConfig
import com.electronwill.nightconfig.core.file.FileConfig
import net.ecoporium.webhooks.SpartanWebhooks
import java.io.File

class FileManager(private val plugin: SpartanWebhooks) {

    val settingsConfig: CommentedFileConfig by lazy {
        CommentedFileConfig.builder(File(plugin.dataFolder.path, "settings.toml"))
            .defaultResource("settings.toml")
            .autosave()
            .build()
    }

    val webhookConfig: FileConfig by lazy {
        FileConfig.builder(File(plugin.dataFolder.path, "webhook.json"))
            .autosave()
            .build()
    }

    init {
        if (!plugin.dataFolder.exists()) plugin.dataFolder.createNewFile()
        reload()
    }

    fun reload() {
        settingsConfig.load()
        webhookConfig.load()
    }

}