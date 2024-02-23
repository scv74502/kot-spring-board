package org.example.assignment.common.logger
import org.slf4j.LoggerFactory

abstract class CommonLogger {
    val logger = LoggerFactory.getLogger(this.javaClass)
}