plugins {
    id("java")
}

group = "org.girardsimon"
version = "1.0-SNAPSHOT"

val guavaVersion = "33.2.0-jre"
val jUnitVersion = "5.10.2"
val jUnitPlatformLauncherVersion = "1.10.2"
val assertjVersion = "3.25.3"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation("com.google.guava:guava:$guavaVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$jUnitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$jUnitVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:$jUnitPlatformLauncherVersion")
}

tasks.test {
    useJUnitPlatform()
}