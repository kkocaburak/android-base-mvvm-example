# Example App
** App Description **

<img src="/assets/resource1" width="260"> | <img src="/assets/resource2" width="260"> | <img src="/assets/resource3" width="260">

## Architecture

- Single Activity
- MVVM Pattern
- Clean Code
- Repository Pattern

**View:** Renders UI and delegates user actions to ViewModel

**ViewModel:** Can have simple UI logic but most of the time just gets the data from UseCase

**UseCase:** Contains all business rules and they written in the manner of single responsibility principle

**Repository:** Single source of data. Responsible to get data from one or more data sources

<img src="/assets/architecture-diagram.png" width="320" height="460">


## Tech Stack
#### Dependencies

- **[Navigation Component](https://developer.android.com/jetpack/androidx/releases/navigation):** Consistent navigation between views
- **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata):** Lifecycle aware observable and data holder
- **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel):** Holds UI data across configuration changes
- **[Databinding](https://developer.android.com/topic/libraries/data-binding/):** Binds UI components in layouts to data sources
- **[Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android):** Dependency injector
- **[Coroutines](https://github.com/Kotlin/kotlinx.coroutines):** Asynchronous programming
- **[Kotlin-Flow](https://developer.android.com/kotlin/flow):** Asynchronous programming
- **[Room](https://developer.android.com/jetpack/androidx/releases/room):** Local database
- **[Retrofit](https://github.com/square/retrofit):** Type safe HTTP client
- **[GSON](https://github.com/google/gson):** JSON serializer/deserializer
- **[MockK](https://mockk.io/ANDROID.html):** Unit test

## Unit Test

One of the benefits of using MVVM design pattern is unit testing. With single responsibility classes we can easily create unit test class. In the project, we have example unit test classes for viewmodel, usecase, repository etc. 


## License

```
Copyright kkocaburak

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
1