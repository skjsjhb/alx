plugins {
    id("java")
}

group = "moe.skjsjhb"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("commons-io:commons-io:2.18.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}