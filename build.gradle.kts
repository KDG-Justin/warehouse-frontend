plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "be.kdg.sa"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.webjars:bootstrap:5.3.3")
	implementation("org.webjars:webjars-locator-core:0.59")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-configuration-processor")
	implementation("org.modelmapper:modelmapper:3.1.1")
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	testImplementation("org.springframework.amqp:spring-rabbit-test")
	developmentOnly("org.springframework.boot:spring-boot-devtools")



	testImplementation("org.springframework.boot:spring-boot-starter-test")
	runtimeOnly("org.postgresql:postgresql")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
