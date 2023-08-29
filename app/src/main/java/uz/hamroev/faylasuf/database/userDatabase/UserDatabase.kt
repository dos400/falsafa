package uz.hamroev.faylasuf.database.userDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.hamroev.faylasuf.database.userDatabase.savedUser.SavedUserAnswerDao
import uz.hamroev.faylasuf.database.userDatabase.savedUser.SavedUserAnswers
import uz.hamroev.faylasuf.database.userDatabase.savedUser.UserAnswers

@Database(
    entities = [UserEntity::class, SavedUserAnswers::class, UserAnswers::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getSavedUserAnswerDao(): SavedUserAnswerDao

    companion object {
        @Volatile
        private var database: UserDatabase? = null

        fun init(context: Context) {
            synchronized(this) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user.db"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }

    }


    object GET {
        fun getUserDatabase(): UserDatabase = database!!
    }

}