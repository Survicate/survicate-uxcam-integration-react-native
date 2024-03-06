# @survicate/survicate-uxcam-integration-react-native ![NPM Version](https://img.shields.io/npm/v/%40survicate%2Fsurvicate-uxcam-integration-react-native)
A thin library designed for seamless integration between [Survicate](https://survicate.com/) and [UXCam](https://uxcam.com/) on React Native. It automatically sends survey answers coming from Survicate SDK as UXCam events that can be previewed directly in the UXCam panel.

## Installation
You'll need to install integration using your preferred package manager:

### NPM
```sh
npm install @survicate/survicate-uxcam-integration-react-native
```

### Yarn
```sh
yarn add @survicate/survicate-uxcam-integration-react-native
```

## Usage

> Note that for the plugin to function properly, the UXCam integration must be enabled in the Survicate panel. Also, it is essential that both the Survicate and UxCam SDKs have been initialized correctly as outlined in their respective documentation:
> - [Survicate developer docs](https://developers.survicate.com/mobile-sdk/react-native/)
> - [UXCam developer docs](https://developer.uxcam.com/docs/react-native)

In order to activate the integration library, go to the place in your app where you initialize the Survicate SDK and add `SurvicateUXCamIntegration` integration:

```javascript
import Survicate from '@survicate/react-native-survicate';
import '@survicate/survicate-uxcam-integration-react-native';

useEffect(() => {
  const subscription = Survicate.integrations.addUXCamIntegration(); 
  return subscription;
}, []);
```

That's it. You can use all UXCam and Survicate features as usual. Every survey answer will be automatically logged to the UXCam using the UXCam.logEvent method.
