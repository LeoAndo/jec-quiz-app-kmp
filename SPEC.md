# クイズアプリ仕様書

## 概要
Kotlin Multiplatform (KMP) と Compose Multiplatform によるクロスプラットフォームのクイズアプリ。

## 対応プラットフォーム
- Android
- iOS
- Web (Wasm / JS)
- Desktop (JVM)

## 技術スタック
- **言語**: Kotlin
- **UI**: Compose Multiplatform + Material 3
- **テーマ/フォント**: `AppTheme.kt`/`Color.kt`、`composeResources/font/notosansjp_regular.ttf`

## 機能仕様

### クイズ進行
1. **問題表示**
   - 画面中央のカードで問題文を表示
   - `HorizontalPager` でページング、ユーザーのスワイプは無効
2. **回答選択**
   - 選択肢を縦並びのボタンで表示
   - 回答時に次の問題へ遷移
3. **正誤判定**
   - 回答選択時に Snackbar で「正解です/不正解です」を表示
   - 正解数をカウント
4. **結果表示**
   - 全問回答後に正解数を表示（例: 「2問正解しました」）
   - 「やり直す」で1問目へ戻り、スコアをリセット
   - 結果表示中は回答ボタンを無効化

### UI/レイアウト
- **レイアウト**: `Scaffold` ベース、縦スクロール（`verticalScroll`）
- **間隔**: コンテンツ間は 12dp
- **問題カード**: `fillMaxWidth(0.5f)` + `aspectRatio(1f)`、背景は `MaterialTheme.colorScheme.secondaryContainer`
- **回答ボタン**: `widthIn(max = 320.dp)` かつ `fillMaxWidth(0.5f)`、テキストは太字

### テーマとフォント
- `AppTheme` でライト/ダーク配色と `Noto Sans JP` を定義（Web の文字化け対策）
- 現状のエントリポイントは `App()` を直接呼び出すため、適用時は `AppTheme { App() }` を使用

## データ構造

### Question enum
```kotlin
enum class Question(
    val message: String,      // 問題文
    val answers: List<String>, // 選択肢リスト
    val answerIndex: Int       // 正解のインデックス
)
```

### サンプル問題
| ID | 問題文 | 選択肢 | 正解 |
|----|--------|--------|------|
| Q1 | Androidの開発言語は？ | Java, PHP, Ruby, Go, Swift | Java (index: 0) |
| Q2 | iOSの開発言語は？ | PHP, Swift, Ruby | Swift (index: 1) |

## 状態管理
| 状態 | 型 | 説明 |
|------|-----|------|
| hostState | SnackbarHostState | Snackbar の表示管理 |
| pagerState | PagerState | 現在のページ位置（`Question.entries.size` でページ数） |
| scrollState | ScrollState | 画面の縦スクロール |
| isFinishedQuiz | Boolean | クイズ完了フラグ |
| collectAnswerCount | Int | 正解数のカウンター |

## 今後の拡張案
- [ ] 問題データの外部ファイル化 (JSON/CSV)
- [ ] カテゴリ別問題選択機能
- [ ] タイマー機能
- [ ] ランキング機能
- [ ] 問題のシャッフル機能
- [ ] 解説表示機能
