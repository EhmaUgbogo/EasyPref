![GitHub release (latest by date)](https://img.shields.io/github/v/release/EhmaUgbogo/EasyPref?style=flat-square) ![GitHub top language](https://img.shields.io/github/languages/top/EhmaUgbogo/EasyPref?style=flat-square) ![GitHub all releases](https://img.shields.io/github/downloads/EhmaUgbogo/EasyPref/total) ![Twitter Follow](https://img.shields.io/twitter/follow/EhmaUgbogo?style=social)

# EasyPref

> Observable EasyPrefLib

### Description:
A SharedPreference Library that can be used to store all types including your custom classes and observe them too if needed.
___

### Features:
- Easily store primitive types
- Easily store custom classes
- Easily observe primitive or custom classes

___

### Setup. 
To get a Git project into your build: 

1. Add the JitPack repository to you root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
	...
	maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency

```groovy
dependencies {
    implementation 'com.github.EhmaUgbogo:EasyPref:1.0.2'
}
```
___

### Usage

1. Initialize EasyPref in your application class or MainActivity

```kotlin
  EasyPref.initialize(this) // If setup in your application class
  EasyPref.initialize(application) // If setup in MainActivity
```

2. Use as needed

```kotlin

  EasyPref.hasKey(key)
  
  EasyPref.deleteKey(key)

  // Store primitive types

  EasyPref.putBoolean(key, boolValue)
  EasyPref.getBoolean(key)  // ... Same for Strings, Int, etc 
  
  
  // Store custom types
  
  EasyPref.putObject(OBJ_KEY, yourData)
  EasyPref.getObject(OBJ_KEY, YourData::class.java)
  
```

2. Observe as needed

```kotlin
  
  EasyPref.observeBoolean(key).observe(this@MainActivity) {
       // your code here
  }
  
  // observe custom types - returns (PrefObjectHolder<T>)
  
  EasyPref.observeObject(OBJ_KEY, YourData::class.java).observe(viewLifecycleOwner) {
      it.value?.let { yourData -> showSnackBar("$yourData Observed") }
  }
 
  
```


### Licence

MIT Licence

Copyright (c) [2021] Ehma Ugbogo

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

___

### Author Info

[@EhmaUgbogo](https://twitter.com/EhmaUgbogo)


[Back To The Top](#easypref)


