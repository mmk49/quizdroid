package edu.uw.ischool.mmk49.quizdroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TopicAdapter (var topics: List<TopicModel>, var listener: RecyclerViewEvent) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {
    inner class TopicViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val topicTextView: TextView = itemView.findViewById(R.id.topicTitle)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val postion = adapterPosition
            if(postion != RecyclerView.NO_POSITION) {
                listener.onItemClick(postion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.topic_rv_row, parent, false)
        return  TopicViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic : TopicModel = topics[position]
        holder.topicTextView.text = topic.topic
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    interface RecyclerViewEvent {
        fun onItemClick(position: Int)
    }
}