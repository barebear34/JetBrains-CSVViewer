package com.csvviewer.model

data class Table(val columns: List<String>, val rows: List<Row>)

data class Row(val items: List<String>)