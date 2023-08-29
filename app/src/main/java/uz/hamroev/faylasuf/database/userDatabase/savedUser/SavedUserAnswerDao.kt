package uz.hamroev.faylasuf.database.userDatabase.savedUser

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SavedUserAnswerDao {


    @Insert
    fun creteNewUserAnswer(savedUserAnswers: SavedUserAnswers)

    @Query("SELECT id FROM saved ORDER BY id DESC LIMIT 1;")
    fun getSavedId(): Int


    @Insert
    fun addUserAnswersToEachQuestion(userAnswers: UserAnswers)


}