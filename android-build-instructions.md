# Building Android APK for Retro Calculator

This guide will help you convert the Retro Calculator web app into an Android APK file.

## Prerequisites

1. **Install Android Studio**
   - Download from: https://developer.android.com/studio
   - Install with Android SDK (API level 33 or higher)
   - Accept all license agreements
   - Install Android SDK Build-Tools and Platform Tools

2. **Install Java Development Kit (JDK)**
   - Install JDK 17 or higher
   - Set JAVA_HOME environment variable

3. **Setup Android Environment Variables**
   ```bash
   export ANDROID_HOME=$HOME/Android/Sdk
   export PATH=$PATH:$ANDROID_HOME/emulator
   export PATH=$PATH:$ANDROID_HOME/platform-tools
   export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin
   ```

4. **Download Project Files**
   - Download all project files to your local machine
   - Ensure you have Node.js 18+ installed

## Build Steps

### Step 1: Prepare the Web Build
```bash
npm run build:mobile
```

### Step 2: Initialize Capacitor Android Platform
```bash
npx cap add android
```

### Step 3: Sync Web Assets to Android
```bash
npx cap sync
```

### Step 4: Open in Android Studio
```bash
npx cap open android
```

### Step 5: Build APK in Android Studio

1. **In Android Studio:**
   - Wait for Gradle sync to complete
   - Go to `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`
   - Wait for build to complete

2. **Locate APK:**
   - APK will be in: `android/app/build/outputs/apk/debug/app-debug.apk`
   - For release: `android/app/build/outputs/apk/release/app-release.apk`

### Step 6: Generate Signed APK for Play Store

1. **Create Keystore:**
   ```bash
   keytool -genkey -v -keystore retro-calculator-release-key.keystore -alias retro-calculator -keyalg RSA -keysize 2048 -validity 10000
   ```

2. **In Android Studio:**
   - Go to `Build` → `Generate Signed Bundle / APK`
   - Choose `APK`
   - Select your keystore file
   - Enter keystore password and key alias
   - Choose `release` build variant
   - Click `Finish`

3. **Upload to Play Store:**
   - Use the generated APK in `android/app/build/outputs/apk/release/`

## App Features in APK

✅ **Offline Functionality**
- Complete calculator works without internet
- Service worker caches all resources
- Local calculations and storage

✅ **Native Android Features**
- Status bar integration
- Haptic feedback
- Keyboard optimization
- Splash screen
- App icon and metadata

✅ **Retro Design**
- Full retro typography and effects
- Sound effects using Web Audio API
- Robot animations and interactions
- Responsive mobile layout

## Troubleshooting

**Gradle Build Failed:**
- Ensure Android SDK is properly installed
- Check Java version (JDK 17+ required)
- Clear Gradle cache: `./gradlew clean`

**APK Size Too Large:**
- Run `npm run build:mobile` to optimize bundle
- Enable ProGuard in `android/app/build.gradle`

**App Crashes on Device:**
- Check device logs: `adb logcat`
- Ensure all permissions are granted
- Test on different Android versions

## File Structure After Setup

```
project-root/
├── android/                 # Native Android project
│   ├── app/
│   │   ├── src/main/
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── res/
│   │   │   └── java/
│   │   └── build.gradle
│   └── build.gradle
├── capacitor.config.ts      # Capacitor configuration
├── dist/                    # Web build output
└── package.json            # Updated with mobile scripts
```

This setup creates a native Android app that:
- Runs your web app in a WebView
- Provides native Android integration
- Works offline completely
- Can be distributed via Google Play Store