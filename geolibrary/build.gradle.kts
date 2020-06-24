plugins {
    kotlin("jvm")
    id("io.gitlab.arturbosch.detekt").version("1.9.1")
    id("org.jetbrains.dokka") version "0.10.1"
    jacoco
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

jacoco {
    toolVersion = "0.8.5"
    reportsDir = file("$buildDir/customJacocoReportDir")
}

tasks.dokka {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        csv.isEnabled = false
        html.destination = file("${buildDir}/jacocoHtml")
    }
    dependsOn(tasks.test) // tests are required to run before generating the report
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.0.6") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core-jvm:4.0.6") // for kotest core jvm assertions
    testImplementation("io.kotest:kotest-property-jvm:4.0.6") // for kotest property test
    testImplementation("io.mockk:mockk:1.10.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}