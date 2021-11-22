package top.meethigher.jpaentitylistenertest.common;

import javax.persistence.Embeddable;
import java.util.Date;

/**
 * @author chenchuancheng
 * @since 2021/11/19 9:48
 */
@Embeddable
public class CommonField {
    private Date createTime;

    private Date updateTime;

    private String createUserId;

    private String createUserName;

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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @Override
    public String toString() {
        return "CommonField{" +
                "createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUserId='" + createUserId + '\'' +
                ", createUserName='" + createUserName + '\'' +
                '}';
    }
}
