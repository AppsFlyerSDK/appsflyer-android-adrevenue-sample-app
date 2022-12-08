
# AppsFlyer AdRevenue Android Sample App

Android sample app which demonstrate the usage of Appsflyer AdRevenue - SDK connector that 
enables the ad networks to report ad revenue using impression-level granularity.
This repository gives you sample app to get you started easily with AdRevenue Generic connector, and how to integrate it with AdMob and IronSource.




## Acknowledgements

 - [AdMob Android Integration page](https://developers.google.com/admob/android/quick-start)
 - [IronSource Android Integration page](https://developers.is.com/ironsource-mobile/android/android-sdk/)


## Documentation

[AppsFlyer AdRevenue Android Integration](https://dev.appsflyer.com/hc/docs/ad-revenue-1)


## Run Locally

Clone the project

```bash
  git clone https://link-to-project
```

Go to the project directory

```bash
  cd my-project
```

Open the App on Android studio, Make sure that the gradle file is synced, Make sure that the device/emulator has network, and start your journey with our sample app! :) 

## First page of the app
At the first activity of the app, you will be asked to choose - whice Ad network you would like to integrate with AppsFlyer:
AdMob or IronSource 
![ScreenShot of the 1st page](/pics/firstpage.png)

## second page of the app
At the second activity of the app, you will be asked to choose from the list, Which kind of ad would you like to show and test.
![ScreenShot of the 2nd page](/pics/secondpage.png)

## third page of the app
At the second activity of the app, you will be asked to choose from the list, Which kind of ad would you like to show and test.
![ScreenShot of the 3rd page](/pics/thirdpage.png)

After presenting the ad, you will be able to see in the app logs - that AppsFlyer generated an AdRevenue event when the ad was presented according to the app code.
