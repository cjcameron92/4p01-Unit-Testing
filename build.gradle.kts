plugins {
    id("java")
}

group = "ca.brocku.cosc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter API for writing tests
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")

    // Engine to run the tests
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}
