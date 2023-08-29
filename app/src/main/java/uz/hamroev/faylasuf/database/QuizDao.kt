package uz.hamroev.faylasuf.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuizDao {

    @Query("select * from quiz")
    fun getAllQuestion(): List<QuizEntity>

    //get random data
    @Query("select * from quiz where coin=0 and difficulty=:difficulty order by random() limit 1")
    fun getQuiz(difficulty: Int): List<QuizEntity>

    //set used data
    @Query("update quiz set coin=1 where id=:questionId")
    fun updateUsedQuestion(questionId: Int)

    //clear all used data
    @Query("update quiz set coin=0")
    fun updateAllUsedQuestion()

    //get answers
    @Query("select * from answers where question_id=:questionId order by random()")
    fun getAnswersOfQuestion(questionId: Int): List<AnswersEntity>


}