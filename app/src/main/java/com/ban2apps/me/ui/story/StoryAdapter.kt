package com.ban2apps.me.ui.story

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ban2apps.me.R
import com.ban2apps.me.database.data.Story
import com.ban2apps.me.utils.MyDateUtil
import kotlinx.android.synthetic.main.item_story.view.*

class StoryAdapter(private val stories: List<Story>, private val listener: (Story) -> Unit)
    : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_story, parent, false))
    }

    override fun getItemCount() = stories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            if (position > 0) holder.bind(stories[position], listener, stories[position - 1].timestamp)
            else holder.bind(stories[position], listener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(story: Story, listener: (Story) -> Unit) = with(itemView) {
            dateText.text = MyDateUtil.getDate(story.timestamp)
            timeText.text = MyDateUtil.getTime(story.timestamp)
            titleText.text = story.title
            storyText.text = story.story
            setOnClickListener { listener(story) }
        }

        fun bind(story: Story, listener: (Story) -> Unit, prevStoryTime: Long) = with(itemView) {
            dateText.text = MyDateUtil.getDate(story.timestamp)
            dateText.visibility = if (MyDateUtil.isSameDate(prevStoryTime, story.timestamp)) View.GONE else View.VISIBLE
            timeText.text = MyDateUtil.getTime(story.timestamp)
            titleText.text = story.title
            storyText.text = story.story
            setOnClickListener { listener(story) }
        }
    }
}
