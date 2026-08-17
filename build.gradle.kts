plugins {
    id("java")
}

group = "com.poc.crud"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")
    compileOnly("org.projectlombok:lombok:1.18.40")
    annotationProcessor("org.projectlombok:lombok:1.18.40")
    implementation("org.hibernate.orm:hibernate-core:7.2.1.Final")
    runtimeOnly("com.h2database:h2:2.4.240")
}

tasks.test {
    useJUnitPlatform()
}