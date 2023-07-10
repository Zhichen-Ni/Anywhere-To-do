package com.example.todo_desktop.service

//import jdk.jfr.internal.SecuritySupport.getAbsolutePath
import java.io.File
import java.util.concurrent.TimeUnit

class RunCommandService {
    private val rt : Runtime = Runtime.getRuntime();
    val osName = System.getProperty("os.name")
    val absPath = this.javaClass.protectionDomain.codeSource.location

    // New implementation for runCommand using Process Building
    fun runCommand(str: String, workingDir: File): String {
        println(osName)
        var dir2: File = workingDir
        if (osName.startsWith("Windows")) {
            dir2 = File(absPath.toString()+"/bin")

        }
        val parts = str.split("\\s".toRegex())
        val proc = ProcessBuilder(*parts.toTypedArray())
            .directory(dir2)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        proc.waitFor(10, TimeUnit.SECONDS)
        return proc.inputStream.bufferedReader().readText()
    }
}