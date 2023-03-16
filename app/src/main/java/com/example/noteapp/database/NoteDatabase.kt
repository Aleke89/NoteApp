package com.example.noteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.models.Note
import com.example.noteapp.utilities.DATABASE_NAME

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun getNoteDao():NoteDao

    companion object{
        @Volatile
        private var INSTANSE:NoteDatabase? = null
        fun getDatabase(context: Context):NoteDatabase{
            return INSTANSE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANSE = instance
                instance
            }
        }
    }
}