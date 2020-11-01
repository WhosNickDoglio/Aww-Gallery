/*
 * MIT License
 *
 *   Copyright (c) 2020. Nicholas Doglio
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 */

package com.nicholasdoglio.eyebleach.features.photolist

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsBuilder
import com.nicholasdoglio.eyebleach.R
import com.nicholasdoglio.eyebleach.databinding.FragmentPhotoListBinding
import com.nicholasdoglio.eyebleach.features.about.withListener
import com.nicholasdoglio.eyebleach.util.DispatcherProvider
import com.nicholasdoglio.eyebleach.util.calculateNumOfColumns
import com.nicholasdoglio.eyebleach.util.openWebPage
import com.nicholasdoglio.eyebleach.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@ExperimentalPagingApi
@AndroidEntryPoint
class PhotoListFragment : Fragment(R.layout.fragment_photo_list) {

    @Inject
    lateinit var dispatcherProvider: DispatcherProvider

    private val viewModel: PhotoListViewModel by viewModels()

    private val binding by viewBinding {
        FragmentPhotoListBinding.bind(it)
    }

    private val aboutArgs = LibsBuilder()
        .withAboutVersionShownCode(false)
        .withEdgeToEdge(true)
        .withLicenseShown(true)
        .withAboutSpecial1("Nicholas Doglio")
        .withAboutSpecial2("Source Code")
        .withAboutSpecial3("Guzman Gonzalez")
        .withListener(
            onExtraClicked = { v, specialButton ->
                when (specialButton) {
                    Libs.SpecialButton.SPECIAL1 ->
                        v.context.openWebPage("https://whosnickdoglio.dev")
                    Libs.SpecialButton.SPECIAL2 ->
                        v.context.openWebPage(
                            "https://github.com/WhosNickDoglio/Aww-Gallery"
                        )
                    Libs.SpecialButton.SPECIAL3 ->
                        v.context.openWebPage(
                            "http://guzzgonzalez.com"
                        )

                    else -> Timber.i("This shouldn't have been clicked 🤷‍")
                }
                true
            }
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoListAdapter = PhotoListAdapter(dispatcherProvider)

        requireActivity().window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark)
        }

        binding.recyclerView.apply {
            adapter = photoListAdapter
            layoutManager = GridLayoutManager(requireContext(), calculateNumOfColumns)
        }

        binding.toolbar.apply {
            inflateMenu(R.menu.grid_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.about_item -> {
                        findNavController().navigate(
                            PhotoListFragmentDirections.openAbout(aboutArgs)
                        )
                        true
                    }
                    else -> false
                }
            }
        }

        photoListAdapter.loadStateFlow
            .onEach { Timber.i("LOAD STATE TRIGGERED $it") }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        photoListAdapter.dataRefreshFlow
            .onEach { Timber.i("REFRESH STATE TRIGGERED $it") }
            .onEach { binding.swipeRefreshLayout.isRefreshing = it }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        // binding.swipeRefreshLayout.refreshes()
        //     .onEach { photoListAdapter.refresh() }
        //     .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.posts
            .flowOn(dispatcherProvider.ui)
            .onEach { photoListAdapter.submitData(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}
