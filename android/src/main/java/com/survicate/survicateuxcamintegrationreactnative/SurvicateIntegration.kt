package com.survicate.survicateuxcamintegrationreactnative

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.survicate.surveys.IntegrationListener;
import com.survicate.surveys.IntegrationPayload;
import com.survicate.surveys.QuestionAnsweredEvent;
import com.uxcam.UXCam;

class SurvicateIntegration(private val context: ReactContext) : IntegrationListener() {

    override val providerName = "uxcam"

    override fun onQuestionAnswered(event: QuestionAnsweredEvent) {
        val params = Arguments.createMap()

        params.putString("answer", event.answer.value ?: "")
        params.putString("question", event.questionText);
        params.putString("questionType", event.answer.type)
        params.putString("responseUuid", event.responseUuid)
        params.putString("visitorUuid", event.visitorUuid)
        params.putString("surveyId", event.surveyId)
        params.putString("surveyName", event.surveyName)
        params.putString("panelAnswerUrl", event.panelAnswerUrl)

        sendEvent(context, "survicate.uxcam.onQuestionAnswered", params)
    }

    override fun onWillSendAnswer() : Map<String, IntegrationPayload> {
        val sessionUrl = UXCam.urlForCurrentSession()
        return mapOf("uxcam_url" to IntegrationPayload(sessionUrl))
    }

    private fun sendEvent(reactContext: ReactContext, eventName: String, params: WritableMap) {
      reactContext
        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
        .emit(eventName, params)
    }
}
