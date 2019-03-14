package com.sv.quackathon.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.sv.quackathon.R
import com.sv.quackathon.model.Image
import com.sv.quackathon.repository.ImageRepositoryImpl
import kotlinx.android.synthetic.main.activity_image_details.*
import kotlinx.android.synthetic.main.item_comment.view.*

class ImageDetailsActivity : AppCompatActivity() {
    val adapter = CommentsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)

        comments.layoutManager = LinearLayoutManager(this)
        comments.adapter = adapter

        if (intent.hasExtra("data")) {
            adapter.comments = intent.getParcelableExtra<Image>("data").comments
        } else {
            ImageRepositoryImpl().getImages { images ->
                if (!images.isNullOrEmpty()) {
                    adapter.comments = images[0].comments
                    Picasso.get().load(images[0].imageResource).into(image)
                }
            }
        }
    }
}

class CommentsAdapter : RecyclerView.Adapter<CommentViewHolder>() {
    var comments:List<String> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        CommentViewHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.item_comment, p0, false)
        )

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(viewHolder: CommentViewHolder, position: Int) {
        viewHolder.bindView(comments[position])
    }

}

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(comment: String) {
        with(itemView) {
            text.text = comment
        }
    }
}