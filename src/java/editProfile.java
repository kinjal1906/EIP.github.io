/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.*;
import logic.logicFunction;
/**
 *
 * @author User
 */
public class editProfile extends HttpServlet 
{
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
 
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        String user="",type="";
        RequestDispatcher rd=null; 
        ArrayList a1=new ArrayList();   
        String query=null;
        try 
        {
            con = logicFunction.getConnection();

            String u1;
            String path="";
            HttpSession s1=request.getSession(false);
            user=s1.getAttribute("userId").toString();
            type=s1.getAttribute("Type").toString();
            if(type.equalsIgnoreCase("admin"))
            {
             query="Select * from admin_detail where admin_Id='"+user.trim()+"'";
             path="/AdminRegistration.jsp?uid="+user;
            }
            if(type.equalsIgnoreCase("Patient"))
            {
             query="select * from child_patient where  Patient_Id='"+user.trim()+"'";   
             path="/ApatientRegistration.jsp?uid="+user;
            }
            if(type.equalsIgnoreCase("HealthcareProvider"))
            {
             query="select * from healthcare_provider_detail where  healthcareProvider_Id='"+user.trim()+"'";
             path="/AhealthcareProviderRegistration.jsp?uid="+user;
            }
            
            out.println("user= "+user);
            stmt=con.createStatement();
            rs=stmt.executeQuery(query); 
            
            if(rs.next())
            {
                
                a1.add(rs.getString("First_Name"));
                a1.add(rs.getString("Last_Name"));
                a1.add(rs.getString("Gender"));
                a1.add(rs.getString("Birth_Date"));
                a1.add(rs.getString("Blood_Group"));
               
                if(type.equalsIgnoreCase("admin") || type.equalsIgnoreCase("HealthcareProvider"))
                {
                     a1.add(rs.getString("Education"));
                     a1.add(rs.getString("Experience"));
                     a1.add(rs.getString("Mobile_No"));
                     a1.add(rs.getString("Email_Id"));
                     a1.add(rs.getString("City"));
   
                }
                else if(type.equalsIgnoreCase("patient"))
                {
                    a1.add(rs.getString("Birth_Time"));
                    a1.add(rs.getString("Birth_Time_Unit"));
                    a1.add(rs.getString("Weight_At_Birth"));
                    a1.add(rs.getString("Height_At_Birth"));
                    a1.add(rs.getString("Heartbit_At_Birth"));
                    a1.add(rs.getString("Delivery_Type"));
                    a1.add(rs.getString("Any_Abnormal_Symptoms"));
                
                }
               
                    a1.add(rs.getString("Description"));
                
            }
            request.setAttribute("data", a1);
            out.println("a1="+a1);

            rd=request.getRequestDispatcher(path);
            
            rd.forward(request, response);  
  
        }
        catch(Exception e)
        {
            out.println(e);
        }
        finally 
        {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
