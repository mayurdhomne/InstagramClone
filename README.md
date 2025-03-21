# Mock Instagram App

## Overview
This project is a mock Instagram homepage for Android, designed to demonstrate UI/UX implementation, local data handling, and good coding practices. The app follows the MVVM architecture and loads posts from a local JSON file.

## Features
- Instagram-like UI: Displays a scrollable feed of image and video posts.
- Local Data Handling: Posts are loaded from `feed_data.json`.
- RecyclerView for Posts: Efficiently displays posts with a horizontal image carousel.
- Video Playback: Plays local video files.
- Infinite Scrolling Simulation: Repeats the JSON data for an endless feed.
- MVVM Architecture: Ensures clean code and separation of concerns.
- Unit Tests: Verifies JSON parsing and data model integrity.

## Project Structure
```
InstagramClone2/
├── app/
│   ├── src/main/java/com/example/instagramclone/
│   │   ├── adapters/ (Adapters)
│   │   ├── viewmodel/ (ViewModels)
│   │   ├── model/ (Data Models)
│   │   ├── repository/ (Repository)
│   ├── src/main/res/
│   │   ├── layout/ (XML Layouts)
│   │   ├── drawable/ (Images and Icons)
│   │   ├── raw/ (Local Videos)
│   │   ├── assets/feed_data.json (Mock Data)
│   ├── src/test/java/ (Unit Tests)
```

## Installation & Setup
### Prerequisites:
- Android Studio IDE 
- Minimum SDK: 24 (Android 7.0)

### Steps to Run:
1. Clone the Repository
   ```bash
   git clone <https://github.com/mayurdhomne/InstagramClone.git>
   ```
2. Open in Android Studio
3. Sync Gradle & Build Project
4. Run the App on an Emulator or Device

## JSON Data Structure
Example `feed_data.json` format:
```json
[
  {
    "type": "image",
    "imageUrl": "local_image1.jpg",
    "caption": "Beautiful sunset",
    "user": "user1",
    "profilePictureUrl": "user1_profile.jpg",
    "likes": 123,
    "carouselImages": ["local_image2.jpg", "local_image3.jpg"]
  },
  {
    "type": "video",
    "videoUrl": "local_video1.mp4",
    "caption": "Funny cat video",
    "user": "user2",
    "profilePictureUrl": "user2_profile.jpg",
    "likes": 456
  }
]
```

## Libraries Used
- Glide - Image loading
- VideoView - Video playback
- Gson - JSON parsing
- RecyclerView - Displaying posts
- ViewModel & LiveData - MVVM Architecture

## Evaluation Criteria
- UI/UX Accuracy: Matches Instagram's design.
- Functionality: Works smoothly without crashes.
- Code Quality: Follows clean coding principles.
- Performance: Efficient rendering of posts.
- Testing: Includes unit tests for JSON parsing.

## License
This project is for educational purposes only and is not affiliated with Instagram.
