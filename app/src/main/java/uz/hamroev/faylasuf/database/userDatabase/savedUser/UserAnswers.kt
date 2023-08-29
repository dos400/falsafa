package uz.hamroev.faylasuf.database.userDatabase.savedUser

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "useranswer")
data class UserAnswers(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    var savedUserAnswersId: Int,
    var questionNumber: Int,
    var question: String,

    var answerA: String,
    var answerB: String,
    var answerC: String,
    var answerD: String,

    var trueAnswer: String,
    var userAnswer: String,

    var dateAnswer: String,
)