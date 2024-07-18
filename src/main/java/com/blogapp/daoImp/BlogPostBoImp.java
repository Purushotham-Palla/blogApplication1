package com.blogapp.daoImp;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.blogapp.dao.BlogPostBo;
import com.blogapp.model.BlogPost;

public class BlogPostBoImp implements BlogPostBo {

	private static Connection connection=null;
	private static PreparedStatement prepareStatement;
	private static Statement statement;
	private static ResultSet res;
	
	
	private final static String INSERT_POST_QUERY = "INSERT into `posts`(`userId`,`title`,`content`) values(?,?,?)";
	
	private final static String UPDATE_POST_QUERY1 = "UPDATE `posts` SET `title`= ? ,`content`=? WHERE `id` = ?";
	
	private final static String UPDATE_MEDIA_QUERY1 = "UPDATE `media` SET `file_data`= ? WHERE `postId` = ?";
	
	private final static String GET_POST_QUERY = "SELECT  * FROM  `posts` WHERE `id` = ?";
	
	private final static String DELETE_POST_QUERY = "DELETE FROM `posts` WHERE `id` = ?";
	
//	private final static String GET_ALL_POSTS_QUERY = "SELECT * FROM  `posts` WHERE `userId` = ?";
	
	private final static String GET_ALL_POSTS_QUERY =  "SELECT * FROM  `posts` INNER JOIN `media` on `id` = `postId` WHERE userId = ?";
	
	private final static String SEARCH_POSTS_BY_TITLE_QUERY = "SELECT * FROM `posts` INNER JOIN `media` ON `id` = `postId` WHERE `userId` = ? AND `title` LIKE ? ORDER BY `title`";

	
	
	public static final BlogPostBoImp objBlogPostBoImp() {
		return new BlogPostBoImp();
	}
		
