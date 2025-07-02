plugins {
    application
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}

group = "com.github.rossilor95"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val jUnitVersion = "5.13.2"
val mockitoVersion = "5.18.0"

dependencies {
    testImplementation(platform("org.junit:junit-bom:$jUnitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.mockito:mockito-junit-jupiter:$mockitoVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass.set("com.github.rossilor95.peakintervalfinder.PeakIntervalFinder")
}

tasks.jar {
    manifest.attributes(
        "Main-Class" to "com.github.rossilor95.peakrangeapp.PeakRangeApp"
    )
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}