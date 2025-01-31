package com.android.study.ui.model

data class LayoutComponent(
    val type: String,
    val content: String? = null,
    val url: String? = null,
    val text: String? = null,
    val id: String,
    val constraints: Constraints
)

data class Constraints(
    val endToEnd: String?,
    val startToEnd: String?,
    val endToStart: String?,
    val startToStart: String?,
    val bottomToBottom: String?,
    val bottomToTop: String?,
    val topToBottom: String?,
    val topToTop: String?,
    val leftToLeft: String?,
    val leftToRight: String?,
    val rightToLeft: String?,
    val rightToRight: String?
) {
}
