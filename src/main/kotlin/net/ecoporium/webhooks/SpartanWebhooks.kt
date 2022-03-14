package net.ecoporium.webhooks

import net.ecoporium.webhooks.manager.FileManager
import org.bukkit.plugin.java.JavaPlugin

class SpartanWebhooks : JavaPlugin() {

    private val fileManager: FileManager by lazy { FileManager(this) }

    override fun onEnable() {

        fileManager.reload()

    }

}