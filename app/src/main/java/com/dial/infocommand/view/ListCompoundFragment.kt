package com.dial.infocommand.view

import android.content.pm.ActivityInfo
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.dial.infocommand.CallbackFragment
import com.dial.infocommand.R
import com.dial.infocommand.adapter.CompoundAdapter
import com.dial.infocommand.databinding.FragmentListCompoundBinding

class ListCompoundFragment : Fragment() {

    private lateinit var binding: FragmentListCompoundBinding
    private lateinit var callbackFragment: CallbackFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListCompoundBinding.inflate(inflater, container, false)

        Glide.with(requireContext())
            .load(resources.getString(R.string.text_url_background))
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: com.bumptech.glide.request.transition.Transition<in Drawable?>?
                ) {
                    binding.constraintCompound.background = resource
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
            })

        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding.btnBackFragCompound.setOnClickListener {
            requireActivity().onBackPressed()
        }

        var mainActivity = (activity as MainActivity?)

        val bundle = this.arguments

        binding.rvCompound.setHasFixedSize(true)
        binding.rvCompound.layoutManager = LinearLayoutManager(requireContext())

        val adapterCompound = mainActivity?.let {
            bundle?.let { it1 ->
                CompoundAdapter(
                    requireContext(),
                    it1.getInt("POSITION"),
                    it1.getInt("POSITION2"),
                    it.arrayInfoCommands
                )
            }
        }
        binding.rvCompound.adapter = adapterCompound

        return binding.root
    }

    fun setCallbackFragment(callbackFragment: CallbackFragment) {
        this.callbackFragment = callbackFragment
    }
}