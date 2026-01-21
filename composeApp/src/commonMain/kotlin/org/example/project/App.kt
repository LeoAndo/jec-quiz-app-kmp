package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    // この画面で使うUI状態（スナックバー、ページャー、スクロール、クイズ進捗）を保持する
    val hostState = remember { SnackbarHostState() }
    val pagerState = rememberPagerState(pageCount = { Question.entries.size })
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    var isFinishedQuiz by remember { mutableStateOf(false) }
    var collectAnswerCount by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = hostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding).padding(12.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // メインのコンテンツをここに記述していきます
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
            ) { pageIndex ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .aspectRatio(1f)
                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        val message = Question.entries[pagerState.currentPage].message
                        Text(
                            text = message,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            if (isFinishedQuiz) {
                Text("${collectAnswerCount}問正解しました")
                Button(onClick = {
                    scope.launch {
                        pagerState.scrollToPage(page = 0)
                        collectAnswerCount = 0
                        isFinishedQuiz = false
                    }
                }) {
                    Text("やり直す")
                }
            }

            val question = Question.entries[pagerState.currentPage]
            question.answers.forEachIndexed { index, answerText ->
                Button(
                    enabled = !isFinishedQuiz,
                    modifier = Modifier.widthIn(max = 320.dp).fillMaxWidth(0.5f),
                    onClick = {
                        scope.launch {
                            isFinishedQuiz = pagerState.currentPage == Question.entries.lastIndex
                            if (!isFinishedQuiz) {
                                pagerState.scrollToPage(page = pagerState.currentPage + 1)
                            }

                            val message = if (question.answerIndex == index) {
                                "正解です"
                            } else {
                                "不正解です"
                            }
                            if (question.answerIndex == index) {
                                collectAnswerCount++
                            }
                            hostState.currentSnackbarData?.dismiss()
                            hostState.showSnackbar(message)
                        }
                    },
                ) {
                    Text(
                        text = answerText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}