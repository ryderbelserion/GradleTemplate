plugins {
    alias(libs.plugins.minotaur)

    id("root-plugin")
}

rootProject.version = "0.1.0"
rootProject.group = "com.ryderbelserion"

val mergedJar by configurations.creating<Configuration> {
    isCanBeResolved = true
    isCanBeConsumed = false
    isVisible = false
}

dependencies {
    mergedJar(project(":fabric"))
    mergedJar(project(":paper"))
}

tasks.withType<Jar> {
    dependsOn(mergedJar)

    val jars = mergedJar.map { zipTree(it) }

    from(jars)
}

modrinth {
    token = System.getenv("MODRINTH_TOKEN")

    projectId = rootProject.name

    versionName = "${rootProject.version}"
    versionNumber = "${rootProject.version}"
    versionType = "release"

    changelog = rootProject.file("changelog.md").readText(Charsets.UTF_8)

    gameVersions.addAll(listOf(libs.versions.minecraft.get()))

    uploadFile = tasks.jar.get().archiveFile.get()

    loaders.addAll(listOf("paper", "folia", "purpur", "fabric"))

    syncBodyFrom = rootProject.file("description.md").readText(Charsets.UTF_8)

    autoAddDependsOn = false
    detectLoaders = false
}