<p align="center"><img src="https://i.imgur.com/8TGWdvP.png" alt="Spartan Webhooks Logo"/></p>
  
This version of Spartan Webhooks is a complete rewrite
mainly in the Kotlin language for ease for programming.

However, it will include many new features which are attractive:
* JSON based embed configuration
    * Popular builders like [this one](https://glitchii.github.io/embedbuilder/) can now be utilized
    * Supports non-embed messages too!
* Embed sending now respects Discord's limits
    * Should now no longer trigger rate limiting errors (429s)
    * Embeds will be collapsed to one message, allowing more to be sent without triggering 429s!
* More configuration options & cleaner configuration
    * Configuration is now done through a [TOML](https://toml.io/) file
* Placeholders managed by [PlaceholderAPI](https://github.com/PlaceholderAPI/PlaceholderAPI)
    * Easier to use and more flexible placeholders that can also be utilized in other plugins
* And many, many more to come!

This newer version will also rely on Paper, instead of Spigot in
order to make use of some newer features like Components that Spigot
still fails to support. Only the latest Minecraft version will be provided
support and actively maintained. You can modify/rebuild the project to
function on different versions at your leisure.  
  
## Limitations
- Only supports the latest Minecraft version (1.18.2)
- Download in release is built with Java 17, and as such is not compatible with all versions of Java
- Only supports Spigot, Paper, and Forge
- Requires [PlaceholderAPI](https://github.com/PlaceholderAPI/PlaceholderAPI)
- Content section is sent once per grouping of embeds; placeholders replaced for user of first embed

## Build Instructions
- Clone the repository
- Use the command `gradlew clean build` to build
- The outputted JAR will be in the `{projectRoot}/build/libs` folder by default