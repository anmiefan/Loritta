plugins {
    kotlin("multiplatform") apply true
    kotlin("plugin.serialization") version Versions.KOTLIN
}

repositories {
    mavenLocal()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
    }

    js {
        browser()
        nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(project(":common-legacy"))
                api("net.perfectdreams.loritta.cinnamon.pudding:data:${Versions.PUDDING}") {
                    exclude(group = "io.ktor") // Pudding requires Ktor 2.0.0, but we still use Ktor 1.6.7
                    exclude(group = "net.perfectdreams.minecraftmojangapi")
                }
            }
        }

        // Default source set for JVM-specific sources and dependencies:
        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }

        // Default source set for JS-specific sources and dependencies:
        js().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
    }
}