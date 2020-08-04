package fr.test.cyllene.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import fr.test.cyllene.R
import fr.test.cyllene.database.AppDatabase
import fr.test.cyllene.model.Book
import fr.test.cyllene.view.Application
import fr.test.cyllene.view.activities.DetailActivity
import fr.test.cyllene.view.adapter.BookListHorizontalAdapter
import fr.test.cyllene.view.adapter.BookListVerticalAdapter
import fr.test.cyllene.view.adapter.ItemListener
import fr.test.cyllene.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : Fragment(), ItemListener {

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.roomComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        setupViews()
    }

    private fun setupViews() {
        viewModel.getBooks()
        recycler_view_home_horizontal.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = BookListHorizontalAdapter(
                emptyList(),
                this@HomeFragment
            )
        }
        recycler_view_home_vertical.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = BookListVerticalAdapter(
                emptyList(),
                this@HomeFragment
            )
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.data.observe(this, Observer {
            Thread {
                viewModel.insertBooks(it, appDatabase)
            }.start()
            updateView(it)
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun updateView(bookList : List<Book>) {
        recycler_view_home_horizontal.adapter =
            BookListHorizontalAdapter(
                bookList,
                this
            )
        recycler_view_home_vertical.adapter =
            BookListVerticalAdapter(
                bookList,
                this
            )
    }

    override fun onClick(position: Int) {
        val intent = Intent(activity, DetailActivity::class.java)
        startActivity(intent)
    }

}