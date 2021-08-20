package lk.game.cocktails.statistics.services

import android.content.SharedPreferences
import com.google.gson.Gson
import lk.game.cocktails.statistics.data.StatisticGame
import lk.game.cocktails.statistics.data.StatisticLibrary
import lk.game.cocktails.statistics.data.parent.StatisticData


class SharedPrefStatisticService(private val sp: SharedPreferences, private val gson: Gson) {

    private val KEY_STATISTIC: String = "STATISTIC"

    fun init(uuid: String, locale: String, region: String) {
        val statistic = getStatistic()
        statistic.uuid = uuid
        statistic.locale = locale
        statistic.region = region
        saveStatistic(statistic)
    }

    fun addLibraryResult(cocktailName: String) {
        val statistic = getStatistic()
        val libraryResult = StatisticLibrary(cocktailName);
        statistic.libraryResults.add(libraryResult)
        saveStatistic(statistic)
    }

    fun addGameResult(cocktailId: Long, result: Boolean) {
        val statistic = getStatistic()
        val gameResult = StatisticGame(cocktailId, result)
        statistic.gameResults.add(gameResult)
        saveStatistic(statistic)
    }

    fun getStatistic(): StatisticData {
        val statisticJson = sp.getString(KEY_STATISTIC, gson.toJson(StatisticData()))
        return gson.fromJson(statisticJson, StatisticData::class.java)
    }

    fun saveStatistic(statistic: StatisticData?) {
        with(sp.edit()) {
            putString(KEY_STATISTIC, gson.toJson(statistic))
            apply()
        }
    }

    fun clearStatistic() {
        val statistic = getStatistic()
        statistic.libraryResults = arrayListOf()
        statistic.gameResults = arrayListOf()

        with(sp.edit()) {
            putString(KEY_STATISTIC, gson.toJson(statistic))
            apply()
        }
    }
}

