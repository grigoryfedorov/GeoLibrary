plugins {
    kotlin("jvm") version "1.3.72"
    id("io.gitlab.arturbosch.detekt").version("1.9.1")
}

group = "org.grigoryfedorov"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

detekt {
    failFast = true // fail build on any finding
    config = files("$projectDir/config/detekt/detekt.yml")

    reports {
        html.enabled = true
        xml.enabled = false
        txt.enabled = false
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}