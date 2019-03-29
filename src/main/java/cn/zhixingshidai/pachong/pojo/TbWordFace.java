package cn.zhixingshidai.pachong.pojo;


import java.io.Serializable;
import java.util.Date;

public class TbWordFace implements Serializable {

    private Integer wordId;
    private String wordCode;
    private Integer goodsClassType;
    private String goodsClassId;
    private Integer userId;
    private String userTrueName;
    private String pTitle;
    private String pTitleImage;
    private Integer status;
    private Integer isFinal;
    private Integer isPublish;
    private Integer isComment;
    private Date commitDate;
    private Date publishDate;
    private Date lastUpdateDate;
    private String pageUrl;
    private String remarks;
    private String tkdTitle;
    private String tkdKeyWords;
    private String tkdDescription;
    private Integer classId;
    private Integer templetType;
    private Date firstPublishDate;

    private Integer typeId;

    private String wordIds;

    private Integer wordDeclareId;
    private String confirmName;

    private String newPTitle;
    private String newTkdTitle;
    private Integer wordRevampApplyId;



    //1:正常   0:失败   当更新和添加词条的时候  用来判断此套pTitle是否存在， 存在就是失败，  不存在就是可以正常提交是正常。   默认是正常
    private Integer code = 1;

    public String getConfirmName() {
        return confirmName;
    }

    public void setConfirmName(String confirmName) {
        this.confirmName = confirmName;
    }

    public Integer getWordDeclareId() {
        return wordDeclareId;
    }

    public void setWordDeclareId(Integer wordDeclareId) {
        this.wordDeclareId = wordDeclareId;
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }


    public String getWordCode() {
        return wordCode;
    }

    public void setWordCode(String wordCode) {
        this.wordCode = wordCode;
    }


    public Integer getGoodsClassType() {
        return goodsClassType;
    }

    public void setGoodsClassType(Integer goodsClassType) {
        this.goodsClassType = goodsClassType;
    }


    public String getGoodsClassId() {
        return goodsClassId;
    }

    public void setGoodsClassId(String goodsClassId) {
        this.goodsClassId = goodsClassId;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }


    public String getPTitle() {
        return pTitle;
    }

    public void setPTitle(String pTitle) {
        this.pTitle = pTitle;
    }


    public String getPTitleImage() {
        return pTitleImage;
    }

    public void setPTitleImage(String pTitleImage) {
        this.pTitleImage = pTitleImage;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Integer isFinal) {
        this.isFinal = isFinal;
    }


    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }


    public Integer getIsComment() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }


    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }


    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }


    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }


    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public String getTkdTitle() {
        return tkdTitle;
    }

    public void setTkdTitle(String tkdTitle) {
        this.tkdTitle = tkdTitle;
    }


    public String getTkdKeyWords() {
        return tkdKeyWords;
    }

    public void setTkdKeyWords(String tkdKeyWords) {
        this.tkdKeyWords = tkdKeyWords;
    }


    public String getTkdDescription() {
        return tkdDescription;
    }

    public void setTkdDescription(String tkdDescription) {
        this.tkdDescription = tkdDescription;
    }


    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }


    public Integer getTempletType() {
        return templetType;
    }

    public void setTempletType(Integer templetType) {
        this.templetType = templetType;
    }

    public String getWordIds() {
        return wordIds;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setWordIds(String wordIds) {
        this.wordIds = wordIds;
    }

    public String getNewPTitle() {
        return newPTitle;
    }

    public void setNewPTitle(String newPTitle) {
        this.newPTitle = newPTitle;
    }

    public String getNewTkdTitle() {
        return newTkdTitle;
    }

    public void setNewTkdTitle(String newTkdTitle) {
        this.newTkdTitle = newTkdTitle;
    }

    public Integer getWordRevampApplyId() {
        return wordRevampApplyId;
    }

    public void setWordRevampApplyId(Integer wordRevampApplyId) {
        this.wordRevampApplyId = wordRevampApplyId;
    }

    public Date getFirstPublishDate() {
        return firstPublishDate;
    }

    public void setFirstPublishDate(Date firstPublishDate) {
        this.firstPublishDate = firstPublishDate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
