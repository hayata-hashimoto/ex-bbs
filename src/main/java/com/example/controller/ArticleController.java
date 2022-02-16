package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentService;
	
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleService.findAll();
		for(Article article : articleList) {
			List<Comment> commentList = commentService.findByArticleId(article.getId());
			article.setCommentList(commentList);
		}
		
		model.addAttribute("articleList", articleList);
		return "bbs";
		
		
	}
	
	@RequestMapping("/insert")
	public String insert(ArticleForm form, Model model) {
		Article article = new Article();
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleService.insert(article);
		
		return "redirect:/article";
	}
	
	@RequestMapping("/CommentInsert")
	public String insert(CommentForm form) {
		Comment comment = new Comment();
		comment.setArticleId(Integer.parseInt(form.getArticlce_id()));
		comment.setName(form.getName());
		comment.setContent(form.getContent());
		
		commentService.insert(comment);
		return "redirect:/article";
	}
	
	@RequestMapping("/delete")
	public String delete(Integer id) {
		commentService.deleteByArticleId(id);
		articleService.deleteById(id);
		
		return "redirect:/article";
	}
	
	
}
