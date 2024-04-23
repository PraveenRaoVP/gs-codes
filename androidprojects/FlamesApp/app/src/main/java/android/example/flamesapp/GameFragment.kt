package android.example.flamesapp

import android.example.flamesapp.databinding.FragmentGameBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

class GameFragment : Fragment() {

    data class Game(val name1: String, val name2: String)


    private lateinit var binding: FragmentGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState != null) {
            binding.name1EditText.setText(savedInstanceState.getString("name1"))
            binding.name2EditText.setText(savedInstanceState.getString("name2"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        binding.startButton.setOnClickListener {view: View ->
            val name1 = binding.name1EditText.text.toString()
            val name2 = binding.name2EditText.text.toString()

            // if name1 or name2 is empty, show a toast
            if(name1.isEmpty() || name2.isEmpty()) {
                if(name1.isEmpty()) {
                    binding.name1EditText.error = "Name 1 is required!"
                    Toast.makeText(context, "Name 1 is required!", Toast.LENGTH_SHORT).show()
                }
                if(name2.isEmpty()) {
                    binding.name2EditText.error = "Name 2 is required!"
                    Toast.makeText(context, "Name 2 is required!", Toast.LENGTH_SHORT).show()
                }
                return@setOnClickListener
            } else {
                view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToResultFragment(name1, name2))
            }

        }

        return binding.root
    }

    companion object {


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("name1", binding.name1EditText.text.toString())
        outState.putString("name2", binding.name2EditText.text.toString())

    }
}