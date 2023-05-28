plugins {
    id("java-library")
    id("java")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm") version "1.6.21"
    application
}

group = "me.xhyrom.hyx"
version = "2.0.0"
description = "A powerful and lightweight plugin"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://repo.jopga.me/releases")
    maven("https://jitpack.io")
}


dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
    compileOnly("me.xhyrom.hylib:hylib-bukkit:2.0.0")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")

    compileOnly("dev.jorel:commandapi-annotations:9.0.1")
    annotationProcessor("dev.jorel:commandapi-annotations:9.0.1")
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    from(sourceSets.main.get().resources.srcDirs) {
        filter(
            org.apache.tools.ant.filters.ReplaceTokens::class, "tokens" to mapOf(
                "name" to project.name,
                "version" to project.version,
                "description" to project.description,
            )
        )
    }
}

tasks {
    shadowJar {
        relocate("dev.jorel.commandapi", "me.xhyrom.hylib.libs.commandapi")

        exclude("kotlin/**")
        archiveFileName.set("HyX-${project.version}-all.jar")
    }
    named("build") {
        dependsOn(shadowJar)
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

application {
    mainClass.set("me.xhyrom.hyx.HyX")
}

publishing {
    publications.create<MavenPublication>("maven") {
        artifact(tasks["shadowJar"])

        repositories.maven {
            url = uri("https://repo.jopga.me/releases")

            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }

        groupId = rootProject.group as String
        artifactId = project.name
        version = rootProject.version as String

        pom {
            name.set("HyX")
        }
    }
}