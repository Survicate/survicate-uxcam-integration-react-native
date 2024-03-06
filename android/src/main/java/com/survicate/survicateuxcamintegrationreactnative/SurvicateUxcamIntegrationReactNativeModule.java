package com.survicate.survicateuxcamintegrationreactnative;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMethod;
import com.survicate.surveys.Survicate;

public class SurvicateUxcamIntegrationReactNativeModule extends SurvicateUxcamIntegrationReactNativeSpec {

    public static final String NAME = "SurvicateUxcamIntegrationReactNative";
    private final SurvicateIntegration integration;
    private int listenerCount = 0;

    public SurvicateUxcamIntegrationReactNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.integration = new SurvicateIntegration(reactContext);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void addListener(String eventName) {
        if (listenerCount == 0) {
            Survicate.addEventListener(integration);
        }
        listenerCount++;
    }

    @ReactMethod
    public void removeListeners(int count) {
        listenerCount -= count;
        if (listenerCount == 0) {
            Survicate.removeEventListener(integration);
        }
    }

    @ReactMethod
    public void removeListeners(double count) {
        removeListeners((int) count);
    }
}
