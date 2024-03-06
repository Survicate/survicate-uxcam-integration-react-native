import { NativeEventEmitter, NativeModules } from 'react-native';
import { SurvicateIntegrations } from '@survicate/react-native-survicate';
import RNUxcam from 'react-native-ux-cam';

// @ts-expect-error
const isTurboModuleEnabled = global.__turboModuleProxy != null;

const SurvicateUxcamIntegrationReactNativeModule = isTurboModuleEnabled
  ? require('./NativeSurvicateUxcamIntegrationReactNative').default
  : NativeModules.SurvicateUxcamIntegrationReactNative;

declare module '@survicate/react-native-survicate' {
  interface SurvicateIntegrations {
    addUXCamIntegration(): () => void;
  }
}

SurvicateIntegrations.prototype.addUXCamIntegration = function (): () => void {
  const eventEmitter = new NativeEventEmitter(
    SurvicateUxcamIntegrationReactNativeModule
  );

  const onQuestionAnsweredCallback = (nativeEvent: any) => {
    const eventArgs = {
      answer: nativeEvent.answer,
      question: nativeEvent.question,
      question_type: nativeEvent.questionType,
      response_uuid: nativeEvent.responseUuid,
      visitor_uuid: nativeEvent.visitorUuid,
      survey_id: nativeEvent.surveyId,
      survey_name: nativeEvent.surveyName,
      response_url: nativeEvent.panelAnswerUrl,
    };

    RNUxcam.logEvent('survicate.Questionanswered', eventArgs);
  };

  const onQuestionAnsweredListener = eventEmitter.addListener(
    'survicate.uxcam.onQuestionAnswered',
    onQuestionAnsweredCallback
  );

  return () => {
    onQuestionAnsweredListener.remove();
  };
};
