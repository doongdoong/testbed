import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

plugins {
    val kotlinVersion = "1.9.24"

    id("org.flywaydb.flyway") version "7.2.0"
    kotlin("plugin.jpa") version kotlinVersion
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
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


    val querydslVersion = "5.0.0"
    // Querydsl
    implementation("com.querydsl:querydsl-jpa:$querydslVersion:jakarta")
    implementation("com.querydsl:querydsl-apt:$querydslVersion:jakarta")
    implementation("jakarta.persistence:jakarta.persistence-api")
    implementation("jakarta.annotation:jakarta.annotation-api")
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jakarta")
}
