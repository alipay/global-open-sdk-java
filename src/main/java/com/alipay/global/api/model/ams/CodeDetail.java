package com.alipay.global.api.model.ams;

public class CodeDetail {

    private CodeValueType codeValueType;
    private String codeValue;
    private DisplayType displayType;

    public CodeValueType getCodeValueType() {
        return codeValueType;
    }

    public void setCodeValueType(CodeValueType codeValueType) {
        this.codeValueType = codeValueType;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    public void setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
    }
}
