package com.glucode.about_you.about

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.glucode.about_you.about.views.ProfileCardView
import com.glucode.about_you.about.views.QuestionCardView
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import com.glucode.about_you.viewmodel.AboutViewModel

private const val TAG = "AboutFragment"

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    private val vm: AboutViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentEngineer = vm.currentEngineer.value
        if (currentEngineer != null) {
            Log.i(TAG, currentEngineer.name)
            setUpProfile(currentEngineer)
            setUpQuestions(currentEngineer)
        }
    }

    private fun setUpQuestions(engineer: Engineer) {
        engineer.questions.forEach { question ->
            val questionView = QuestionCardView(requireContext())
            questionView.title = question.questionText
            questionView.answers = question.answerOptions
            questionView.selection = question.answer.index

            binding.container.addView(questionView)
        }
    }

    private fun setUpProfile(engineer: Engineer) {
        val profileCardView = ProfileCardView(requireContext())
        profileCardView.setEngineerDetails(engineer)
        binding.container.addView(profileCardView)
    }

}