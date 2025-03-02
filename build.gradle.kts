plugins {
    id("java")
}

group = "moe.skjsjhb"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20250107")
    implementation("org.java-websocket:Java-WebSocket:1.6.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.compileClasspath.get().map { if (it.isDirectory()) it else zipTree(it) })
}