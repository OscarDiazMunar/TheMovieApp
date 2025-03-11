# TheMovieApp

PokeApp is a modern Android application built using **Clean Architecture**, following best practices to ensure maintainability, scalability, and testability. The app fetches TheMovieDB data from an API, supports pagination, uses image loading.

## 📂 Project Structure

The project follows **Clean Architecture**, which divides the codebase into three layers:

### 1️⃣ **Presentation Layer**

- Implements **MVVM (Model-View-ViewModel) architecture**.
- Uses **Jetpack Compose** for UI.
- Handles user interactions and UI state.
- Uses **Hilt** for dependency injection.

### 2️⃣ **Domain Layer**

- Contains **use cases** that encapsulate business logic.
- Defines repository interfaces.
- Independent of frameworks and UI.

### 3️⃣ **Data Layer**

- Implements repositories and data sources.
- Uses **Retrofit + OkHttp3** for network requests.
- Caches data using **Room Database**.
- Implements pagination with **Paging 3**.

## 🛠️ Tech Stack

- **Kotlin** - Modern, concise, and expressive language.
- **Clean Architecture** - Separation of concerns for better maintainability.
- **MVVM** - Architecture pattern for handling UI and state.
- **Hilt** - Dependency Injection framework.
- **Retrofit & OkHttp3** - For networking and API calls.
- **Room Database** - Local database for caching.
- **Paging 3** - Efficient data loading with pagination.
- **Coil** - Image loading and caching.

## 🔧 Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/OscarDiazMunar/TheMovieApp.git
   ```
2. Open the project in **Android Studio**.
3. add your token into Constants.kt
4. Sync Gradle and run the app on an emulator or a physical device.

## 🚀 Features

✅ Fetch Movies list from API\
✅ Implement infinite scrolling with Paging 3\
✅ Cache data using Room Database\
✅ Load images efficiently with Coil\
✅ Follow Clean Architecture best practices

## 📸 Screenshots

Here are some screenshots of PokeApp in action:

### Home Screen
<p align="center">
  <img width="270" src="https://github.com/OscarDiazMunar/TheMovieApp/blob/main/screenshots/movie1.png"/>
</p>

### Movie Details
<p align="center">
  <img width="270" src="https://github.com/OscarDiazMunar/TheMovieApp/blob/main/screenshots/movie2.png"/>
</p>

<p align="center">
  <img width="270" src="https://github.com/OscarDiazMunar/TheMovieApp/blob/main/screenshots/movie3.png"/>
</p>






