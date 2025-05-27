package com.inha.sellstarter_android.presentation.inventory

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.domain.model.InventoryItem
import com.inha.sellstarter_android.domain.usecase.inventory.InventoryUseCases
import com.inha.sellstarter_android.util.base.UiState
import com.inha.sellstarter_android.util.base.safeApiCall
import com.inha.sellstarter_android.util.extension.logHttpError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val inventoryUseCases: InventoryUseCases
) : ViewModel() {

    // 1. 전체 재고 리스트
    private val _inventoryListState =
        MutableStateFlow<UiState<List<InventoryItem>>>(UiState.Loading)
    val inventoryListState: StateFlow<UiState<List<InventoryItem>>> = _inventoryListState

    // 2. 재고 상세
    private val _inventoryDetailState = MutableStateFlow<UiState<Inventory>>(UiState.Loading)
    val inventoryDetailState: StateFlow<UiState<Inventory>> = _inventoryDetailState

    // 3. 수량 수정
    private val _editCountState = MutableStateFlow<UiState<Inventory>>(UiState.Loading)
    val editCountState: StateFlow<UiState<Inventory>> = _editCountState

    // 4. 재고 검색
    private val _searchResultState = MutableStateFlow<UiState<List<InventoryItem>>>(UiState.Loading)
    val searchResultState: StateFlow<UiState<List<InventoryItem>>> = _searchResultState

    var draft: InventoryCreateRequestDto? by mutableStateOf(null)
    var draftImageUri: Uri? by mutableStateOf(null)

    private val _registerState = MutableStateFlow<UiState<Inventory>>(UiState.Loading)
    val registerState: StateFlow<UiState<Inventory>> = _registerState

    fun getInventoryList(status: Boolean, page: Int = 0, size: Int = 20) {
        viewModelScope.launch {
            _inventoryListState.value = safeApiCall(
                onStart = { _inventoryListState.value = UiState.Loading },
                onError = { it.logHttpError("getInventoryList") },
                apiCall = {
                    inventoryUseCases.inventoryListUseCase.invoke(status, page, size)
                }
            )
        }
    }

    fun getInventoryDetail(barcodeId: String) {
        viewModelScope.launch {
            _inventoryDetailState.value = safeApiCall(
                onStart = { _inventoryDetailState.value = UiState.Loading },
                onError = { it.logHttpError("getInventoryDetail") },
                apiCall = {
                    inventoryUseCases.inventoryDetailUseCase.invoke(barcodeId)
                }
            )
        }
    }

    fun editInventoryCount(
        barcodeId: String,
        currentCount: Int,
        newCount: Int
    ) {
        viewModelScope.launch {
            _editCountState.value = safeApiCall(
                onStart = { _editCountState.value = UiState.Loading },
                onError = { it.logHttpError("editInventoryCount") },
                apiCall = {
                    inventoryUseCases.inventoryEditCountUseCase.invoke(
                        barcodeId = barcodeId,
                        inventoryCountRequest = InventoryCountRequestDto(
                            currentCount = currentCount,
                            inventoryCount = newCount
                        )
                    ).also {
                        getInventoryDetail(barcodeId)
                    }
                }
            )
        }
    }

    fun searchInventory(search: String, status: Boolean) {
        viewModelScope.launch {
            _searchResultState.value = safeApiCall(
                onStart = { _searchResultState.value = UiState.Loading },
                onError = { it.logHttpError("searchInventory") },
                apiCall = {
                    inventoryUseCases.inventorySearchUseCase.invoke(
                        search = search,
                        status = status,
                        page = 0,
                        size = 10
                    )
                }
            )
        }
    }


    fun registerInventory(
        imageUri: Uri?,
        context: Context,
        inventoryCreateRequest: InventoryCreateRequestDto
    ) {
        viewModelScope.launch {
            val imagePart = imageUri?.let {
                val file = uriToFile(it, context)
                file?.let { f ->
                    val requestBody = f.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("image", f.name, requestBody)
                }
            }

           _registerState.value =  safeApiCall(
                onStart = { _registerState.value = UiState.Loading },
                onError = { it.logHttpError("registerInventory") },
                apiCall = {
                    inventoryUseCases.inventoryRegisterUseCase.invoke(
                        inventoryCreateRequest = inventoryCreateRequest,
                        image = imagePart
                    )
                }
            )
        }
    }

    fun saveDraft(dto: InventoryCreateRequestDto, image: Uri?) {
        draft = dto
        draftImageUri = image
    }

    fun clearDraft() {
        draft = null
        draftImageUri = null
    }

    private fun uriToFile(uri: Uri, context: Context): File? {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val tempFile = File.createTempFile("upload", ".jpg", context.cacheDir)
        tempFile.outputStream().use { output ->
            inputStream.copyTo(output)
        }
        return tempFile
    }
}