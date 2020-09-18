val sourceCompatibility by extra(JavaVersion.VERSION_1_8)
val targetCompatibility by extra(JavaVersion.VERSION_1_8)
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        mavenCentral()
        google()
        jcenter()
    }
    dependencies {
        classpath(Deps.android.plugin)
        classpath(Deps.kotlin.plugin)
        classpath(Deps.sqlDelight.plugin)
        classpath(Deps.sqlDelight.runtime)
        classpath(Deps.kotlinx.atomicfu.plugin)
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        jcenter()

        maven { url = uri("https://dl.bintray.com/badoo/maven") }
    }

}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}