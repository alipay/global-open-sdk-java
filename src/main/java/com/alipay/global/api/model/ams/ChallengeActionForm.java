package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeActionForm {

    private ChallengeType challengeType;
    private String challengeRenderValue;
    private ChallengeTriggerSourceType triggerSource;
    private String extendInfo;

}
