plugins {
    application
    java
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.20"
    `maven-publish`
    `java-library`
}

java {
    withSourcesJar()
}

group = "com.github.drzoddiak"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "com.github.drzoddiak.MainKt"
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "com.github.drzoddiak.MainKt")
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("io.ktor:ktor-client-core:3.0.0")
    implementation("io.ktor:ktor-client-cio:3.0.0")
    implementation("io.ktor:ktor-client-content-negotiation:3.0.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.0")

    implementation(kotlin("reflect"))
}

kotlin {
    jvmToolchain(21)
}

publishing {
    repositories {
        maven {
            name = "deploy"
            url = uri("https://maven.democracycraft.net/snapshots/")
            credentials {
                username = "realtyCheckerPublish"
                password = "YdH2m94iEAks9Eq3eXrdYTlYzNnLEqoNoRjv5hYA2uDtJSW+jPg6vsTjMR19LR25"
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
                // url.set("https://github.com/MCCitiesNetwork/realty")
                developers {
                    developer {
                        id.set("DrZoddiak")
                        name.set("Jesse McKee")
                        // email.set("")
                    }
                }
            /*    scm {
                    connection.set("scm:git:git://github.com/MCCitiesNetwork/realty.git")
                    developerConnection.set("scm:git:ssh://github.com/MCCitiesNetwork/realty.git")
                    url.set("https://github.com/MCCitiesNetwork/realty")
                }*/
            }
        }
    }
}