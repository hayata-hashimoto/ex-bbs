package com.example.domain;

public class Comment {
	private Integer id;
	private String name;
	private String content;
	private Integer article_id;
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", article_id=" + article_id + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getArticle_id() {
		return article_id;
	}

	public void setArticleId(Integer article_id) {
		this.article_id = article_id;
	}
	
	
}
