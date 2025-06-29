# Native Android Retro Calculator

This is a complete native Android application that replicates the exact functionality and design of the web-based retro calculator. Built with modern Android development practices using Jetpack Compose.

## Features

### 🤖 Animated Robot Character
- Fully animated robot with floating, scaling, and rotation effects
- Different animation states (idle, excited, confirming)
- Scanning eyes with color transitions
- Pulsing antenna and control panels

### 🎨 Retro Design System
- Custom color scheme matching the web app
- Retro typography with Orbitron, Russo One, and Share Tech Mono fonts
- Gradient backgrounds and glowing effects
- Animated background grid and floating particles

### 🧮 Calculator Functionality
- Step-by-step quantity and rate input
- Multiple unit support (kg, gram, quintal, ton, liter, ml, gallon, piece, dozen, box)
- Precise calculations with proper decimal formatting
- Real-time robot chat responses

### 📱 Native Android Features
- Haptic feedback on all interactions
- Material 3 design components
- Responsive layout for all screen sizes
- Native navigation with Jetpack Navigation Compose
- Optimized performance with Compose animations

## Project Structure

```
android-native/
├── app/
│   ├── src/main/
│   │   ├── java/com/retro/calculator/
│   │   │   ├── MainActivity.kt                    # Main activity
│   │   │   ├── ui/
│   │   │   │   ├── theme/                        # Theme and colors
│   │   │   │   ├── screens/                      # Screen composables
│   │   │   │   │   ├── landing/                  # Landing screen
│   │   │   │   │   ├── calculator/               # Calculator screen
│   │   │   │   │   └── components/               # Reusable components
│   │   │   │   └── components/                   # UI components
│   │   │   └── utils/                           # Utility classes
│   │   └── res/
│   │       ├── values/                          # Colors, strings, themes
│   │       ├── font/                            # Custom fonts
│   │       └── xml/                             # Configuration files
│   └── build.gradle                             # App dependencies
├── build.gradle                                 # Project configuration
└── settings.gradle                              # Project settings
```

## Setup Instructions

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or higher
- Android SDK 34
- Kotlin 1.9.22

### Installation Steps

1. **Download Fonts**
   - Download the following fonts from Google Fonts:
     - Orbitron (Regular, Bold, Black)
     - Russo One (Regular)
     - Share Tech Mono (Regular)
   - Place them in `app/src/main/res/font/` directory

2. **Open in Android Studio**
   ```bash
   # Open the android-native folder in Android Studio
   File -> Open -> Select android-native folder
   ```

3. **Sync Project**
   - Wait for Gradle sync to complete
   - Resolve any dependency issues

4. **Build and Run**
   ```bash
   # Build the project
   Build -> Make Project
   
   # Run on device/emulator
   Run -> Run 'app'
   ```

## Key Components

### 🎭 RobotCharacter.kt
Complete animated robot with:
- Floating animation
- Scale effects for different states
- 360° rotation for confirmations
- Color-changing eyes
- Pulsing antennas and control panels

### 🧮 CalculatorViewModel.kt
State management for:
- Multi-step calculation flow
- Input validation
- Result formatting
- Robot message updates

### 🎨 Theme System
- Custom color palette matching web app
- Typography system with retro fonts
- Material 3 integration
- Dark theme optimized

### 📱 Haptic Feedback
- Light, medium, heavy vibrations
- Success and error patterns
- Context-aware feedback

## Build Variants

### Debug Build
- Development optimized
- Debugging enabled
- Faster build times

### Release Build
- Production optimized
- ProGuard enabled
- Signed APK ready for distribution

## Dependencies

### Core Android
- Jetpack Compose BOM 2024.02.00
- Material 3
- Navigation Compose
- Lifecycle ViewModel Compose

### UI & Animation
- Compose Animation
- Accompanist System UI Controller
- Material Icons Extended

### Development
- Kotlin 1.9.22
- Android Gradle Plugin 8.2.0

## Performance Optimizations

- Lazy composition where possible
- Efficient animation handling
- Memory-conscious state management
- Optimized recomposition

## Testing

The app includes:
- Unit tests for ViewModel logic
- UI tests for Compose components
- Integration tests for navigation flow

## Distribution

### Debug APK
Generated in: `app/build/outputs/apk/debug/`

### Release APK
Generated in: `app/build/outputs/apk/release/`
Requires signing configuration for Play Store distribution.

## Customization

### Colors
Modify `ui/theme/Color.kt` to adjust the color scheme

### Animations
Adjust animation parameters in respective component files

### Robot Behavior
Customize robot responses in `CalculatorViewModel.kt`

This native Android app provides the complete retro calculator experience with enhanced performance, native features, and platform-specific optimizations.