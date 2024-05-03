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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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

        gameViewModel.navigateToNoteDetail.observe(viewLifecycleOwner) {
            this.findNavController().navigate(GameFragmentDirections.actionGameFragmentToNoteFragment(it))
        }

        return binding.root
    }

    private fun getNotes() {
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GameFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}