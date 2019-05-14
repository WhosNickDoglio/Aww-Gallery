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

package com.nicholasdoglio.baseui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/*

TODO: Idea plugin composition library?

https://medium.com/@manuelvicnt/composite-views-in-android-composition-over-inheritance-4a7114609560

https://commonsware.com/blog/2010/12/02/composition-over-inheritance.html

https://github.com/passsy/CompositeAndroid

https://github.com/chrisbanes/tivi/blob/master/app/src/main/java/app/tivi/appinitializers/AppInitializers.kt

https://github.com/chrisbanes/tivi/blob/master/app/src/main/java/app/tivi/inject/AppModuleBinds.kt

https://github.com/eyeem/decorator

https://github.com/BaloghTamas/Composition/blob/master/app/src/main/java/hu/aut/example/composition/social/SocialDelegate.java

https://s3.eu-central-1.amazonaws.com/autsoft-storage/google-io-extended-2017/Composition_over_Inheritance.pdf

https://medium.com/@programmerr47/refactor-your-basefragment-class-d6f721decc85

https://blog.kotlin-academy.com/inheritance-composition-delegation-and-traits-b11c64f11b27

https://blog.kotlin-academy.com/programmer-dictionary-delegation-vs-composition-3025d9e8ae3d

 */

interface Plugin : LifecycleObserver {

    fun init()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume()

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy()

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny()
}

abstract class LifecyclePlugin(private val lifecycle: Lifecycle) : LifecycleObserver, Plugin {

    override fun init() {
        lifecycle.addObserver(this)
    }

    override fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        lifecycle.removeObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    override fun onAny() {
    }
}

class LoggingPlugin(lifecycle: Lifecycle) : LifecyclePlugin(lifecycle) {

    override fun onCreate() {
        super.onCreate()
        Log.d("PLUGIN", "This is a test")
    }
}

abstract class PluginFragment : Fragment() {
    protected abstract val plugins: List<Plugin>

    override fun onAttach(context: Context?) {
        plugins.forEach { it.init() }
        super.onAttach(context)
    }
}

abstract class PluginActivity : AppCompatActivity() {
    protected abstract val plugins: List<Plugin>

    override fun onCreate(savedInstanceState: Bundle?) {
        plugins.forEach { it.init() }
        super.onCreate(savedInstanceState)
    }
}
