import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

@WebServlet("/Customer")
public class CustomerController extends HttpServlet {

    private CustomerService customerService;

    // Use init() method for initialization of service, not the constructor.
    @Override
    public void init() throws ServletException {
        super.init();
        this.customerService = new CustomerService(); // Initialize service here
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CustomerController doPost");
        JSONObject respJson = new JSONObject();
        Reader reqReader = req.getReader();
        JSONParser parser = new JSONParser();

        try {
            // Parse the incoming JSON request
            org.json.simple.JSONObject reqJson = (org.json.simple.JSONObject) parser.parse(reqReader);

            // Extract customer details from the request JSON
            String email = reqJson.get("email").toString();
            String name = reqJson.get("name").toString();
            int age = Integer.parseInt(reqJson.get("age").toString());

            // Create a customer object and add it
            Customer customer = new Customer(email, name, age);
            customerService.addCustomer(customer);

            // Prepare the success response
            respJson.put("status", "success");
            respJson.put("message", "Customer added successfully");

        } catch (ParseException e) {
            // Handle JSON parsing error
            e.printStackTrace();
            respJson.put("status", "failed");
            respJson.put("message", "Failed to parse the request");
        } catch (Exception e) {
            // Handle any other errors
            e.printStackTrace();
            respJson.put("status", "failed");
            respJson.put("message", "An error occurred");
        } finally {
            // Send the response
            resp.setContentType("application/json");
            resp.getWriter().write(respJson.toString());
        }
    }
}
