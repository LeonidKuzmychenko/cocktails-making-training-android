package lk.game.cocktails.statistics.data.parent

import lk.game.cocktails.statistics.data.StatisticGame
import lk.game.cocktails.statistics.data.StatisticSearch

data class StatisticData(
    var uuid: String?,
    var locale: String?,
    var region: String?,
    var androidVersion: String?,
    var searchResults: MutableList<StatisticSearch>,
    var gameResults: MutableList<StatisticGame>
) {

    constructor() : this(null, null, null, null, arrayListOf(), arrayListOf())
}