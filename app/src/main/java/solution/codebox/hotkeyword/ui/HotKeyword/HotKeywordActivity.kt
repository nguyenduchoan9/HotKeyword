package solution.codebox.hotkeyword.ui.HotKeyword

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_hot_key_word.*
import solution.codebox.hotkeyword.R
import solution.codebox.hotkeyword.repo.KeywordRepoImpl
import solution.codebox.hotkeyword.ui.BaseActivity

class HotKeywordActivity : BaseActivity<HotKeywordContract.Presenter>(), HotKeywordContract.View {
    private lateinit var hotKeywordAdapter: HotKeywordAdapter

    override fun onResume() {
        super.onResume()
        presenter().fetchKeywords()
    }

    override fun showProgress() {
        flProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        flProgress.visibility = View.GONE
    }

    override fun showHotKeywords(keywords: ArrayList<String>) {
        hotKeywordAdapter = HotKeywordAdapter(keywords)
        rvKeywords.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvKeywords.adapter = hotKeywordAdapter
    }

    override fun presenter(): HotKeywordContract.Presenter {
        return presenter
    }

    override fun layoutRes(): Int {
        return R.layout.activity_hot_key_word
    }

    override fun bindViews() {

    }

    override fun initPresenter() {
        presenter = HotKeywordPresenter(KeywordRepoImpl())
    }
}
