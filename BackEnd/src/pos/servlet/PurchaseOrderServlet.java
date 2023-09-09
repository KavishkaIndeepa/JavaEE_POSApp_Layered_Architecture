package pos.servlet;

import pos.util.ResponseUtil;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = {"/pages/placeOrder"})
public class PurchaseOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String orderID = req.getParameter("oid");

            //Initializing connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samadhi", "root", "1234");
            PreparedStatement pstm = connection.prepareStatement("select orders.oid,orders.date,orders.id," +
                    "orderDetails.code,orderDetails.qty,orderDetails.unitPrice from orders inner join orderDetails " +
                    "on orders.oid = orderDetails.oid where orders.oid=?");
            pstm.setObject(1, orderID);

            ResultSet rst = pstm.executeQuery();

            JsonArrayBuilder allOrders = Json.createArrayBuilder();
            while (rst.next()) {
                String oid = rst.getString(1);
                String date = rst.getString(2);
                String customerID = rst.getString(3);
                String itemCode = rst.getString(4);
                String qty = rst.getString(5);
                String unitPrice = rst.getString(6);

                JsonObjectBuilder orders = Json.createObjectBuilder();
                orders.add("oid", oid);
                orders.add("date", date);
                orders.add("id", customerID);
                orders.add("code", itemCode);
                orders.add("qtyOnHand", qty);
                orders.add("unitPrice", unitPrice);

                allOrders.add(orders.build());
            }
            //create the response Object
            resp.getWriter().print(ResponseUtil.genJson("Success", "Loaded", allOrders.build()));
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

        JsonReader reader=Json.createReader(req.getReader());
        JsonObject orderJsonOb = reader.readObject();

        String oid=orderJsonOb.getString("oid");
        String date=orderJsonOb.getString("date");
        String cusId=orderJsonOb.getString("id");


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samadhi", "root", "sanu1234");
            connection.setAutoCommit(false);

            resp.addHeader("Content-Type","application/json");
            resp.addHeader("Access-Control-Allow-Origin","*");

            PreparedStatement pstm = connection.prepareStatement("insert into orders values (?,?,?)");
            pstm.setObject(1,oid);
            pstm.setObject(2,date);
            pstm.setObject(3,cusId);

            if (!(pstm.executeUpdate() >0)){
                connection.rollback();
                connection.setAutoCommit(true);
                throw new SQLException("Order Not Added");
            }

            JsonArray orderDetails = orderJsonOb.getJsonArray("orderDetails");

            for (JsonValue orderDetail : orderDetails){
                JsonObject obObject = orderDetail.asJsonObject();
                String itemCode = obObject.getString("code");
                String qty = obObject.getString("qty");
                String avQty = obObject.getString("avQty");
                String unitPrice = obObject.getString("price");


                PreparedStatement pstm2 = connection.prepareStatement("insert into orderDetails values(?,?,?,?)");
                pstm2.setObject(1,oid);
                pstm2.setObject(2,itemCode);
                pstm2.setObject(3,qty);
                pstm2.setObject(4,unitPrice);

                if(!(pstm2.executeUpdate() > 0)){
                    connection.rollback();
                    connection.setAutoCommit(true);
                    throw new SQLException("Order Details Not Added");
                }

                PreparedStatement pstm3 = connection.prepareStatement("update item set qtyOnHand=? where code=?");
                pstm3.setObject(2,itemCode);
                int availableQty = Integer.parseInt((avQty));
                int purchasingQty = Integer.parseInt((qty));
                pstm3.setObject(1,availableQty-purchasingQty);

                if (!(pstm3.executeUpdate() > 0)) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    throw new SQLException("ItemDTO cannot be updated");
                }

            }

            connection.rollback();
            connection.setAutoCommit(true);
            resp.getWriter().print(ResponseUtil.genJson("Success", "Successfully Added.!"));

        } catch (ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
    }
}
