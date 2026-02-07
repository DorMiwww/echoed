#  Architecture Diagram

## Layer Dependency Flow

```
┌─────────────────────────────────────────────────────────────────────┐
│                            UI LAYER                                  │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │                         Screens                               │  │
│  │  ┌───────────┐ ┌───────────┐ ┌───────────┐ ┌───────────┐   │  │
│  │  │   Home    │ │ Settings  │ │Permissions│ │Onboarding │   │  │
│  │  │  Screen   │ │  Screen   │ │  Screen   │ │  Screen   │   │  │
│  │  └───────────┘ └───────────┘ └───────────┘ └───────────┘   │  │
│  │                                                               │  │
│  │  ┌────────────────────────────────────────────────────────┐ │  │
│  │  │               Reusable Components                       │ │  │
│  │  │  VoiceButton | StatusIndicator | PermissionCard        │ │  │
│  │  │  AppSelector | CommandItem                             │ │  │
│  │  └────────────────────────────────────────────────────────┘ │  │
│  │                                                               │  │
│  │  ┌────────────────────────────────────────────────────────┐ │  │
│  │  │                 Theme System                            │ │  │
│  │  │  Color | Typography | Shape | Dimensions               │ │  │
│  │  └────────────────────────────────────────────────────────┘ │  │
│  │                                                               │  │
│  │  ┌────────────────────────────────────────────────────────┐ │  │
│  │  │                  Navigation                             │ │  │
│  │  │  Routes | NavGraph | Transitions                       │ │  │
│  │  └────────────────────────────────────────────────────────┘ │  │
│  └──────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
                                   ↕
┌─────────────────────────────────────────────────────────────────────┐
│                      PRESENTATION LAYER                              │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │                       ViewModels                              │  │
│  │  ┌───────────┐ ┌───────────┐ ┌───────────┐ ┌───────────┐   │  │
│  │  │   Home    │ │ Settings  │ │Permissions│ │Onboarding │   │  │
│  │  │ ViewModel │ │ ViewModel │ │ ViewModel │ │ ViewModel │   │  │
│  │  └─────┬─────┘ └─────┬─────┘ └─────┬─────┘ └─────┬─────┘   │  │
│  │        │             │              │              │          │  │
│  │        └─────────────┴──────────────┴──────────────┘          │  │
│  │                           │                                    │  │
│  │                   Manages UI State                             │  │
│  │                   Handles User Events                          │  │
│  │                   Exposes StateFlow                            │  │
│  └──────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
                                   ↕
┌─────────────────────────────────────────────────────────────────────┐
│                        DOMAIN LAYER                                  │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │                       Use Cases                               │  │
│  │  StartVoiceListening | ProcessCommand | CheckPermissions     │  │
│  └──────────────────────────────────────────────────────────────┘  │
│                                   ↕                                  │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │                  Repository Interfaces                        │  │
│  │  VoiceRepository | PermissionRepository | SettingsRepository │  │
│  └──────────────────────────────────────────────────────────────┘  │
│                                   ↕                                  │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │                     Domain Models                             │  │
│  │  Command | AppTarget | CommandAction | CommandCategory       │  │
│  └──────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
                                   ↕
┌─────────────────────────────────────────────────────────────────────┐
│                         DATA LAYER                                   │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │                Repository Implementations                      │  │
│  │  VoiceRepositoryImpl | PermissionRepositoryImpl               │  │
│  └──────────────────────────────────────────────────────────────┘  │
│                                   ↕                                  │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │                    Data Sources                               │  │
│  │  ┌────────────────┐        ┌─────────────────┐               │  │
│  │  │     Local      │        │     Remote      │               │  │
│  │  │ SharedPrefs    │        │   (Future)      │               │  │
│  │  │ Room Database  │        │   APIs          │               │  │
│  │  └────────────────┘        └─────────────────┘               │  │
│  └──────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
                                   ↕
┌─────────────────────────────────────────────────────────────────────┐
│                      SERVICES & SYSTEM                               │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │  VoxSwipeAccessibilityService | VoiceRecognitionService      │  │
│  └──────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

## Screen Navigation Flow

```
┌─────────────────┐
│   App Launch    │
└────────┬────────┘
         │
         ↓
    ┌────────────────┐      ┌──────────────────────┐
    │  Onboarding    │ Yes  │  Check: First Time   │
    │    Screen      │←─────┤  User?               │
    └────────┬────────┘      └──────────┬───────────┘
         │                              │ No
         │ Complete                     │
         ↓                              │
    ┌────────────────┐                 │
    │  Permissions   │←────────────────┘
    │    Screen      │
    └────────┬────────┘
         │
         │ All Granted
         ↓
    ┌────────────────┐
    │  Home Screen   │◄─────┐
    │  (Main UI)     │      │
    └────┬───┬───────┘      │
         │   │               │
    ┌────┘   └────┐         │
    ↓             ↓          │
