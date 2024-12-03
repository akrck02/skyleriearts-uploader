package org.akrck02.skyleriearts.core

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap

inline fun <K : Any, V> buildMutableStateMap(builderAction: MutableMap<K, V>.() -> Unit): SnapshotStateMap<K, V> =
    mutableStateMapOf(*buildMap(builderAction).toList().toTypedArray())


fun <T> MutableList<T>.addIfNotPresent(data: T) {
    when (this.contains(data)) {
        true -> return
        false -> this.add(data)
    }
}


fun <T> MutableList<T>.removeIfPresent(data: T) {
    when (this.contains(data)) {
        true -> this.remove(data)
        false -> return
    }
}


fun <K> MutableMap<K, Int>.toggle(
    key: K,
    value: Int = 0,
    onAdd: () -> Unit = {},
    onRemove: () -> Unit = {},
) {

    when (this.contains(key)) {
        true -> {
            this.remove(key)
            onRemove()
        }

        false -> {
            this[key] = value
            onAdd()
        }
    }
}