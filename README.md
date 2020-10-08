# todokmp
A Kotlin Multiplatform demo Todo app

The goal of this project is to help writing use cases in Kotlin Multiplatform and to test them.

The architectural pattern followed in this project is MVVM, which is also implemented in KMP

We added sample Unit and Integration tests with the help of `MockingBird` library which we
wrote to help us Mock and Spy in Common tests

This project has been used as an example during the talk:
Acing your tests with KMP @ droidcon EMEA 2020
[slides of the talk](https://docs.google.com/presentation/d/1iRgLZvUj7b6eA_bPOAgOv1oxaJE9lID45yZpWt2X5kc/edit?usp=sharing)
 

Note: `MockingBird` will be open-sourced soon. For now, we have included the artifacts to help you run the 
tests. Please run setup using the script included in the repo as mentioned below to be able to
compile the tests
  
## Setup ( Mac OS )

To setup the project you need to run the script

```shell script
./setup.sh
```

This script will prepare your local maven to use a SNAPSHOT of MockingBird, the assumption is that your maven local is in your 
home directory `~/.m2`

## Build iOS and Android

To build the whole project for both iOS and Android run:

```shell script
./iosbuild.sh
```
