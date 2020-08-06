package fr.test.cyllene.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.test.cyllene.R
import fr.test.cyllene.model.Book
import fr.test.cyllene.utils.loadImage
import kotlinx.android.synthetic.main.item_book_horizontal_detail.view.*

class BookListHorizontalDetailAdapter(var bookList: List<Book>, private val listener: ItemListener) : RecyclerView.Adapter<BookListHorizontalDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_book_horizontal_detail, parent, false),
            listener
        )

    override fun getItemCount() = bookList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    class ViewHolder(view : View, private val listener: ItemListener): RecyclerView.ViewHolder(view), View.OnClickListener{

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onClick(layoutPosition)
        }

        private var imageBook = view.img_book_horizontal_detail

        fun bind(book: Book){
            imageBook.loadImage(book.imageUrl)
        }
    }

}