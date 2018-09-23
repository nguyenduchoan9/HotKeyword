package solution.codebox.hotkeyword.ui.HotKeyword

import solution.codebox.hotkeyword.repo.DataResponse
import solution.codebox.hotkeyword.repo.KeywordRepo
import solution.codebox.hotkeyword.ui.BasePresenter

class HotKeywordPresenter(private val keywordRepo: KeywordRepo)
    : BasePresenter<HotKeywordContract.View>(), HotKeywordContract.Presenter {
    override fun fetchKeywords() {
        view?.showProgress()
        keywordRepo.fetchKeywords(object : DataResponse<ArrayList<String>> {
            override fun onError() {
                view?.hideProgress()
            }

            override fun onSuccess(result: ArrayList<String>) {
                view?.apply {
                    showHotKeywords(result)
                    hideProgress()
                }
            }
        })
    }
}