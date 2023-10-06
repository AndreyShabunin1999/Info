package com.dial.infocommand.view

import android.content.pm.ActivityInfo
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.dial.infocommand.CallbackFragment
import com.dial.infocommand.R
import com.dial.infocommand.adapter.CommandsAdapter
import com.dial.infocommand.databinding.FragmentListCommandsBinding


@Suppress("DEPRECATION")
class ListCommandsFragment : Fragment() {

    private lateinit var binding: FragmentListCommandsBinding
    private lateinit var callbackFragment: CallbackFragment
    private lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListCommandsBinding.inflate(inflater, container, false)

        Glide.with(requireContext())
            .load(resources.getString(R.string.text_url_background))
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: com.bumptech.glide.request.transition.Transition<in Drawable?>?
                ) {
                    binding.constraintCommands.background = resource
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
            })

        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding.btnBackFragCommands.setOnClickListener {
            requireActivity().onBackPressed()
        }

        var mainActivity = (activity as MainActivity?)

        bundle = this.requireArguments()

        binding.rvCommands.setHasFixedSize(true)
        binding.rvCommands.layoutManager = LinearLayoutManager(requireContext())

        val adapterCommands =
            mainActivity?.let {
                CommandsAdapter(requireContext(), bundle.getInt("POSITION"), it.arrayInfoCommands) { it: Int ->
                    changeFragment(it)
                }
            }
        binding.rvCommands.adapter = adapterCommands

        return binding.root
    }

    fun setCallbackFragment(callbackFragment: CallbackFragment) {
        this.callbackFragment = callbackFragment
    }

    private fun changeFragment(position: Int) {
        callbackFragment.changeFragment(2, bundle.getInt("POSITION"), position)
    }
}