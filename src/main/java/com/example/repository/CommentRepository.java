package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;


@Repository
public class CommentRepository {
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = new BeanPropertyRowMapper<>(Comment.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public List<Comment> findByArticleId(Integer Article_id){
		String sql = "select * from comments where Article_id = :Article_id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("Article_id", Article_id);
		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
		return commentList;
	}
	
	public void insert(Comment comment) {
		String sql = "insert into comments "
				+ "       ( name,   content, article_id) "
				+ "values (:name, :content, :article_id)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("name", comment.getName())
				.addValue("content", comment.getContent())
				.addValue("article_id", comment.getArticle_id());
		
		template.update(sql, param);
	}
	
	public void deleteByArticleId(int article_id) {
		String sql = "delete from comments where article_id = :article_id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("article_id", article_id);
		
		template.update(sql, param);
	}
}
