# VoxSwipe Quick Start Guide

## Project Setup Complete

The VoxSwipe project structure has been successfully set up with a comprehensive Jetpack Compose architecture following Clean Architecture principles.

## What Has Been Created

### 1. Theme System (Material Design 3)
- `/app/src/main/java/com/dormiwww/echodo/ui/theme/`
  - **Color.kt**: Complete color palette with voice-specific semantic colors
  - **Type.kt**: Typography system with all MD3 text styles
  - **Theme.kt**: Main theme with dynamic colors, dark mode, OLED optimization
  - **Shape.kt**: Shape system with rounded corners
  - **Dimensions.kt**: Centralized spacing and sizing tokens

### 2. Navigation System
- `/app/src/main/java/com/dormiwww/echodo/ui/navigation/`
  - **Routes.kt**: Type-safe navigation routes
  - **NavGraph.kt**: Complete navigation graph with animations

### 3. Screen Scaffolding (5 Screens)
All screens have placeholder implementations with proper structure:

#### Home Screen
- `/app/src/main/java/com/dormiwww/echodo/ui/screens/home/`
  - HomeScreen.kt
  - HomeUiState.kt (with 5 states: Loading, Ready, Listening, CommandRecognized, Error)

#### Settings Screen
- `/app/src/main/java/com/dormiwww/echodo/ui/screens/settings/`
  - SettingsScreen.kt
  - SettingsUiState.kt (theme, voice, accessibility settings)

#### Permissions Screen
- `/app/src/main/java/com/dormiwww/echodo/ui/screens/permissions/`
  - PermissionsScreen.kt
  - PermissionsUiState.kt (4 permission types defined)

#### Onboarding Screen
- `/app/src/main/java/com/dormiwww/echodo/ui/screens/onboarding/`
  - OnboardingScreen.kt
  - OnboardingUiState.kt (5 onboarding pages defined)

#### Commands List Screen
- `/app/src/main/java/com/dormiwww/echodo/ui/screens/commands/`
  - CommandsListScreen.kt
  - CommandsListUiState.kt (9 predefined voice commands)

### 4. Reusable Components (5 Components)
- `/app/src/main/java/com/dormiwww/echodo/ui/components/`
  - **VoiceButton.kt**: Large mic button with multiple states
  - **StatusIndicator.kt**: Service status with animated dot
  - **PermissionCard.kt**: Permission item with status badge
  - **AppSelector.kt**: Multi-select chips for TikTok/Instagram/YouTube
  - **CommandItem.kt**: Voice command list item

### 5. Domain Layer
- `/app/src/main/java/com/dormiwww/echodo/domain/`
  - **model/Command.kt**: Voice command domain model
  - **model/AppTarget.kt**: Target app domain model
  - **repository/VoiceRepository.kt**: Voice operations interface
  - **repository/PermissionRepository.kt**: Permission management interface

### 6. Data Layer Structure
- `/app/src/main/java/com/dormiwww/echodo/data/`
  - local/ (for SharedPreferences, Room)
  - remote/ (for API calls if needed)
  - repository/ (for repository implementations)

### 7. Presentation Layer Structure
- `/app/src/main/java/com/dormiwww/echodo/presentation/`
  - home/, settings/, permissions/, onboarding/, commands/
  - Ready for ViewModel implementations

### 8. Utilities
- `/app/src/main/java/com/dormiwww/echodo/util/`
  - **Constants.kt**: App-wide constants
  - **Extensions.kt**: Kotlin extension functions

### 9. Documentation
- **PROJECT_STRUCTURE.md**: Comprehensive architecture documentation
- **QUICKSTART.md**: This file

### 10. Updated MainActivity
- Integrated with navigation system
- Theme applied
- Edge-to-edge enabled

## Project Statistics

- **Total Kotlin files created**: 28
- **Total directories created**: 27
- **Lines of documented code**: ~2,500+
- **Preview functions**: 20+
- **Ready-to-use components**: 5
- **Screen templates**: 5
- **Domain models**: 2
- **Repository interfaces**: 2

## Next Steps for Development

### Phase 1: ViewModels (Presentation Layer)
1. Create `HomeViewModel.kt`
2. Create `SettingsViewModel.kt`
3. Create `PermissionsViewModel.kt`
4. Create `OnboardingViewModel.kt`
5. Create `CommandsListViewModel.kt`

### Phase 2: Repository Implementations (Data Layer)
1. Create `VoiceRepositoryImpl.kt`
2. Create `PermissionRepositoryImpl.kt`
3. Create `PreferencesDataSource.kt`
4. Implement SharedPreferences wrapper

### Phase 3: Services
1. Create `VoxSwipeAccessibilityService.kt`
2. Create `VoiceRecognitionService.kt`
3. Update AndroidManifest.xml with service declarations

### Phase 4: Dependency Injection
1. Add Hilt/Koin dependency
2. Create DI modules
3. Inject ViewModels and repositories

