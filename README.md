
# Solutions architecture sample
Нужна Android Studio 4.2 Canary-6 (на Canary-7 плохо работает)  
Но также можно просто собирать из консоли ./gradlew assembleDebug  
А потом установить  app-android/build/outputs/apk/debug/app-android-debug.apk

##Build iOS App
```bash
sudo gem install cocoapods
sudo gem install cocoapods-generate
#open [project root] in AndroidStudio and update gradle (Sync Project with Gradle Files)
cd ios/App
pod install
pod update
#open [./ios/App/SolutionArchitecture.xcworkspace] in XCode or AppCode and run project on iOS Emulator
```

## todo Gradle
```
app-di:podBuildDependenciesIOS - Calls `xcodebuild` on xcworkspace for the pod scheme
3)app-di:podImport - Called on Gradle sync, depends on Cinterop tasks for every used pod
2)app-di:podInstall - Invokes `pod install` call within Podfile location directory
app-di:podSetupBuildIOS - Collect environment variables from .xcworkspace file
1)app-di:podspec - Generates a podspec file for CocoaPods import
app-di:podGenIOS - Сreates a synthetic Xcode project to retrieve CocoaPods dependencies
```
./gradlew app-di:podImport

