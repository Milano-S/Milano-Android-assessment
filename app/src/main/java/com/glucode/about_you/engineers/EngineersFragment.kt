package com.glucode.about_you.engineers

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.glucode.about_you.R
import com.glucode.about_you.databinding.FragmentEngineersBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import com.glucode.about_you.viewmodel.AboutViewModel

class EngineersFragment : Fragment() {

    private lateinit var binding: FragmentEngineersBinding
    private val vm: AboutViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEngineersBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setUpEngineersList(vm.engineerList.value ?: MockData.engineers)

        // Observe the engineer list and update the RecyclerView adapter when it changes
        vm.engineerList.observe(viewLifecycleOwner, Observer { engineers ->
            setUpEngineersList(engineers)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_engineers, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_years -> {
                vm.sortEngineersByYears()
            }

            R.id.action_coffees -> {
                vm.sortEngineersByCoffees()
            }

            R.id.action_bugs -> {
                vm.sortEngineersByBugs()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpEngineersList(engineers: List<Engineer>) {
        binding.list.adapter = EngineersRecyclerViewAdapter(engineers) {
            vm.setCurrentProfileImage(null, null)
            vm.setCurrentEngineer(it)
            goToAbout(it)
        }
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(dividerItemDecoration)
    }

    private fun goToAbout(engineer: Engineer) {
        val bundle = Bundle().apply {
            putString("name", engineer.name)
        }
        findNavController().navigate(R.id.action_engineersFragment_to_aboutFragment, bundle)
    }
}