┌────────┐   ┌──────────┐   │
│Settings│   │ Commands │   │
│ Screen │   │  Screen  │   │
└───┬────┘   └────┬─────┘   │
    │             │          │
    │             │          │
    └─────────────┴──────────┘
       (Back Navigation)
```

## State Management Pattern

```
┌──────────────────────────────────────────────────────────┐
│                      Screen (Compose)                     │
│                                                           │
│  ┌────────────────────────────────────────────────────┐  │
│  │  @Composable                                       │  │
│  │  fun HomeScreen(                                   │  │
│  │      uiState: HomeUiState,                         │  │
│  │      onEvent: (HomeEvent) -> Unit                  │  │
│  │  )                                                 │  │
│  └────────────────────────────────────────────────────┘  │
│              ↑ observes                  │ triggers       │
└──────────────┼──────────────────────────┼───────────────┘
               │                           │
               │                           ↓
┌──────────────┴──────────────────────────┴───────────────┐
│                      ViewModel                            │
│                                                           │
│  private val _uiState = MutableStateFlow<UiState>(...)   │
│  val uiState: StateFlow<UiState> = _uiState              │
│                                                           │
│  fun onEvent(event: HomeEvent) {                         │
│      when(event) {                                       │
│          is VoiceButtonClicked -> startListening()       │
│          is AppSelected -> updateSelectedApps()          │
│      }                                                   │
│  }                                                       │
└──────────────┬──────────────────────────────────────────┘
               │ calls
               ↓
┌──────────────────────────────────────────────────────────┐
│                      Use Case                             │
│                                                           │
│  class StartVoiceListeningUseCase(                       │
│      private val repository: VoiceRepository             │
│  ) {                                                     │
│      suspend operator fun invoke(): Flow<String>         │
│  }                                                       │
└──────────────┬──────────────────────────────────────────┘
               │ delegates to
               ↓
┌──────────────────────────────────────────────────────────┐
│                  Repository Interface                     │
│                                                           │
│  interface VoiceRepository {                             │
│      fun startListening(): Flow<String>                  │
│  }                                                       │
└──────────────┬──────────────────────────────────────────┘
               │ implemented by
               ↓
┌──────────────────────────────────────────────────────────┐
│               Repository Implementation                   │
│                                                           │
│  class VoiceRepositoryImpl(                              │
│      private val dataSource: VoiceDataSource             │
│  ) : VoiceRepository {                                   │
│      override fun startListening(): Flow<String>         │
│  }                                                       │
└──────────────┬──────────────────────────────────────────┘
               │ uses
               ↓
┌──────────────────────────────────────────────────────────┐
│                     Data Source                           │
│                                                           │
│  - Android Speech Recognition API                        │
│  - SharedPreferences                                     │
│  - Room Database                                         │
└──────────────────────────────────────────────────────────┘
```

## Component Interaction Example: Voice Button

```
User taps VoiceButton
       ↓
┌─────────────────┐
│   VoiceButton   │ (UI Component)
│   onClick()     │
└────────┬────────┘
         │ callback
         ↓
