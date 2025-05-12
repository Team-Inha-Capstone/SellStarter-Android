package com.inha.sellstarter_android.data.repository

import com.inha.sellstarter_android.data.datasource.remote.MyPageDataSource
import com.inha.sellstarter_android.data.mapper.toDomain
import com.inha.sellstarter_android.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter_android.domain.model.UserInfo
import com.inha.sellstarter_android.domain.repository.MyPageRepository
import javax.inject.Inject

class MyPageRepositoryImpl @Inject
constructor(
    private val myPageDataSource: MyPageDataSource,
) : MyPageRepository {
    override suspend fun getUserDetail(): Result<UserInfo> {
        return runCatching {
            myPageDataSource.getUserDetail().data.toDomain()
        }
    }

    override suspend fun deleteUserApi(userApiDeleteRequestDto: UserApiDeleteRequestDto): Result<UserInfo> {
        return runCatching {
            myPageDataSource.deleteUserApi(userApiDeleteRequestDto).data.toDomain()
        }
    }

    override suspend fun updateUserApi(userApiUpdateRequest: UserApiUpdateRequest): Result<UserInfo> {
        return runCatching {
            myPageDataSource.updateUserApi(userApiUpdateRequest).data.toDomain()
        }
    }

    override suspend fun createUserApi(userApiRequestDto: UserApiRequestDto): Result<UserInfo> {
        return runCatching {
            myPageDataSource.postUserApi(userApiRequestDto).data.toDomain()
        }
    }
}