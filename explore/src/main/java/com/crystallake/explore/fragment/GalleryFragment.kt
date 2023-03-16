package com.crystallake.explore.fragment

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.crystallake.base.config.DataBindingConfig
import com.crystallake.base.fragment.DataBindingFragment
import com.crystallake.base.vm.BaseViewModel
import com.crystallake.explore.R
import com.crystallake.explore.adapter.GalleryAdapter
import com.crystallake.explore.databinding.FragmentGalleryBinding

class GalleryFragment :DataBindingFragment<FragmentGalleryBinding,BaseViewModel>(){

    override fun createObserver() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        initRecycler()
    }

    private fun initRecycler(){
        val dataList = mutableListOf<String>()
        dataList.add("http://pic1.win4000.com/wallpaper/3/59914c489cfd3.jpg")
        dataList.add("http://pic1.win4000.com/wallpaper/3/59914c567b383.jpg")
        dataList.add("http://pic1.win4000.com/wallpaper/3/59914c437c099.jpg")
        dataList.add("http://pic1.win4000.com/wallpaper/3/59914c522dba2.jpg")
        dataList.add("http://pic1.win4000.com/tj/2020-07-07/5f04352c40a11.jpg")
        dataList.add("http://pic1.win4000.com/wallpaper/a/5253b01dcbbbf.jpg")
        dataList.add("http://pic1.win4000.com/wallpaper/d/523e699a6036c.jpg")
        dataList.add("http://pic1.win4000.com/wallpaper/d/523e699d4b1b1.jpg")

        val adapter = GalleryAdapter(dataList)
        val gridLayoutManager = GridLayoutManager(requireContext(),1)

        mBinding?.galleryRecycler?.let {
            it.layoutManager = gridLayoutManager
            it.adapter = adapter
            it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        Glide.with(recyclerView).resumeRequests()
                    } else {
                        Glide.with(recyclerView).pauseRequests()
                    }
                }
            })
        }


    }

    override fun lazyLoadData() {

    }

    override fun initDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_gallery)
    }
}