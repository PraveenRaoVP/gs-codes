package android.example.todo_application.screens.notes

import android.example.todo_application.R
import android.example.todo_application.database.Note
import android.example.todo_application.databinding.ItemNoteBinding
import android.example.todo_application.screens.game.GameViewModel
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteAdapter(val gameViewModel: GameViewModel, var dataList: List<Note>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){
    class NoteViewHolder(val binding: ItemNoteBinding ) : RecyclerView.ViewHolder(binding.root) {
        val cardView: CardView = binding.cardView
        val deleteButton: FloatingActionButton = binding.deleteButton
        fun bind(note: Note) {
            binding.note = note
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)

        holder.cardView.setOnClickListener(View.OnClickListener {
                // navigate to note fragment from game fragment
                gameViewModel.onNoteClicked(currentItem.id)
                Log.i("NoteAdapter", "Note clicked")
        })
        holder.deleteButton.setOnClickListener {
            gameViewModel.onDeleteClicked(currentItem.id)
        }
    }
}