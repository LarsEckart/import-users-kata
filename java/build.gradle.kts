plugins {
    `java-library`
    application
}

repositories {
    mavenCentral()
}

dependencies {
    runtimeOnly(libs.sqlite)

    implementation(libs.json)
    implementation(libs.slf4j)
    implementation(libs.slf4jbasic)

    testImplementation(libs.junit)
    testImplementation(libs.approvaltests)
}

group = "kata"
version = "1.0"

java.sourceCompatibility = JavaVersion.VERSION_21

application {
    mainClass.set("kata.Main")
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}
