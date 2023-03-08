package com.rickandmortykmm.di
import com.rickandmortykmm.data.local.dao.CharacterDao
import com.rickandmortykmm.data.local.dao.LastSyncDao
import com.rickandmortykmm.data.local.service.IRealmService
import com.rickandmortykmm.data.local.service.RealmServiceImpl
import com.rickandmortykmm.data.remote.service.IKtorService
import com.rickandmortykmm.data.remote.service.KtorServiceImpl
import com.rickandmortykmm.data.repository.CharacterRepoImpl
import com.rickandmortykmm.domain.repo.ICharacterRepo
import com.rickandmortykmm.domain.usecase.GetCharactersUseCase
import com.rickandmortykmm.platformModule
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    enableNetworkLogs: Boolean = false,
    baseUrl: String,
    appDeclaration: KoinAppDeclaration = {}
) =
    startKoin {
        appDeclaration()
        modules(commonModule(enableNetworkLogs = enableNetworkLogs, baseUrl))
    }

// called by iOS etc
fun initKoin(baseUrl: String) = initKoin(enableNetworkLogs = true, baseUrl) {}

fun commonModule(enableNetworkLogs: Boolean, baseUrl: String) =
    getUseCaseModule() + getDateModule(
        enableNetworkLogs,
        baseUrl
    ) + platformModule()

fun getDateModule(enableNetworkLogs: Boolean, baseUrl: String) = module {

    single<ICharacterRepo> {
        CharacterRepoImpl(
            get(),
            get()
        )
    }

    single<IKtorService> {
        KtorServiceImpl(
            get(),
            baseUrl
        )
    }

    single<IRealmService> {
        RealmServiceImpl(
            get()
        )
    }

    single {
        Realm.open(
            RealmConfiguration.Builder(schema = setOf(CharacterDao::class, LastSyncDao::class))
                .build()
        )
    }

    single { createJson() }

    single {
        createHttpClient(
            get(),
            get(),
            enableNetworkLogs = enableNetworkLogs
        )
    }


}

fun getUseCaseModule() = module {
    single {
        GetCharactersUseCase(get())
    }
}


fun createHttpClient(
    httpClientEngine: HttpClientEngine,
    json: Json,
    enableNetworkLogs: Boolean
) =
    HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(json)
        }
        if (enableNetworkLogs) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }