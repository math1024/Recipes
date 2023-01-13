package com.jetpack.recipes

import android.app.Application
import com.jetpack.recipes.db.AppDatabase

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this)
    }
}