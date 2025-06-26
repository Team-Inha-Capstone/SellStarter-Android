package com.inha.sellstarter_android.data.service

import com.inha.sellstarter_android.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter_android.data.model.response.order.OrderDetailResponseDto
import com.inha.sellstarter_android.data.model.response.order.OrderListResponseDto
import com.inha.sellstarter_android.data.model.response.order.PickingAvailableResponseDto
import com.inha.sellstarter_android.data.util.Constants.API
import com.inha.sellstarter_android.data.util.Constants.CORE
import com.inha.sellstarter_android.data.util.Constants.ORDER
import com.inha.sellstarter_android.util.base.BaseResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OrderService {
    @GET("$API/$CORE/$ORDER/{userId}")
    suspend fun loadOrderConfirmList(
        @Path("userId") userId: Int = 4,
        @Query("status") status: String = "ORDER_COMPLETED",
        @Query("page") page: Int,
        @Query("size") size: Int
    ): BaseResponseDto<OrderListResponseDto>// 주문확인 리스트 조회

    @GET("$API/$CORE/$ORDER/{userId}/{orderId}")
    suspend fun loadOrderConfirmationDetail(
        @Path("userId") userId: Int = 4,
        @Path("orderId") orderId: String
    ): BaseResponseDto<OrderDetailResponseDto>// 주문 확인 상세

    @GET("$API/$CORE/$ORDER/{userId}/{orderId}/pick-check")
    suspend fun checkPickingAvailable(
        @Path("userId") userId: Int = 4,
        @Path("orderId") orderId: String,
        @Query("barcodeId") barcodeId: String? = null
    ): BaseResponseDto<PickingAvailableResponseDto>// 피킹 가능 여부 확인

    @POST("$API/$CORE/$ORDER/{userId}/{orderId}/pick-order")
    suspend fun completeOrderPickings(
        @Path("userId") userId: Int = 4,
        @Path("orderId") orderId: String,
    ): Response<Unit> //주문 피킹 완료 (전체 상품에 대해)

    @POST("$API/$CORE/$ORDER/{userId}/{orderId}/pick-inventory")
    suspend fun completeSinglePicking(
        @Path("userId") userId: Int = 4,
        @Path("orderId") orderId: String,
        @Body request: OrderInventoryPickingRequestDto
    ): BaseResponseDto<OrderDetailResponseDto> // 단일 상품 피킹 완료

    @GET("$API/$CORE/$ORDER/{userId}")
    suspend fun loadCompletedPickingList(
        @Path("userId") userId: Int = 4,
        @Query("status") status: String = "PICKING_COMPLETED",
        @Query("page") page: Int,
        @Query("size") size: Int
    ): BaseResponseDto<OrderListResponseDto> //피킹 완료 리스트 조회

    @POST("$API/$CORE/$ORDER/{userId}/{orderId}/ship")
    suspend fun shipOrder(
        @Path("userId") userId: Int = 4,
        @Path("orderId") orderId: String,
    ): Response<Unit> // 출고완료

    @POST("$API/$CORE/$ORDER/{userId}/{orderId}/cancel")
    suspend fun cancelOrder(
        @Path("userId") userId: Int = 4,
        @Path("orderId") orderId: String,
    ): Response<Unit> //주문 취소
}