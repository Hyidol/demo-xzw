package com.pro.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author Yuhua
 * @since 21.8.4 11:11
 */
@TableName
public class Evaluation {
    @TableId(type = IdType.AUTO)
    @TableField("evaluationId")
    private Long evaluationId;
    private Long bookId;
    private Long memberId;
    private String content;
    private Integer score;
    private Date createTime;
    private Integer enjoy;
    private String statu;//状态 审核状态，enable 有效，disable 无效
    private Date disableTime;
    private String disableReason;

    //我们需要在这里，增加一些和表不对应的属性
    @TableField(exist = false)//表示 数据库表格中 不存在对应的字段，不会参与到sql的自动生成
    private Book book;//这个对象是由我们手动来查的

    @TableField(exist = false)
    private Member member;

    public Evaluation() {
    }

    public Evaluation(Long bookId, Long memberId, String content, Integer score, Date createTime, Integer enjoy, String statu, Date disableTime, String disableReason) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.content = content;
        this.score = score;
        this.createTime = createTime;
        this.enjoy = enjoy;
        this.statu = statu;
        this.disableTime = disableTime;
        this.disableReason = disableReason;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEnjoy() {
        return enjoy;
    }

    public void setEnjoy(Integer enjoy) {
        this.enjoy = enjoy;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public String getDisableReason() {
        return disableReason;
    }

    public void setDisableReason(String disableReason) {
        this.disableReason = disableReason;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "evaluationId=" + evaluationId +
                ", bookId=" + bookId +
                ", memberId=" + memberId +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", createTime=" + createTime +
                ", enjoy=" + enjoy +
                ", statu='" + statu + '\'' +
                ", disableTime=" + disableTime +
                ", disableReason='" + disableReason + '\'' +
                '}';
    }

    public Evaluation(Long evaluationId, Long bookId, Long memberId, String content, Integer score, Date createTime, Integer enjoy, String statu, Date disableTime, String disableReason) {
        this.evaluationId = evaluationId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.content = content;
        this.score = score;
        this.createTime = createTime;
        this.enjoy = enjoy;
        this.statu = statu;
        this.disableTime = disableTime;
        this.disableReason = disableReason;
    }
}