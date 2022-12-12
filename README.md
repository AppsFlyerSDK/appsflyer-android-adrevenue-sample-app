
# AppsFlyer AdRevenue Android Sample App

This repository contains an Android sample app which demonstrate the usage of **Appsflyer AdRevenue**: 
A SDK connector enabling the ad networks to report ad revenue using impression-level granularity.
The sample app will an easy start with AdRevenue Generic connector, and integration with AdMob and IronSource.


## Acknowledgements

 - [AdMob Android Integration page](https://developers.google.com/admob/android/quick-start)
 - [IronSource Android Integration page](https://developers.is.com/ironsource-mobile/android/android-sdk/)


## Documentation
Learn more in our [Developer Hub for Android](https://dev.appsflyer.com/hc/docs/ad-revenue-1)


## Competability
Android version >= 29

AppsFlyer SDK versions >= 5.1.0

AppsFlyer AdRevenue versions >= 6.4.3

* It's recommended to use the most updated versions for the SDK and AdRevenue connector


## Run Locally

Clone the project

```bash
  git clone https://github.com/AppsFlyerSDK/appsflyer-android-adrevenue-sample-app.git
```

Go to the project directory

```bash
  cd my-project
```

* Open the App on Android studio
* Make sure that the gradle file is synced
* Make sure that the device/emulator has network
* Start your new journey with our sample app! 😁

## First page of the app
At the first activity of the app choose the Ad network you would like to integrate with AppsFlyer:
* AdMob or IronSource 

<img src="/pics/firstpage.png" width="400" height="675">

## Second page of the app
At the second activity of the app choose from the list, the ad type would you like to show and test.

<img src="/pics/secondpage.png" width="400" height="675">

## Third page of the app
At the third activity of the app, the chosen ad will be presented - sometimes it takes a few moments.

<img src="/pics/thirdpage.png" width="400" height="675">

After presenting the ad, you will be able to see in the app logs - that AppsFlyer generated an AdRevenue event when the ad was presented according to the app code.
