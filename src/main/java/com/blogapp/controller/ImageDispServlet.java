package com.blogapp.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/display")
public class ImageDispServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int imageId = Integer.parseInt(request.getParameter("imageId"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blogapp", "root", "root");

            String sql = "SELECT * FROM media WHERE `mediaId` = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, imageId);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                byte[] imageBytes = resultSet.getBytes("file_data");
                response.setContentType("image/jpeg");
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(imageBytes);
                outputStream.flush();
            } else {
                response.getWriter().println("Image not found!");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Failed to retrieve image!");
        }
    }
}
