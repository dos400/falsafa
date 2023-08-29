package uz.hamroev.faylasuf.database.userDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class UserEntity {


    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var firstName: String = ""
    var lastName: String = ""
    var age: Int = 0
    var email: String = ""
    var phoneNumber: String = ""
    var course: Int = 0
    var address: String = ""
    var interests: String = ""

    var coin: Int = 0

    var level: Int = 1
    var trueAnswerCount: Int = 0
    var replaceQuiz: Boolean = false
    var hintAnswer: Boolean = false
    var helpAnswer: Boolean = false


}