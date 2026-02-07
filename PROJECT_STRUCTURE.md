# VoxSwipe Project Structure

This document outlines the complete project structure for VoxSwipe, a voice-controlled social media scrolling application built with Jetpack Compose and Clean Architecture.

## Architecture Overview

The project follows **Clean Architecture** principles with clear separation between layers:

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│  (ViewModels, UI State Management)      │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│            UI Layer                     │
│  (Compose Screens, Components, Theme)   │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│          Domain Layer                   │
│  (Business Logic, Use Cases, Models)    │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│           Data Layer                    │
│  (Repositories, Data Sources)           │
└─────────────────────────────────────────┘
```

## Directory Structure

```
app/src/main/java/com/dormiwww/echodo/
│
├── data/                           # Data layer
│   ├── local/                      # Local data sources
│   │   ├── PreferencesDataSource.kt
│   │   └── CommandsDatabase.kt
│   ├── remote/                     # Remote data sources (if any)
│   └── repository/                 # Repository implementations
│       ├── VoiceRepositoryImpl.kt
│       └── PermissionRepositoryImpl.kt
│
├── domain/                         # Domain layer (business logic)
│   ├── model/                      # Domain models
│   │   ├── Command.kt
│   │   └── AppTarget.kt
│   ├── repository/                 # Repository interfaces
│   │   ├── VoiceRepository.kt
│   │   └── PermissionRepository.kt
│   └── usecase/                    # Use cases
│       ├── StartVoiceListeningUseCase.kt
│       ├── ProcessCommandUseCase.kt
│       └── CheckPermissionsUseCase.kt
│
├── presentation/                   # Presentation layer (ViewModels)
│   ├── home/
│   │   └── HomeViewModel.kt
│   ├── settings/
│   │   └── SettingsViewModel.kt
│   ├── permissions/
│   │   └── PermissionsViewModel.kt
│   ├── onboarding/
│   │   └── OnboardingViewModel.kt
│   └── commands/
│       └── CommandsListViewModel.kt
│
├── ui/                            # UI layer (Compose)
│   ├── theme/                     # Material Design 3 theme
│   │   ├── Color.kt              # Color palette
│   │   ├── Type.kt               # Typography system
│   │   ├── Theme.kt              # Main theme composable
│   │   ├── Shape.kt              # Shape system
│   │   └── Dimensions.kt         # Spacing and sizing tokens
│   │
│   ├── screens/                   # Screen-level composables
│   │   ├── home/
│   │   │   ├── HomeScreen.kt
│   │   │   └── HomeUiState.kt
│   │   ├── settings/
│   │   │   ├── SettingsScreen.kt
│   │   │   └── SettingsUiState.kt
│   │   ├── permissions/
│   │   │   ├── PermissionsScreen.kt
│   │   │   └── PermissionsUiState.kt
│   │   ├── onboarding/
│   │   │   ├── OnboardingScreen.kt
│   │   │   └── OnboardingUiState.kt
│   │   └── commands/
│   │       ├── CommandsListScreen.kt
│   │       └── CommandsListUiState.kt
│   │
│   ├── components/                # Reusable UI components
│   │   ├── VoiceButton.kt
│   │   ├── StatusIndicator.kt
│   │   ├── PermissionCard.kt
│   │   ├── AppSelector.kt
│   │   ├── CommandItem.kt
│   │   ├── EmptyState.kt
│   │   ├── ErrorState.kt
│   │   └── LoadingState.kt
│   │
│   ├── navigation/                # Navigation setup
│   │   ├── NavGraph.kt
│   │   └── Routes.kt
│   │
│   └── animation/                 # Custom animations
│       ├── PulseAnimation.kt
│       ├── ShakeAnimation.kt
│       └── ShimmerAnimation.kt
│
├── service/                       # Android services
│   ├── VoxSwipeAccessibilityService.kt
│   └── VoiceRecognitionService.kt
│
├── di/                           # Dependency injection
│   ├── AppModule.kt
│   ├── DataModule.kt
│   └── DomainModule.kt
│
├── util/                         # Utility classes
│   ├── Constants.kt
│   └── Extensions.kt
│
└── MainActivity.kt               # Main entry point
```

## Layer Responsibilities

### 1. UI Layer (`ui/`)

**Purpose**: Displays data to the user and handles user interactions.

**Components**:
- **Screens**: Full-screen composables representing app destinations
- **Components**: Reusable UI elements
- **Theme**: Material Design 3 theming (colors, typography, shapes)
- **Navigation**: App navigation structure
- **Animation**: Custom animation implementations

**Key Principles**:
- Screens are stateless and receive state via parameters
- All state is hoisted to ViewModels
- Components accept `modifier` as last parameter
- Every public composable has `@Preview` functions
- Accessibility is built into every interactive element

### 2. Presentation Layer (`presentation/`)

**Purpose**: Manages UI state and handles user events.

**Components**:
- ViewModels for each screen
- UI state data classes
- Event handlers

**Key Principles**:
- ViewModels expose `StateFlow<UiState>` for screens to observe
- No direct Android dependencies (except Android ViewModel)
- Uses domain layer use cases for business logic
- Handles error states and loading states

### 3. Domain Layer (`domain/`)

**Purpose**: Contains business logic and domain models.

**Components**:
- **Models**: Business entities (Command, AppTarget, etc.)
- **Repository Interfaces**: Contracts for data operations
- **Use Cases**: Single-responsibility business logic operations

**Key Principles**:
- Pure Kotlin (no Android dependencies)
- Repository interfaces define contracts
- Use cases encapsulate single business operations
- Models represent business concepts

### 4. Data Layer (`data/`)

**Purpose**: Handles data operations and external data sources.

**Components**:
- **Repository Implementations**: Concrete implementations of domain repositories
- **Local Data Sources**: SharedPreferences, Room Database
- **Remote Data Sources**: Network APIs (if needed)

**Key Principles**:
- Repositories are the single source of truth
- Data sources handle specific storage mechanisms
- Exposes domain models, not data models
- Uses Kotlin Coroutines for async operations

### 5. Service Layer (`service/`)

**Purpose**: Android background services.

**Components**:
- Accessibility Service for app control
- Voice Recognition Service for speech-to-text

### 6. Dependency Injection (`di/`)

**Purpose**: Provides dependencies to all layers.

**Note**: DI framework (Hilt/Koin) not yet configured. Modules are placeholders.

## Key Design Patterns

### 1. State Hoisting
All screen state is managed by ViewModels and passed down to composables.

```kotlin
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onEvent: (HomeEvent) -> Unit
)
```

### 2. Sealed Interfaces for UI State
Type-safe state representation with exhaustive when expressions.

```kotlin
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Ready(...) : HomeUiState
    data class Error(...) : HomeUiState
}
```

### 3. Repository Pattern
Abstraction over data sources with domain-defined contracts.

```kotlin
interface VoiceRepository {
    fun startListening(): Flow<String>
    suspend fun stopListening()
}
```

### 4. Use Case Pattern
Single-responsibility operations encapsulating business logic.

```kotlin
class ProcessCommandUseCase(
    private val repository: VoiceRepository
) {
    suspend operator fun invoke(text: String): Command?
}
```

## Theme System

### Color Palette
- **Primary**: Deep violet/indigo for voice actions
- **Secondary**: Teal/cyan for status indicators
- **Tertiary**: Warm amber for warnings
- **Semantic Colors**: VoiceActive, PermissionGranted, AppTikTok, etc.

### Typography
- Material Design 3 type scale
- Display, Headline, Title, Body, Label styles
- Responsive sizing via LocalDensity

### Spacing System
- 4dp grid system
- Centralized in `Dimensions.kt`
- Semantic naming (spacing1, spacing2, etc.)

## Navigation Flow

```
Onboarding → Permissions → Home
                ↓           ↓
              Settings ← Commands
