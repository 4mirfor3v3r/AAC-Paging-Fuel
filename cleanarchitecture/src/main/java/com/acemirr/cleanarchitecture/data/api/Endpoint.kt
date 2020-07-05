package com.acemirr.cleanarchitecture.data.api

import com.acemirr.cleanarchitecture.BuildConfig

object Endpoint {
    const val BASE_URL = BuildConfig.BASE_URL

    const val LIST_MALANG_BATU = "${BASE_URL}List_place_malang_batu.json"
    const val GRID_GALLERY = "${BASE_URL}Gallery_Malang_Batu.json"

    const val PAGING_BASE_URL = "https://newsapi.org/v2/everything?q=sports&apiKey=b0b90bff982042f6a8e03fb6430dbc50"
}