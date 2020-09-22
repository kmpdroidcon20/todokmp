# todokmp

## Build ios framework

```shell script
./gradlew build
./gradlew iosframework:zipUniversalFramework
cd iosapp
pod install
```