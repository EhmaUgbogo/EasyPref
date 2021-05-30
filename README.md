# EasyPref

> EasyPrefLib

### Description:
A SharedPreference Library that can be used to store all types including your custom classes.
___

### Features:
- Easily store primitive types
- Easily store custom classes

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
	implementation 'com.github.EhmaUgbogo:EasyPref:1.0'
}
```
___

### Usage

1. Initialize EasyPref in your application class of MainActivity

```kotlin
  EasyPref.initialize(this) // If setup in your application class
  EasyPref.initialize(application) // If setup in MainActivity
```

2. Use as needed

```kotlin
  // Store custom types
  EasyPref.putBoolean(key, boolValue)
  EasyPref.getBoolean(key)
  
  // ... Same for Strings, Int, etc 
  
  // Store custom types
  EasyPref.putObject(OBJ_KEY, yourData)
  EasyPref.getObject(OBJ_KEY, YourData::class.java)
  
```

### Licence

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


