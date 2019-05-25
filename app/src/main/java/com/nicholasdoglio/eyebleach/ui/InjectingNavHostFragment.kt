package com.nicholasdoglio.eyebleach.ui

import android.content.Context
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.nicholasdoglio.eyebleach.di.InjectingFragmentFactory
import com.nicholasdoglio.eyebleach.di.injector
import javax.inject.Inject

class InjectingNavHostFragment : NavHostFragment() {

    @Inject
    protected lateinit var factory: InjectingFragmentFactory

    override fun onAttach(context: Context) {
        requireActivity().injector.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        childFragmentManager.fragmentFactory = factory
        super.onCreate(savedInstanceState)
    }
}
