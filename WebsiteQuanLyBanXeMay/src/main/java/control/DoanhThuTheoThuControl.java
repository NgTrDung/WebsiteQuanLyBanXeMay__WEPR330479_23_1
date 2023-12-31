/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import entity.GioHang;
import entity.DanhMuc;
import entity.HoaDon;
import entity.XeMay;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "DoanhThuTheoThuControl", urlPatterns = {"/doanhThuTheoThu"})
public class DoanhThuTheoThuControl extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        DAO dao = new DAO();
        
        List<List<Double>> dDoanhThuTuan = dao.totalMoneyDay_Current();
        System.out.print(dDoanhThuTuan);
        
        int n = dDoanhThuTuan.size();
        
        
        double totalMoney1 = dao.Get_Money(1,dDoanhThuTuan);
        double totalMoney2 = dao.Get_Money(2,dDoanhThuTuan);
        double totalMoney3 = dao.Get_Money(3,dDoanhThuTuan);
        double totalMoney4 = dao.Get_Money(4,dDoanhThuTuan);
        double totalMoney5 = dao.Get_Money(5,dDoanhThuTuan);
        double totalMoney6 = dao.Get_Money(6,dDoanhThuTuan);
        double totalMoney7 = dao.Get_Money(7,dDoanhThuTuan);
        request.setAttribute("totalMoney1", totalMoney1);
        request.setAttribute("totalMoney2", totalMoney2);
        request.setAttribute("totalMoney3", totalMoney3);
        request.setAttribute("totalMoney4", totalMoney4);
        request.setAttribute("totalMoney5", totalMoney5);
        request.setAttribute("totalMoney6", totalMoney6);
        request.setAttribute("totalMoney7", totalMoney7);
        
        
        
        request.getRequestDispatcher("DoanhThuTheoThu.jsp").forward(request, response);
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
