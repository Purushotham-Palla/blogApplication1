package com.blogapp.model;

public class BlogPost {
    private int postId;
    private String postTitle;
    private String postContent;
    private int imageId; 
    private byte[] imageBytes;
//    private String videoUrl;
    private int userId;
    private boolean edited;

    public BlogPost() {
    }
    
    

    public BlogPost(String postTitle, String postContent) {
    	super();
    	this.postTitle = postTitle;
    	this.postContent = postContent;
    }

    

	//for to edit and getOnepost the post
	public BlogPost(int postId, String postTitle, String postContent,int userId) {
		
		this.postId = postId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.userId = userId;
	}
	
	//to update the post
	public BlogPost(int postId, String postTitle, String postContent,boolean edited) {
		
		this.postId = postId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.edited = edited;
	}
	
	//to create the post
	public BlogPost(int userId, String postTitle, String postContent) {
		
		this.userId = userId;
		this.postTitle = postTitle;
		this.postContent = postContent;
	}


	public BlogPost(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}


	public BlogPost(int imageId, byte[] imageBytes) {
		// TODO Auto-generated constructor stub
		this.imageId = imageId;
		this.imageBytes = imageBytes;
	}


	public BlogPost(int postId, String postTitle, String postContent, int mediaId, byte[] imageBytes, int userId) {

		this.postId = postId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.imageId = mediaId;
		this.userId = userId;
		this.imageBytes = imageBytes;
		
//		media url in byte[] format
	
	}


	public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getUserId() {
        return userId;
    }
//
    public void setUserId(int userId) {
        this.userId = userId;
    }


	public boolean isEdited() {
		return edited;
	}


	public void setEdited(boolean edited) {
		this.edited = edited;
	}


	public byte[] getImageBytes() {
		return imageBytes;
	}


	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}


	public int getImageId() {
		return imageId;
	}


	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
}

