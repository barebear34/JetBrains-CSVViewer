package com.csvviewer.python

import com.csvviewer.model.Row
import com.csvviewer.model.Table
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

/**
 * Class-helper for loading csv files with python
 * @author Aleksey Petrenko
 */
object CsvLoader {

    var pathToInterpreter: String = ""

    fun getTable(pathToCsv: String): Table {

        val path = Paths.get(pathToCsv)

        if (Files.exists(path) && Files.isRegularFile(path) && path.toFile().length() > 0) {
            val process = ProcessBuilder(pathToInterpreter, "loader.py", pathToCsv).start()
            val out = BufferedReader(InputStreamReader(process.inputStream))
                .lines()
                .toList()
                .toMutableList()
            val columns = out
                .removeAt(0)
                .split(", ")
            val rows = out
                .map {
                    Row(
                        items = it
                            .replace("\"", "")
                            .split(", ")
                    )
                }
            return Table(
                columns = columns,
                rows = rows
            )
        }
        return Table(
            columns = emptyList(),
            rows = emptyList()
        )
    }
}


