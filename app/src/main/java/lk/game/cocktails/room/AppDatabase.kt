package lk.game.cocktails.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao?
}