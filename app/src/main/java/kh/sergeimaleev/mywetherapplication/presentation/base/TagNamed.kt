package kh.sergeimaleev.mywetherapplication.presentation.base

interface TagNamed<out T> {
    val tagName: Int
    val item: T
}