# todokmp
A Kotlin Multiplatform demo Todo app

The goal of this project is to help writing use cases in Kotlin Multiplatform and to test them.

The architectural pattern followed in this project is MVVM, which is also implemented in KMP

We added sample Unit and Integration tests with the help of `MockingBird` library which we
wrote to help us Mock and Spy in Common tests

This project has been used as an example during the talk:
Acing your tests with KMP @ droidcon EMEA 2020
[slides of the talk](https://docs.google.com/presentation/d/1iRgLZvUj7b6eA_bPOAgOv1oxaJE9lID45yZpWt2X5kc/edit?usp=sharing)
[Video of the talk](https://www.droidcon.com/media-detail?video=470525677)

## Build iOS and Android

To build the whole project for both iOS and Android run:

```shell script
./iosbuild.sh
```
