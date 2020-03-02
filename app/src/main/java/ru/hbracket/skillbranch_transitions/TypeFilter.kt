package ru.hbracket.skillbranch_transitions

inline fun <reified T> Any.typeFilter(): T? =
    this as? T