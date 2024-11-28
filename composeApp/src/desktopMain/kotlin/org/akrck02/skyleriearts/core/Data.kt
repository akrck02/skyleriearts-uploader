package org.akrck02.skyleriearts.core

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap

inline fun <K, V> buildMutableStateMap(builderAction: MutableMap<K, V>.() -> Unit): SnapshotStateMap<K, V> =
    mutableStateMapOf(*buildMap(builderAction).toList().toTypedArray())

fun <T> MutableList<T>.addIfNotPresent(data : T) {
    when (this.contains(data)) {
        true -> this.add(data)
        false -> return
    }
}