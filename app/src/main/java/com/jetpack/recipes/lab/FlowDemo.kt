package com.jetpack.recipes.lab

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class FlowDemo {

}

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
fun main() {
    /**
     * 提供协程作用域
     */
    runBlocking {
        val flow = flowOf(1, 2, 3, 4, 5)
        println("map:")
        flow.map { it * it }
            .collect {
                println(it)
            }

        // onEach 中间状态， collect 结果
        println("onEach filter:")
        flow.filter {
            it % 2 == 0
        }.onEach {
            print(it.toString().plus("--"))
        }.map {
            it * it
        }.collect {
            println(it)
        }

        println("debounce")
        flow {
            emit(1)
            emit(2)
            delay(600)
            emit(3)
            delay(500)
            emit(4)
            delay(100)
            emit(5) // 最后一个数据也会被发出
        }.debounce(500) // 超过500ms才能发出, 参考相机防抖功能
            .collect {
                println(it)
            }

        println("sample")
//        flow {
//            while (true) {
//                emit("this is test line")
//            }
//        }.sample(1000).flowOn(Dispatchers.IO).collect {
//            println(it)
//        }

        println("reduce")
        val res = flow {
            for (i in (1..100)) {
                emit(i)
            }
        }.reduce { acc, value -> acc + value } // 不需要collect就可以输出
        println(res)

        println("fold")
        val resFold = flow {
            for (i in ('A'..'Z')) {
                emit(i.toString())
            }
        }.fold("INDEX: ") { acc, value -> acc + value } // 与reduce类似，但需要初始值
        println(resFold)

        println("flatMapConcat")
        flowOf(1, 2, 3) // 拼接两个网络请求数据
            .flatMapConcat {
                flowOf("a$it", "b$it")
            }
            .collect {
                println(it)
            }

        println("flatMapMerge,flatMapConcat")
        flowOf(300, 200, 100)
            .flatMapConcat {
                flow {
                    delay(it.toLong())
                    emit("a$it")
                    emit("b$it")
                }
            }
            .collect {
                println(it)
            }

        flowOf(300, 200, 100)
            .flatMapMerge {// 优先用时短的
                flow {
                    delay(it.toLong())
                    emit("a$it")
                    emit("b$it")
                }
            }.collect {
                println(it)
            }

        flow {
            emit(1)
            delay(150)
            emit(2)
            delay(50)
            emit(3)
        }.flatMapLatest {// 只处理最近，非正式api
            flow {
                delay(100)
                emit("$it")
            }
        }
            .collect {
                println(it)
            }

        println("zip") // 合并，时长采用耗时最长
        val flow1 = flowOf("a", "b", "c")
        val flow2 = flowOf(1, 2, 3, 4, 5)
        flow1.zip(flow2) { a, b ->
            a + b
        }.collect {
            println(it)
        }

        println("buffer")
        flow {
            emit(1);
            delay(1000);
            emit(2);
            delay(1000);
            emit(3);
        }
            .onEach {
                println("$it is ready")
            }
            .buffer()// flow collect 分开两个协程中，不互相干扰
            .collect {
                delay(1000)
                println("$it is handled")
            }

        println("conflate")
        flow {
            for ((count, i) in (1 .. 10).withIndex()) {
                emit(count)
                delay(1000)
            }
        }
            .conflate()// 处理最近的数据
            .collect {
                println("start handle $it")
                delay(2000)
                println("finish handle $it")
            }

    }
}