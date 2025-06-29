# 🚀 Android Studio Setup Guide

## 📁 **Project Structure**
```
android-native/                    # ← Open THIS folder in Android Studio
├── app/
│   ├── src/main/
│   │   ├── java/com/retro/calculator/
│   │   │   ├── MainActivity.kt
│   │   │   ├── ui/
│   │   │   └── utils/
│   │   ├── res/
│   │   │   ├── values/
│   │   │   ├── font/
│   │   │   └── mipmap-*/
│   │   └── AndroidManifest.xml
│   ├── build.gradle              # App configuration
│   └── proguard-rules.pro
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── build.gradle                  # Project configuration
├── settings.gradle
├── gradle.properties
├── gradlew                       # Unix Gradle wrapper
├── gradlew.bat                   # Windows Gradle wrapper
└── local.properties              # SDK path (auto-generated)
```

## 🔧 **Setup Steps**

### **1. Download Fonts (Required)**
Download these fonts and place in `app/src/main/res/font/`:
- **Orbitron**: Regular, Bold, Black → `orbitron_regular.ttf`, `orbitron_bold.ttf`, `orbitron_black.ttf`
- **Russo One**: Regular → `russo_one_regular.ttf`
- **Share Tech Mono**: Regular → `share_tech_mono_regular.ttf`

**Download from**: https://fonts.google.com/

### **2. Open in Android Studio**
```bash
# Method 1: File Menu
File → Open → Select "android-native" folder

# Method 2: Command Line
cd android-native
studio .
```

### **3. First-Time Setup**
1. **Accept Gradle Sync** (2-3 minutes)
2. **Install SDK 36** if prompted
3. **Accept licenses** if prompted
4. **Wait for indexing** to complete

### **4. Build APK**
```bash
# In Android Studio
Build → Build Bundle(s) / APK(s) → Build APK(s)

# Or use terminal
./gradlew assembleDebug
```

### **5. Find Your APK**
```
android-native/app/build/outputs/apk/debug/app-debug.apk
```

## ⚙️ **Configuration Details**

### **Latest Versions Used**
- **Gradle**: 8.11.0 ✅
- **Android Gradle Plugin**: 8.11.0 ✅
- **Kotlin**: 2.0.21 ✅
- **Compile SDK**: 36 ✅
- **Target SDK**: 36 ✅
- **Min SDK**: 24 (Android 7.0)
- **JDK**: 21 ✅

### **App Features**
- **Package**: com.retro.calculator
- **Name**: Retro Calculator
- **Size**: ~12-15MB
- **Offline**: 100% functional
- **Animations**: Native 60fps
- **Haptics**: Android vibration
- **Material 3**: Latest design

## 🎯 **Quick Commands**

```bash
# Clean build
./gradlew clean

# Debug APK
./gradlew assembleDebug

# Release APK (signed)
./gradlew assembleRelease

# Install on device
./gradlew installDebug

# Check dependencies
./gradlew dependencies
```

## 🔍 **Troubleshooting**

### **Gradle Sync Failed**
```bash
./gradlew clean
./gradlew build --refresh-dependencies
```

### **SDK Not Found**
- Android Studio → SDK Manager → Install API 36

### **JDK Issues**
- Settings → Build Tools → Gradle → Gradle JDK → Select JDK 21

### **Font Issues**
- Ensure all 5 font files are in `app/src/main/res/font/`
- File names must match exactly (lowercase, underscores)

## ✅ **Success Indicators**

You'll know it's working when:
- ✅ Gradle sync completes without errors
- ✅ No red underlines in code
- ✅ Build succeeds in 2-3 minutes
- ✅ APK file appears in outputs folder
- ✅ App installs and runs on device/emulator

## 📱 **Testing**

1. **Install APK** on Android device
2. **Test offline** (airplane mode)
3. **Check animations** (robot movements)
4. **Test calculations** (all units)
5. **Verify haptics** (button vibrations)

Your Android project is ready for Android Studio! 🎉