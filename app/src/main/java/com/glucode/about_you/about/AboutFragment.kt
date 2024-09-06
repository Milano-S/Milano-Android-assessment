package com.glucode.about_you.about

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import com.glucode.about_you.viewmodel.AboutViewModel

private const val TAG = "AboutFragment"

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    private val vm: AboutViewModel by activityViewModels()
    private lateinit var profileCardView: ProfileCardView

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
            setUpProfile(currentEngineer)
            setUpQuestions(currentEngineer)
        }

        vm.currentProfileImageUri.observe(viewLifecycleOwner) { uri ->
            uri?.let {
                profileCardView.setProfileImage(it)
            }
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
        profileCardView = ProfileCardView(requireContext())
        profileCardView.setEngineerDetails(engineer) {
            try {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, 100)
            } catch (e: Exception) {
                Log.i(TAG, e.message.toString())
            }
        }
        binding.container.addView(profileCardView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            if (selectedImageUri != null) {
                vm.setCurrentProfileImage(selectedImageUri)
                Log.i(TAG, "Image set ${vm.currentProfileImageUri.value}")
            }
        }
    }
}
