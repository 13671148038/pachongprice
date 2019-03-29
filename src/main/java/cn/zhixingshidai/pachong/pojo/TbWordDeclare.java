package cn.zhixingshidai.pachong.pojo;


import java.io.Serializable;
import java.util.Date;

public class TbWordDeclare implements Serializable {

    private Integer wordDeclareId;
    private String wordName;
    private String goodsClassId;
    private String link;
    private String accessory;
    private String remark;
    private String userId;
    private Date commitTime;
    private String wordId;
    private String status;
    private String noPassPrompt;
    private String source;
    private String confirmName;
    private Integer classId;
    private Integer typeId;
    private String userTrueName;
    private String goodsClassName;
    private String wordDeclareIds;

    public String getWordDeclareIds() {
        return wordDeclareIds;
    }

    public void setWordDeclareIds(String wordDeclareIds) {
        this.wordDeclareIds = wordDeclareIds;
    }

    public String getGoodsClassName() {
        return goodsClassName;
    }

    public void setGoodsClassName(String goodsClassName) {
        this.goodsClassName = goodsClassName;
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }

    public Integer getWordDeclareId() {
        return wordDeclareId;
    }

    public void setWordDeclareId(Integer wordDeclareId) {
        this.wordDeclareId = wordDeclareId;
    }


    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }


    public String getGoodsClassId() {
        return goodsClassId;
    }

    public void setGoodsClassId(String goodsClassId) {
        this.goodsClassId = goodsClassId;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }


    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getNoPassPrompt() {
        return noPassPrompt;
    }

    public void setNoPassPrompt(String noPassPrompt) {
        this.noPassPrompt = noPassPrompt;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public String getConfirmName() {
        return confirmName;
    }

    public void setConfirmName(String confirmName) {
        this.confirmName = confirmName;
    }


    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
