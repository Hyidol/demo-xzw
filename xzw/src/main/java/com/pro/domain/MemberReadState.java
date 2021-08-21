package com.pro.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author Yuhua
 * @since 21.8.6 9:26
 */
@TableName("readstate")
public class MemberReadState {
    @TableId(type = IdType.AUTO)
    private Long rsId;
    private Long bookId;
    private Long memberId;
    private Integer readState;
    private Date createTime;

    public MemberReadState() {
    }

    public MemberReadState(Long bookId, Long memberId, Integer readState, Date createTime) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.readState = readState;
        this.createTime = createTime;
    }

    public MemberReadState(Long rsId, Long bookId, Long memberId, Integer readState, Date createTime) {
        this.rsId = rsId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.readState = readState;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MemberReadState{" +
                "rsId=" + rsId +
                ", bookId=" + bookId +
                ", memberId=" + memberId +
                ", readState=" + readState +
                ", createTime=" + createTime +
                '}';
    }

    public Long getRsId() {
        return rsId;
    }

    public void setRsId(Long rsId) {
        this.rsId = rsId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getReadState() {
        return readState;
    }

    public void setReadState(Integer readState) {
        this.readState = readState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}