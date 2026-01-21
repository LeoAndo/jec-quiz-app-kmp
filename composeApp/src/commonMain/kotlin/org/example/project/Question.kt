package org.example.project

enum class Question(
    val message: String,
    val answers: List<String>,
    val answerIndex: Int
) {
    Q1(
        "Androidの開発言語は？",
        listOf("Java", "PHP", "Ruby", "Go", "Swift"),
        0
    ),
    Q2(
        "iOSの開発言語は？",
        listOf("PHP", "Swift", "Ruby"),
        1
    )
}