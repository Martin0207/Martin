# Martin

## 简介

该项目主要是对各个框架的简单整合，以modul的形式导入到项目中

## 接入过程

### 导入文件

#### 1. Module

`lib_base`是框架的核心了，毋庸置疑需要第一步导过来

#### 2. 配置文件

- versions.gradle
  - 项目版本控制
  - 各框架控制
- gradle.properties
  - 主要设置工具包需要的值
    - versionCode
    - versionName
    - isModuled
    - apkType
- build.gradle
  - 这里不涉及项目的私密设置，可以直接替换
  - 如果不想替换，可以按照Martin项目中的内容逐一替换
- app目录下build.gradle
  - 这个文件只有一个私密设置`applicationId`，需要替换为自己项目的包名
  - 直接替换是最方便的，如果不想直接替换，也可以按照需求逐一替换

## 功能介绍

### 网络请求

