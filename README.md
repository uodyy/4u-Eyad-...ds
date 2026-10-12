# Discord Username Checker - APK Build Guide

## ✨ Features

✅ **Real Discord API Verification** - Uses Discord's official signup endpoint
✅ **Smart Username Generator** - 3-5 letter combinations with custom rules
✅ **Persistent Database** - Stores all checked usernames (no re-checking)
✅ **Modern UI** - Dark theme with live statistics
✅ **Batch Processing** - Check multiple usernames with configurable delays
✅ **Status Classification** - Available, Taken, Invalid, Uncertain

## 📋 Requirements

- Android Studio (latest)
- Java JDK 11+
- Gradle 8.0+
- Android SDK 34 (API Level 34)

## 🏗️ Build Instructions

### Option 1: Android Studio (Recommended)

1. Open Android Studio
2. Click "Open" and select this folder
3. Wait for Gradle sync to complete
4. Go to **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
5. APK will be generated in `app/build/outputs/apk/release/`

### Option 2: Command Line

```bash
# Navigate to project root
cd discord_checker

# Build APK
./gradlew assembleRelease

# APK location: app/build/outputs/apk/release/app-release.apk
```

### Option 3: Online Build Service (Free)

Use **Codemagic**, **AppMaster**, or **Bitrise**:
1. Push this repository to GitHub
2. Connect to build service
3. Select Android build
4. Download APK

## 📱 Installation

1. Download APK (enable Unknown Sources if needed)
2. Tap to install
3. Grant Internet permission
4. Launch app

## 🔧 How It Works

### Username Checking

- **Endpoint**: `https://discord.com/api/v9/unique-username/username-attempt-unauthed`
- **Method**: POST (tokenless, read-only)
- **Response**: Available/Taken/Invalid status
- **Rate Limit**: ~500ms delay between checks

### Generator Modes

**Generate Mode**:
- Length: 3-32 characters
- Character types: Letters, Numbers, Dots (.), Underscores (_)
- Auto-validation before checking
- Prevents duplicates

**Manual Mode**:
- Enter usernames line-by-line
- Each checked individually
- Instant results

### Database

- Stored locally on device
- Persists after app close
- Prevents duplicate checks
- Fast lookups

## ⚙️ Settings

- **Username Length**: Adjustable 3-32
- **Character Types**: Toggle letters/numbers/dots/underscores
- **Batch Size**: Configure how many to generate
- **Delay Between Checks**: Prevents rate limiting (default 500ms)

## 🔒 Security & Compliance

✅ Read-only verification (no account creation)
✅ No credentials stored
✅ No Discord ToS violations
✅ Anonymous, tokenless
✅ Respects Discord's rate limits
✅ No harmful actions

## 📊 Statistics

- **Available**: Usernames ready to claim
- **Taken**: Already registered
- **Invalid**: Violate Discord naming rules
- **Uncertain**: Connection/API errors

Results are saved to device database for future reference.

## 🐛 Troubleshooting

**"Build failed"**
- Ensure Android SDK 34 is installed
- Check Java version: `java -version`
- Sync Gradle: File → Sync Now

**"Network error"**
- Check internet connection
- Discord API might be temporarily down
- Try again in a few moments

**"Rate limited"**
- Increase delay between checks in settings
- App automatically handles this

## 📝 File Structure

```
.
├── app/
│   ├── src/main/
│   │   ├── java/com/checker/discord/
│   │   │   ├── data/         (Database & Repository)
│   │   │   ├── network/      (Discord API)
│   │   │   ├── ui/           (UI & ViewModel)
│   │   │   └── util/         (Generator & Validator)
│   │   └── res/              (Layouts & Resources)
│   └── build.gradle
├── build.gradle
└── settings.gradle
```

## 📦 Dependencies

- **Room**: Local database
- **Retrofit + OkHttp**: HTTP client
- **Gson**: JSON parsing
- **Coroutines**: Async operations
- **LiveData & ViewModel**: UI patterns
- **Material Design**: UI components

All dependencies are specified in `app/build.gradle`.

## 🚀 Usage Example

1. **Launch App**
2. Select **"Generate"** mode
3. Set length to **4**
4. Enable **"Letters"** only
5. Set count to **20**
6. Tap **"Start"**
7. Watch stats update in real-time
8. Results saved automatically

## 📄 License

This tool is for educational purposes. Respect Discord's ToS and terms of service.

## 🤝 Support

If you encounter issues:
1. Check Android version (26+)
2. Verify internet connection
3. Check app permissions
4. Review build logs for details

---

**Built with**: Kotlin, Android Architecture Components, Retrofit
**API**: Discord (unauthenticated signup verification endpoint)
**Status**: ✅ Production Ready
