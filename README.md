# Efficient Channel Search

Test task implementation for efficient search and filtering over a dataset of ~5000 channels.

The main focus of this project is correctness, performance awareness and clean separation of responsibilities.  
UI is intentionally simple.

---

## Architecture

The project is split into three logical layers:

- data – data sources, DTOs, mappers and repository implementation
- domain – business models, repository contracts and use cases
- presentation – ViewModel and Compose UI


Data flow:
channels.json (assets)

↓

ChannelLocalDataSource

↓

ChannelRepository

↓

SearchChannelsUseCase

↓

ChannelsViewModel

↓

Compose UI


---

## Data layer

**ChannelLocalDataSource**

- Loads `channels.json` from assets
- Parses the file only once
- Caches the result in memory

`AssetManager` is injected instead of `Context` to keep the dependency minimal.

**ChannelRepository**

- Converts DTOs to domain models
- Does not contain search or filtering logic

---

## Domain layer

**SearchChannelsUseCase**

- Case-insensitive search
- Filters by:
  - `title`
  - `category`

Filtering is implemented as a simple linear scan.

### Complexity
```
O(n)
```
With ~5000 items this is sufficient and predictable.  
More complex indexing would add unnecessary complexity.

Search logic is intentionally placed in the use case, not in the repository, because it represents a business rule.

---

## Presentation layer

**ViewModel**

- Uses `StateFlow`
- Applies:
  - `debounce`
  - `distinctUntilChanged`
  - `mapLatest`

Only the latest query is processed.

Filtering is executed on a background dispatcher.

`stateIn` with `SharingStarted.WhileSubscribed(...)` is used so the flow is active only while the UI is observing.

---

## UI

The UI is built with Jetpack Compose and kept intentionally minimal.

The screen is split into:
- a composable that obtains the ViewModel from Koin
- a pure UI composable that receives only state and callbacks (used for previews)

---

## Dependency injection

Koin is used as a lightweight DI framework.

It is initialized in the `Application` class.

---

## Dataset

The dataset is stored as a static JSON file:
```
app/src/main/assets/channels.json
```

---

## Performance notes

- JSON is parsed only once
- Results are cached in memory
- Search is linear and predictable
- Debounce prevents excessive recomputation
- Heavy work is executed off the main thread
