# Spartan Webhooks
This version of Spartan Webhooks is a complete rewrite
mainly in the Kotlin language for ease for programming.

However, it will include many new features which are attractive:
* JSON based embed configuration
    * Popular builders like [this one](https://glitchii.github.io/embedbuilder/) can now be utilized
    * Supports non-embed messages too!
* Only one webhook is sent per user every few seconds
    * Limits 429s and makes it easier to keep track of messages
* More configuration options & cleaner configuration
    * Change how often a user's webhook is sent
    * No more hunting for essential fields to modify
    * Toggle if certain checks report to webhooks
* Placeholders managed by [PlaceholderAPI](https://github.com/PlaceholderAPI/PlaceholderAPI)
    * Easier to use and more flexible placeholders that can also be utilized in other plugins
* And many, many more to come!

This newer version will also rely on Paper, instead of Spigot in
order to make use of some newer features like Components that Spigot
still fails to support. Only the latest Minecraft version will be provided
support and actively maintained. You can modify/rebuild the project to
function on different versions at your leisure.

## Build Instructions
- Clone the repository
- Use the command `gradlew clean shadowJar` to build
- The outputted JAR will be in the `{projectRoot}/build/libs` folder by default