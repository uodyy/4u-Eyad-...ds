# Quick Start Guide

## 🚀 Fastest Way to Build

### Windows
```batch
cd discord_checker
gradlew.bat assembleRelease
```

### Mac/Linux
```bash
cd discord_checker
chmod +x build_apk.sh
./build_apk.sh
```

## ⚡ What Gets Generated

After build completes:
- **APK File**: `DiscordChecker.apk`
- **Install**: Copy to phone → Install
- **Use**: Launch app → Check Discord usernames

## 🎯 First Launch

1. Open Discord Checker
2. Select **"Generate"** mode
3. Configure:
   - Length: 4 characters
   - Enable: Letters, Numbers
   - Count: 50
4. Tap **"Start"**
5. Watch real-time results!

## 📊 Understanding Results

| Status | Meaning | Color |
|--------|---------|-------|
| Available | Ready to register | 🟢 Green |
| Taken | Already registered | 🔴 Red |
| Invalid | Violates Discord rules | 🟡 Yellow |
| Uncertain | Connection error | ⚪ Gray |

## 🔑 Key Features

✨ **Instant Verification** - Real Discord API  
📱 **Local Storage** - Never re-check same username  
⚙️ **Smart Generator** - Customizable 3-32 characters  
📈 **Live Stats** - Track results in real-time  
💾 **Auto-Save** - Results persist after close  

## ⏱️ Performance

- **Check Speed**: ~500ms per username (default)
- **Batch Size**: Up to 1000 per session
- **Database**: Unlimited local storage
- **Network**: Minimal bandwidth usage

## 🛡️ Safety

✅ Read-only operations only  
✅ No credentials needed  
✅ No account access  
✅ Respects Discord ToS  
✅ Anonymous checking  

## 🐛 Quick Fixes

**APK won't install?**
- Enable: Settings → Unknown Sources → On
- Check: Android version 8.0+

**App crashes on launch?**
- Clear app data
- Reinstall APK

**No results appearing?**
- Check internet connection
- Increase delay (Settings)
- Try different username length

**Too many "Uncertain" results?**
- Discord rate limiting
- Wait a few minutes
- Restart app

## 📧 Support

Need help? Check README.md for detailed documentation.

---

**Happy checking! 🎉**
