# クイズアプリ仕様書

## 概要

Kotlin Multiplatform (KMP) と Compose Multiplatform を使用したクロスプラットフォーム対応のクイズアプリケーション。

## 対応プラットフォーム

- Android
- iOS
- Desktop (JVM)

## 技術スタック

- **言語**: Kotlin
- **UI フレームワーク**: Compose Multiplatform
- **アーキテクチャ**: Kotlin Multiplatform (KMP)
- **UI コンポーネント**: Material 3

## 機能仕様

### クイズ機能

1. **問題表示**
   - 問題文を画面中央のカード形式で表示
   - HorizontalPager によるページング形式で問題を切り替え
   - ユーザーによる手動スワイプは無効化（回答選択時のみ遷移）

2. **回答選択**
   - 各問題に対して複数の選択肢をボタンで表示
   - 選択肢は縦方向に配置
   - ボタン幅は最大320dp、画面幅の50%に制限

3. **正誤判定**
   - 回答選択時に Snackbar で「正解です」または「不正解です」を表示
   - 正解数をカウント

4. **結果表示**
   - 全問回答後に正解数を表示（例：「2問正解しました」）
   - 「やり直す」ボタンで最初の問題に戻り、スコアをリセット

### UI 仕様

- **レイアウト**: Scaffold ベースのレイアウト
- **スクロール**: 縦方向スクロール対応
- **間隔**: コンテンツ間は12dpの間隔
- **問題カード**: アスペクト比1:1の正方形、secondaryContainer カラー

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
| pagerState | PagerState | 現在のページ位置 |
| isFinishedQuiz | Boolean | クイズ完了フラグ |
| collectAnswerCount | Int | 正解数のカウンター |

## 今後の拡張案

- [ ] 問題データの外部ファイル化 (JSON/CSV)
- [ ] カテゴリ別問題選択機能
- [ ] タイマー機能
- [ ] ランキング機能
- [ ] 問題のシャッフル機能
- [ ] 解説表示機能