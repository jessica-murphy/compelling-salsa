/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.10.2/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
	// Apply the application plugin to add support for building a CLI application in Java.
	application
	checkstyle
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.the-company"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	checkstyle("io.spring.javaformat:spring-javaformat-checkstyle:0.0.43")
	implementation("org.springframework.boot:spring-boot-starter-web:3.4.4")
	testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.4")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.11.3")

	compileOnly("org.projectlombok:lombok:1.18.36")
	annotationProcessor("org.projectlombok:lombok:1.18.36")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.4.4")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.4")
	implementation("org.postgresql:postgresql:42.7.4")
	testImplementation("org.testcontainers:postgresql:1.20.4")
	implementation("org.liquibase:liquibase-core:4.29.2")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4")
}

checkstyle {
	toolVersion = "9.3"
}

tasks.withType<Test> {
	useJUnitPlatform()
}

configurations.all {
	resolutionStrategy {
		force("org.apache.commons:commons-compress:1.26.0")
		force("net.minidev:json-smart:2.5.2")
		force("com.google.guava:guava:33.4.5-jre")
	}
}
