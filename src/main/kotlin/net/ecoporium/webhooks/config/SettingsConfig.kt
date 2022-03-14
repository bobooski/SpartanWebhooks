package net.ecoporium.webhooks.config

import com.electronwill.nightconfig.core.file.FileConfig

class SettingsConfig(config: FileConfig) {

    val webhookUrl: String = config["webhook.url"]
    val debug: Boolean = config["general.debug"]

}