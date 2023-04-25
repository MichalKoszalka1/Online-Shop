package com.Shop.Shop.controller;

import com.Shop.Shop.Service.ProductService;
import com.Shop.Shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@SuppressWarnings( "ALL" )
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserController userController;

    @GetMapping("/home.html")
    public String showExampleView(Model model)
    {

        String keyword =null;
        return userController.listByPage (model, 1,"costPrice","asc",keyword);
    }
    @GetMapping("/create")
    public String showAddProduct()
    {
        return "/create.html";
    }
    @PostMapping("/addP")
    public String saveProduct(@RequestParam("image") MultipartFile file,
                              @RequestParam("pname") String name,
                              @RequestParam("costPrice") Double costPrice,
                              @RequestParam("category") String category,
                              @RequestParam("quantity") int quantity,
                              @RequestParam("sizes") BigDecimal sizes,
                              @RequestParam("mass") BigDecimal mass,
                              @RequestParam("description") String desc,
                              @RequestParam("conditions") String conditions,
                              @RequestParam("brand") String brand,Model model)
    {         String keyword =null;

        productService.saveProductToDB(file, name, desc, costPrice,brand,quantity,mass,conditions,sizes,category);

        return userController.listByPage (model, 1,"costPrice","asc",keyword);
    }

    @GetMapping( "/cancel" )
    public String cancel(Model model) {
        String keyword =null;
        return userController.listByPage (model, 1,"costPrice","asc",keyword);
    }
    @GetMapping("/product/details/{id}")
    public String showIndex(@PathVariable("id") Long id, Model model) {
        Product produt = productService.getProductById(id);
        model.addAttribute("product", produt);
        return "productDetails";
    }


}




