package com.dial.infocommand

import androidx.fragment.app.Fragment
import java.text.FieldPosition

interface CallbackFragment {
    fun changeFragment(numberFragment: Int, position: Int, position2: Int = 0)
}