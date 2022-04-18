<p align="center"><img src="https://i.imgur.com/8TGWdvP.png" alt="Spartan Webhooks Logo"/></p>  

[![](https://img.shields.io/github/downloads/bobby29831/SpartanWebhooks/total?color=blue&style=for-the-badge)](https://github.com/bobby29831/SpartanWebhooks/releases)
[![](https://img.shields.io/discord/928058065759113266?label=discord&logo=discord&style=for-the-badge)](https://discord.gg/AHYXEN8qq3)  

This version of Spartan Webhooks is a complete rewrite
mainly in the Kotlin language for ease for programming.
Modifying the plugin to work on different platforms and versions
is okay, but will never take place on this project.

## Features
* JSON based embed configuration
    * Popular builders like [this one](https://embedbuilder.nadekobot.me) can now be utilized
    * Supports non-embed messages too!
* Embed sending now respects Discord's limits
    * Should now no longer trigger rate limiting errors (429s)
    * Embeds will be collapsed to one message, allowing more to be sent without triggering 429s!
* More configuration options & cleaner configuration
    * Configuration is now done through a [TOML](https://toml.io/) file
* Placeholders managed by [PlaceholderAPI](https://github.com/PlaceholderAPI/PlaceholderAPI)
    * Easier to use and more flexible placeholders that can also be utilized in other plugins
* And many, many more to come!
  
## Limitations
- Only supports the latest Minecraft version (1.18.2)
- Download in release is built with Java 17, and as such is not compatible with all versions of Java
- Only supports Paper, not Spigot servers
- Requires [PlaceholderAPI](https://github.com/PlaceholderAPI/PlaceholderAPI)
- Content section is sent once per grouping of embeds; placeholders not supported for this field

## Installation
- Download the file `SpartanWebhooks-2.0-beta.jar` and place it in your `plugins` folder.
- Ensure you have the `Spartan` and `PlaceholderAPI` plugin downloaded
- Configure the `url` field in the `plugins/SpartanWebhooks/settings.toml` file
- Run `/webhooks reload`
- You're good to go! Edit the `plugins/SpartanWebhooks/webhook.json` file to your heart's content!  

## Creating a Discord Webhook
If for some strange reason you wish to use this plugin, but have no idea what a Discord Webhook is, how to create
one, or how to get its URL which will be necessary to make this plugin work, you can check out Discord's own
tutorial [here](https://support.discord.com/hc/en-us/articles/228383668-Intro-to-Webhooks). Please note that
appending `/github` to the end of the URL is **ONLY** for webhooks coming from GitHub, and is not necessary
for this plugin and will actually break it.

## Build Instructions
- Clone the repository
- Use the command `gradlew clean build` to build
- The outputted JAR will be in the `{projectRoot}/build/libs` folder by default
