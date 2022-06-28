package com.hanshow.myapplication

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.Test


/**
 * Created by yikai on 2022/6/14.
 * Describe:
 */
class MapTest {

    @Test
    fun toJson() = runBlocking<Unit> {

        testFlow()
            // .flowOn(Dispatchers.IO)
            // .buffer(100)
            // .collectLatest {  }
            // .conflate()
            .first {

                println("first $it")
                true
            }
//        (1..3).asFlow()
//            .collect {
//                println("hello $it  ${Thread.currentThread().name}")
//            }


    }


    fun testFlow() = flow<Int> {
        for (i in 1..3) {
            println("testFlow $i")
            emit(i)
        }
    }


    fun testFlow1() = flow<Int> {
        for (i in 1..3) {
            println("testFlow1 current data $i current Thread ${Thread.currentThread().name}")
            emit(i)
        }
    }

    @Test
    fun funTestFlow1() = runBlocking {
        testFlow1()
            .flowOn(Dispatchers.Default)
            .onEach {
                println("current data $it current Thread ${Thread.currentThread().name}")
            }
            .launchIn(CoroutineScope(Dispatchers.IO))
            .join()


    }


}