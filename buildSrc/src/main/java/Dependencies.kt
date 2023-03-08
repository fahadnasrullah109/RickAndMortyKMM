object Dependencies {
    // Accompanist
    const val coil = "io.coil-kt:coil-compose:2.1.0"
    private const val systemUIControllerVersion = "0.24.3-alpha"
    const val systemUIController =
        "com.google.accompanist:accompanist-systemuicontroller:$systemUIControllerVersion"

    // Compose
    const val composeUiVersion = "1.3.3"
    const val composeFoundationVersion = "1.3.1"
    const val composeNavVersion = "2.5.3"
    const val composeActivityVersion = "1.6.1"
    const val composeUI = "androidx.compose.ui:ui:$composeUiVersion"
    const val composeTooling = "androidx.compose.ui:ui-tooling:$composeUiVersion"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview:$composeUiVersion"
    const val composeFoundation = "androidx.compose.foundation:foundation:$composeFoundationVersion"
    const val composeActivity = "androidx.activity:activity-compose:$composeActivityVersion"
    const val composeNavigation = "androidx.navigation:navigation-compose:$composeNavVersion"
    const val composeUtil = "androidx.compose.ui:ui-util:$composeUiVersion"

    // Coroutines
    private const val coroutineVersion = "1.6.4"
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"

    // Koin
    private const val koinVersion = "3.4.2"
    const val koinAndroid = "io.insert-koin:koin-androidx-compose:$koinVersion"
    const val koin = "io.insert-koin:koin-core:3.1.4"

    // Kotlinx
    private const val kotlinxSerialization = "1.3.3"
    const val serializationCore =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${kotlinxSerialization}"

    // Kotlinx-datetime
    private const val dateTimeVersion = "0.4.0"
    const val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${dateTimeVersion}"

    // Ktor
    private const val ktorVersion = "2.2.3"
    const val clientCore = "io.ktor:ktor-client-core:${ktorVersion}"
    const val clientJson = "io.ktor:ktor-client-json:${ktorVersion}"
    const val clientLogging = "io.ktor:ktor-client-logging:${ktorVersion}"
    const val clientSerialization = "io.ktor:ktor-client-serialization:${ktorVersion}"
    const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${ktorVersion}"
    const val json = "io.ktor:ktor-serialization-kotlinx-json:${ktorVersion}"
    const val clientAndroid = "io.ktor:ktor-client-android:${ktorVersion}"
    const val clientIos = "io.ktor:ktor-client-ios:${ktorVersion}"

    // Material3
    private const val materialVersion = "1.0.1"
    val material3 = "androidx.compose.material3:material3:$materialVersion"
    val window = "androidx.compose.material3:material3-window-size-class:$materialVersion"

    // Moko
    private var mokoMvvmVersion = "0.15.0"
    val mokoMVVMCore = "dev.icerock.moko:mvvm-core:$mokoMvvmVersion"

    // Realm
    private const val realmVersion = "1.6.1"
    const val realm = "io.realm.kotlin:library-base:$realmVersion"

    // Splash
    private const val splashVersion = "1.0.0-beta02"
    const val splashScreen = "androidx.core:core-splashscreen:$splashVersion"

}