# Solutions architecture sample
Нужна Android Studio 4.2 Canary-6 (на Canary-7 плохо работает)  
Но также можно просто собирать из консоли ./gradlew assembleDebug  
А потом установить  app/build/outputs/apk/debug/app-debug.apk  

```bash
sudo gem install cocoapods
sudo gem install cocoapods-generate
#open [project root] in AndroidStudio and update gradle (Sync Project with Gradle Files)
cd ios/App
pod install
pod update
#open [./ios/App/SolutionArchitecture.xcworkspace] in XCode or AppCode and run project on iOS Emulator
```
