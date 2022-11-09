package com.appsflyer.af_adrevenue.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.appsflyer.af_adrevenue.databinding.FragmentStartBinding
import com.appsflyer.af_adrevenue.enums.AdNetwork

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.continueBtn.setOnClickListener(View.OnClickListener {
            val adNetwork = if(binding.chipGroup.checkedChipId == binding.chipAdmob.id){
               AdNetwork.ADMOB
            } else{
                AdNetwork.IRONSOURCE
            }
            val action = StartFragmentDirections.actionStartFragmentToRecyclerViewFragment(adNetwork)
            view.findNavController().navigate(action)
        })
        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}