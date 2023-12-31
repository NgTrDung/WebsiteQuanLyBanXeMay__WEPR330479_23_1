/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import entity.GioHang;
import entity.XeMay;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "AddCartControl", urlPatterns = {"/addCart"})
public class AddGioHangControl extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8"); 
        int maXe = Integer.parseInt(request.getParameter("pid"));
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if(a == null) {
        	response.sendRedirect("login");
        	return;
        }
        int maAccount = a.getMaAccount();
        int soLuong = Integer.parseInt(request.getParameter("quantity"));
        
        // String size = request.getParameter("size");
        
        DAO dao = new DAO();
        GioHang gioHangExisted = dao.checkGioHangExist(maAccount, maXe);
        XeMay xeMay = dao.getXeMayBymaXe(request.getParameter("pid"));
        
        if(xeMay.getSoLuongCon() <= 0)
        {
        	request.setAttribute("error", "Xe Máy này đã hết hàng!");
       	 	request.getRequestDispatcher("managerCart").forward(request, response);
        }
        else if(xeMay.getSoLuongCon() <= soLuong)
        {
        	request.setAttribute("error", "Số Lượng Xe Máy còn lại không đáp ứng đủ cho bạn!");
       	 	request.getRequestDispatcher("managerCart").forward(request, response);
        }
        else
        {
        	int soLuongExisted;
            if(gioHangExisted != null) {
            	 soLuongExisted = gioHangExisted.getSoLuong();
            	 dao.editsoLuongGioHang(maAccount, maXe, (soLuongExisted + soLuong));
            	 request.setAttribute("mess", "Đã tăng số lượng Sản Phẩm!");
            	 request.getRequestDispatcher("managerCart").forward(request, response);
            }
            else {
            	  dao.insertGioHang(maAccount, maXe, soLuong);
            	  request.setAttribute("mess", "Đã thêm Sản Phẩm vào giỏ hàng!");
            	  request.getRequestDispatcher("managerCart").forward(request, response);
            }
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
