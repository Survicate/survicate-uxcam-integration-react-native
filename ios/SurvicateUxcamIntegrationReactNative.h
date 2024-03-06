#import <Survicate/Survicate-Swift.h>
#import <React/RCTEventEmitter.h>

#ifdef RCT_NEW_ARCH_ENABLED
#import "RNSurvicateUxcamIntegrationReactNativeSpec.h"

@interface SurvicateUxcamIntegrationReactNative : RCTEventEmitter <NativeSurvicateUxcamIntegrationReactNativeSpec, IntegrationListener>
#else
#import <React/RCTBridgeModule.h>

@interface SurvicateUxcamIntegrationReactNative : RCTEventEmitter <RCTBridgeModule, IntegrationListener>
#endif

@end
