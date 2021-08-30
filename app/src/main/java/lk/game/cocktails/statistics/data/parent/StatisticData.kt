package lk.game.cocktails.statistics.data.parent

import lk.game.cocktails.statistics.data.StatisticGame
import lk.game.cocktails.statistics.data.StatisticLibrary

data class StatisticData(
    var uuid: String?,
    var locale: String?,
    var region: String?,
    var libraryResults: MutableList<StatisticLibrary>,
    var gameResults: MutableList<StatisticGame>
) {

    constructor() : this(null, null, null, arrayListOf(), arrayListOf())
}