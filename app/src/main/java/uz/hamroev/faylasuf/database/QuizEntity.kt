package uz.hamroev.faylasuf.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz")
class QuizEntity (

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var question: String = "",
    var difficulty: Int = 0,
    var source: String? = "",
    var coin: Int? = 0
)