/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.example.guesstheword.screens.score

import android.example.guesstheword.R
import android.example.guesstheword.databinding.ScoreFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

/**
 * Fragment where the final score is shown, after the game is over
 */

class ScoreFragment : Fragment() {

    private lateinit var scoreViewModel: ScoreViewModel
    private lateinit var scoreViewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class.
        val binding: ScoreFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.score_fragment,
                container,
                false
        )

        // Get args using by navArgs property delegate - ScoreFragmentArgs from the safe args plugin. by is a keyword in Kotlin that allows you to delegate a property to another object. In this case, you're delegating the property to the navArgs() function. What is delegating a property? It means that you're allowing another object to provide the implementation for the property. In this case, the navArgs() function provides the implementation for the property. The navArgs() function is a property delegate that retrieves the arguments passed to the fragment.
        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
        scoreViewModelFactory = ScoreViewModelFactory(scoreFragmentArgs.score)
        scoreViewModel = ViewModelProvider(this, scoreViewModelFactory)[ScoreViewModel::class.java]

        binding.scoreViewModel = scoreViewModel

        scoreViewModel.eventPlayAgain.observe(viewLifecycleOwner) {playAgain ->
            if(playAgain) {
                findNavController().navigate(ScoreFragmentDirections.actionRestart())
                scoreViewModel.onPlayAgainComplete()
            }
        }

        return binding.root
    }
}
