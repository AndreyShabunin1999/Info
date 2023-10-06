package com.dial.infocommand.view

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dial.infocommand.CallbackFragment
import com.dial.infocommand.adapter.CountryBtnAdapter
import com.dial.infocommand.databinding.FragmentListCountryBinding

@Suppress("DEPRECATION")
class ListCountryFragment : Fragment() {

    private lateinit var binding: FragmentListCountryBinding
    private lateinit var callbackFragment: CallbackFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListCountryBinding.inflate(inflater, container, false)

        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        var mainActivity = (activity as MainActivity?)

        binding.rvCountry.setHasFixedSize(true)
        binding.rvCountry.layoutManager = LinearLayoutManager(requireContext())


        val adapterCountry =
            mainActivity?.let {
                CountryBtnAdapter(requireContext(), it.arrayInfoCommands) { it: Int ->
                    changeFragment(it)
                }
            }
        binding.rvCountry.adapter = adapterCountry

        return binding.root
    }

    fun setCallbackFragment(callbackFragment: CallbackFragment) {
        this.callbackFragment = callbackFragment
    }

    private fun changeFragment(position: Int) {
        callbackFragment.changeFragment(1, position)
    }
}