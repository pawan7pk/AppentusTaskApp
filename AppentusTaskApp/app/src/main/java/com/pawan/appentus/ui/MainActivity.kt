package com.pawan.appentus.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pawan.appentus.R
import com.pawan.appentus.adapters.AuthorAdapter
import com.pawan.appentus.data_class.ResponseData
import com.pawan.appentus.databinding.ActivityMainBinding
import com.pawan.appentus.utils.ResponseStatus
import com.pawan.appentus.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var authorList: ResponseData? = null
    private lateinit var authorAdapter: AuthorAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDataBindingAndViewModel()
        setUpRecyclerView()
    }

    private fun setDataBindingAndViewModel() {
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.callPicsumApi()
        viewModel.liveDataResponse.observe(this, observerAuthorResponse)
    }

    private fun setUpRecyclerView() {
        rvAuthors.layoutManager = GridLayoutManager(this, 2)
        rvAuthors.setHasFixedSize(true)
        authorAdapter = AuthorAdapter()
        rvAuthors.adapter = authorAdapter
    }

    private val observerAuthorResponse = Observer<ResponseStatus<ResponseData>> {
        when (it) {
            is ResponseStatus.Success -> {
                authorList = it.response
                authorAdapter.setAuthorList(authorList)
            }

            is ResponseStatus.Error -> {
                Log.e("log", "Error getting Response")

            }

            is ResponseStatus.Loader -> if (it.isLoading) {
                progressBar.visibility = View.VISIBLE
                rvAuthors.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                rvAuthors.visibility = View.VISIBLE
            }
        }
    }
}