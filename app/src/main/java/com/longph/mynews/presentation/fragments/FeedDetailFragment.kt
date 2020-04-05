package com.longph.mynews.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.longph.mynews.MainApplication
import com.longph.mynews.databinding.LayoutNewsListBinding
import com.longph.mynews.presentation.adapters.NewsDetailAdapter
import com.longph.mynews.presentation.viewmodel.NewsFeedViewModel
import com.longph.mynews.presentation.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.layout_news_list.*
import javax.inject.Inject

class FeedDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NewsFeedViewModel>

    lateinit var application: MainApplication
    lateinit var newsListBinding: LayoutNewsListBinding
    lateinit var adapter: NewsDetailAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        application = activity?.application as MainApplication
        application.applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsListBinding = LayoutNewsListBinding.inflate(inflater, container, false).apply {
            viewModel = ViewModelProvider(
                viewModelStore,
                viewModelFactory
            ).get(NewsFeedViewModel::class.java)
        }
        return newsListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        setupObservers()
        newsListBinding.viewModel?.getNewsDetail("newsId")
    }

    private fun setupViews() {
        var linearLayoutManager = LinearLayoutManager(activity!!)
        rcvNewsList.layoutManager = linearLayoutManager
        adapter = NewsDetailAdapter()
        rcvNewsList.adapter = adapter
    }

    private fun setupObservers() {
        newsListBinding.viewModel?.getNewsDetailLiveData?.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it.title, it.description, it.sections)
        })

        newsListBinding.viewModel?.loadingData?.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading)
                llLoadingCircle.visibility = View.VISIBLE
            else
                llLoadingCircle.visibility = View.GONE
        })
    }
}