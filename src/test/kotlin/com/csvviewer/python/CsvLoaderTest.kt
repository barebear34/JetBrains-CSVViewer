package com.csvviewer.python

import com.csvviewer.model.Row
import com.csvviewer.model.Table
import org.junit.Assert
import org.junit.Test

class CsvLoaderTest {

    @Test
    fun `should properly read csv file`() {
        val expectedResult = Table(
            columns = listOf("QuotaAmount", "StartDate", "OwnerName", "Username"),
            rows = listOf(Row(
                items = listOf("150000", "2016-01-01" , "Chris Riley", "trailhead9.ub20k5i9t8ou@example.com")
            ))
        )

        CsvLoader.pathToInterpreter = InterpreterFinder.findPathToPythonInterpreter()
        val actualResult = CsvLoader.getTable("src/test/resources/test.csv")

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `should return empty table due to file does not exist`() {
        val expectedResult = Table(
            columns = emptyList(),
            rows = emptyList()
        )

        CsvLoader.pathToInterpreter = InterpreterFinder.findPathToPythonInterpreter()
        val actualResult = CsvLoader.getTable("src/test/resources/not_exist.csv")

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `should return empty table due to empty file`() {
        val expectedResult = Table(
            columns = emptyList(),
            rows = emptyList()
        )

        CsvLoader.pathToInterpreter = InterpreterFinder.findPathToPythonInterpreter()
        val actualResult = CsvLoader.getTable("src/test/resources/empty.csv")

        Assert.assertEquals(expectedResult, actualResult)
    }
}