package lk.game.cocktails.shared

import android.content.SharedPreferences


class SharedPrefCocktailService(private val sp: SharedPreferences) {

    private val KEY_EXCLUDE: String = "KEY"

    fun addExclude(exclude: Long) {
        val excludeList = getExcludeList()
        excludeList.add(exclude)

        val editor: SharedPreferences.Editor = sp.edit()
        editor.putStringSet(KEY_EXCLUDE, excludeList.map { it.toString() }.toSet())
        editor.apply()
    }

    fun getExcludeList(): MutableList<Long> {
        val excludeStringSet: Set<String>? = sp.getStringSet(KEY_EXCLUDE, HashSet<String>())
        val excludeLongList: List<Long>? = excludeStringSet?.map { it.toLong() }
        return excludeLongList?.toMutableList() ?: mutableListOf()
    }

    fun getExcludeListSize(): Int = getExcludeList().size

    fun clearExcludeList(): MutableList<Long> {
        val editor: SharedPreferences.Editor = sp.edit()
        editor.putStringSet(KEY_EXCLUDE, HashSet<String>())
        editor.apply()
        return mutableListOf()
    }
}