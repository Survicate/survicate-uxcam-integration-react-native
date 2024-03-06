#import "SurvicateUxcamIntegrationReactNative.h"
#import <Survicate/Survicate-Swift.h>
#import "UXCam/UXCam.h"

@implementation SurvicateUxcamIntegrationReactNative {
    bool hasListeners;
}
RCT_EXPORT_MODULE()

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

- (NSArray<NSString*> *)supportedEvents {
    return @[@"survicate.uxcam.onQuestionAnswered"];
}

- (void)startObserving {
    hasListeners = YES;
    [[SurvicateSdk shared] addListener:self];
}

- (void)stopObserving {
    hasListeners = NO;
    [[SurvicateSdk shared] removeListener:self];
}

- (void)questionAnswered:(QuestionAnsweredEvent * _Nonnull)event {
    if (!hasListeners) return;
    [self sendEventWithName:@"survicate.uxcam.onQuestionAnswered" body:
         @{@"answer": event.answer.value ?: @"",
           @"question": event.question,
           @"questionType": event.answer.type,
           @"responseUuid": event.responseUUID,
           @"visitorUuid": event.visitorUUID,
           @"surveyId": event.surveyId,
           @"surveyName": event.surveyName,
           @"panelAnswerUrl": event.panelAnswerUrl,
         }
    ];
}

- (NSString *)providerName {
    return @"uxcam";
}

- (NSDictionary<NSString *, IntegrationPayload *> *)onWillSendAnswer {
	NSString *url = [UXCam urlForCurrentSession];
    IntegrationPayload *payload = [[IntegrationPayload alloc] initWithString:url];
    return @{@"uxcam_url": payload};
}

// Don't compile this code when we build for the old architecture.
#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeSurvicateUxcamIntegrationReactNativeSpecJSI>(params);
}
#endif

@end
