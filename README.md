
# AppsFlyer AdRevenue Android Sample App

Android sample app which demonstrate the usage of Appsflyer AdRevenue - SDK connector that 
enables the ad networks to report ad revenue using impression-level granularity.
This repository gives you sample app to get you started easily with AdRevenue Generic connector, and how to integrate it with AdMob and IronSource.




## Acknowledgements

 - [AdMob Android Integration page](https://developers.google.com/admob/android/quick-start)
 - [IronSource Android Integration page](https://developers.is.com/ironsource-mobile/android/android-sdk/)


## Documentation
You can read more in our Developer Hub for Android [here](https://dev.appsflyer.com/hc/docs/ad-revenue-1)


## Competability
Android version >= 29

AppsFlyer SDK versions >= 5.1.0

AppsFlyer AdRevenue versions >= 6.4.3

* It's more than recommended to use the most updated versions for the SDK and for the AdRevenue connector

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
* start your journey with our sample app! 😁

## First page of the app
At the first activity of the app, you will be asked to choose - whice Ad network you would like to integrate with AppsFlyer:
* AdMob or IronSource 

<img src="/pics/firstpage.png" width="400" height="675">

## second page of the app
At the second activity of the app, you will be asked to choose from the list, Which kind of ad would you like to show and test.

<img src="/pics/secondpage.png" width="400" height="675">

## third page of the app
At the third activity of the app, the ad you chose will be presented - sometimes it takes a few moments.

<img src="/pics/thirdpage.png" width="400" height="675">

After presenting the ad, you will be able to see in the app logs - that AppsFlyer generated an AdRevenue event when the ad was presented according to the app code.
