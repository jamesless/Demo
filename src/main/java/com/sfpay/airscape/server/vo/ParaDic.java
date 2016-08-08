package com.sfpay.airscape.server.vo;

import java.util.Date;

import com.sfpay.airscape.server.vo.auxiliary.ParaDicKey;

/**
 * 
 * @author sfhq1588
 */
public class ParaDic extends ParaDicKey {
    private String dicName;

    private String dicGroupName;

    private String orderBy;

    private String usableFlg;

    private String description;

    private Date createTime;

    private Date updateTime;

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName == null ? null : dicName.trim();
    }

    public String getDicGroupName() {
        return dicGroupName;
    }

    public void setDicGroupName(String dicGroupName) {
        this.dicGroupName = dicGroupName == null ? null : dicGroupName.trim();
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy == null ? null : orderBy.trim();
    }

    public String getUsableFlg() {
        return usableFlg;
    }

    public void setUsableFlg(String usableFlg) {
        this.usableFlg = usableFlg == null ? null : usableFlg.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}