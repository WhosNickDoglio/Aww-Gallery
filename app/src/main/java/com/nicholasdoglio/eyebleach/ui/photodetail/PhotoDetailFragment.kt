/*
 * MIT License
 *
 * Copyright (c) 2019 Nicholas Doglio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.nicholasdoglio.eyebleach.ui.photodetail

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.nicholasdoglio.eyebleach.R
import com.nicholasdoglio.eyebleach.di.injector
import com.nicholasdoglio.eyebleach.ui.base.AwwGalleryFragment
import com.nicholasdoglio.eyebleach.ui.util.CircularProgressPlaceholderListener
import com.nicholasdoglio.eyebleach.util.createViewModel
import com.nicholasdoglio.eyebleach.util.openWebPage
import com.nicholasdoglio.eyebleach.util.shareUrl
import com.uber.autodispose.android.lifecycle.autoDisposable
import kotlinx.android.synthetic.main.fragment_photo_detail.*

class PhotoDetailFragment : AwwGalleryFragment<PhotoDetailViewModel>() {

    private val myArgs: PhotoDetailFragmentArgs by navArgs()

    override fun createViewModel() {
        viewModel = createViewModel(factory)
    }

    override fun injectFragment() {
        requireActivity().injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_photo_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarBlack()

        val placeholder = CircularProgressDrawable(requireContext()).apply {
            setStyle(CircularProgressDrawable.LARGE)
            setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorAccent))
        }

        placeholder.start()

        viewModel.onAttached(myArgs.photoId)
            .subscribeOn(schedulersProvider.database)
            .observeOn(schedulersProvider.main)
            .autoDisposable(viewLifecycleOwner)
            .subscribe { post ->

                openSourceButton.setOnClickListener { requireContext().openWebPage(post.fullUrl) }

                shareButton.setOnClickListener { requireContext().shareUrl(post.fullUrl) }

                Glide.with(detailPhoto.context)
                    .load(post.url)
                    .error(R.drawable.cat_error)
                    .placeholder(placeholder)
                    .listener(
                        CircularProgressPlaceholderListener(
                            placeholder
                        )
                    )
                    .transition(withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(detailPhoto)
            }
    }

    private fun setStatusBarBlack() {
        ObjectAnimator.ofArgb(
            requireActivity().window,
            "statusBarColor",
            requireActivity().window.statusBarColor,
            Color.BLACK
        ).start()
    }
}