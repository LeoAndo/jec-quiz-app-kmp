# Repository Guidelines

このドキュメントはこのリポジトリの参加者向けガイドです。Java ベースのカウンターアプリを迅速に理解し、作業を進めるための構成と基本ルールをまとめます。

## プロジェクト構成とモジュール
- ルートは Gradle Kotlin DSL 構成で、`settings.gradle.kts` と `build.gradle.kts` が入口。
- アプリ本体は単一モジュール `app/`。Java ソースは `app/src/main/java/jp/ac/jec/cm0199/jecandroidjavatemplate/`。
- 画面は `MainActivity` のみで、カウンターの増減/リセットを実装。
- リソースは `app/src/main/res/`、マニフェストは `app/src/main/AndroidManifest.xml`。
- 共有設定は `gradle/` と `gradle/libs.versions.toml` で管理。

## ビルド・テスト・開発コマンド
- `./gradlew assembleDebug` : デバッグ APK を生成。
- `./gradlew assembleRelease` : リリース APK を生成（minify は無効）。
- `./gradlew installDebug` : 接続端末にインストール。
- `./gradlew test` : JVM ユニットテスト（JUnit4）。
- `./gradlew connectedAndroidTest` : 実機/エミュレータ向け計測テスト。
- `./gradlew lint` : Android Lint を実行。

## コーディング規約と命名
- Java 11 を前提。インデントは 4 スペース、タブ禁止。
- クラスは PascalCase、メソッド/変数は lowerCamelCase。
- リソース名は lower_snake_case。例: `activity_main.xml`, `@+id/btn_increment`。
- 画面実装は View System を採用する。
- レイアウトは LinearLayout を基本とし、ConstraintLayout は避ける。
- ロジックも含めて処理は Activity にまとめる。
- EdgeToEdge を有効にし、システムバーのインセットを処理する。
- UI はダーク配色固定（ライト/ダーク切り替えなし）。

## テスト方針
- 単体テストは `app/src/test/java/`、計測テストは `app/src/androidTest/java/`。
- 新規修正には再現テストを追加し、`*Test.java` 命名を維持。
- UI 変更は Espresso の追加を検討。

## コミット & PR
- `.git` が無いため既存ルールを確認できない。短い命令形 + 変更内容（例: "Add settings screen"）を推奨。
- PR には目的、変更点、実行したコマンドを記載。UI 変更はスクリーンショットを添付。

## 設定・環境
- `local.properties` は SDK パス用。個人環境の値は共有しない。
- 依存バージョン変更は影響範囲を記録し、`gradle/libs.versions.toml` を更新。

## ドキュメント
- `README.md` : プロジェクト概要とセットアップ手順。
- `SPEC.md` : アプリ仕様書。画面一覧と技術仕様を記載。
- `TUTORIAL.md` : 授業用テキスト。Android アプリ開発入門の解説と演習問題。
