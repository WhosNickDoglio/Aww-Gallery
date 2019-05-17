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

package com.nicholasdoglio.eyebleach.ui.about

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholasdoglio.eyebleach.R
import com.nicholasdoglio.eyebleach.di.ViewModelFactory
import com.nicholasdoglio.eyebleach.di.injector
import com.nicholasdoglio.eyebleach.util.SchedulersProvider
import com.nicholasdoglio.eyebleach.util.createViewModel
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.android.lifecycle.autoDisposable
import kotlinx.android.synthetic.main.fragment_about.*
import javax.inject.Inject

class AboutFragment : DialogFragment() {

    @Inject
    protected lateinit var factory: ViewModelFactory

    @Inject
    protected lateinit var schedulersProvider: SchedulersProvider

    protected lateinit var viewModel: AboutViewModel

    protected lateinit var scopeProvider: AndroidLifecycleScopeProvider

    private lateinit var aboutAdapter: AboutAdapter

    override fun onAttach(context: Context?) {
        requireActivity().injector.inject(this)
        super.onAttach(context)
        viewModel = createViewModel(factory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_about, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scopeProvider = AndroidLifecycleScopeProvider.from(viewLifecycleOwner)
        aboutAdapter = AboutAdapter(activity = requireActivity() as AppCompatActivity)

        viewModel.aboutInt
            .observeOn(schedulersProvider.main)
            .autoDisposable(viewLifecycleOwner)
            .subscribe { info -> aboutAdapter.submitList(info) }

        aboutRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = aboutAdapter
        }
    }
}
