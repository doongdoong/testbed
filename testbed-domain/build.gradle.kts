import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

plugins {
    id("org.flywaydb.flyway") version "7.2.0"
    kotlin("plugin.jpa") version "1.6.21"
}

flyway {
    url = "jdbc:mysql://localhost:3306/testbed?useSSL=false&serverTimezone=UTC&characterEncoding=utf8"
    user = "root"
    password = "admin"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("mysql:mysql-connector-java:8.0.32")
    implementation("org.flywaydb:flyway-core")
}
