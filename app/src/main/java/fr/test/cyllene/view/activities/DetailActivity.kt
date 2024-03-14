package fr.test.cyllene.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import fr.test.cyllene.R
import fr.test.cyllene.database.BookDao
import fr.test.cyllene.databinding.ActivityDetailBinding
import fr.test.cyllene.model.Book
import fr.test.cyllene.model.Favorite
import fr.test.cyllene.repository.Repository
import fr.test.cyllene.utils.Constants
import fr.test.cyllene.utils.loadImage
import fr.test.cyllene.view.Application
import fr.test.cyllene.view.adapter.BookListHorizontalDetailAdapter
import fr.test.cyllene.view.adapter.ItemListener
import fr.test.cyllene.viewmodel.detailview.DetailViewModel
import fr.test.cyllene.viewmodel.detailview.DetailViewModelFactory
import javax.inject.Inject


class DetailActivity : AppCompatActivity(), ItemListener {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    @Inject
    lateinit var bookDao: BookDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Application.roomComponent.inject(this)
        val factory =
            DetailViewModelFactory(
                Repository(
                    bookDao
                )
            )
        viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
        setupViews()
    }

    private fun setupViews() {
        Log.d("MONTEST", "ID : " + intent.extras?.getInt(Constants.BOOK_ID))


        intent.extras?.getInt(Constants.BOOK_ID)?.let {
            viewModel.getBookById(it).observe(this,
                Observer<Book>{book->
                    updateBookView(book)
                }
            )
        }
        viewModel.getBooks().observe(this,
            Observer<List<Book>>{list->
                updateRecyclerView(list)
            }
        )
    }

    private fun updateRecyclerView(bookList : List<Book>) {
        binding.recyclerViewDetailActivity.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = BookListHorizontalDetailAdapter(
                bookList,
                this@DetailActivity
            )
        }
    }

    private fun updateBookView(book: Book) {
        loadImage(book, binding.imageBookDetail, this)
        binding.txtTitleDetail.text = getString(R.string.book_title, book.volume.toString(), book.title)
        binding.txtAuthorDetail.text = book.author

        Thread {
            if(!viewModel.isFavoriteRowExist(book.id)){
                binding.imageFavoriteDetail.setImageResource(R.drawable.ic_saved_default)
            }  else {
                binding.imageFavoriteDetail.setImageResource(R.drawable.ic_saved_selected)
            }
        }.start()

        binding.imageFavoriteDetail.setOnClickListener {
            addOrRemoveFromFavorite(book)
        }
    }

    private fun addOrRemoveFromFavorite(book: Book){
        Thread {
            if(!viewModel.isFavoriteRowExist(book.id)){
                viewModel.insertFavorite(Favorite(idFavorite = 0, bookId = book.id))
                binding.imageFavoriteDetail.setImageResource(R.drawable.ic_saved_selected)
            } else {
                viewModel.deleteFavorite(book.id)
                binding.imageFavoriteDetail.setImageResource(R.drawable.ic_saved_default)
            }
        }.start()
    }

    override fun onClick(position: Int) {
    }

}

