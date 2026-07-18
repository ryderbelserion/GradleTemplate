plugins {
    alias(libs.plugins.fabric.loom)

    `java-plugin`
}

repositories {
    maven("https://maven.fabricmc.net/")
}

dependencies {
    minecraft(libs.minecraft)

    compileOnly(libs.fabric.loader)
    compileOnly(libs.fabric.api)
}