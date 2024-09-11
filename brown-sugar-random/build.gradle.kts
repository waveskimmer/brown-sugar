plugins {
    id("java")
    alias(libs.plugins.kotlin.jvm)
}

group = "io.github.waveskimmer.kotlin"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":brown-sugar-core"))

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}