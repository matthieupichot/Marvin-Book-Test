package fr.test.cyllene.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import fr.test.cyllene.R
import fr.test.cyllene.database.BookDao
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
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject


class DetailActivity : AppCompatActivity(), ItemListener {

    private lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var bookDao: BookDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
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
        intent.extras?.getString(Constants.BOOK_ID)?.let {
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
        recycler_view_detail_activity.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = BookListHorizontalDetailAdapter(
                bookList,
                this@DetailActivity
            )
        }
    }

    private fun updateBookView(book: Book) {
        loadImage(book, image_book_detail, this)
        txt_title_detail.text = getString(R.string.book_title, book.volume.toString(), book.title)
        txt_author_detail.text = book.author

        Thread {
            if(!viewModel.isFavoriteRowExist(book.id)){
                image_favorite_detail.setImageResource(R.drawable.ic_saved_default)
            }  else {
                image_favorite_detail.setImageResource(R.drawable.ic_saved_selected)
            }
        }.start()

        image_favorite_detail.setOnClickListener {
            addOrRemoveFromFavorite(book)
        }
    }

    private fun addOrRemoveFromFavorite(book: Book){
        Thread {
            if(!viewModel.isFavoriteRowExist(book.id)){
                viewModel.insertFavorite(Favorite(idFavorite = 0, bookId = book.id))
                image_favorite_detail.setImageResource(R.drawable.ic_saved_selected)
            } else {
                viewModel.deleteFavorite(book.id)
                image_favorite_detail.setImageResource(R.drawable.ic_saved_default)
            }
        }.start()
    }

    override fun onClick(position: Int) {
    }

}

