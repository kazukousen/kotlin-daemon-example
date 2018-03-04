package com.github.kazukousen.kt_example

import java.io.File
import java.lang.Runtime
import java.lang.Thread
import java.lang.Thread.sleep
import java.time.ZonedDateTime

object Hoge {
	fun run() {
		Misc.print("Hello, World!")
	}

}

object Misc {
	fun print(s: String) {
		println("[${ZonedDateTime.now()}] ${s}")
	}

	fun printErr(s: String) {
		System.err.println("[${ZonedDateTime.now()}] ${s}")
	}
}

class ShutdownThread : Thread() {

	override fun run() {
		Misc.print("get HUP signal.")
		// ここに安全に終了する処理
		Misc.print("Shutdown.")
	}
}

fun main(args: Array<String>) {

	// HUPをフックする
	Runtime.getRuntime().addShutdownHook(ShutdownThread())

	// 処理
	while(true) {
		Hoge.run()
		Thread.sleep(5000L)
	}
}