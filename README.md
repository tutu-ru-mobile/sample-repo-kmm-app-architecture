
# Solutions architecture sample
Для запуска Android выбираем run конфигурацию android_jetpack_compose  
![image_run_config](misc/android_run_configuration.png)
  
Но также можно просто собирать из консоли ./android_install.sh
А потом установить app-android/build/outputs/apk/debug/app-android-debug.apk  

##Build iOS App

#First time install
```bash
sudo gem install cocoapods
sudo gem install cocoapods-generate
```

#Repeat on code update
```bash
ssh-add
./repo.sh update #if needs to update dependencies
./gradlew podImport
pod install
pod update
#open [./ios/App/SolutionArchitecture.xcworkspace] in XCode or AppCode and run project on iOS Emulator
```


