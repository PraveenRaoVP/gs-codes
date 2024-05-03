package android.example.todo_application.screens.notes

import android.example.todo_application.R
import android.example.todo_application.database.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val dataList: List<Note>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTitle: TextView = itemView.findViewById(R.id.title_item)
        val noteContent: TextView = itemView.findViewById(R.id.content_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.noteTitle.text = currentItem.title
        holder.noteContent.text = currentItem.content
    }
}