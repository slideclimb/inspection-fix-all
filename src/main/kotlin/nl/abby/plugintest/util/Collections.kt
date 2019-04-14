package nl.abby.plugintest.util

import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * Collects stream to [List].
 */
fun <T> Stream<T>.list(): List<T> = this.mutableList()

/**
 * Collects stream to [MutableList].
 */
fun <T> Stream<T>.mutableList(): MutableList<T> = this.collect(Collectors.toList())

/**
 * Collects stream to [Set].
 */
fun <T> Stream<T>.set(): Set<T> = this.mutableSet()

/**
 * Collects stream to [MutableSet]
 */
fun <T> Stream<T>.mutableSet(): MutableSet<T> = this.collect(Collectors.toSet())