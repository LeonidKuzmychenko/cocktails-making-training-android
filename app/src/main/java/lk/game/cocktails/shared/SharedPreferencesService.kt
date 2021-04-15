package lk.game.cocktails.shared

import android.content.Context
import android.content.SharedPreferences


class SharedPreferencesService(context: Context) {
    val APP_PREFERENCES_NAME = "Nickname"

    var sp: SharedPreferences = context.getSharedPreferences(
        "mysettings",
        Context.MODE_PRIVATE
    )

    fun addExclude(exclude: String) {
        val excludeList = getExcluded()
        excludeList.add(exclude)

        val editor: SharedPreferences.Editor = sp.edit()
        editor.putStringSet(APP_PREFERENCES_NAME, excludeList.toSet())
        editor.apply()
    }

    fun getExcluded(): MutableList<String> {
        val ex: MutableSet<String>? = sp.getStringSet(APP_PREFERENCES_NAME, HashSet<String>())
        return ex?.toMutableList() ?: mutableListOf()
    }
}