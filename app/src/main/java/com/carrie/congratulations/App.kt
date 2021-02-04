package com.carrie.congratulations

import android.app.Application
import com.carrie.congratulations.data.dao.AppDatabase
import com.carrie.congratulations.utils.CrashHandler

/**
 * Created by Carrie on 2021/2/3.
 */
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        CrashHandler.getInstance()
        AppDatabase.getInstance(applicationContext)


    }
}