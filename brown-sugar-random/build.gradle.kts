plugins {
    id("java")
    alias(libs.plugins.kotlin.jvm)
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":brown-sugar-core"))

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}