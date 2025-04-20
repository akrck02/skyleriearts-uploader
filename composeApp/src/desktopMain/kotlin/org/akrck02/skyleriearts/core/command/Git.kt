package org.akrck02.skyleriearts.core.command

import java.io.File

fun File.gitAdd(path: String) = exec("git add $path")
fun File.gitCommit(message: String) = exec("git commit -m \"$message\"")
fun File.gitPull() = exec("git pull")
fun File.gitPush() = exec("git push")

fun File.update(path: String, message: String) {
    this.gitPull()
    this.gitAdd(path)
    this.gitCommit(message)
    this.gitPush()
}