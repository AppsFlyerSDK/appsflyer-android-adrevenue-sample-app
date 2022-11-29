package com.appsflyer.af_adrevenue.fragments

object DataProvider {

    init {
        println("DataProvider class invoked.")
    }

    var shouldInitIronSource = true
    var isWallAvailable = false
    var isRewardedAvailable = false
}

fun main(args: Array<String>) {
    var ironSourceFrag = IronSourceFragment()
}
