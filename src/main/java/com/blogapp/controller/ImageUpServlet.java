package com.blogapp.controller;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;




/*			Not used for this project
 * 
 *
 * 			Its for Image uploading Test purpose
 */



@WebServlet("/upload")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class ImageUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get image name
        String imageName = request.getParameter("imageName");

        // Get the uploaded file part
        Part filePart = request.getPart("imageFile");

        if (filePart != null) {
            // Obtain the input stream of the uploaded file
            InputStream inputStream = filePart.getInputStream();

            // Insert image into the database
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
    		 	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/image_db","root","root");
            	
                String sql = "INSERT INTO images (name, image) VALUES (?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, imageName);
                if (inputStream != null) {
                    statement.setBlob(2, inputStream);
                }
                int row = statement.executeUpdate();
                if (row > 0) {
                	response.sendRedirect("display?imageName="+imageName);
//                   response.getWriter().println("Image uploaded successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
//                response.getWriter().println("Failed to upload image!");
			}
            catch (ClassNotFoundException e) {
                e.printStackTrace();
//                response.getWriter().println("Failed to upload image!");
			}
        }
    }
}
