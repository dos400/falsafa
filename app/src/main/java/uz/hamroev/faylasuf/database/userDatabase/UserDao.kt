package uz.hamroev.faylasuf.database.userDatabase

import androidx.room.*


@Dao
interface UserDao {


    //crete user
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun creteUser(userEntity: UserEntity)

    // update
    @Update
    fun updateUser(userEntity: UserEntity)

    // get current level
    @Query("select level from user")
    fun getCurrentLevel(): Int

    //get current coin
    @Query("select coin from user")
    fun getCurrentCoin(): Int

    //get has replace quiz
    @Query("select replaceQuiz from user")
    fun getHasReplaceQuiz(): Boolean

    //get current hint answer
    @Query("select hintAnswer from user")
    fun getHasHintAnswer(): Boolean

    //get current help answer
    @Query("select helpAnswer from user")
    fun getHasHelpAnswer(): Boolean

    //clear level
    @Query("update user set level = 1")
    fun clearLevel()

    //clear replace quiz
    @Query("update user set replaceQuiz=0")
    fun clearReplaceQuiz()

    //clear hint answer
    @Query("update user set hintAnswer=0")
    fun clearHintAnswer()

    //clear help answer
    @Query("update user set helpAnswer=0")
    fun clearHelpAnswer()


    //update level
    @Query("update user set level=:currentLevel+1")
    fun updateLevel(currentLevel: Int)



    //use replace quiz
    @Query("update user set replaceQuiz=1")
    fun useReplaceQuiz()

    //use hint answer
    @Query("update user set hintAnswer=1")
    fun useHintAnswer()

    //use help answer
    @Query("update user set helpAnswer=1")
    fun useHelpAnswer()



    //coin
    //update
    @Query("update user set coin = coin + :newCoin")
    fun updateCoin(newCoin: Int)

    //clear coin
    @Query("update user set coin=0")
    fun clearCoin()

    //spendCoin
    @Query("update user set coin = coin - :amount")
    fun spendCoin(amount: Int)




}