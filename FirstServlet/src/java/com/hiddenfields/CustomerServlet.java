/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiddenfields;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rasakpal
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {"/customer", "/editCustomer", "/updateCustomer"})
public class CustomerServlet extends HttpServlet {

    private List<Customer> customers = new ArrayList<Customer>();

    @Override
    public void init() throws ServletException {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("QuickCheck");
        customer1.setCity("Pune");
        customers.add(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Acme");
        customer2.setCity("Bangalore");
        customers.add(customer2);
    }

    private void sendCustomerInfo(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Customer Details</title>");
        out.println("</head>");
        out.println("<body><h2>Customers:</h2>");
        out.println("<ul>");
        for (Customer customer : customers) {
            //out.println("<li>xyz<a href='editCustomer?id=1'>edit</a></li>");
            //     out.println("<li>" + customer.getName() + "(" + customer.getCity() + ")" + "<a href='editCustomer?id=" + customer.getId() + "'>edit</a></li>");
            out.println("<li>" + customer.getName() + " (" + customer.getCity() + ") " + "<a href='editCustomer?id=" + customer.getId() + "'>edit</a></li>");
            out.println("<br>");
            //out.println(customers.isEmpty()); 
            //out.println(customers.get(1).getName() + "(" + customers.get(1).getCity() + ")" + "<a href='editCustomer?id=" + customers.get(1).getId() + "'>edit</a>");
        }
        //out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }

    private Customer getCustomer(int customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    private void sendEditCustomerForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int customerId = 0;
        //customerId = Integer.getInteger(request.getParameter("id"));
        customerId = Integer.parseInt(request.getParameter("id"));
        Customer customer = getCustomer(customerId);
        if (customer != null) {
            //out.println("!DOCTYPE html");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Edit Customer</title>");
            out.println("</head>");
            out.println("<body><h2>Edit Customer:</h2>");
            //out.println("<form method='get' action='updateCustomer'>");//doGet() needs to be modified to handle this request ..
            out.println("<form method='post' action='updateCustomer'>");
            out.println("<input type='hidden' name='id' value='"+ customerId+"'/>");//HIDDEN FIELD
            out.println("<table>");
            out.println("<tr><td>Customer Name:</td><td>" + "<input name='name' value='" + customer.getName() + "'/></td></tr>");
            out.println("<tr><td>City:</td><td>" + "<input name='city' value ='" + customer.getCity() + "'/></td></tr>");
            out.println("<tr><td>" + "<input type='submit' value='Update'/></td></tr>");
            out.println("</table>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } else {
            out.println("!DOCTYPE html");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Message</title>");
            out.println("</head>");
            out.println("<body><h2>No Record Found</h2>");
            out.println("Customer record was not found in database.");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
            out.println("<title>Servlet CustomerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);        
        String uri = request.getRequestURI();
        if (uri.endsWith("/customer")) {
            sendCustomerInfo(response);
        } else if (uri.endsWith("/editCustomer")) {
            sendEditCustomerForm(request, response);
        }
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
        //processRequest(request, response);
        
        int customerId = Integer.parseInt(request.getParameter("id"));//HIDDEN FIELD IS CAPTURED
        Customer customer = getCustomer(customerId);
        if(customer != null){            
            customer.setName(request.getParameter("name"));
            customer.setCity(request.getParameter("city"));
            sendCustomerInfo(response);
        }
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
