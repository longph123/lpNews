package com.longph.mynews.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.longph.mynews.MainApplication
import com.longph.mynews.databinding.LayoutNewsListBinding
import com.longph.mynews.presentation.adapters.NewsFeedAdapter
import com.longph.mynews.presentation.viewmodel.NewsFeedViewModel
import com.longph.mynews.presentation.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.layout_news_list.*
import javax.inject.Inject


class NewsFeedFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NewsFeedViewModel>

    lateinit var application: MainApplication
    lateinit var newsListBinding: LayoutNewsListBinding
    lateinit var adapter: NewsFeedAdapter

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
        newsListBinding.viewModel?.getNewsList()
    }

    private fun setupViews() {
        var linearLayoutManager = LinearLayoutManager(activity!!)
        rcvNewsList.layoutManager = linearLayoutManager
        adapter = NewsFeedAdapter()
        rcvNewsList.adapter = adapter
    }

    private fun setupObservers() {
        newsListBinding.viewModel?.getNewsListLiveData?.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

        newsListBinding.viewModel?.loadingData?.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading)
                llLoadingCircle.visibility = View.VISIBLE
            else
                llLoadingCircle.visibility = View.GONE
        })

        newsListBinding.viewModel?.errorMessage?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
    }
}