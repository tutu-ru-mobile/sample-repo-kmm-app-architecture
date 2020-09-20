
# Solutions architecture sample
Для запуска Android выбираем run конфигурацию android_jetpack_compose  
![image_run_config](misc/android_run_configuration.png)
  
Но также можно просто собирать из консоли ./android_install.sh
А потом установить app-android/build/outputs/apk/debug/app-android-debug.apk  

##Build iOS App
```bash
ssh-add
./repo.sh update
sudo gem install cocoapods
sudo gem install cocoapods-generate
./gradlew podImport
cd ios/App
pod install
pod update
#open [./ios/App/SolutionArchitecture.xcworkspace] in XCode or AppCode and run project on iOS Emulator
```


