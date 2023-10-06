package com.dial.infocommand.view

import android.content.pm.ActivityInfo
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.dial.infocommand.CallbackFragment
import com.dial.infocommand.R
import com.dial.infocommand.api.ApiInterface
import com.dial.infocommand.api.ApiUtilities
import com.dial.infocommand.databinding.ActivityMainBinding
import com.dial.infocommand.model.InfoModel
import com.dial.infocommand.repository.CommandsRepository
import com.dial.infocommand.viewmodel.CommandsViewModel
import com.dial.infocommand.viewmodel.CommandsViewModelFactory

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), CallbackFragment {

    private lateinit var commandsViewModel: CommandsViewModel
    private lateinit var binding: ActivityMainBinding
    var arrayInfoCommands = ArrayList<InfoModel>()
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private var bundle: Bundle = Bundle()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this@MainActivity)
            .load(resources.getString(R.string.text_url_background))
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: com.bumptech.glide.request.transition.Transition<in Drawable?>?
                ) {
                    binding.fragmentContainer.background = resource
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
            })

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val commandsRepository = CommandsRepository(apiInterface)

        commandsViewModel = ViewModelProvider(this, CommandsViewModelFactory(commandsRepository)
        )[CommandsViewModel::class.java]

        commandsViewModel.commands.observe(this) {
            arrayInfoCommands = it
            addFragmentTransaction()
        }
    }

    private fun addFragmentTransaction() {
        val fragment = ListCountryFragment()
        fragment.setCallbackFragment(this)
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }

    private fun replaceCommandsFragment() {
        val fragment = ListCommandsFragment()
        fragment.setCallbackFragment(this)
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragment.arguments = bundle
        fragmentTransaction.commit()
    }

    private fun replaceCompoundFragment() {
        val fragment = ListCompoundFragment()
        fragment.setCallbackFragment(this)
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragment.arguments = bundle
        fragmentTransaction.commit()
    }

    override fun changeFragment(numberFragment: Int, position: Int, position2: Int) {
        bundle.putInt("POSITION", position)
        bundle.putInt("POSITION2", position2)
        when(numberFragment){
            1 -> replaceCommandsFragment()
            2 -> replaceCompoundFragment()
        }
    }


}