package com.sfpay.airscape.server.vo.auxiliary;

/**
 * 
 * @author sfhq1588
 */
public class ParaDicKey {
    private String dicGroupCode;

    private String dicCode;

    public String getDicGroupCode() {
        return dicGroupCode;
    }

    public void setDicGroupCode(String dicGroupCode) {
        this.dicGroupCode = dicGroupCode == null ? null : dicGroupCode.trim();
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode == null ? null : dicCode.trim();
    }
}