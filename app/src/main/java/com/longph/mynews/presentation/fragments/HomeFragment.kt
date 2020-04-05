package com.longph.mynews.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.longph.mynews.R
import com.longph.mynews.presentation.adapters.HomeCollectionsAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment() {

    lateinit var adapter: HomeCollectionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var tabNames = resources.getStringArray(R.array.tab_names)
        adapter = HomeCollectionsAdapter(this)
        vpPages.adapter = adapter
        vpPages.isUserInputEnabled = false
        TabLayoutMediator(tlTabs, vpPages) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }
}