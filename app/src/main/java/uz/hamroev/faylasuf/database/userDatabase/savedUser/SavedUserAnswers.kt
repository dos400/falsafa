package uz.hamroev.faylasuf.database.userDatabase.savedUser

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "saved")
class SavedUserAnswers {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var total: Int = 0
    var date: String = ""

    constructor(total: Int, date: String) {
        this.total = total
        this.date = date
    }
}
