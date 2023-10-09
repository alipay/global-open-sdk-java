package com.alipay.global.api.model.ams;

public class CardVerificationResult {
    /**
     * Authentication type
     */
    private String authenticationType;

    /**
     * The authentication method that a merchant uses for a card payment
     */
    private String authenticationMethod;

    /**
     * The verification result of the card verification value (CVV)
     */
    private String cvvResult;

    /**
     * The address verification result.
     */
    private String avsResult;

    /**
     * The authorization code granted by the payment method provider for a successful 3D authentication.
     */
    private String authorizationCode;

    /**
     * The result of 3D Secure authentication.
     */
    private RiskThreeDSResult threeDSResult;

    public String getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }

    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(String authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    public String getCvvResult() {
        return cvvResult;
    }

    public void setCvvResult(String cvvResult) {
        this.cvvResult = cvvResult;
    }

    public String getAvsResult() {
        return avsResult;
    }

    public void setAvsResult(String avsResult) {
        this.avsResult = avsResult;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public RiskThreeDSResult getThreeDSResult() {
        return threeDSResult;
    }

    public void setThreeDSResult(RiskThreeDSResult threeDSResult) {
        this.threeDSResult = threeDSResult;
    }
}