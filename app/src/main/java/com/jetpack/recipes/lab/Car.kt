package com.jetpack.recipes.lab

import javax.inject.Inject


class Car @Inject constructor(var driver: Driver){
    fun deliver() {
        println("deliver cargo Driven by $driver")
    }
}