object SupportedTargets {
    const val ANDROID = "ANDROID"
    const val IOS = "IOS"
    const val JVM = "JVM"

    val ALL = listOf(JVM, ANDROID, IOS)
    val COMMON = listOf(JVM, IOS)
    val MOBILE = listOf(ANDROID, IOS)
}