package com.example.noteapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.noteapp.databinding.ActivityNoteAddBinding
import com.example.noteapp.models.Note
import java.text.SimpleDateFormat
import java.util.*

class NoteAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteAddBinding
    private lateinit var note: com.example.noteapp.models.Note
    private lateinit var old_note: com.example.noteapp.models.Note
    var isUpdated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            old_note =
                intent.getSerializableExtra("current_note") as com.example.noteapp.models.Note
            binding.etTitle.setText(old_note.title)
            binding.etNote.setText(old_note.note)
            isUpdated = true

        } catch (e: Exception) {
            e.printStackTrace()
        }
        binding.imgCheck.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val note_desc = binding.etNote.text.toString()

            if (title.isNotEmpty() || note_desc.isNotEmpty()) {
                val formatter = SimpleDateFormat("EEE,d MMM yyyy HH:mm a")
                if (isUpdated) {
                    note = Note(old_note.id, title, note_desc, formatter.format(Date())
                    )
                } else {
                    note = Note(null, title, note_desc, formatter.format(Date()))
                }

                val intent = Intent()
                intent.putExtra("note",note)
                setResult(Activity.RESULT_OK,intent)
                finish()

            }else{
                Toast.makeText(this@NoteAddActivity,"Please enter all data",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

    }
}