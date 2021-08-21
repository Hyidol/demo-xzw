package com.pro.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author Yuhua
 * @since 21.8.4 12:36
 */
@TableName
public class Member {

    @TableId(type = IdType.AUTO)
    @TableField("memberId")
    private Long memberId;
    private String username;
    private String password;
    private Integer salt;
    private Date createTime;
    private String nickName;

    public Member() {
    }

    public Member(String username, String password, Integer salt, Date createTime, String nickName) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.createTime = createTime;
        this.nickName = nickName;
    }

    public Member(Long memberId, String username, String password, Integer salt, Date createTime, String nickName) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.createTime = createTime;
        this.nickName = nickName;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt=" + salt +
                ", createTime=" + createTime +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}