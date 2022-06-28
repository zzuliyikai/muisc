package com.hanshow.myapplication

/**
 * Created by yikai on 2022/6/12.
 * Describe:
 */
class WrapperApi(private val api: Api) : Api by api {

    override fun c() {
        api.c()
        println("hello c!!!!!")
    }
}