### Phase 5: Complete UI Components
1. Add animations to VoiceButton (pulsing rings)
2. Add Lottie animations to OnboardingScreen
3. Implement BottomSheet for HomeScreen
4. Add SearchBar to SettingsScreen
5. Complete CommandsListScreen with filters

### Phase 6: Testing
1. Write ViewModel unit tests
2. Write repository tests
3. Write Compose UI tests
4. Accessibility testing with TalkBack

## Running the Project

### Prerequisites
- Android Studio Hedgehog or later
- Android SDK 35
- Minimum Android device/emulator: API 24 (Android 7.0)

### Build and Run
```bash
# From project root
./gradlew clean assembleDebug

# Run on connected device
./gradlew installDebug
```

### View Previews
Open any screen or component file in Android Studio and use the Split/Design view to see Compose previews.

## Design System Quick Reference

### Colors
```kotlin
MaterialTheme.colorScheme.primary          // Voice actions
MaterialTheme.colorScheme.secondary        // Status indicators
MaterialTheme.colorScheme.tertiary         // Warnings
VoiceActive                                // Green for active state
VoiceInactive                              // Gray for inactive
PermissionGranted / Denied / NotRequested  // Permission states
AppTikTok / AppInstagram / AppYouTube      // Brand colors
```

### Spacing
```kotlin
Dimensions.spacing1    // 4dp
Dimensions.spacing2    // 8dp
Dimensions.spacing4    // 16dp (most common)
Dimensions.spacing6    // 24dp
Dimensions.minTouchTarget  // 48dp
```

### Typography
```kotlin
MaterialTheme.typography.displayLarge      // Hero text
MaterialTheme.typography.headlineSmall     // Screen titles
MaterialTheme.typography.titleMedium       // Section headers
MaterialTheme.typography.bodyLarge         // Main content
MaterialTheme.typography.labelMedium       // Buttons, chips
```

## Navigation Routes

```kotlin
Routes.Onboarding.route     // First launch
Routes.Permissions.route    // Permission setup
Routes.Home.route          // Main screen
Routes.Settings.route      // Configuration
Routes.Commands.route      // Voice commands list
```

## Architecture Pattern

```
User Interaction
       ↓
Screen (Compose) → observes → ViewModel → exposes → StateFlow<UiState>
       ↑                            ↓
       └────────── renders ─────────┘
                                     ↓
                            calls Use Case
                                     ↓
                            accesses Repository
                                     ↓
                            queries Data Source
```

## Voice Commands Reference

### Navigation
- "Scroll down" / "Next" / "Swipe up"
- "Scroll up" / "Previous" / "Go back" / "Swipe down"

### Interaction
- "Like" / "Heart" / "Love it"
- "Comment" / "Comments" / "Show comments"
- "Share" / "Send"

### Playback
- "Pause" / "Stop"
- "Resume" / "Play" / "Continue"

### System
- "Open TikTok" / "Open Instagram" / "Open YouTube"
- "Go back" / "Back"

## Supported Apps

| App       | Package Name                    | Status      |
|-----------|---------------------------------|-------------|
| TikTok    | com.zhiliaoapp.musically       | Defined     |
| Instagram | com.instagram.android          | Defined     |
| YouTube   | com.google.android.youtube     | Defined     |

## Permissions Flow

1. **Accessibility Service** - Required for app interaction
2. **Overlay Permission** - Required for voice control overlay
3. **Microphone** - Required for voice input
4. **Notifications** - Optional, for feedback (Android 13+)

## File Naming Conventions

- Screens: `[Feature]Screen.kt` (e.g., HomeScreen.kt)
- Components: `[ComponentName].kt` (e.g., VoiceButton.kt)
- State: `[Feature]UiState.kt` (e.g., HomeUiState.kt)
- ViewModels: `[Feature]ViewModel.kt` (e.g., HomeViewModel.kt)
- Repositories: `[Domain]Repository.kt` interface, `[Domain]RepositoryImpl.kt` implementation

## Accessibility Features Built-In

- Minimum 48dp touch targets
- Content descriptions on all interactive elements
- State descriptions for stateful components
- Semantic grouping where appropriate
- High contrast mode support (planned)
- Respects system animator duration scale

## Resources

- Project Structure: `/PROJECT_STRUCTURE.md`
- Android Manifest: `/app/src/main/AndroidManifest.xml`
- Gradle Build: `/app/build.gradle.kts`
- Theme System: `/app/src/main/java/com/dormiwww/echodo/ui/theme/`

## Need Help?

Refer to:
1. `PROJECT_STRUCTURE.md` for detailed architecture documentation
2. KDoc comments in each file for component-specific details
3. Preview functions for visual examples
4. Material Design 3 guidelines: https://m3.material.io/

---

**Status**: Foundation Complete - Ready for Feature Implementation
**Created**: 2026-02-07
**Package**: com.dormiwww.echodo
**Min SDK**: 24 | **Target SDK**: 35
