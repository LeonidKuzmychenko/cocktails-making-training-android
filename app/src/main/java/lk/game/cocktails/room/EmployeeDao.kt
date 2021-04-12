package lk.game.cocktails.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee")
    suspend fun getAll(): List<Employee?>?

    @Query("SELECT * FROM employee WHERE id = :id")
    suspend fun getById(id: Long): Employee?

    @Insert
    suspend fun insert(employee: Employee?)

    @Update
    suspend fun update(employee: Employee?)

    @Delete
    suspend fun delete(employee: Employee?)
}