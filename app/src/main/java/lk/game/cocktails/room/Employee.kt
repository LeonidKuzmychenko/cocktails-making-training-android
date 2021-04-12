package lk.game.cocktails.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "firstName")
    val firstName: String?
)

