package com.rickandmortykmm


import com.rickandmortykmm.presentation.CharactersViewModel
import io.ktor.client.engine.darwin.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        Darwin.create()
    }

    //single or factory can be used to get a view-model object for swiftui

    single {
        CharactersViewModel(get())
    }
}

/**
 * ViewModels object implements koin component thus its able to get any
 * dependency that is listed in platform module we can call getHomeviewmodel()
 * in swift ui to get an object of HomeViewModel
 */
object ViewModels : KoinComponent {
    fun getCharactersViewModel() = get<CharactersViewModel>()
}

actual interface CommonParcelable
