package com.longph.mynews.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.longph.mynews.presentation.fragments.NewsFeedFragment

class HomeCollectionsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        val fragment = NewsFeedFragment()
        return fragment
    }
}