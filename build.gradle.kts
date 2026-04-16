plugins {
    id("shared-plugin")

    id("modrinth-plugin")
    id("hangar-plugin")

    id("java-plugin")
}

val mergedJar by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false
}

dependencies {
    //mergedJar(project(":fabric"))
    mergedJar(project(":paper"))
}

tasks.withType<Jar> {
    dependsOn(mergedJar)

    val jars = mergedJar.map { zipTree(it) }

    from(jars)
}