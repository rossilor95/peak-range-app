plugins {
    application
}

group = "com.github.rossilor95"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.14.0")
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