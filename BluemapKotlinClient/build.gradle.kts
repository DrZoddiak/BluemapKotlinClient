plugins {
    java
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.20"
    `maven-publish`
    `java-library`
}

group = "com.github.drzoddiak"
version = "1.0.0-SNAPSHOT"

val classMain = "$group.MainKt"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-core:3.0.0")
    implementation("io.ktor:ktor-client-cio:3.0.0")
    implementation("io.ktor:ktor-client-content-negotiation:3.0.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    implementation(kotlin("reflect"))
}

kotlin {
    jvmToolchain(21)
}

java {
    withSourcesJar()
}

publishing {
    repositories {
        maven {
            name = "deploy"
            url = uri(providers.gradleProperty("deployUrl").getOrElse(""))
            credentials {
                username = providers.gradleProperty("deployUsername").orNull
                password = providers.gradleProperty("deployPassword").orNull
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                name.set("Kotlin DC-Map Client")
                description.set("Allows you to create a client with object mappings using Kotlin")
            }
        }
    }
    afterEvaluate {
        publications.withType<MavenPublication> {
            pom {
                url.set("https://github.com/DrZoddiak/BluemapKotlinClient")
                developers {
                    developer {
                        id.set("DrZoddiak")
                        name.set("Jesse McKee")
                    }
                }
            }
        }
    }
}