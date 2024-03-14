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
import fr.test.cyllene.database.BookDao
import fr.test.cyllene.databinding.FragmentHomeBinding
import fr.test.cyllene.model.Book
import fr.test.cyllene.repository.Repository
import fr.test.cyllene.utils.Constants
import fr.test.cyllene.view.Application
import fr.test.cyllene.view.activities.DetailActivity
import fr.test.cyllene.view.adapter.BookListHorizontalHomeAdapter
import fr.test.cyllene.view.adapter.BookListVerticalAdapter
import fr.test.cyllene.view.adapter.ItemListener
import fr.test.cyllene.viewmodel.homeview.HomeViewModel
import fr.test.cyllene.viewmodel.homeview.HomeViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment(), ItemListener {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var bookDao: BookDao

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.roomComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val factory =
            HomeViewModelFactory(
                Repository(
                    bookDao
                )
            )
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        setupViews()
    }

    private fun setupViews() {
        binding.recyclerViewHomeHorizontal.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = BookListHorizontalHomeAdapter(
                emptyList(),
                this@HomeFragment
            )
        }
        binding.recyclerViewHomeVertical.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = BookListVerticalAdapter(
                emptyList(),
                this@HomeFragment
            )
        }
        loadDataFromDatabase()
        fetchDataFromServer()
    }

    private fun loadDataFromDatabase(){
        viewModel.getBooks().observe(viewLifecycleOwner,
            Observer<List<Book>>{list->
                updateView(list)
            }
        )
    }

    private fun fetchDataFromServer() {
        viewModel.fetchBooks()
        viewModel.data.observe(viewLifecycleOwner, Observer {
            Thread {
                viewModel.insertBooks(it)
            }.start()
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun updateView(bookList : List<Book>) {
        binding.recyclerViewHomeHorizontal.adapter =
            BookListHorizontalHomeAdapter(
                bookList,
                this
            )
        binding.recyclerViewHomeVertical.adapter =
            BookListVerticalAdapter(
                bookList,
                this
            )
        binding.txtBestSeller.text = context?.getString(R.string.best_seller)
    }

    override fun onClick(position: Int) {
        val book = (binding.recyclerViewHomeVertical.adapter as BookListVerticalAdapter).bookList[position]
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(Constants.BOOK_ID, book.id)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}