package com.pajk.hlsync.client.model.param;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class HaiNaSpiderContentDTO implements Serializable {
    private static final long serialVersionUID = -4269822382547049480L;

    private String pageUrl; //文章采集地址
    private String pageUrlDomain; //文章采集地址域名
    private String pageTitle; //网页标题
    private String articleTitle; //文章标题
    private String articleAuthor; //文章作者
    private String articleFrom; //文章来源网站
    private String articleContentFrom; //文章中写明的来源名称
    private String articlePublishDate; //文章发布时间
    private String articleContent; //文章正文
    private String articleTextContent; //文章纯文字正文
    private String articleAbstract; //文章摘要   或者是帖子文本
    private String articleKeyword; //文章关键词
    private String articleFingerprint; //文章的语义指纹(唯一ID)
    private String tag; //文章的TAG词
    private String articleImageUrl; //文章中的图片URL    或者是图片，多个逗号分割
    private String article23Class; //文章分类(新华社23分类)
    private String watchSourceUrl;//资讯监控地址  用于与频道关联
    private String showType;        //网易有料文章类型：article, video, svideo
    private int type; //文章类型，与头条类型保持一致
    private String feature;

    private long userId; //健康号
    private String articleClass; //文章分类，如减脂-瘦大腿-
    private String outId;  //外部ID
    private String outType; //外部类型
    private String label; //如官方必读|用户经验


    private String ext1; //扩展1
    private String ext2; //扩展2
    private String ext3; //扩展3

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getArticleClass() {
        return articleClass;
    }

    public void setArticleClass(String articleClass) {
        this.articleClass = articleClass;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageUrlDomain() {
        return pageUrlDomain;
    }

    public void setPageUrlDomain(String pageUrlDomain) {
        this.pageUrlDomain = pageUrlDomain;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleFrom() {
        return articleFrom;
    }

    public void setArticleFrom(String articleFrom) {
        this.articleFrom = articleFrom;
    }

    public String getArticleContentFrom() {
        return articleContentFrom;
    }

    public void setArticleContentFrom(String articleContentFrom) {
        this.articleContentFrom = articleContentFrom;
    }

    public String getArticlePublishDate() {
        return articlePublishDate;
    }

    public void setArticlePublishDate(String articlePublishDate) {
        this.articlePublishDate = articlePublishDate;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleTextContent() {
        return articleTextContent;
    }

    public void setArticleTextContent(String articleTextContent) {
        this.articleTextContent = articleTextContent;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getArticleKeyword() {
        return articleKeyword;
    }

    public void setArticleKeyword(String articleKeyword) {
        this.articleKeyword = articleKeyword;
    }

    public String getArticleFingerprint() {
        return articleFingerprint;
    }

    public void setArticleFingerprint(String articleFingerprint) {
        this.articleFingerprint = articleFingerprint;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getArticleImageUrl() {
        return articleImageUrl;
    }

    public void setArticleImageUrl(String articleImageUrl) {
        this.articleImageUrl = articleImageUrl;
    }

    public String getArticle23Class() {
        return article23Class;
    }

    public void setArticle23Class(String article23Class) {
        this.article23Class = article23Class;
    }

    public String getWatchSourceUrl() {
		return watchSourceUrl;
	}

	public void setWatchSourceUrl(String watchSourceUrl) {
		this.watchSourceUrl = watchSourceUrl;
	}

	@Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }
}
