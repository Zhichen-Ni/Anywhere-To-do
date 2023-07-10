import org.jetbrains.kotlin.gradle.plugin.statistics.ReportStatisticsToElasticSearch.url
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "edu.uwaterloo.cs"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://git.uwaterloo.ca/api/v4/projects/69446/packages/maven")
        name = "GitLab"
        credentials(HttpHeaderCredentials::class) {
            name = "Deploy-Token"
            value = "KtqpZE3e2fURjpqgd7BG"
        }
        authentication {
            create<HttpHeaderAuthentication>("header")
        }
    }
}

dependencies {
    //implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    //implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("edu.uwaterloo.cs:todo-lib:1.0.0.29-DEV")


    implementation("com.alibaba:fastjson:1.2.83")
    implementation("com.squareup.okhttp3:okhttp:4.9.2")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
