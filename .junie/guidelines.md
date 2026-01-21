# Repository Guidelines

## プロジェクト概要
Kotlin Multiplatform と Compose Multiplatform を使い、Android/iOS/Web/Desktop を対象としたクイズアプリです。共有 UI とロジックは `composeApp` に集約し、iOS のエントリは `iosApp` にあります。

## プロジェクト構成（Project Structure & Module Organization）
- 共有コード: `composeApp/src/commonMain/kotlin`、共有テスト: `composeApp/src/commonTest/kotlin`
- 端末別コード: `androidMain`, `iosMain`, `jvmMain`, `jsMain`, `wasmJsMain`, `webMain`
- アセット: `composeApp/src/androidMain/res`, `composeApp/src/commonMain/composeResources`, `composeApp/src/webMain/resources`, `iosApp/iosApp/Assets.xcassets`
- iOS エントリ/設定: `iosApp/iosApp`, `iosApp/iosApp.xcodeproj`, `iosApp/Configuration/Config.xcconfig`

## ビルド・実行・開発コマンド（Build, Test, and Development Commands）
代表的な Gradle コマンド（macOS/Linux）は以下です。Windows は `gradlew.bat` を使います。
```shell
./gradlew :composeApp:assembleDebug
./gradlew :composeApp:run
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
./gradlew :composeApp:jsBrowserDevelopmentRun
```
iOS は `iosApp` を Xcode で開いて実行します。

## コーディングスタイルと命名（Coding Style & Naming Conventions）
Kotlin の公式スタイルに準拠し、インデントは 4 スペース。Compose の `@Composable` 関数は `AppScreen` のように PascalCase、一般関数は camelCase。パッケージは `org.example.project` 形式。Android リソースは `ic_launcher_background.xml` のように snake_case。

## テスト指針（Testing Guidelines）
共有テストは `composeApp/src/commonTest/kotlin`、`kotlin.test` を使用。基本は `*Test.kt` 命名。例: `./gradlew :composeApp:test`（JVM）。現時点でカバレッジ基準は未定です。

## コミット・PR 指針（Commit & Pull Request Guidelines）
履歴は `first commit` のみで明確な規約なし。短く意味が伝わる命令形を推奨（例: `Add quiz flow`）。PR には概要、影響プラットフォーム、手動確認結果、UI 変更時のスクリーンショットを含め、仕様変更があれば `SPEC.md` を更新してください。

## 設定・秘密情報（Configuration Tips）
依存関係は `gradle/libs.versions.toml`、Android 設定は `gradle.properties` と `composeApp/build.gradle.kts`、iOS 設定は `iosApp/Configuration/Config.xcconfig` にあります。秘密情報はコミットせず、ローカル上書きで管理します。