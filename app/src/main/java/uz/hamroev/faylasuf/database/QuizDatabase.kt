package uz.hamroev.faylasuf.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [QuizEntity::class, AnswersEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QuizDatabase: RoomDatabase() {

    abstract fun getQuizDao(): QuizDao

    companion object {
        @Volatile
        private var database: QuizDatabase? = null

        fun init(context: Context) {
            synchronized(this) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        QuizDatabase::class.java,
                        "quiz.db"
                    )
                        .createFromAsset("quiz.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }

    }


    object GET {
        fun getQuizDatabase(): QuizDatabase = database!!
    }

}