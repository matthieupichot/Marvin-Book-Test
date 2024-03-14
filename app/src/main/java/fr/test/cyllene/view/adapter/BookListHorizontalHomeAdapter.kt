package fr.test.cyllene.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.test.cyllene.databinding.ItemBookHorizontalHomeBinding
import fr.test.cyllene.model.Book
import fr.test.cyllene.utils.loadImage

class BookListHorizontalHomeAdapter(var bookList: List<Book>, private val listener: ItemListener) : RecyclerView.Adapter<BookListHorizontalHomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val binding = ItemBookHorizontalHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun getItemCount() = bookList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    class ViewHolder(binding : ItemBookHorizontalHomeBinding, private val listener: ItemListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onClick(layoutPosition)
        }

        private var imageBook = binding.imgBookHorizontalHome

        fun bind(book: Book){
            loadImage(book, imageBook, imageBook.context)
        }
    }

}