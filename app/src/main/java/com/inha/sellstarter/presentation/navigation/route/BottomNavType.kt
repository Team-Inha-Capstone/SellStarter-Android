package com.inha.sellstarter.presentation.navigation.route

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.inha.sellstarter.R

sealed class BottomNavType(
    val rootRoute: String,
    val startDestination: String,
    @StringRes
    val resourceId: Int,
    @DrawableRes
    val icon: Int,
) {
    data object Home : BottomNavType(
        rootRoute = "home_root",
        startDestination = AppRoute.Home.route,
        resourceId = R.string.home,
        icon = R.drawable.ic_home,
    )

    data object Inventory : BottomNavType(
        rootRoute = "inventory_root",
        startDestination = AppRoute.Inventory.route,
        resourceId = R.string.inventory_list,
        icon = R.drawable.ic_inventory,
    )

    data object Order : BottomNavType(
        rootRoute = "order_root",
        startDestination = AppRoute.Order.route,
        resourceId = R.string.order_list,
        icon = R.drawable.ic_order,
    )

    data object MyPage : BottomNavType(
        rootRoute = "mypage_root",
        startDestination = AppRoute.MyPage.route,
        resourceId = R.string.mypage,
        icon = R.drawable.ic_profile_bottoms,
    )

    companion object {
        val items: List<BottomNavType> =
            listOf(
                Home,
                Inventory,
                Order,
                MyPage,
            )
    }
}
