package com.blogapp.dao;

import java.io.InputStream;
import java.util.List;

import com.blogapp.model.BlogPost;

public interface BlogPostBo {

//	int savePost(BlogPost bp);
	int savePost(BlogPost bp,InputStream inputStream);
//	int updatePost(BlogPost bp);
	int deletePost(int postId);
	BlogPost  getOnePost(int postId);
	List<BlogPost > getAllPost(int postId);
	int updatePost(BlogPost bp, InputStream inputStream);
}
