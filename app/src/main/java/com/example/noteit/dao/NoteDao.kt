package com.example.noteit.dao

import androidx.room.*
import com.example.noteit.model.NotesData

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes() : MutableList<NotesData>

    @Query("SELECT * FROM notes WHERE id =:id")
    fun getSpecificNote(id:Int) : NotesData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(note:NotesData)

    @Delete
    fun deleteNote(note:NotesData)

    @Query("DELETE FROM notes WHERE id =:id")
    fun deleteSpecificNote(id:Int)

    @Query("UPDATE notes SET title = :sTitle , date = :sDate , note_text = :sNoteTxt WHERE id = :sId")
    fun updateNote(sTitle: String,sDate: String,sNoteTxt: String,sId: Int)
}