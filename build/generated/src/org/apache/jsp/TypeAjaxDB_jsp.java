package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import java.sql.*;

public final class TypeAjaxDB_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    try
    {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/eip";
        con =DriverManager.getConnection(url,"root", "");
        ArrayList a1=new ArrayList();
        Statement st=con.createStatement();
        out.print("kinjal");
        String type=request.getParameter("t");
        String query=null;
             if(type.equalsIgnoreCase("Admin"))
            {
             query="select * from admin_Detail order by User_Name";
            }
            if(type.equalsIgnoreCase("Patient"))
            {
             query="select * from child_patient order by User_Name";    
            }
            if(type.equalsIgnoreCase("HealthcareProvider"))
            {
             query="select * from healthcare_provider_detail order by User_Name";
            }

         
        ResultSet rs= st.executeQuery(query);
        StringBuffer sb;

        while(rs.next())
        {
             sb=new StringBuffer();

            sb.append(rs.getString(1)+"$ "+rs.getString("User_Name"));
            a1.add(sb);
        }
        request.setAttribute("data", a1);
        out.println(a1);
        out.println("kinjal");
    }
    catch(Exception e)
    {
        out.println(e);
    }


      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}