package android.example.todo_application.screens.notes

import android.example.todo_application.R
import android.example.todo_application.database.NoteDatabase
import android.example.todo_application.databinding.FragmentNoteFragmentBinding
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController




class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_fragment, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Note"

        val dataSource = NoteDatabase.getInstance(requireContext().applicationContext).noteDatabaseDao()
        val application = requireNotNull(this.activity).application

        val arguments = NoteFragmentArgs.fromBundle(requireArguments()!!)
        val noteViewModel = NoteViewModel(dataSource, application)

        // i need to pass arguments to the view model
        noteViewModel.noteId = arguments.id

        binding.noteViewModel = noteViewModel

        binding.lifecycleOwner = this

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
        )

        noteViewModel.navigateToGame.observe(viewLifecycleOwner) {
            if (it == true) {
                Log.i("NoteFragment", "Navigating to GameFragment")
                this.findNavController()
                    .navigate(NoteFragmentDirections.actionNoteFragmentToGameFragment())
                noteViewModel.doneNavigating()
            }
        }

        noteViewModel.showErrorToast.observe(viewLifecycleOwner) {
            if (it == true) {
                Log.i("NoteFragment", "Showing error toast")
                Toast.makeText(context, "Title cannot be empty", Toast.LENGTH_SHORT).show()
                noteViewModel.doneShowingErrorToast()
            }
        }

        binding.titleEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                do nothing
            }

            override fun afterTextChanged(s: Editable?) {
                noteViewModel.title.value = s.toString()
            }
        })

        binding.contentEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // do nothing
            }

            override fun afterTextChanged(s: Editable?) {
                noteViewModel.content.value = s.toString()
            }

        })

        return binding.root
    }
}