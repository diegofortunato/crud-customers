package com.crud.customers.util

import org.slf4j.LoggerFactory

object APIUtil {

    private val log = LoggerFactory.getLogger(javaClass)

    @JvmStatic
    fun removeSpecialCaracters(field: String): String {
        log.info("Remove special caracters for document")

        return field.replace("""[/.-]""".toRegex(), "")
    }
}