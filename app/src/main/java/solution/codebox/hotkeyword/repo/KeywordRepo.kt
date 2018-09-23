package solution.codebox.hotkeyword.repo

interface KeywordRepo {
    fun fetchKeywords(dataResponse: DataResponse<ArrayList<String>>)
}