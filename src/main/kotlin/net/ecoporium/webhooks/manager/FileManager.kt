package net.ecoporium.webhooks.manager

import com.electronwill.nightconfig.core.file.CommentedFileConfig
import com.electronwill.nightconfig.core.file.FileConfig
import net.ecoporium.webhooks.SpartanWebhooks
import java.io.File
import java.net.URL

class FileManager(private val plugin: SpartanWebhooks) {

    val settingsConfig: CommentedFileConfig by lazy {
        CommentedFileConfig.builder(File(plugin.dataFolder.path, "settings.toml"))
            .defaultData(URL("https://gist.githubusercontent.com/bobby29831/b7cd78ad831cb130c32490421d3d3cbc/raw/568041df2a6d7de69afc26e1d81878cd5ebfd042/settings.toml"))
            .autosave()
            .build()
    }

    val webhookConfig: FileConfig by lazy {
        FileConfig.builder(File(plugin.dataFolder.path, "webhook.json"))
            .defaultData(URL("https://gist.githubusercontent.com/bobby29831/501e11fe4823bec2dbfc21337090c045/raw/154abe4b6c7ab4194a68656a2fc1ed9b3b7c188c/webhook.json"))
            .autosave()
            .build()
    }

    init {
        plugin.dataFolder.mkdir()
        reload()
    }

    fun reload() {
        settingsConfig.load()
        webhookConfig.load()
    }

}