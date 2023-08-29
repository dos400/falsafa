package uz.hamroev.faylasuf.app

import android.app.Application
import uz.hamroev.faylasuf.cache.Cache
import uz.hamroev.faylasuf.database.QuizDatabase
import uz.hamroev.faylasuf.database.userDatabase.UserDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Cache.init(this)
        QuizDatabase.init(this)
        UserDatabase.init(this)
    }
}