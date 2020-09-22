@file:Suppress("SpellCheckingInspection")

plugins {
    java
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.8"
    id("jacoco")
}

application {
    mainClassName = "$moduleName/com.github.cc3002.finalreality.gui.FinalReality"
}

group = "com.github.cc3002"
version = "1.0-RELEASE"

repositories {
    mavenCentral()
}

dependencies {
    implementation(group = "org.openjfx", name = "javafx", version = "14-ea+6", ext = "pom")
    implementation("org.jetbrains:annotations:19.0.0")
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api",
            version = "5.1.0")
    testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine",
            version = "5.1.0")
}

javafx {
    version = "14-ea+6"
    modules = mutableListOf("javafx.controls")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}