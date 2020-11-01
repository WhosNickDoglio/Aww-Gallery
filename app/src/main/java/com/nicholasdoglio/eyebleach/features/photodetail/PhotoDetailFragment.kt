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

package com.nicholasdoglio.eyebleach.features.photodetail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.Coil
import coil.request.ImageRequest
import com.nicholasdoglio.eyebleach.R
import com.nicholasdoglio.eyebleach.data.redditUrl
import com.nicholasdoglio.eyebleach.databinding.FragmentPhotoDetailBinding
import com.nicholasdoglio.eyebleach.util.openWebPage
import com.nicholasdoglio.eyebleach.util.setStatusBarColor
import com.nicholasdoglio.eyebleach.util.shareUrl
import com.nicholasdoglio.eyebleach.util.viewBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PhotoDetailFragment : Fragment(R.layout.fragment_photo_detail) {

    private val myArgs: PhotoDetailFragmentArgs by navArgs()

    private val binding by viewBinding {
        FragmentPhotoDetailBinding.bind(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor()

        val post = myArgs.post

        binding.openSourceButton.setOnClickListener {
            requireContext().openWebPage(
                post.redditUrl
            )
        }

        binding.shareButton.setOnClickListener {
            requireContext().shareUrl(post.redditUrl)
        }

        GlobalScope.launch {
            Coil.execute(
                ImageRequest.Builder(requireContext())
                    .data(post.url)
                    .placeholder(
                        CircularProgressDrawable(requireContext()).apply {
                            setStyle(CircularProgressDrawable.LARGE)
                            setColorSchemeColors(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.colorAccent
                                )
                            )
                            start()
                        }
                    )
                    .target(binding.detailPhoto)
                    .build()
            )
        }
    }
}
