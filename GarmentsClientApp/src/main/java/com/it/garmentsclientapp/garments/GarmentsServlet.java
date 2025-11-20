/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.it.garmentsclientapp.garments;

import jakarta.inject.Inject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aditya Pathak R
 */
@WebServlet(name = "GarmentsServlet", urlPatterns = {"/GarmentsServlet"})
public class GarmentsServlet extends HttpServlet {

    @Inject
    GarmentsService garmentsService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GarmentsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Garments Data</h1>");

            Response res;
            res = garmentsService.getAllGarments();
            if (res != null && res.hasEntity()) {
                List<?> list = res.readEntity(List.class);

                if (list != null && !list.isEmpty()) {
                    out.println("<table>");
                    out.println("<tr><th>GarmentName</th><th>Size</th><th>Category</th><th>Description</th><th>Price</th><th>Stock</th></tr>");

                    for (Object obj : list) {
                        Map<?, ?> map = (Map<?, ?>) obj;
                        out.println("<tr>");
                        out.println("<td>" + map.get("garmentName") + "</td>");
                        out.println("<td>" + map.get("size") + "</td>");
                        out.println("<td>" + map.get("category") + "</td>");
                        out.println("<td>" + map.get("description") + "</td>");
                        out.println("<td>" + map.get("price") + "</td>");
                        out.println("<td>" + map.get("stock") + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                } else {
                    out.println("No data found!");
                }
            }

            out.println("<br/>");

            out.println("<h1>Filter garments data</h1>");
            out.println("<br/>");

            out.println("<form method='get'>");
            out.println("<h1><b>Filter garments data by category.</b></h1>");
            out.println("<br/>");
            out.println("<select name='category'>");
            out.println("<option value=''>Select the category</option>");
            out.println("<option value='mens'>Mens</option>");
            out.println("<option value='womens'>Womens</option>");
            out.println("<option value='children'>Children</option>");
            out.println("</select>");
            out.println("<input type='submit' value='Search'>");
            out.println("</form>");

            out.println("<br/>");

            out.println("<form method='get'>");
            out.println("<h1><b>Filter garments data by price.</b></h1>");
            out.println("<br/>");
            out.println("<select name='price'>");
            out.println("<option value=''>Select the price range</option>");
            out.println("<option value='500-1000'>₹500 - ₹1000</option>");
            out.println("<option value='1001-1500'>₹1001 - ₹1500</option>");
            out.println("<option value='1501-99999'>₹1500+</option>");
            out.println("</select>");
            out.println("<input type='submit' value='Search'>");
            out.println("</form>");
            out.println("<br/>");

            String category = request.getParameter("category");
            String price = request.getParameter("price");

            if (category != null && !category.isEmpty()) {
                res = garmentsService.findGarmentsByByCategory(category);

            } else if (price != null && !price.isEmpty()) {
                String[] values = price.split("-");
                BigInteger min = new BigInteger(values[0]);
                BigInteger max = new BigInteger(values[1]);
                res = garmentsService.findGarmentsByPrice(min, max);

            } else {
                res = garmentsService.getAllGarments();
            }

            if (res != null && res.hasEntity()) {
                List<?> list = res.readEntity(List.class);

                if (list != null && !list.isEmpty()) {
                    out.println("<table>");
                    out.println("<tr><th>GarmentName</th><th>Size</th><th>Category</th><th>Description</th><th>Price</th><th>Stock</th></tr>");

                    for (Object obj : list) {
                        Map<?, ?> map = (Map<?, ?>) obj;
                        out.println("<tr>");
                        out.println("<td>" + map.get("garmentName") + "</td>");
                        out.println("<td>" + map.get("size") + "</td>");
                        out.println("<td>" + map.get("category") + "</td>");
                        out.println("<td>" + map.get("description") + "</td>");
                        out.println("<td>" + map.get("price") + "</td>");
                        out.println("<td>" + map.get("stock") + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                } else {
                    out.println("No data found!");
                }
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
