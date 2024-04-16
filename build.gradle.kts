plugins {
    kotlin("jvm") version "1.9.22"

    id("maven-publish")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
}

group = "ru.lavafrai.exler.mai"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jsoup:jsoup:1.10.2")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.0.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("Maven") {
            groupId = group as String
            artifactId = "api"
            version = version as String
            from(components["kotlin"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
    maxParallelForks = 6
}