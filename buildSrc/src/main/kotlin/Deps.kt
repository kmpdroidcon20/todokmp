object Deps {

    private const val kotlinVersion = "1.4.10"

    val kotlin = Kotlin
    val stately = Stately
    val kotlinx = Kotlinx
    val careem = Careem
    val android = Android
    val sqlDelight = SqlDelight
    val reaktive = Reaktive

    // Robolectric
    private const val robolectricVersion = "4.3.1"
    const val robolectric = "org.robolectric:robolectric:$robolectricVersion"


    object Kotlin {
        val stdlib = Stdlib()
        val test = Test
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

        const val reflectJvm = "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"

        class Stdlib(
            private val name: String = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        ) : CharSequence by name {
            val jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
            val js = "org.jetbrains.kotlin:kotlin-stdlib-js:$kotlinVersion"
            val common = "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"

            override fun toString(): String = name
        }

        object Test {
            const val common = "org.jetbrains.kotlin:kotlin-test-common:$kotlinVersion"
            const val junit = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
            const val js = "org.jetbrains.kotlin:kotlin-test-js:$kotlinVersion"
            const val test = "org.jetbrains.kotlin:kotlin-test:$kotlinVersion"
            const val annotationsCommon =
                "org.jetbrains.kotlin:kotlin-test-annotations-common:$kotlinVersion"
        }
    }

    object Kotlinx {
        val atomicfu = AtomicFu

        object AtomicFu {
            private const val atomicFuVersion = "0.14.2"
            const val plugin = "org.jetbrains.kotlinx:atomicfu-gradle-plugin:$atomicFuVersion"

            const val common = "org.jetbrains.kotlinx:atomicfu-common:$atomicFuVersion"
            const val jvm = "org.jetbrains.kotlinx:atomicfu:$atomicFuVersion"
            const val native = "org.jetbrains.kotlinx:atomicfu-native:$atomicFuVersion"
            const val js = "org.jetbrains.kotlinx:atomicfu-js:$atomicFuVersion"
        }
    }

    object Careem {
        val mockingbird = MockingBird

        object MockingBird {
            private const val mockingBirdVersion = "1.3.0"
            const val common = "com.careem.mockingbird:mockingbird:$mockingBirdVersion"
        }
    }

    object SqlDelight {
        private const val sqlDelightVersion = "1.3.0"
        const val plugin = "com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion"
        const val runtime = "com.squareup.sqldelight:runtime:$sqlDelightVersion"
        val driver = Driver

        object Driver {
            const val native = "com.squareup.sqldelight:native-driver:$sqlDelightVersion"
            const val sqlite = "com.squareup.sqldelight:sqlite-driver:$sqlDelightVersion"
            const val android = "com.squareup.sqldelight:android-driver:$sqlDelightVersion"
            const val js = "com.squareup.sqldelight:sqljs-driver:$sqlDelightVersion"
        }
    }

    object Stately {
        private const val statelyVersion = "1.0.3-a4"

        val isocollections = IsoCollections
        val isolate = Isolate

        object Isolate {
            const val common = "co.touchlab:stately-isolate:$statelyVersion"
        }

        object IsoCollections {
            const val common = "co.touchlab:stately-iso-collections:$statelyVersion"
        }
    }

    object Android {
        const val compileSdkVersion = 29
        const val minSdkVersion = 16
        const val targetSdkVersion = 29
        private const val androidGradlePlugin = "4.0.0"
        const val plugin = "com.android.tools.build:gradle:$androidGradlePlugin"
        val androidx = Androidx

        object Androidx {
           val core = Core

            object Core {
                private const val androidxKtxVersion = "1.2.0"
                const val ktx = "androidx.core:core-ktx:$androidxKtxVersion"
            }
        }
    }

    object Reaktive {
        private const val reaktiveVersion = "1.1.15"
        const val reaktive = "com.badoo.reaktive:reaktive:$reaktiveVersion"
        const val coroutinesInterop = "com.badoo.reaktive:coroutines-interop:$reaktiveVersion"
        const val testing = "com.badoo.reaktive:reaktive-testing:$reaktiveVersion"
    }
}
