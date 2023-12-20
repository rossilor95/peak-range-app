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
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.mockito:mockito-core:5.5.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.5.0")
}

application {
    mainClass.set("com.github.rossilor95.peakintervalfinder.PeakIntervalFinder")
}

tasks.test {
    useJUnitPlatform()
}