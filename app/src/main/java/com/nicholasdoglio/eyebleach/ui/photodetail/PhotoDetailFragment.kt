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
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.api.load
import com.nicholasdoglio.eyebleach.R
import com.nicholasdoglio.eyebleach.data.util.redditUrl
import com.nicholasdoglio.eyebleach.di.injector
import com.nicholasdoglio.eyebleach.ui.util.openWebPage
import com.nicholasdoglio.eyebleach.ui.util.shareUrl
import kotlinx.android.synthetic.main.fragment_photo_detail.*
import kotlinx.coroutines.launch

class PhotoDetailFragment : Fragment(R.layout.fragment_photo_detail) {

    private val myArgs: PhotoDetailFragmentArgs by navArgs()
    private val viewModel: PhotoDetailViewModel by viewModels {
        requireActivity().injector.viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarBlack()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.postId.offer(myArgs.photoId)
        }

        viewModel.post.observe(viewLifecycleOwner, Observer { post ->
            openSourceButton.setOnClickListener { requireContext().openWebPage(post.redditUrl) }

            shareButton.setOnClickListener { requireContext().shareUrl(post.redditUrl) }

            detailPhoto.load(post.url) {
                placeholder(CircularProgressDrawable(requireContext()).apply {
                    setStyle(CircularProgressDrawable.LARGE)
                    setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorAccent))
                    start()
                })
            }
        })
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
