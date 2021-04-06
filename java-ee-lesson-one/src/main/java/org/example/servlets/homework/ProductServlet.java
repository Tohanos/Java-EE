package org.example.servlets.homework;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private List<Product> productList;
    private ProductFactory productFactory;

    private final String[] productTitles = {"Арбуз", "Абрикос", "Апельсин", "Ананас", "Бекон"};

    @Override
    public void init() {
        productFactory = new ProductFactory();
        productList = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        for (int i = 0; i < 10; i++) {
            productList.add(productFactory.createProduct( i,
                    productTitles[(int) (Math.random() * 5)],
                    (float) (Math.random() * 1000.0)));
        }

        resp.getWriter().println("<h3>Форма для добавления продукта</h3>");
        resp.getWriter().println("<form method='post'>");
        resp.getWriter().println("Введите id продукта: <input type='text' name='id'> <br>");
        resp.getWriter().println("Введите название продукта: <input type='text' name='title'> <br>");
        resp.getWriter().println("Введите стоимость продукта: <input type='text' name='cost'> <br>");
        resp.getWriter().println("<input type='submit'>");
        resp.getWriter().println("</form>");

        if (req.getParameter("id") != null && req.getParameter("title") != null && req.getParameter("cost") != null) {
            int id;
            float cost;
            try {
                id = Integer.parseInt(req.getParameter("id"));
            } catch (NumberFormatException e) {
                id = 0;
            }
            String title = req.getParameter("title");

            try {
                cost = Float.parseFloat(req.getParameter("cost"));
            } catch (NumberFormatException e){
                cost = 0.0F;
            }
            productList.add(productFactory.createProduct(id, title, cost));

            for (Product s : productList) {
                if (s != null) {
                    resp.getWriter().println("<p>" + s + "</p>");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }


}
