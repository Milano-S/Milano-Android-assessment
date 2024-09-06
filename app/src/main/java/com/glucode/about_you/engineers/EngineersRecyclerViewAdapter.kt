package com.glucode.about_you.engineers

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ItemEngineerBinding
import com.glucode.about_you.engineers.models.Engineer

class EngineersRecyclerViewAdapter(
    private var engineers: List<Engineer>,
    private val onClick: (Engineer) -> Unit
) : RecyclerView.Adapter<EngineersRecyclerViewAdapter.EngineerViewHolder>() {

    override fun getItemCount() = engineers.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngineerViewHolder {
        return EngineerViewHolder(ItemEngineerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EngineerViewHolder, position: Int) {
        holder.bind(engineers[position], onClick)
    }

    inner class EngineerViewHolder(private val binding: ItemEngineerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(engineer: Engineer, onClick: (Engineer) -> Unit) {
            val imageUri = if (engineer.defaultImageName.isBlank()) null else Uri.parse(engineer.defaultImageName)
            if (imageUri != null) {
                binding.profileImage.setImageURI(imageUri)
                binding.profileImage.clearColorFilter() // Remove the tint if there's a valid URI
            } else {
                binding.profileImage.setImageResource(R.drawable.ic_person)
                binding.profileImage.setColorFilter(ContextCompat.getColor(binding.root.context, R.color.black)) // Apply the tint if no URI
            }
            binding.name.text = engineer.name
            binding.role.text = engineer.role
            binding.root.setOnClickListener {
                onClick(engineer)
            }
        }

    }
}