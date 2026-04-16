plugins {
    id("java-plugin")
}

rootProject.version = "0.1.0"
rootProject.group = "com.ryderbelserion"

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