	public BlogPostBoImp() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blogapp","root","root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}

	}

	public int savePost(BlogPost bp,InputStream inputStream) {

		try{
			
			prepareStatement = connection.prepareStatement(INSERT_POST_QUERY,Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setInt(1, bp.getUserId());
			prepareStatement.setString(2, bp.getPostTitle());
			prepareStatement.setString(3, bp.getPostContent());
			
			int row1 = prepareStatement.executeUpdate();
			int row2=0;
			
			 ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
		        if (generatedKeys.next()) {
		            int postId = generatedKeys.getInt(1);
		            
		            // Use the postId as needed, for example, insert into the media table
		            // insertIntoMediaTable(postId, otherParams);
		            System.out.println("postId "+postId);
		            
		            String sql = "INSERT INTO `media` (`postId`,`file_data`) VALUES (?, ?)";
		            prepareStatement = connection.prepareStatement(sql);
		            prepareStatement.setInt(1, postId);
		            if (inputStream != null) {
		            	prepareStatement.setBlob(2, inputStream);
	                }
		             row2 = prepareStatement.executeUpdate();
		            
		        }
			
			
			return row1+row2;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int updatePost(BlogPost bp, InputStream inputStream) {
		try {
//			UPDATE `posts` SET `title`= ? ,`content`=? WHERE `id` = ?";
			prepareStatement = connection.prepareStatement(UPDATE_POST_QUERY1);
			
			prepareStatement.setString(1, bp.getPostTitle());
			prepareStatement.setString(2, bp.getPostContent());
			prepareStatement.setInt(3, bp.getPostId());
			
			int row1 = prepareStatement.executeUpdate();
			
//			String sql = "UPDATE `media` SET `file_data`= ?" ;
            prepareStatement = connection.prepareStatement(UPDATE_MEDIA_QUERY1);
            
            int row2=0;
            if (inputStream != null) {
            	prepareStatement.setBlob(1, inputStream);
            }
            prepareStatement.setInt(2, bp.getPostId());
             row2 = prepareStatement.executeUpdate();
            
			return row1+row2;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;

	}

	


	
	public int deletePost(int postId) {
		try {
			prepareStatement = connection.prepareStatement(DELETE_POST_QUERY);
			
			prepareStatement.setInt(1, postId);
			
			int row1 = prepareStatement.executeUpdate();
			
			String deleteMediaQuery = "DELETE FROM `media` WHERE `postId` = ?";
			prepareStatement= connection.prepareStatement(deleteMediaQuery) ;
			prepareStatement.setInt(1, postId);
			int row2 = prepareStatement.executeUpdate();
			
			return row1+row2;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public BlogPost getOnePost(int postId) {

		BlogPost bp = null;
		
		try { 

			prepareStatement = connection.prepareStatement(GET_POST_QUERY);
			
			
			prepareStatement.setInt(1, postId);
			
			res  = prepareStatement.executeQuery();
			
			if(res.next()){
				
//				int id = res.getInt("id");
				String title = res.getString("title");
				String content = res.getString("content");
//				Date date = res.getDate("date");
				int userId = res.getInt("userId");
//				int id, String title, String content,int userId) {
				
				bp = new BlogPost(postId,title,content,userId);
			}
			return bp;
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}

	public List<BlogPost> getAllPost(int userId) {

		ArrayList<BlogPost> list = new ArrayList<BlogPost>();
		
		try {
//			statement = connection.createStatement();
			prepareStatement = connection.prepareStatement(GET_ALL_POSTS_QUERY);
			prepareStatement.setInt(1, userId);
			res= prepareStatement.executeQuery();
			
			while(res.next())
			{
				
				int postId = res.getInt("id");
				String postTitle = res.getString("title");
				String postContent = res.getString("content");
				int mediaId = res.getInt("mediaId");
				byte[] imageBytes = res.getBytes("media.file_data");
//				Date date = res.getDate("date");
				int userid = res.getInt("userId");
				BlogPost bp = new BlogPost(postId,postTitle,postContent,mediaId,imageBytes,userid);

				list.add(bp);
				
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

	public List<BlogPost> searchPosts(String searchTitle,int userId) {
		// TODO Auto-generated method stub
		String title = searchTitle.toLowerCase();
		 List<BlogPost> posts = new ArrayList<BlogPost>();
		 try {
			 prepareStatement = connection.prepareStatement(SEARCH_POSTS_BY_TITLE_QUERY);
			 
			 
			 prepareStatement.setInt(1, userId);
			 prepareStatement.setString(2, "%" + title + "%"); // Using wildcard for LIKE query
		     res = prepareStatement.executeQuery();
			 
			 
		     while (res.next()) {
		            BlogPost post = new BlogPost();
		            int postId = res.getInt("postId");;
		            String postTitle = res.getString("postTitle");
		            String postContent = res.getString("postContent");
		            int mediaId = res.getInt("mediaId");
		            
		            byte[] imageBytes = res.getBytes("media.file_data");
//					Date date = res.getDate("date");
					int userid = res.getInt("userId");
		            
		            
		            BlogPost bp = new BlogPost(postId,postTitle,postContent,mediaId,imageBytes,userid);
		            posts.add(post);
		        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	
	public static List<BlogPost> getPostsSortedByTitle(String searchTitle,int userId) {

		return null;
		
	}

	public static List<BlogPost> getPostsSortedByDate(String sortBy, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BlogPost> getPostsForPage(int page, int recordsPerPage) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
//	public int filePartUpload(InputStream inputStream,int postId){
//	
//	String sql = "INSERT INTO images (`postId`,`file_data`) VALUES (?, ?)";
////	String sql = "INSERT INTO images (`file_data`) VALUES (?)";
//	 try {
//		 prepareStatement = connection.prepareStatement(sql);
//         if (inputStream != null) {
//        	 prepareStatement.setInt(1,postId );
//        	 prepareStatement.setBlob(1, inputStream);
//         }
//         int row = prepareStatement.executeUpdate();
//         if (row > 0) {
//        	 
////             response.getWriter().println("Image uploaded successfully!");
//        	 return row;
//         }
//     } catch (SQLException e) {
//         e.printStackTrace();
////         response.getWriter().println("Failed to upload image!");
//     }
//	
//	
//	return 0;
//	
//}

// public BlogPost filePartDisplay(int id)
//
//{	BlogPost bp=null;
//	String sql = "SELECT * FROM media WHERE `id` = ?";
//	 try {
//            prepareStatement = connection.prepareStatement(sql);
//            prepareStatement.setInt(1, id);
//
//            res= prepareStatement.executeQuery();
//            if (res.next()) {
//            	int imageId = res.getInt("id");
//            	byte[] imageBytes = res.getBytes("file_data");
////                response.setContentType("image/jpeg");
////                int 
//            	bp = new BlogPost(imageId,imageBytes);
//                return bp;
//            } else {
////                response.getWriter().println("Image not found!");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
////            response.getWriter().println("Failed to retrieve image!");
//        }
//	 return bp;
//}
//	public int updatePost(BlogPost bp) {
//
//		try {
////			UPDATE `posts` SET `title`= ? ,`content`=? WHERE `id` = ?";
//			prepareStatement = connection.prepareStatement(UPDATE_POST_QUERY1);
//			
//			prepareStatement.setString(1, bp.getPostTitle());
//			prepareStatement.setString(2, bp.getPostContent());
//			prepareStatement.setInt(3, bp.getPostId());
//			
//			int i = prepareStatement.executeUpdate();
//			return i;
//			
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		return 0;
//
//	}


	
	 
	
		
	
	


}

