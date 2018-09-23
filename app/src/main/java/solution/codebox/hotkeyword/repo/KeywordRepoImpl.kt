package solution.codebox.hotkeyword.repo

import solution.codebox.hotkeyword.api.KeywordApi

class KeywordRepoImpl : KeywordRepo {
    override fun fetchKeywords(dataResponse: DataResponse<ArrayList<String>>) {
        KeywordApi.fetchKeyword(object : KeywordApi.Companion.FetchDataListener<ArrayList<String>?> {
            override fun onResult(keywords: ArrayList<String>?) {
                if (null != keywords)
                    dataResponse.onSuccess(keywords)
                else
                    dataResponse.onError()
            }
        })
    }
}