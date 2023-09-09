package pos.servlet;

import pos.bo.BOFactory;
import pos.bo.custom.CustomerBO;
import pos.dto.CustomerDTo;
import pos.util.ResponseUtil;

import javax.json.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet(urlPatterns = {"/pages/customer"})

public class CustomerServlet extends HttpServlet {
    private CustomerBO customerBO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        customerBO= (CustomerBO) BOFactory.getBoFactory().getBoType(BOFactory.BoType.Customer);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/samadhi", "root", "1234");
//            PreparedStatement pstm=connection.prepareStatement("select *from customer");
//            ResultSet rst=pstm.executeQuery();

            List<CustomerDTo> allCustomer = customerBO.getAllCustomer();

            resp.addHeader("Content-Type", "application/json");
            resp.addHeader("Access-Control-Allow-Origin","*");

            JsonArrayBuilder allCus= Json.createArrayBuilder();

//            while ((rst.next())){
//                String id =rst.getString(1);
//                String name =rst.getString(2);
//                String address=rst.getString(3);

            for (CustomerDTo cus :allCustomer) {
                JsonObjectBuilder customerBuilder=Json.createObjectBuilder();
                customerBuilder.add("id",cus.getId());
                customerBuilder.add("name",cus.getName());
                customerBuilder.add("address",cus.getAddress());
                allCus.add(customerBuilder.build());
            }

            resp.setContentType("application/json");
            resp.getWriter().print(ResponseUtil.genJson("Success","Loaded",allCus.build()));

        } catch (ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        resp.addHeader("Content-Type", "application/json");
        resp.addHeader("Access-Control-Allow-Origin","*");

        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/samadhi", "root", "1234");
//            PreparedStatement pstm=connection.prepareStatement("insert into customer values (?,?,?)");
//
//            pstm.setObject(1, cusId);
//            pstm.setObject(2, cusName);
//            pstm.setObject(3, cusAddress);
            String cusId=req.getParameter("cusID");
            String cusName=req.getParameter("cusName");
            String cusAddress= req.getParameter("cusAddress");


            if (customerBO.saveCustomer(new CustomerDTo(cusId,cusName,cusAddress))){
//                JsonObjectBuilder objectBuilder=Json.createObjectBuilder();
//                objectBuilder.add("state","OK");
//                objectBuilder.add("message","Successfully Added ");
//                objectBuilder.add("data"," ");
                resp.getWriter().print(ResponseUtil.genJson("Success","Successfully Added"));
            }else {
                resp.getWriter().print(ResponseUtil.genJson("error","Customer Added Fail"));
            }



        }catch (SQLException e){
//            JsonObjectBuilder objectBuilder=Json.createObjectBuilder();
//            objectBuilder.add("state","Error");
//            objectBuilder.add("message",e.getMessage());
//            objectBuilder.add("data"," ");
//            resp.setStatus(400);
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));

        }



    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader=Json.createReader(req.getReader());
        JsonObject customerObject=reader.readObject();

        String cusId=customerObject.getString("id");
        String cusName = customerObject.getString("name");
        String cusAddress =customerObject.getString("address");
        CustomerDTo customerDTO=new CustomerDTo(cusId,cusName,cusAddress);

        resp.addHeader("Content-Type", "application/json");
        resp.addHeader("Access-Control-Allow-Origin","*");

        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/samadhi", "root", "1234");
//            PreparedStatement pstm2=connection.prepareStatement("update customer set name=? ,address=? where id=?");
//
//            pstm2.setObject(3,cusId);
//            pstm2.setObject(1,cusName);
//            pstm2.setObject(2,cusAddress);

            if (customerBO.updateCustomer(customerDTO)){
//                JsonObjectBuilder objectBuilder=Json.createObjectBuilder();
//                objectBuilder.add("state","OK");
//                objectBuilder.add("message","Successfully Added ");
//                objectBuilder.add("data"," ");
//                resp.getWriter().print(objectBuilder.build());
                resp.getWriter().print(ResponseUtil.genJson("Success", "Customer Updated..!"));
            }else{
                resp.getWriter().print(ResponseUtil.genJson("Failed", "Customer Updated Failed..!"));
            }

        } catch (ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }catch (SQLException e){
//            JsonObjectBuilder objectBuilder=Json.createObjectBuilder();
//            objectBuilder.add("state","Error");
//            objectBuilder.add("message",e.getMessage());
//            objectBuilder.add("data"," ");
//            resp.setStatus(400);
//            resp.getWriter().print(objectBuilder.build());
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }



    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String cusId=req.getParameter("id");


        resp.addHeader("Content-Type", "application/json");
        resp.addHeader("Access-Control-Allow-Origin","*");

        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/samadhi", "root", "1234");
//            PreparedStatement pstm3=connection.prepareStatement("delete from customer where id=?");
//
//            pstm3.setObject(1, cusId);


            if (customerBO.deleteCustomer(cusId)){
//                JsonObjectBuilder objectBuilder=Json.createObjectBuilder();
//                objectBuilder.add("state","OK");
//                objectBuilder.add("message","Successfully Added ");
//                objectBuilder.add("data"," ");
//                resp.getWriter().print(objectBuilder.build());
                resp.getWriter().print(ResponseUtil.genJson("Success", "Customer Deleted..!"));
            }else{
                resp.getWriter().print(ResponseUtil.genJson("Failed", "Customer Delete Failed..!"));
            }

        } catch (ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }catch (SQLException e){
//            JsonObjectBuilder objectBuilder=Json.createObjectBuilder();
//            objectBuilder.add("state","Error");
//            objectBuilder.add("message",e.getMessage());
//            objectBuilder.add("data"," ");
//            resp.setStatus(400);
//            resp.getWriter().print(objectBuilder.build());
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Access-Control-Allow-Methods","PUT");
        resp.addHeader("Access-Control-Allow-Methods","DELETE");
        resp.addHeader("Access-Control-Allow-Headers","Content-type");
    }
}
