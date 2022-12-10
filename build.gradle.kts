plugins {
    id("java-library")
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm") version "1.6.21"
    application
}

group = "me.xhyrom.hyx"
version = "1.0.0"
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
    compileOnly("me.xhyrom.hylib:hylib-bukkit:1.1.0")

    compileOnly("dev.jorel:commandapi-annotations:8.5.1")
    annotationProcessor("dev.jorel:commandapi-annotations:8.5.1")
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
    mainClass.set("me.xhyrom.hychat.HyChat")
}