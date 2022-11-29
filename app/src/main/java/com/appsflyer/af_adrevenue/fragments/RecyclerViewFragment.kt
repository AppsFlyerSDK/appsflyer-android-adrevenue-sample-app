package com.appsflyer.af_adrevenue.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.appsflyer.af_adrevenue.RecyclerViewAdapter
import com.appsflyer.af_adrevenue.databinding.FragmentRecyclerViewBinding
import com.appsflyer.af_adrevenue.enums.AdNetwork
import com.appsflyer.af_adrevenue.enums.AdType
import com.appsflyer.af_adrevenue.viewModel.RecyclerViewModel


class RecyclerViewFragment : Fragment() {
    private var _binding: FragmentRecyclerViewBinding? = null
    private val binding get() = _binding!!
    private val args: RecyclerViewFragmentArgs by navArgs()
    lateinit var viewModel: RecyclerViewModel
    lateinit var adTypes: List<com.appsflyer.af_adrevenue.data.AdTypeData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        val adNetwork = args.adNetwork
        binding.toolbar.title = adNetwork.toString()
        val factory = RecyclerViewModel.Factory(adNetwork)
        viewModel = ViewModelProvider(this,factory)[RecyclerViewModel::class.java]
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerViewAdapter(emptyList()){
                position, view ->
                onItemClick(position, view)
            }
        }
        binding.toolbar.setNavigationOnClickListener { it.findNavController().popBackStack() }
        viewModel.adTypesData.observe(viewLifecycleOwner, Observer {
            adTypes = it
            (binding.recyclerView.adapter as RecyclerViewAdapter).updateList(it)
        })


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClick(position: Int, view: View?) {
        val adNetwork = binding.toolbar.title
        var action:NavDirections
        val adType = AdType.parseFromString(adTypes[position].title)
        action = if(adNetwork.toString() == AdNetwork.ADMOB.toString()){
            RecyclerViewFragmentDirections.actionRecyclerViewfragmentToAdMobFragment(adType)
        } else{
                RecyclerViewFragmentDirections.actionRecyclerViewFragmentToIronSourceFragment(adType)
            }
        view?.findNavController()?.navigate(action)
    }

}