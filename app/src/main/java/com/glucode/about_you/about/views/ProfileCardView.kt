package com.glucode.about_you.about.views

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.glucode.about_you.databinding.ViewProfileCardBinding
import com.glucode.about_you.engineers.models.Engineer

private const val TAG = "ProfileCard"

class ProfileCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding: ViewProfileCardBinding = ViewProfileCardBinding.inflate(LayoutInflater.from(context), this, true)

    fun setEngineerDetails(engineerDetails: Engineer, onClickAction: () -> Unit) {
        binding.name.text = engineerDetails.name
        binding.jobTitle.text = engineerDetails.role
        binding.tvYears.text = engineerDetails.quickStats.years.toString()
        binding.tvCoffees.text = engineerDetails.quickStats.coffees.toString()
        binding.tvBugs.text = engineerDetails.quickStats.bugs.toString()
        binding.profileImage.setOnClickListener {
            onClickAction()
        }
    }

    fun setProfileImage(uri: Uri) {
        binding.profileImage.setImageURI(uri)
    }

}
