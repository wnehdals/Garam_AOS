package com.jdm.garam.state

sealed class BaseState{
    object Uninitialized: BaseState()   //초기 상태

    object Loading: BaseState()         //로딩 상태

    data class Error(                   //API 호출 실패
        val error: Throwable
    ) : BaseState()

    data class Success<T>(              //API 호출 성공 - code : 200
        val SuccessResp: T
    ): BaseState()
    data class Fail<T>(                 //API 호출 성공 그러나 code 200이 아닌경우
        val FailResp: T
    ): BaseState()
}
