package uz.hamroev.faylasuf.cache

import android.content.Context
import android.content.SharedPreferences

object Cache {

    private const val NAME = "faylasuf"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var firstTime: Boolean?
        get() = sharedPreferences.getBoolean("firstTime", false)
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putBoolean("firstTime", value)
            }
        }

    var sound: Boolean?
        get() = sharedPreferences.getBoolean("sound", true)
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putBoolean("sound", value)
            }
        }

    var music: Boolean?
        get() = sharedPreferences.getBoolean("music", true)
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putBoolean("music", value)
            }
        }

    var isHaveUser: Boolean?
        get() = sharedPreferences.getBoolean("isHaveUser", false)
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putBoolean("isHaveUser", value)
            }
        }



}
