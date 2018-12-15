package com.nicholasdoglio.eyebleach.ui.about.libs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicholasdoglio.eyebleach.R

class LibsFragment : androidx.fragment.app.Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_libs, container, false)
  }

}