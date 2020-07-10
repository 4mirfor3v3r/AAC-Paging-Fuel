package com.acemirr.cleanarchitecture.data.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Ali Asadi on 13/05/2020
 */
class DiskExecutor : Executor {

    val diskExecutor: Executor = Executors.newSingleThreadExecutor()


    override fun execute(runnable: Runnable) {
        diskExecutor.execute(runnable)
    }

}