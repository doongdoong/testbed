import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.9.24"

	id("org.springframework.boot") version "3.3.0" apply false
	id("io.spring.dependency-management") version "1.1.5" apply false
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion apply false
	kotlin("kapt") version kotlinVersion apply false
}

allprojects {
	group = "com.koby"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.withType<JavaCompile>{
		sourceCompatibility = "17"
		targetCompatibility = "17"
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
		}
	}
}

subprojects {
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "kotlin")
	apply(plugin = "org.jetbrains.kotlin.kapt")

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	}
}

