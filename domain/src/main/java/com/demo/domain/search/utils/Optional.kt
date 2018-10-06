package com.demo.domain.search.utils

class Optional<T> {

    private var value: T? = null

    val isEmpty: Boolean
        get() = value == null

    val isNotEmpty: Boolean
        get() = value != null

    private constructor() {
        this.value = null
    }

    private constructor(value: T?) {
        if (value == null)
            throw NullPointerException()
        this.value = value
    }

    fun get(): T? {
        return value
    }

    companion object {

        fun <T> empty(): Optional<T> {
            return Optional<T>()
        }

        fun <T> of(value: T): Optional<T> {
            return Optional(value)
        }
    }

}