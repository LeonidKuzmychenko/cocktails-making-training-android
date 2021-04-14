//package lk.game.cocktails.room
//
//import androidx.room.*
//
//@Dao
//interface GameResultDao {
//
//    @Query("SELECT * FROM gameresult")
//    suspend fun getAll(): List<GameResult?>?
//
//    @Query("SELECT * FROM gameresult WHERE id = :id")
//    suspend fun getById(id: Long): GameResult?
//
//    @Insert
//    suspend fun insert(gameResult: GameResult?)
//
//    @Update
//    suspend fun update(gameResult: GameResult?)
//
//    @Delete
//    suspend fun delete(gameResult: GameResult?)
//}