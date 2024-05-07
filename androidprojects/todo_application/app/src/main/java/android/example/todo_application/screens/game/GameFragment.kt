package android.example.todo_application.screens.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.todo_application.R
import android.example.todo_application.database.NoteDatabase
import android.example.todo_application.databinding.FragmentGameBinding
import android.example.todo_application.screens.notes.NoteAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GameFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.title = "Noteify"


        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        recyclerView = binding.recyclerView

        val dataSource = NoteDatabase.getInstance(requireContext().applicationContext).noteDatabaseDao()

        val application = requireNotNull(this.activity).application

        val viewModelFactory = GameViewModelFactory(dataSource, application)

        gameViewModel = ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]

        binding.gameViewModel = gameViewModel

        binding.lifecycleOwner = this

        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)

        getNotes()

//        gameViewModel.noteIdMonitor.observe(viewLifecycleOwner) {
//            this.findNavController().navigate(GameFragmentDirections.actionGameFragmentToNoteFragment(it))
//        }
        gameViewModel.onNoteClickedEvent.observe(viewLifecycleOwner) {
            if(it==true){
                this.findNavController().navigate(GameFragmentDirections.actionGameFragmentToNoteFragment(gameViewModel.noteIdMonitor.value!!))
                gameViewModel.onDoneNavigating()
            }
        }

        gameViewModel.noteIdMonitor.observe(viewLifecycleOwner) {
            if(it == -1L) {
                this.findNavController().navigate(GameFragmentDirections.actionGameFragmentToNoteFragment(-1))
                gameViewModel.onDoneNavigating()
            }
        }


        return binding.root
    }

    private fun getNotes() {
        // display content only upto 20 chars in recycler view
        val adapter = NoteAdapter(gameViewModel)
        recyclerView.adapter = adapter
        gameViewModel.notes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            for (note in it) {
                if (note.content.length > 20) {
                    note.content = note.content.substring(0, 20) + "..."
                }
            }
            adapter.notifyDataSetChanged()
        }
    }
}