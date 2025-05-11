plugins {
    alias(libs.plugins.minotaur)

    id("root-plugin")
}

rootProject.version = "0.1.0"
rootProject.group = "com.ryderbelserion"

subprojects {
    apply(plugin = "com.modrinth.minotaur")

    tasks {
        modrinth {
            token.set(System.getenv("MODRINTH_TOKEN"))

            projectId.set("SimpleEdit")

            versionName.set("${rootProject.name} ${rootProject.version}")
            versionNumber.set(rootProject.version.toString())

            changelog.set(rootProject.file("changelog.md").readText(Charsets.UTF_8))

            gameVersions.set(listOf("1.21.5"))

            syncBodyFrom.set(rootProject.file("description.md").readText(Charsets.UTF_8))

            autoAddDependsOn.set(false)
            detectLoaders.set(false)
        }
    }
}