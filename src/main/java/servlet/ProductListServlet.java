package servlet;

import bean.Product;
import dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductListServlet extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> products = new ProductDAO().list();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println(product.getId() + "   " + product.getName() + "   " + product.getPrice());
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("/list/listProduct.jsp").forward(request, response);
    }

}