```

### Deep Links
- `echodo://permissions` - Permission setup
- `echodo://settings` - App settings

## Supported Apps

1. **TikTok** (`com.zhiliaoapp.musically`)
2. **Instagram** (`com.instagram.android`)
3. **YouTube** (`com.google.android.youtube`)

## Voice Commands

### Navigation
- Scroll down, Scroll up

### Interaction
- Like, Comment, Share

### Playback
- Pause, Resume

### System
- Open [app], Go back

## Permissions Required

1. **Accessibility Service** - Interact with target apps
2. **Overlay Permission** - Show voice control overlay
3. **Microphone** - Capture voice commands
4. **Notifications** - Show feedback (Android 13+)

## Testing Strategy

### Unit Tests (`test/`)
- ViewModels
- Use Cases
- Repositories
- Utility functions

### UI Tests (`androidTest/`)
- Screen navigation
- Component interactions
- Accessibility compliance

### Compose Tests
- Component rendering
- State changes
- User interactions

## Build Configuration

### Minimum SDK: 24 (Android 7.0)
### Target SDK: 35 (Android 15)
### Compile SDK: 35

### Key Dependencies
- Jetpack Compose BOM
- Material 3
- Navigation Compose
- ViewModel Compose
- Kotlin Coroutines
- Flow

## Development Guidelines

### Code Style
- Follow Kotlin coding conventions
- Use meaningful variable names
- Add KDoc comments to public APIs
- Keep functions small and focused

### Compose Best Practices
1. State hoisting
2. Stability annotations (`@Immutable`, `@Stable`)
3. Keys in lazy lists
4. Derived state with `derivedStateOf`
5. Side effects with proper keys
6. Previews for all composables

### Accessibility Requirements
- 48dp minimum touch targets
- Content descriptions on all interactive elements
- State descriptions for stateful components
- High contrast mode support
- Screen reader testing

### Performance Considerations
- Target 60fps minimum
- Use `graphicsLayer` for GPU acceleration
- Avoid unnecessary recompositions
- Profile with Layout Inspector
- Optimize list scrolling

## Next Steps

### Immediate (Foundation)
1. ✅ Set up project structure
2. ✅ Create theme system
3. ✅ Define navigation routes
4. ✅ Create placeholder screens
5. ✅ Create base components

### Short-term (Core Features)
1. Implement ViewModels
2. Implement repository interfaces
3. Add accessibility service
4. Add voice recognition
5. Implement animations

### Medium-term (Polish)
1. Add Lottie animations
2. Implement settings persistence
3. Add haptic feedback
4. Create adaptive layouts
5. Add localization

### Long-term (Enhancement)
1. Add more supported apps
2. Custom wake words
3. Voice training
4. Analytics
5. Cloud sync

## Resources

- [Material Design 3](https://m3.material.io/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Android Accessibility](https://developer.android.com/guide/topics/ui/accessibility)

---

**Project**: VoxSwipe
**Package**: `com.dormiwww.echodo`
**Last Updated**: 2026-02-07
