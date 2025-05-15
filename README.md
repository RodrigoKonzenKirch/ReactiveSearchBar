Reactive User Search App

This is a simple Reactive User Search Android application built with Jetpack Compose, Kotlin, and Hilt. The app demonstrates how to implement real-time search functionality with debounced user input, a responsive UI, and clean architecture practices.

Features

    - Real-time user search with debounce to avoid unnecessary operations

    - MVVM architecture with ViewModel and StateFlow

    - Hilt dependency injection

    - Compose UI with LazyColumn list rendering

    - Toast notifications on error

    - Fully built with Jetpack Compose

How It Works

    - User types in the search bar

    - Input is debounced (300ms) and sent to the ViewModel

    - The ViewModel fetches filtered results from a repository (simulated in-memory)

    - UI updates the user list and shows a progress indicator while loading

Notes

    The search is currently performed on a hardcoded list of users (simulating a network call with delay).

    Replace the repository implementation with a real API when integrating backend functionality.
