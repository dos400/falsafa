package uz.hamroev.faylasuf.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answers")
class AnswersEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var answer: String = ""
    @ColumnInfo(name = "is_true")
    var correct: Int = 0
    var question_id: Int = 0

    override fun toString(): String {
        return "AnswersEntity(id=$id, answer='$answer', correct=$correct, question_id=$question_id)"
    }


}