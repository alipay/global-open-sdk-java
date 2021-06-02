package com.alipay.global.api.model.ams;

public class ChallengeActionForm {

    private ChallengeType              challengeType;
    private String                     challengeRenderValue;
    private ChallengeTriggerSourceType triggerSource;
    private String                     extendInfo;

    public ChallengeType getChallengeType() {
        return challengeType;
    }

    public void setChallengeType(ChallengeType challengeType) {
        this.challengeType = challengeType;
    }

    public String getChallengeRenderValue() {
        return challengeRenderValue;
    }

    public void setChallengeRenderValue(String challengeRenderValue) {
        this.challengeRenderValue = challengeRenderValue;
    }

    public ChallengeTriggerSourceType getTriggerSource() {
        return triggerSource;
    }

    public void setTriggerSource(ChallengeTriggerSourceType triggerSource) {
        this.triggerSource = triggerSource;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

}
