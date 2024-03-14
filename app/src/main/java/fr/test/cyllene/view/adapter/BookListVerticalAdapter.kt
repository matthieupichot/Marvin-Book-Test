package fr.test.cyllene.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.test.cyllene.R
import fr.test.cyllene.databinding.ItemBookVerticalBinding
import fr.test.cyllene.model.Book
import fr.test.cyllene.utils.loadImage

class BookListVerticalAdapter (var bookList: List<Book>, private val listener: ItemListener) : RecyclerView.Adapter<BookListVerticalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val binding = ItemBookVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun getItemCount() = bookList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    class ViewHolder(binding : ItemBookVerticalBinding, private val listener: ItemListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onClick(layoutPosition)
        }

        private val image = binding.imgBookVertical
        private val title = binding.title
        private val author = binding.author

        fun bind(book: Book){
            loadImage(book, image, image.context)
            title.text = itemView.context.getString(R.string.book_title, book.volume.toString(), book.title)
            author.text = book.author
        }
    }
}

interface ItemListener {
    fun onClick(position: Int)
}