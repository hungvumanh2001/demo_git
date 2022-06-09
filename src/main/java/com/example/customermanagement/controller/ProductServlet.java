package com.example.customermanagement.controller;

import com.example.customermanagement.model.Category;
import com.example.customermanagement.model.Product;
import com.example.customermanagement.service.CategoryService;
import com.example.customermanagement.service.ProductService;
import com.example.customermanagement.service.impl.CategoryServiceImpl;
import com.example.customermanagement.service.impl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "view":
                showDetailProduct(request, response);
                break;
            case "create":
                showCreateForm(request, response);
                break;
            default:
                showListProduct(request, response);
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showListProduct(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/list-product.jsp");
        List<Product> productList = productService.findAll();
        String key = request.getParameter("key");
        if(key != null){
            productList = productService.findByName("%"+key+"%");
        }
        List<Category> categoryList = findAllCategory(productList);
        request.setAttribute("products", productList);
        request.setAttribute("categories", categoryList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showDetailProduct(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-detail.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Product productById = productService.findById(id);
        request.setAttribute("productById", productById);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public List<Category> findAllCategory(List<Product> products){
        List<Category> categoryList = new ArrayList<>();
        for (int i=0; i<products.size(); i++){
            Category category = categoryService.findById(products.get(i).getCategoryId());
            categoryList.add(category);
        }
        return categoryList;
    }
}
