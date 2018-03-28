/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.urlrewriting;

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
@WebServlet(name = "URLRewritingServlet", urlPatterns = {"/urlrewrite"})
public class URLRewritingServlet extends HttpServlet {

    List<String> puneList = new ArrayList<String>();
    List<String> mumbaiList = new ArrayList<String>();

    public void init() {

        puneList.add("Shaniwar Wada");
        puneList.add("Vishrambaug Wada");
        puneList.add("Sinhagad Fort");
        puneList.add("Lonavla");
        puneList.add("Katraj Snake Park");
        puneList.add("Balgandharv Rang Mandir");
        puneList.add("Sarasbaug");
        puneList.add("Pashan Lake");
        puneList.add("Pataleshwar Temple");
        puneList.add("Empress Garden");

        mumbaiList.add("Byculla");
        mumbaiList.add("Powai Lake");
        mumbaiList.add("Vihar Lake");
        mumbaiList.add("Borivali National Park");
        mumbaiList.add("Juhu Chowpaty");
        mumbaiList.add("Dadar");
        mumbaiList.add("VT");
        mumbaiList.add("Parel");
        mumbaiList.add("Mulund");
        mumbaiList.add("Vikhroli");

    }

    protected void showMainPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        System.out.println("Character Encoding :" + response.getCharacterEncoding());

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Famous Destinations</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("Choose your city:");
        out.println("<br><a href='?city=Pune'>Pune</a>");
        out.println("<br><a href='?city=Mumbai'>Mumbai</a>");
        out.println("</body>");
        out.println("</html>");

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

        String city = request.getParameter("city");

        if ("pune".equalsIgnoreCase(city) || "mumbai".equalsIgnoreCase(city)) {
            showFamousDestinations(request, response, city);
        } else {
            showMainPage(request, response);
        }
    }

    private void showFamousDestinations(HttpServletRequest request, HttpServletResponse response, String city) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Famous Attraction Of " + city + "</title>");
        out.println("<head>");
        out.println("<body>");
        int start = 0;
        int pageNumber;
        if (request.getParameter("page") == null) {
            pageNumber = 1;
            start = 5 * pageNumber - 5;
        } else {
            pageNumber = Integer.parseInt(request.getParameter("page"));
            start = 5 * pageNumber - 5;
        }

        int pageNumberInLink = (pageNumber == 1) ? 2 : 1;

        if (city.equalsIgnoreCase("pune")) {
            out.println("<br>Top Ten Attractions Of " + city);
            out.println("<br><br>");
            for (int i = start; i < start + 5; i++) {
                out.println("</br>" + puneList.get(i));
            }
            out.println("<br><br>");
            out.println("<br><a href='?city=Pune&page=" + pageNumberInLink + "'>Pune: Page " + pageNumberInLink + "</a>");
           // out.println("<br><a href='?city=Mumbai&page=1'>Mumbai</a>");

        } else {

            out.println("<br>Top Ten Attractions Of " + city);
            out.println("<br><br>");

            for (int i = start; i < start + 5; i++) {
                out.println("</br>" + mumbaiList.get(i));
            }
            out.println("<br><br>");
            //out.println("<br><a href='?city=Pune&page=1'>Pune</a>");
            out.println("<br><a href='?city=Mumbai&page=" + pageNumberInLink + "'>Mumbai: Page " + pageNumberInLink + "</a>");
        }

        out.println("</body>");
        out.println("</html>");

    }

}
