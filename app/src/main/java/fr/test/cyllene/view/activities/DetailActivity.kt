package fr.test.cyllene.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.test.cyllene.R
import fr.test.cyllene.view.adapter.BookListHorizontalAdapter
import fr.test.cyllene.view.adapter.ItemListener
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), ItemListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupViews()
    }

    private fun setupViews() {
        recycler_view_detail_activity.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = BookListHorizontalAdapter(
                emptyList(),
                this@DetailActivity
            )
        }
    }

    override fun onClick(position: Int) {
    }

}

