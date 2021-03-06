# Me-ALCwithGoogle-FinalChallenge
The final challenge of the ALCwithGoogle scholarship programme

## Getting Started
Clone the project onto your computer. Import it into your Android Studio, and build.

### Prerequisites
This application was built using Android Studio. That is the only requirement you will need, and an internet connection for dependency loading.

### Installing
Simply import the project into Android Studio, or any other preferred development environment, and run it.

## About the app
This is a journal app that records your thoughts at any given time of day. It records your thoughts to a local Room database, as well as a Firebase database for backup as well as data restore when you log in to the app from another device. This app was built in Kotlin with the exception of the database class, which never seems to work for me in Kotlin.

### Features
* The list of entries is displayed in a RecyclerView that groups them according to dates
* The time is displayed according to how long ago you made the journal entry
* Data is stored locally, and in the cloud for both offline and online access from seperate devices
* Creating, Editing and Deleting journal entries is all handled seemlessly through a single activity, [StoryActivity](https://github.com/bandacode/Me-ALCwithGoogle-FinalChallenge/blob/master/app/src/main/java/com/ban2apps/me/ui/story/StoryActivity.kt)

## Author
* **Chembe Banda** - *Initial work* - [bandacode](https://github.com/bandacode)

## License
This project is licensed under the MIT License

## Acknowledgments
* [Udacity](https://www.udacity.com), for their great tutorials
* Google, for sponsoring this programme
