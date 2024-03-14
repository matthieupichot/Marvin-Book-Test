package fr.test.cyllene.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.test.cyllene.databinding.ItemBookHorizontalDetailBinding
import fr.test.cyllene.model.Book
import fr.test.cyllene.utils.loadImage

class BookListHorizontalDetailAdapter(var bookList: List<Book>, private val listener: ItemListener) : RecyclerView.Adapter<BookListHorizontalDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val binding = ItemBookHorizontalDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun getItemCount() = bookList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    class ViewHolder(binding: ItemBookHorizontalDetailBinding, private val listener: ItemListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onClick(layoutPosition)
        }

        private var imageBook = binding.imgBookHorizontalDetail

        fun bind(book: Book){
            loadImage(book, imageBook, imageBook.context)
        }
    }

}