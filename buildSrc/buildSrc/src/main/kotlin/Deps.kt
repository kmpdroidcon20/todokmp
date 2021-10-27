object Deps {
    private const val kotlinVersion = "1.5.31"
    private const val junitAndroidxVersion = "1.1.1"
    private const val robolectricVersion = "4.3.1"
    private const val atomicFuVersion = "0.16.3"
    private const val sqlDelightVersion = "1.5.2"
    private const val mockingBirdVersion = "1.14.0"
    private const val statelyVersion = "1.1.10-a1"
    private const val androidGradlePlugin = "4.2.1"
    private const val testRunnerVersion = "1.0.2"
    private const val appCompatVersion = "1.1.0"
    private const val constraintLayoutVersion = "1.1.3"
    private const val androidxKtxVersion = "1.2.0"
    private const val reaktiveVersion = "1.2.1"
    private const val espressoVersion = "3.4.0"
    private const val junitVersion = "4.12"
    private const val androidxVersion = "1.0.0"

    val kotlin = Kotlin
    val stately = Stately
    val kotlinx = Kotlinx
    val careem = Careem
    val android = Android
    val junit = JUnit
    val sqlDelight = SqlDelight
    val reaktive = Reaktive

    // Robolectric
    const val robolectric = "org.robolectric:robolectric:$robolectricVersion"

    object Kotlin {
        val stdlib = Stdlib()
        val test = Test
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

        class Stdlib(
            private val name: String = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        ) : CharSequence by name {
            val common = "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"

            override fun toString(): String = name
        }

        object Test {
            const val common = "org.jetbrains.kotlin:kotlin-test-common:$kotlinVersion"
            const val junit = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
            const val test = "org.jetbrains.kotlin:kotlin-test:$kotlinVersion"
            const val annotationsCommon =
                "org.jetbrains.kotlin:kotlin-test-annotations-common:$kotlinVersion"
        }
    }

    object Kotlinx {
        val atomicfu = AtomicFu()

        class AtomicFu(
            private val name: String = "org.jetbrains.kotlinx:atomicfu:$atomicFuVersion"
        ) : CharSequence by name {

            val plugin = "org.jetbrains.kotlinx:atomicfu-gradle-plugin:$atomicFuVersion"
            override fun toString() = name
        }
    }

    object Careem {
        val mockingbird = MockingBird

        object MockingBird {
            const val common = "com.careem.mockingbird:mockingbird:$mockingBirdVersion"
        }
    }

    object SqlDelight {
        const val plugin = "com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion"
        const val runtime = "com.squareup.sqldelight:runtime:$sqlDelightVersion"
        val driver = Driver

        object Driver {
            const val native = "com.squareup.sqldelight:native-driver:$sqlDelightVersion"
            const val sqlite = "com.squareup.sqldelight:sqlite-driver:$sqlDelightVersion"
            const val android = "com.squareup.sqldelight:android-driver:$sqlDelightVersion"
        }
    }

    object Stately {
        val isoCollections = IsoCollections
        val isolate = Isolate()

        class Isolate(
            private val name: String = "co.touchlab:stately-isolate:$statelyVersion"
        ) : CharSequence by name {
            override fun toString() = name
        }

        object IsoCollections {
            const val common = "co.touchlab:stately-iso-collections:$statelyVersion"
        }
    }

    object Android {
        const val compileSdkVersion = 30
        const val minSdkVersion = 21
        const val targetSdkVersion = 30

        const val plugin = "com.android.tools.build:gradle:$androidGradlePlugin"
        val androidx = Androidx

        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

        const val testRunner = "com.android.support.test:runner:$testRunnerVersion"
        const val espresso = "com.android.support.test.espresso:espresso-core:$espressoVersion"
        const val recyclerview = "androidx.recyclerview:recyclerview:$androidxVersion"

        object Androidx {
            val core = Core
            const val junitAndroidx = "androidx.test.ext:junit:$junitAndroidxVersion"


            object Core {
                const val ktx = "androidx.core:core-ktx:$androidxKtxVersion"
            }
        }
    }

    object JUnit {
        const val junit = "junit:junit:$junitVersion"
    }

    object Reaktive {
        const val reaktive = "com.badoo.reaktive:reaktive:$reaktiveVersion"
        const val coroutinesInterop = "com.badoo.reaktive:coroutines-interop:$reaktiveVersion"
        const val testing = "com.badoo.reaktive:reaktive-testing:$reaktiveVersion"
    }
}
