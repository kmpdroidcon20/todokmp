import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJsOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

@Suppress("UnstableApiUsage")
abstract class JsPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        configureJsCompilation(target)
    }

    private fun configureJsCompilation(target: Project) {
        target.extensions.configure(KotlinMultiplatformExtension::class.java) {
            js(TARGET_NAME_JS) {
                // Enable for browser support
//                browser {
//                    testTask {
//                        useKarma {
//                            useChromeHeadless()
//                        }
//                    }
//                }
                nodejs {
                    testTask {
                        useCommonJs()
                    }
                }
            }
            sourceSets.getByName("${TARGET_NAME_JS}Main") {
                dependencies {

                }
            }
            sourceSets.getByName("${TARGET_NAME_JS}Test") {
                dependencies {
                    implementation(Deps.kotlin.test.js)
                }
            }
        }
        target.tasks.named(TASK_COMPILE_KOTLIN_JS, Kotlin2JsCompile::class.java) {
            kotlinOptions.configure()
        }
        target.tasks.named(TASK_COMPILE_KOTLIN_JS_TEST, Kotlin2JsCompile::class.java) {
            kotlinOptions.configure()
        }
    }

    private fun KotlinJsOptions.configure() {
        metaInfo = true
        sourceMap = true
        sourceMapEmbedSources = "always"
        moduleKind = "umd"
        main = "call"
    }

    companion object {
        private const val TASK_COMPILE_KOTLIN_JS = "compileKotlinJs"
        private const val TASK_COMPILE_KOTLIN_JS_TEST = "compileTestKotlinJs"

        const val TARGET_NAME_JS = "js"
    }
}
