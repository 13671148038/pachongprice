package cn.zhixingshidai.pachong.pojo;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CodeAttrModel implements Serializable {

    private Integer id;
    private String goodsImg;
    private String goodsCode;
    private String categoryId;
    private String goodsName;
    private String brandName;
    private String partyContactName;
    private String partyContactAddress;
    private String goodsSpecifications;
    private Integer price;
    private Integer status;
    private Date createDate;
    private Date updateDate;
    private String source;
    private Integer istag;
    private String goodsClassId;
    private Integer goodsWordId;
    //发布时间
    private Date updateWordDate;
    private Integer userId;
    //0:入库  1:修改待审核,  3:审核通过,  4 审核不通过,  2:已发布   5:发布的时候给退回的- 已退回   6:提交到seo的 7:seo退回  8:已经提交到熊掌号的
    private Integer isUpdate;
    private Integer indexed;
    private Integer codeStatus;
    private Integer brandId;
    private Integer brandStatus;
    private Date brandUpdateDate;
    private Integer newGoodClassId;
    private Integer newGoodClassStatus;
    private Date newGoodClassUpdateDate;
    private String alias;
    private String showName;
    private Integer typeId;

    private List<String> goodsCodeList;
    private Integer oldIsUpdate;

    //是否是批量更新展示名和别名操作 1:不是,  2是
    private Integer isUpdateAlias = 1;


    private String newGoodClassName;
    private String brandShowName;
    private String userTrueName;
    private Integer operateUserId;

    //分表后缀
    private String submeterSuffix;

    public Integer getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Integer operateUserId) {
        this.operateUserId = operateUserId;
    }

    public String getNewGoodClassName() {
        return newGoodClassName;
    }

    public void setNewGoodClassName(String newGoodClassName) {
        this.newGoodClassName = newGoodClassName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPartyContactName() {
        return partyContactName;
    }

    public void setPartyContactName(String partyContactName) {
        this.partyContactName = partyContactName;
    }

    public String getPartyContactAddress() {
        return partyContactAddress;
    }

    public void setPartyContactAddress(String partyContactAddress) {
        this.partyContactAddress = partyContactAddress;
    }

    public String getGoodsSpecifications() {
        return goodsSpecifications;
    }

    public void setGoodsSpecifications(String goodsSpecifications) {
        this.goodsSpecifications = goodsSpecifications;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getIstag() {
        return istag;
    }

    public void setIstag(Integer istag) {
        this.istag = istag;
    }

    public String getGoodsClassId() {
        return goodsClassId;
    }

    public void setGoodsClassId(String goodsClassId) {
        this.goodsClassId = goodsClassId;
    }

    public Integer getGoodsWordId() {
        return goodsWordId;
    }

    public void setGoodsWordId(Integer goodsWordId) {
        this.goodsWordId = goodsWordId;
    }

    public Date getUpdateWordDate() {
        return updateWordDate;
    }

    public void setUpdateWordDate(Date updateWordDate) {
        this.updateWordDate = updateWordDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Integer getIndexed() {
        return indexed;
    }

    public void setIndexed(Integer indexed) {
        this.indexed = indexed;
    }

    public Integer getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(Integer codeStatus) {
        this.codeStatus = codeStatus;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getBrandStatus() {
        return brandStatus;
    }

    public void setBrandStatus(Integer brandStatus) {
        this.brandStatus = brandStatus;
    }

    public Date getBrandUpdateDate() {
        return brandUpdateDate;
    }

    public void setBrandUpdateDate(Date brandUpdateDate) {
        this.brandUpdateDate = brandUpdateDate;
    }

    public Integer getNewGoodClassId() {
        return newGoodClassId;
    }

    public void setNewGoodClassId(Integer newGoodClassId) {
        this.newGoodClassId = newGoodClassId;
    }

    public Integer getNewGoodClassStatus() {
        return newGoodClassStatus;
    }

    public void setNewGoodClassStatus(Integer newGoodClassStatus) {
        this.newGoodClassStatus = newGoodClassStatus;
    }

    public Date getNewGoodClassUpdateDate() {
        return newGoodClassUpdateDate;
    }

    public void setNewGoodClassUpdateDate(Date newGoodClassUpdateDate) {
        this.newGoodClassUpdateDate = newGoodClassUpdateDate;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getBrandShowName() {
        return brandShowName;
    }

    public void setBrandShowName(String brandShowName) {
        this.brandShowName = brandShowName;
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }

    public List<String> getGoodsCodeList() {
        return goodsCodeList;
    }

    public void setGoodsCodeList(List<String> goodsCodeList) {
        this.goodsCodeList = goodsCodeList;
    }

    public Integer getOldIsUpdate() {
        return oldIsUpdate;
    }

    public void setOldIsUpdate(Integer oldIsUpdate) {
        this.oldIsUpdate = oldIsUpdate;
    }

    public String getSubmeterSuffix() {
        if (StringUtils.isNotBlank(goodsCode)) {
            submeterSuffix = goodsCode.charAt(goodsCode.length() - 1) + "";
        }
        return submeterSuffix;
    }
    /*public void setSubmeterSuffix(String submeterSuffix) {
        this.submeterSuffix = submeterSuffix;
    }*/

    public Integer getIsUpdateAlias() {
        return isUpdateAlias;
    }

    public void setIsUpdateAlias(Integer isUpdateAlias) {
        this.isUpdateAlias = isUpdateAlias;
    }
}
