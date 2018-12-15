package com.nicholasdoglio.eyebleach.ui.photolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicholasdoglio.eyebleach.R
import com.nicholasdoglio.eyebleach.util.inflate

class PhotoListFragment : androidx.fragment.app.Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = container?.inflate(R.layout.fragment_photo_list)

}