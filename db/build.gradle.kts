plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.liquibase.gradle") version "2.2.0"
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
}

group = "walliet.db"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    runtimeOnly("org.postgresql:postgresql:42.6.0")

    // LIQUIBASE
    liquibaseRuntime("org.liquibase:liquibase-core:4.24.0")
    liquibaseRuntime("org.liquibase:liquibase-groovy-dsl:2.0.1")
    liquibaseRuntime("org.postgresql:postgresql:42.6.0")

    liquibaseRuntime("info.picocli:picocli:4.7.5")
    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks["bootJar"].enabled = false
tasks["jar"].enabled = true

liquibase {
    activities.register("main") {
        this.arguments = mapOf(
            "logLevel" to "info",
            "changeLogFile" to "/src/main/resources/db.changelog/changelog-master.yaml",
            "url" to "jdbc:postgresql://localhost:5432/walliet",
            "username" to "w_admin",
            "password" to "pass123",
            "defaultSchemaName" to "w_dev")
    }
}

tasks.register("dbUpdate") {
    // depend on the liquibase status task
    dependsOn("update")
}

tasks.withType<Test> {
    useJUnitPlatform()
}