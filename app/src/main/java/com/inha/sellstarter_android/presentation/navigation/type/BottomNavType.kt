package com.inha.sellstarter_android.presentation.navigation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.inha.sellstarter_android.R

sealed class BottomNavType(
    val route: String,
    @StringRes
    val resourceId: Int,
    @DrawableRes
    val icon: Int,
) {


    data object Home : BottomNavType(
        route = "home",
        resourceId = R.string.home,
        icon = R.drawable.ic_home
    )

    data object Inventory : BottomNavType(
        route = "inventory",
        resourceId = R.string.inventory_list,
        icon = R.drawable.ic_inventory
    )

    data object Order : BottomNavType(
        route = "order",
        resourceId = R.string.order_list,
        icon = R.drawable.ic_order
    )

    data object MyPage : BottomNavType(
        route = "mypage",
        resourceId = R.string.mypage,
        icon = R.drawable.ic_profile_bottoms
    )
}