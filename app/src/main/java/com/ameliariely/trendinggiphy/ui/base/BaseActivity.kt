package com.ameliariely.trendinggiphy.ui.base

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity<V: BaseViewModel> : AppCompatActivity(), BaseNavigator {

    lateinit var viewModel: V

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }
}