package com.inha.sellstarter.domain.model.type

enum class ShoppingMallType(
    val shoppingMallTypeId: Int,
    val displayName: String,
) {
    GROCERY(1, "식료품점"),
    FASHION_ACCESSORIES(2, "패션잡화점"),
    HOUSEHOLD_GOODS(3, "생활용품점"),
    ELECTRONICS(4, "전자제품점"),
    BEAUTY_COSMETICS(5, "뷰티/화장품"),
    SPORTS_EQUIPMENT(6, "스포츠용품점"),
    PET_SUPPLIES(7, "반려동물용품"),
    BABY_PRODUCTS(8, "유아용품점"),
}
