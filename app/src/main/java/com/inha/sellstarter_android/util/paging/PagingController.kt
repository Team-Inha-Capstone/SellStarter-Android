package com.inha.sellstarter_android.util.paging

data class PagingController(
    var page: Int = 0,
    var totalPages: Int = Int.MAX_VALUE,
    var isLoading: Boolean = false
) {
    fun reset() {
        page = 0
        totalPages = Int.MAX_VALUE
        isLoading = false
    }

    fun canLoadMore(): Boolean = !isLoading && page < totalPages
    fun markLoading() { isLoading = true }
    fun complete(page: Int, totalPages: Int) {
        this.page = page
        this.totalPages = totalPages
        isLoading = false
    }
}