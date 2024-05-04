package android.example.todo_application.screens.notes

import android.example.todo_application.R
import android.example.todo_application.database.Note
import android.example.todo_application.databinding.ItemNoteBinding
import android.example.todo_application.screens.game.GameViewModel
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteAdapter(val gameViewModel: GameViewModel): ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffCallback()) { // this is the adapter for the recycler view
    class NoteViewHolder private constructor(val binding: ItemNoteBinding ) : RecyclerView.ViewHolder(binding.root) { // this is the view holder which contains the card view content
        val cardView: CardView = binding.cardView
        val deleteButton: FloatingActionButton = binding.deleteButton

        fun bind(note: Note) { // this function binds the note to the card view
            binding.note = note
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NoteViewHolder {
                val itemView =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
                val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return NoteViewHolder(binding) // returns the view holder
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder { // this function inflates the card view
        return NoteViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) { // this function binds the note to the card view
        val currentItem = getItem(position)// get the current note
        holder.bind(currentItem)

        holder.cardView.setOnClickListener(View.OnClickListener {
                // navigate to note fragment from game fragment
                gameViewModel.onNoteClicked(currentItem.id)
                Log.i("NoteAdapter", "Note clicked")
        }) // when the card view is clicked, navigate to the note fragment
        holder.deleteButton.setOnClickListener {
            gameViewModel.onDeleteClicked(currentItem.id)
            notifyDataSetChanged()
        } // when the delete button is clicked, delete the note
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}