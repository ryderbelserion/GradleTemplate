import org.gradle.accessors.dm.LibrariesForLibs

// Hack which exposes `libs` to this convention plugin
val libs = the<LibrariesForLibs>()


plugins {
    id("root-plugin")
}

dependencies {
    compileOnly(libs.paper)
}