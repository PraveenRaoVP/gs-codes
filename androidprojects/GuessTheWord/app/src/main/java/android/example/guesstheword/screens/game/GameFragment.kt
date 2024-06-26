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

package android.example.guesstheword.screens.game

import android.example.guesstheword.R
import android.example.guesstheword.databinding.GameFragmentBinding
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {
    private lateinit var binding: GameFragmentBinding

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.i("GameFragment", "Called ViewModelProvider")
        viewModel = ViewModelProvider(this)[GameViewModel::class.java] // deprecated command:
        // ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventGameFinish.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished) {
                gameFinished()
                viewModel.onGameFinish()
            }
        }

        viewModel.currentTime.observe(viewLifecycleOwner) { time ->
            binding.timerText.text = DateUtils.formatElapsedTime(time)
        }

        viewModel.eventBuzz.observe(viewLifecycleOwner) { buzzType ->
            if (buzzType != GameViewModel.BuzzType.NO_BUZZ) {
                buzz(buzzType.pattern)
                viewModel.onBuzzComplete()
            }
        }

        return binding.root
    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameToScore(viewModel.score.value ?: 0)
        // GameFragmentDirections is a generated class
        findNavController().navigate(action) // Navigate to the next screen using the action, passing score with it.
    }

    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()

        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            }
        }
    }
}
