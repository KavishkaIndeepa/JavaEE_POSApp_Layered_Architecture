package pos.servlet;

import pos.bo.BOFactory;
import pos.bo.custom.ItemBO;
import pos.dto.ItemDTO;
import pos.util.ResponseUtil;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

@WebServlet(urlPatterns = {"/pages/item"})
public class ItemServlet extends HttpServlet {

    private ItemBO itemBO;

    @Override
    public void init() throws ServletException {
        itemBO = (ItemBO) BOFactory.getBoFactory().getBoType(BOFactory.BoType.Item);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/samadhi", "root", "1234");
//            PreparedStatement pstm=connection.prepareStatement("select *from item");
//            ResultSet rst=pstm.executeQuery();

            List<ItemDTO> allItem = itemBO.getAllItem();

            resp.addHeader("Content-Type", "application/json");
            resp.addHeader("Access-Control-Allow-Origin","*");

            JsonArrayBuilder allItems= Json.createArrayBuilder();

            for (ItemDTO dto:allItem) {
                JsonObjectBuilder itemBuilder=Json.createObjectBuilder();

                itemBuilder.add("code",dto.getCode());
                itemBuilder.add("description",dto.getDescription());
                itemBuilder.add("qtyOnHand",dto.getQtyOnHand());
                itemBuilder.add("unitPrice",dto.getUnitPrice());
                allItems.add(itemBuilder.build());
            }

            resp.setContentType("application/json");
            resp.getWriter().print(ResponseUtil.genJson("Success", "Loaded", allItems.build()));

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
//        String code=req.getParameter("itemCode");
//        String description=req.getParameter("itemName");
//        String qtyOnHand= req.getParameter("itemQty");
//        String unitPrice= req.getParameter("itemPrice");

        resp.setContentType("application/json");

        resp.addHeader("Content-Type", "application/json");
        resp.addHeader("Access-Control-Allow-Origin","*");

        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/samadhi", "root", "1234");
//            PreparedStatement pstm=connection.prepareStatement("insert into item values (?,?,?,?)");
            String code=req.getParameter("itemCode");
            String description=req.getParameter("itemName");
            String qtyOnHand= req.getParameter("itemQty");
            String unitPrice= req.getParameter("itemPrice");

//            pstm.setObject(1, code);
//            pstm.setObject(2, description);
//            pstm.setObject(3, qtyOnHand);
//            pstm.setObject(4, unitPrice);

            if (itemBO.saveItem(new ItemDTO(code, description, qtyOnHand, unitPrice))){
//                JsonObjectBuilder objectBuilder=Json.createObjectBuilder();
//                objectBuilder.add("state","OK");
//                objectBuilder.add("message","Successfully Added ");
//                objectBuilder.add("data"," ");
//                resp.getWriter().print(objectBuilder.build());
                resp.getWriter().print(ResponseUtil.genJson("Success", "Successfully Added.!"));
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonReader reader=Json.createReader(req.getReader());
        JsonObject customerObject=reader.readObject();

        String code=customerObject.getString("code");
        String description = customerObject.getString("description");
        String qtyOnHand = customerObject.getString("qtyOnHand");
        String unitPrice = customerObject.getString("unitPrice");

        resp.addHeader("Content-Type", "application/json");
        resp.addHeader("Access-Control-Allow-Origin","*");

        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/samadhi", "root", "1234");
//            PreparedStatement pstm2=connection.prepareStatement("update item set description=? ,qtyOnHand=?, unitPrice=? where code=?");
//
//            pstm2.setObject(4,code);
//            pstm2.setObject(1,description);
//            pstm2.setObject(2,qtyOnHand);
//            pstm2.setObject(3,unitPrice);

            if (itemBO.updateItem(new ItemDTO(code,description,qtyOnHand,unitPrice))){
//                JsonObjectBuilder objectBuilder=Json.createObjectBuilder();
//                objectBuilder.add("state","OK");
//                objectBuilder.add("message","Successfully Added ");
//                objectBuilder.add("data"," ");
//                resp.getWriter().print(objectBuilder.build());
                resp.getWriter().print(ResponseUtil.genJson("Success", "Item Updated..!"));
            }else{
                resp.getWriter().print(ResponseUtil.genJson("Failed", "Item Updated Failed..!"));
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
        String code=req.getParameter("code");


        resp.addHeader("Content-Type", "application/json");
        resp.addHeader("Access-Control-Allow-Origin","*");

        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/samadhi", "root", "1234");
//            PreparedStatement pstm3=connection.prepareStatement("delete from item where code=?");
//
//            pstm3.setObject(1, code);


            if (itemBO.deleteItem(code)){
//                JsonObjectBuilder objectBuilder=Json.createObjectBuilder();
//                objectBuilder.add("state","OK");
//                objectBuilder.add("message","Successfully Added ");
//                objectBuilder.add("data"," ");
//                resp.getWriter().print(objectBuilder.build());
                resp.getWriter().print(ResponseUtil.genJson("Success", "Item Deleted..!"));
            }else{
                resp.getWriter().print(ResponseUtil.genJson("Failed", "Item Delete Failed..!"));
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
