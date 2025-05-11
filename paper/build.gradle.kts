plugins {
    alias(libs.plugins.runPaper)

    id("paper-plugin")
}

project.group = "${rootProject.group}.paper"
project.version = rootProject.version
project.description = "A nifty plugin for Paper based servers!"

base {
    archivesName = "${rootProject.name}-${project.name}"
}

tasks {
    processResources {
        filteringCharset = Charsets.UTF_8.name()

        filesMatching("paper-plugin.yml") {
            expand("name" to rootProject.name,
                "description" to project.description,
                "minecraft" to libs.versions.minecraft.get(),
                "version" to project.version,
                "group" to project.group)
        }
    }

    modrinth {
        loaders.addAll(listOf("paper", "folia", "purpur"))
        versionType.set("beta")
        uploadFile.set(jar.get())
    }

    runPaper.folia.registerTask()

    runServer {
        jvmArgs("-Dnet.kyori.ansi.colorLevel=truecolor")

        defaultCharacterEncoding = Charsets.UTF_8.name()

        minecraftVersion(libs.versions.minecraft.get())
    }
}