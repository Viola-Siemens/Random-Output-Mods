Output the wrong recipe which causes game crashes in crash report.

Most recipe type won't crash the game when loading a bad json file, especially vanilla. But some mods and its dependents may do so. For example, when you install:

[Create: Totem Factory](https://www.curseforge.com/minecraft/mc-mods/create-totem-factory) v1.0
[Create](https://www.curseforge.com/minecraft/mc-mods/create) v1.20.1-0.5.1.f

You will get a crash without any information. Even the log only mentions create. But the problem is, recipe `totemfactory:totemofundying` causes the crash. With this mod, you'll get the id of the error recipe.
