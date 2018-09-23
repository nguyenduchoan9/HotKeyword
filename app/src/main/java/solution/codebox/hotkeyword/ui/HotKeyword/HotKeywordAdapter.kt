package solution.codebox.hotkeyword.ui.HotKeyword

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import solution.codebox.hotkeyword.R
import java.lang.StringBuilder

class HotKeywordAdapter(private var keywords: ArrayList<String>) : RecyclerView.Adapter<HotKeywordAdapter.HotKeywordViewHolder>() {
    private val backgroundColors = listOf(R.color.green, R.color.brown,
            R.color.darkGreen, R.color.purple, R.color.blue)

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): HotKeywordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_keyword, parent, false)
        return HotKeywordViewHolder(view)
    }

    override fun getItemCount(): Int {
        return keywords.size
    }

    override fun onBindViewHolder(viewholder: HotKeywordViewHolder, pos: Int) {
        viewholder.bindDataToView(getKeywordByPos(pos), getBackgroundColorByPos(pos))
    }

    private fun getKeywordByPos(pos: Int): String {
        return keywords[pos]
    }

    private fun getBackgroundColorByPos(pos: Int): Int {
        return backgroundColors[pos % backgroundColors.size]
    }

    inner class HotKeywordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvKeyWord = view.findViewById<TextView>(R.id.tvKeyword)
        private val rlContainer = view.findViewById<RelativeLayout>(R.id.rlContainer)

        init {
            rlContainer.clipToOutline = true
        }

        fun bindDataToView(keywordString: String, backgroundColor: Int) {
            tvKeyWord.setBackgroundColor(ContextCompat.getColor(itemView.context, backgroundColor))
            tvKeyWord.text = separateToTwoLines(keywordString)
        }

        private fun separateToTwoLines(string: String): String {
            val words = string.split(" ")
            if (1 == words.size) return string
            var newStringBuilder = StringBuilder()
            for (i in 0 until words.size) {
                when {
                    i == words.size / 2 -> newStringBuilder.append("\n${words[i]}")
                    newStringBuilder.isEmpty() -> newStringBuilder.append(words[i])
                    else -> newStringBuilder.append(" ${words[i]}")
                }
            }
            return newStringBuilder.toString()
        }
    }
}