package solution.codebox.hotkeyword.repo

interface DataResponse<T> {
    fun onSuccess(result: T)
    fun onError()
}