┌─────────────────┐
│   HomeScreen    │ (Screen)
│  onEvent(       │
│   VoiceClicked  │
│  )              │
└────────┬────────┘
         │ event
         ↓
┌─────────────────┐
│  HomeViewModel  │ (Presentation)
│  startListening │
└────────┬────────┘
         │ invoke use case
         ↓
┌──────────────────────┐
│ StartVoiceListening  │ (Domain)
│     UseCase          │
└────────┬─────────────┘
         │ call repository
         ↓
┌──────────────────────┐
│  VoiceRepository     │ (Domain Interface)
│  startListening()    │
└────────┬─────────────┘
         │ implementation
         ↓
┌──────────────────────┐
│ VoiceRepositoryImpl  │ (Data)
│ Android Speech API   │
└────────┬─────────────┘
         │ emits Flow<String>
         ↓
┌──────────────────────┐
│  HomeViewModel       │
│  updates uiState     │
│  to Listening        │
└────────┬─────────────┘
         │ emits StateFlow
         ↓
┌──────────────────────┐
│  HomeScreen          │
│  recomposes with     │
│  new state           │
└────────┬─────────────┘
         │ passes state
         ↓
┌──────────────────────┐
│  VoiceButton         │
│  shows listening     │
│  animation           │
└──────────────────────┘
```

## File Organization by Feature

```
home/
├── presentation/
│   └── HomeViewModel.kt
├── ui/
│   ├── HomeScreen.kt
│   └── HomeUiState.kt
└── domain/
    └── GetRecentCommandsUseCase.kt

settings/
├── presentation/
│   └── SettingsViewModel.kt
├── ui/
│   ├── SettingsScreen.kt
│   └── SettingsUiState.kt
└── domain/
    └── UpdateSettingsUseCase.kt

permissions/
├── presentation/
│   └── PermissionsViewModel.kt
├── ui/
│   ├── PermissionsScreen.kt
│   └── PermissionsUiState.kt
└── domain/
    └── CheckPermissionUseCase.kt
```

## Dependency Rules

```
✅ ALLOWED:
  Presentation → Domain
  Data → Domain
  UI → Presentation
  UI → Domain (for models only)

❌ NOT ALLOWED:
  Domain → Presentation
  Domain → Data
  Domain → UI
  Data → Presentation
  Data → UI
```

## Testing Strategy

```
┌─────────────────────────────────────────┐
│            Unit Tests                    │
│  ┌───────────────────────────────────┐  │
│  │  ViewModels                       │  │
│  │  Use Cases                        │  │
│  │  Repository Implementations       │  │
│  │  Utility Functions                │  │
│  └───────────────────────────────────┘  │
└─────────────────────────────────────────┘

┌─────────────────────────────────────────┐
│          Integration Tests               │
│  ┌───────────────────────────────────┐  │
│  │  Repository + Data Source         │  │
│  │  ViewModel + Repository           │  │
│  └───────────────────────────────────┘  │
└─────────────────────────────────────────┘

┌─────────────────────────────────────────┐
│             UI Tests                     │
│  ┌───────────────────────────────────┐  │
│  │  Screen Navigation                │  │
│  │  Component Rendering              │  │
│  │  User Interactions                │  │
│  │  Accessibility Compliance         │  │
│  └───────────────────────────────────┘  │
└─────────────────────────────────────────┘
```

## Key Architectural Decisions

1. **Unidirectional Data Flow**: Data flows down (state) and events flow up
2. **State Hoisting**: UI components are stateless
3. **Single Source of Truth**: Repositories manage data
4. **Immutable State**: UI state classes use `@Immutable`
5. **Sealed Interfaces**: Type-safe state representation
6. **Interface Segregation**: Small, focused repository interfaces
7. **Dependency Inversion**: Domain layer defines interfaces

---

**Architecture Pattern**: Clean Architecture + MVVM
**UI Framework**: Jetpack Compose
**State Management**: StateFlow + Immutable State
**Navigation**: Navigation Compose
**Async**: Kotlin Coroutines + Flow
