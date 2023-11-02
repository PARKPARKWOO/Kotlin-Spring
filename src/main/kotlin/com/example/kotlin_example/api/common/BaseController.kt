package com.example.kotlin_example.api.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class BaseController {
    protected val log: Logger = LoggerFactory.getLogger(this::class.java)
}