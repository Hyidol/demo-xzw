package com.pro.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author Yuhua
 * @since 21.8.2 9:12
 */
@TableName("book")
public class Book {
    @TableId(type = IdType.AUTO)
    @TableField("bookId")
    private Integer bookId;
    private Long categoryId;
    private String bookName;
    private String subTitle;
    private String author;
    private String cover; //图书的图片
    private String description; //描述
    private float evaluationScore; //评分
    private Integer evaluationQuantity; //评价数量

    public Book() {
    }

    public Book(Long categoryId, String bookName, String subTitle, String author, String cover, String description, float evaluationScore, Integer evaluationQuantity) {
        this.categoryId = categoryId;
        this.bookName = bookName;
        this.subTitle = subTitle;
        this.author = author;
        this.cover = cover;
        this.description = description;
        this.evaluationScore = evaluationScore;
        this.evaluationQuantity = evaluationQuantity;
    }

    public Book(Integer bookId, Long categoryId, String bookName, String subTitle, String author, String cover, String description, float evaluationScore, Integer evaluationQuantity) {
        this.bookId = bookId;
        this.categoryId = categoryId;
        this.bookName = bookName;
        this.subTitle = subTitle;
        this.author = author;
        this.cover = cover;
        this.description = description;
        this.evaluationScore = evaluationScore;
        this.evaluationQuantity = evaluationQuantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", categoryId=" + categoryId +
                ", bookName='" + bookName + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", author='" + author + '\'' +
                ", cover='" + cover + '\'' +
                ", description='" + description + '\'' +
                ", evaluationScore=" + evaluationScore +
                ", evaluationQuantity=" + evaluationQuantity +
                '}';
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(float evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

    public Integer getEvaluationQuantity() {
        return evaluationQuantity;
    }

    public void setEvaluationQuantity(Integer evaluationQuantity) {
        this.evaluationQuantity = evaluationQuantity;
    }
}