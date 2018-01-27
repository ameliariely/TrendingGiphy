package com.ameliariely.trendinggiphy.ui.gifList

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.ameliariely.trendinggiphy.GiphyApp
import com.ameliariely.trendinggiphy.ui.base.BaseActivity
import com.ameliariely.trendinggiphy.ui.base.BaseNavigator
import com.ameliariely.trendinggiphy.R
import com.ameliariely.trendinggiphy.data.remote.model.Gif
import kotlinx.android.synthetic.main.activity_gifs_list.*

class GifsListActivity : BaseActivity<GifsListViewModel>(), GifsListNavigator {

    private val adapter = GifsListAdapter({ itemCount : Int -> onLastItemBoundListener(itemCount) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = GifsListViewModel((application as GiphyApp).dataManager, this)
        setContentView(R.layout.activity_gifs_list)
        gifsListRecyclerView.layoutManager = GridLayoutManager(this, 2)
        gifsListRecyclerView.adapter = adapter
        search_button.setOnClickListener { search() }
    }

    private fun search() {
        viewModel.fetchGifsForQueryAndSetAdapterItems(search_edit_text.text.toString())
    }

    //TODO set items in databinding to keep view more separate
    override fun setAdapterItems(items: List<Gif>?) {
        if (items == null) return
        adapter.setItems(items)
    }

    override fun addAdapterItems(items: List<Gif>?) {
        if (items == null) return
        adapter.addItems(items)
    }


    private fun onLastItemBoundListener(itemCount : Int) {
        viewModel.fetchNextGifsForCurrentQueryAndSetAdapterItems(itemCount)
    }
}

interface GifsListNavigator : BaseNavigator {
    fun setAdapterItems(items: List<Gif>?)
    fun addAdapterItems(items: List<Gif>?)
}