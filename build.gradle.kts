plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

tasks {
    register("runExample", JavaExec::class) {
        mainClass.set("org.example.Main")
        classpath(sourceSets["main"].runtimeClasspath)
    }
}

dependencies {
    implementation("io.zenoh:zenoh-java-jvm:0.11.0-dev")
}

tasks.test {
    useJUnitPlatform()
}
