package com.example.kotlin_example.api.common // ktlint-disable package-name

import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class Log {
    protected val log: Logger = LoggerFactory.getLogger(this::class.java)
}
