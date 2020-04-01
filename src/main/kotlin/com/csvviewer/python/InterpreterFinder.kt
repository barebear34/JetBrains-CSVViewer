package com.csvviewer.python

import org.apache.commons.exec.OS
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

/**
 * Class-helper for finding way to python3 interpreter
 * @author Aleksey Petrenko
 */
object InterpreterFinder {

    fun findPathToPythonInterpreter(): String = try {
        val process = when {
            OS.isFamilyWindows() -> ProcessBuilder("where", "python3").start()
            else -> ProcessBuilder("which", "python3").start()
        }
        BufferedReader(InputStreamReader(process.inputStream)).readLine()
    } catch (e: Exception) {
        ""
    }
}