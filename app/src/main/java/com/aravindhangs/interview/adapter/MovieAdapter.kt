package com.aravindhangs.interview.adapter

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.aravindhangs.interview.databinding.AdapterMovieBinding
import com.aravindhangs.interview.model.ResultsItem
import com.bumptech.glide.Glide

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var movies = mutableListOf<ResultsItem>()

    fun setMovieList(movies: List<ResultsItem>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.name.text = movie.display_title!!.toString()
        Glide.with(holder.itemView.context)
            .load(movie.multimedia!!.src.toString())
            .into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root)

class RecyclerItemClickListener(
    context: Context,
    recyclerView: RecyclerView,
    private val mListener: OnItemClickListener?
) : RecyclerView.OnItemTouchListener {

    private val mGestureDetector: GestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            val childView = recyclerView.findChildViewUnder(e.x, e.y)

            if (childView != null && mListener != null) {
                mListener.onItemLongClick(
                    childView,
                    recyclerView.getChildAdapterPosition(childView)
                )
            }
        }
    })

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)

        fun onItemLongClick(view: View?, position: Int)
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.x, e.y)

        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView))
        }

        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}
