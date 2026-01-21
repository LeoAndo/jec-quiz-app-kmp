# Repository Guidelines

## プロジェクト概要
Kotlin Multiplatform と Compose Multiplatform を使ったクイズアプリで、Android/iOS/Web/Desktop を対象にしています。UI とロジックは `composeApp` に集約し、iOS は `iosApp` がエントリポイントです。

## プロジェクト構成（Project Structure & Module Organization）
- 共有 UI/ロジック: `composeApp/src/commonMain/kotlin/org/example/project`（`App.kt`, `Question.kt`, `AppTheme.kt`, `Color.kt`）
- 共有テスト: `composeApp/src/commonTest/kotlin`
- 端末別コード: `composeApp/src/*Main`（`androidMain`, `iosMain`, `jvmMain`, `jsMain`, `wasmJsMain`, `webMain`）
- リソース: `composeApp/src/commonMain/composeResources`, `composeApp/src/androidMain/res`, `composeApp/src/webMain/resources`, `iosApp/iosApp/Assets.xcassets`
- 生成物: `kotlin-js-store/` は自動生成のため手編集しない

## ビルド・実行・開発コマンド（Build, Test, and Development Commands）
macOS/Linux 例。Windows は `gradlew.bat` を使用。
```shell
./gradlew :composeApp:assembleDebug        # Android デバッグ APK
./gradlew :composeApp:run                   # Desktop (JVM)
./gradlew :composeApp:wasmJsBrowserDevelopmentRun  # Web (Wasm)
./gradlew :composeApp:jsBrowserDevelopmentRun      # Web (JS)
```
iOS は `iosApp` を Xcode で開いて実行します。

## コーディングスタイルと命名（Coding Style & Naming Conventions）
Kotlin 公式スタイル（インデント 4 スペース）。`@Composable` は PascalCase、一般関数は camelCase。パッケージは `org.example.project`、Android リソースは `snake_case`。テーマ変更は `AppTheme.kt`/`Color.kt` を更新し、各エントリで `AppTheme { App() }` を適用します。

## テスト指針（Testing Guidelines）
`composeApp/src/commonTest/kotlin` で `kotlin.test` を使用。命名は `*Test.kt`。例: `./gradlew :composeApp:test`（JVM）。現時点でカバレッジ要件は未定です。

## コミット・PR 指針（Commit & Pull Request Guidelines）
履歴に厳密な規約はないため、短い命令形を推奨（例: `Add quiz flow`）。PR には概要、影響プラットフォーム、手動確認結果、UI 変更時のスクリーンショットを記載し、挙動変更がある場合は `SPEC.md` を更新してください。

## 設定・構成（Configuration Tips）
依存関係は `gradle/libs.versions.toml`、Android 設定は `gradle.properties` と `composeApp/build.gradle.kts`、iOS 設定は `iosApp/Configuration/Config.xcconfig`。秘密情報や端末固有設定は `local.properties` で管理し、コミットしません。
