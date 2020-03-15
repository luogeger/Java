package com.first.aop.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体基类
 *
 * @author luoxiaoqing
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer id;
/*     protected Integer createId;// 创建者id
    protected Integer updateId;//修改人id
    protected Date    createTime;// 创建日期
    protected Date    updateTime;// 更新日期
    protected Integer deleted;// 是否删除
    protected Integer enabled;// 是否可用
    protected String  extend;// 扩展字段

    //IgnoreAutoGenerate
    protected Integer currentPage = 1;//页码
    //IgnoreAutoGenerate
    protected Integer pageSize = 10;//分页大小

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "DataEntity{" +
                "id=" + id +
                ", createId=" + createId +
                ", updateId=" + updateId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", extend='" + extend + '\'' +
                ", isEnable=" + isEnable +
                ", isDelete=" + isDelete +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }*/
}


