plugins {
    kotlin("jvm") version "1.8.21"

    id("maven-publish")
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
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
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