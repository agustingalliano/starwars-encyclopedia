package com.example.star_wars_encyclopedia.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.star_wars_encyclopedia.adapters.PersonageAdapter
import com.example.star_wars_encyclopedia.databinding.FragmentListBinding
import com.example.star_wars_encyclopedia.domain.model.Personage
import com.example.star_wars_encyclopedia.listeners.OnItemClickListener
import com.example.star_wars_encyclopedia.viewmodel.PersonageViewModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.star_wars_encyclopedia.listeners.FragmentCallBack
import com.example.star_wars_encyclopedia.util.Constants.Companion.QUERY_PAGE_SIZE


class ListFragment : Fragment(){

    private lateinit var binding: FragmentListBinding
    private val personageViewModel: PersonageViewModel by viewModels()
    private lateinit var adapter: PersonageAdapter
    val personages : MutableList<Personage> = mutableListOf()
    private var onCreateViewCalled = false
    lateinit var fragmentCallBack: FragmentCallBack
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        if (!hasOnCreateViewBeenCalled()) {
            adapter = PersonageAdapter()
            personageViewModel.onCreate()
            onCreateViewCalled = true
        }
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount =
                        (binding.recyclerView.layoutManager as LinearLayoutManager).childCount
                    val totalItemCount =
                        (binding.recyclerView.layoutManager as LinearLayoutManager).itemCount
                    val firstVisibleItemPosition =
                        (binding.recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()

                    val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
                    val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
                    val isNotAtBeginning = firstVisibleItemPosition >= 0
                    val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
                    val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                            isTotalMoreThanVisible && isScrolling
                    if(shouldPaginate) {
                        personageViewModel.onCreate()
                        isScrolling = false
                    } else {
                        binding.recyclerView.setPadding(0, 0, 0, 0)
                    }

                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }
        })

        personageViewModel.peoples.observe(viewLifecycleOwner, Observer {
            if(viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                personages.addAll(it)
                adapter.addPeoples(it as ArrayList<Personage>)
                personageViewModel.currentPage++
                isLastPage = personageViewModel.currentPage == 10
                adapter.notifyDataSetChanged()
            }
        })


        personageViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progressCircular.isVisible = it
        })

//        peopleViewModel.messageError.observe(viewLifecycleOwner) {
//
//            if(viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
//                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
//            }
//        }

        personageViewModel.person.observe(viewLifecycleOwner) {
            if(viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                fragmentCallBack.onCallBack(it)
            }
        }

        adapter.setOnClickListener(object : OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                onPeopleClicked(adapter.personageFilterList[position].name)
            }
        })

        binding.personageSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })

        val textView = binding.personageSearch.findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
        textView.setTextColor(Color.WHITE)

        return binding.root
    }

    private fun onPeopleClicked(name: String) {
        personageViewModel.getPersonage(name)
    }

    fun setFragmentCallBack1(fragmentCallBack: FragmentCallBack) {
        this.fragmentCallBack = fragmentCallBack
    }

    private fun hasOnCreateViewBeenCalled(): Boolean {
        return onCreateViewCalled
    }

}