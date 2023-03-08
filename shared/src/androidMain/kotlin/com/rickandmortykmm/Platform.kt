package com.rickandmortykmm


import android.os.Parcelable
import com.rickandmortykmm.presentation.CharactersViewModel
import io.ktor.client.engine.android.*
import kotlinx.parcelize.Parcelize
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * shared implementation of parcelable
 */
actual typealias CommonParcelize = Parcelize

actual typealias CommonParcelable = Parcelable


actual fun platformModule() = module {


    single {
        Android.create()
    }
    /**
     *
     * for android koin has a special viewmodel scope that we can use
     * to create a viewmodel
     *
     */

    viewModel {
        CharactersViewModel(
            get()
        )
    }
}