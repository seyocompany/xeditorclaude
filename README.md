# XEditor

Phase 1, Step 1 of ROADMAP.md: **Project foundation** — now with a
cloud build pipeline, so a local Android Studio install isn't required.

## Build without Android Studio (recommended for you)
1. On github.com, create a new repository — call it `xeditor`, public or
   private, doesn't matter.
2. Open it, click **Add file → Upload files**, then drag the *entire
   unzipped folder* from this download into the upload box. Modern
   browsers (Chrome/Edge) preserve the folder structure when you drag a
   folder in. Commit the upload.
3. That commit triggers `.github/workflows/build.yml` automatically — go
   to the **Actions** tab and watch it run (a few minutes).
4. When it finishes with a green checkmark, click into the run, scroll
   down to **Artifacts**, and download `xeditor-debug-apk`. Unzip it —
   inside is `app-debug.apk`.
5. Get that APK onto your Android phone (email it to yourself, Google
   Drive, USB cable — anything works) and tap it to install. Your phone
   will ask to allow "install from unknown sources" for whichever app
   you used to open the file — allow it just for that app.

From here on, every step I build gets pushed the same way (or I can just
hand you updated files to re-upload), and Actions rebuilds the APK for
you automatically. No SDK, no IDE, nothing installed on your laptop.

## If you ever do get access to a machine that runs Android Studio
1. File → Open → select this folder.
2. Let it generate the Gradle wrapper jar if prompted (this sandbox can't
   produce that binary file, so it isn't included — Android Studio or
   the CI workflow both generate it automatically on first run).
3. Let Gradle sync, accept any version-update prompts — see the note at
   the top of `gradle/libs.versions.toml`.
4. Run on a device/emulator on Android 8.0 (API 26) or higher.

## What's here
A single `:app` module using Clean Architecture-style packages (not
separate Gradle modules, to keep things simple at this stage): `core`,
`ui`, `navigation`, `editor`, `media`, `export`, `settings`, `domain`,
`data`, `utils`. Hilt is wired up app-wide. Compose, Room, Media3, and
WorkManager are declared as dependencies, ready for the features that
need them.

## Architecture note: FFmpeg
ARCHITECTURE.md lists FFmpeg, which most Android projects pull in via
**FFmpegKit** — but FFmpegKit was retired in January 2025 and its
binaries have been pulled from Maven, so it's not a safe choice for a
new project. The plan going forward: use **Media3 Transformer + Effects**
(actively maintained by Google, GPU-accelerated, built specifically for
trimming, speed changes, overlays, transitions, and color effects) as
the primary editing engine, and only reach for a self-bundled FFmpeg
(via NDK) for anything Media3 genuinely can't do. This gets revisited
concretely in the Export Engine step.

## Next roadmap step
Phase 1, Step 2: Navigation.
