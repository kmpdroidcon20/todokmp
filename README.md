# todokmp
A Kotlin Multiplatform demo Todo app

The goal of this project is to help writing use cases in Kotlin Multiplatform and to test them.

Architectural pattern followed in this project is MVVM and it is also implemented in KMP

We added sample Unit and Integration tests with the help of `MockingBird` library which we
wrote to help us Mock and Spy in Common tests 

Note: `MockingBird` will be opened sourced soon. For now we included artifacts to help you run the 
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
