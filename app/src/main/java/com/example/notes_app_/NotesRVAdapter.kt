package com.example.notes_app_

import android.content.Context
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text


class NotesRVAdapter(private val context: Context, private val listener: INotesRVAdapter) :
    RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()


    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById<TextView>(R.id.textitem)
        val phoneView: TextView = itemView.findViewById<TextView>(R.id.textitemphone)
        val deleteButton: ImageView = itemView.findViewById<ImageView>(R.id.deletebtn)
        val AmountView: TextView = itemView.findViewById(R.id.textitemAmount)

        fun bind(note: Note) {
            textView.text = note.text
            phoneView.text = note.Phone
            AmountView.text= note.Amount
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder =
            NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.bind(currentNote)
//        holder.textView.text = currentNote.text
//        holder.phoneView.text = currentNote.Phone


    }

    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return allNotes.size

    }
}

interface INotesRVAdapter {
    fun onItemClicked(note: Note)

}

