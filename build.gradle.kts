plugins {
	java
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.github.ioloolo"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok")
 	annotationProcessor("org.projectlombok:lombok")

	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("com.google.code.gson:gson:2.10.1")
}
