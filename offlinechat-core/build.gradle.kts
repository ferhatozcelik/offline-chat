import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.room)
    id("maven-publish")
}

kapt {
    correctErrorTypes = true
}

room {
    schemaDirectory("$projectDir/schemas")
}

android {
    namespace = "org.turkiye.offlinechat.core"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

val libraryVersion = providers.gradleProperty("VERSION_NAME").getOrElse("1.0.0")

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.ferhatozcelik"
            artifactId = "offlinechat-core"
            version = libraryVersion

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("Offline Chat Core")
                description.set(
                    "Core data, networking and dependency-injection layer for the Offline Chat app.",
                )
                url.set("https://github.com/ferhatozcelik/offline-chat")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("ferhatozcelik")
                        name.set("Ferhat OZCELIK")
                        url.set("https://github.com/ferhatozcelik")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/ferhatozcelik/offline-chat.git")
                    developerConnection.set("scm:git:ssh://github.com:ferhatozcelik/offline-chat.git")
                    url.set("https://github.com/ferhatozcelik/offline-chat")
                }
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
