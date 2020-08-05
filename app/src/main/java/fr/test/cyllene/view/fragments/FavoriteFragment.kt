package fr.test.cyllene.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import fr.test.cyllene.R
import fr.test.cyllene.database.BookDao
import fr.test.cyllene.model.Book
import fr.test.cyllene.repository.Repository
import fr.test.cyllene.utils.Constants
import fr.test.cyllene.view.Application
import fr.test.cyllene.view.activities.DetailActivity
import fr.test.cyllene.view.adapter.BookListVerticalAdapter
import fr.test.cyllene.view.adapter.ItemListener
import fr.test.cyllene.viewmodel.FavoriteViewModel
import fr.test.cyllene.viewmodel.FavoriteViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite.*
import javax.inject.Inject

class FavoriteFragment : Fragment(), ItemListener {

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var bookDao: BookDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.roomComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val factory =
            FavoriteViewModelFactory(
                Repository(
                    bookDao
                )
            )
        viewModel = ViewModelProviders.of(this, factory).get(FavoriteViewModel::class.java)
        setupViews()
    }

    private fun setupViews() {
        recycler_view_favorite.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = BookListVerticalAdapter(
                emptyList()
                ,
                this@FavoriteFragment
            )
        }
        loadFavoriteBooks()
    }

    private fun loadFavoriteBooks() {
        viewModel.getFavoriteList().observe(viewLifecycleOwner, Observer {
            updateView(it)
        })
    }

    private fun updateView(bookList : List<Book>) {
        recycler_view_favorite.adapter =
            BookListVerticalAdapter(
                bookList,
                this
            )
    }

    override fun onClick(position: Int) {
        val book = (recycler_view_favorite.adapter as BookListVerticalAdapter).bookList[position]
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(Constants.BOOK_ID, book.id)
        startActivity(intent)
    }

}