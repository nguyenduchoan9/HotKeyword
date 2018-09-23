package solution.codebox.hotkeyword.ui.HotKeyword

import solution.codebox.hotkeyword.ui.BaseContract

interface HotKeywordContract {
    interface View : BaseContract.View<Presenter> {
        fun showProgress()
        fun hideProgress()
        fun showHotKeywords(keyword: ArrayList<String>)
    }
    interface Presenter : BaseContract.Presenter<View>{
        fun fetchKeywords()
    }
}