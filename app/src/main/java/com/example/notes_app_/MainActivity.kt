package com.example.notes_app_

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), INotesRVAdapter {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)

            }
        })


    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
       Toast.makeText(this,"${note.text} DELETED ",Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val noteText = findViewById<EditText>(R.id.input).text.toString()
        val phoneText =findViewById<EditText>(R.id.inputphone).text.toString()
        val AmountText = findViewById<EditText>(R.id.inputAmount).text.toString()



        if (noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText,phoneText,AmountText))
            Toast.makeText(this,"$noteText ,$phoneText SUBMITED  ",Toast.LENGTH_LONG).show()

        }
    }
}