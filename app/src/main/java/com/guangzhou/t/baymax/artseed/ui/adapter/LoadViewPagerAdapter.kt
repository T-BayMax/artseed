package com.guangzhou.t.baymax.artseed.ui.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * Created by Min on 2016/11/24.
 */

class LoadViewPagerAdapter(private val list: List<View>) : PagerAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(list[position])
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = list[position]
        container.addView(view)
        return view
    }
}
