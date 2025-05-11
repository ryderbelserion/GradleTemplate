plugins {
    alias(libs.plugins.fabric.loom)

    id("root-plugin")
}

project.group = "${rootProject.group}.fabric"
project.version = rootProject.version
project.description = "A nifty mod for Fabric based servers!"

base {
    archivesName = "${rootProject.name}-${project.name}"
}

repositories {
    maven("https://maven.fabricmc.net/")
}

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())
    modCompileOnly(libs.fabric.loader)
    modCompileOnly(libs.fabric.api)
}

tasks {
    processResources {
        filteringCharset = Charsets.UTF_8.name()

        filesMatching("fabric.mod.json") {
            expand("name" to rootProject.name,
                "id" to rootProject.name.lowercase(),
                "description" to project.description,
                "version" to project.version,
                "minecraft" to libs.versions.minecraft.get(),
                "fabricloader" to libs.versions.fabricLoader.get())
        }
    }
}