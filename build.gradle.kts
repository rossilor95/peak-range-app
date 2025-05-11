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

val jUnitVersion = "5.12.2"
val mockitoVersion = "5.17.0"

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
        "Main-Class" to "com.github.rossilor95.peakintervalfinder.PeakIntervalFinder"
    )
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}