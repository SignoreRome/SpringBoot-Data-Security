package com.signore.SpringBootEx.controllers;

import com.signore.SpringBootEx.entities.Product;
import com.signore.SpringBootEx.entities.User;
import com.signore.SpringBootEx.repositories.specifications.ProductSpecs;
import com.signore.SpringBootEx.services.ProductsService;
import com.signore.SpringBootEx.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/products")
public class ProductsController {

    private ProductsService productsService;
    private UserServiceImpl userService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    //    @GetMapping
//    public String index(){
//        return "Hello world";
//    }

    @GetMapping
    public String index(Principal principal,
                        Model model,
                        @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "word", required = false) String word,
                        @RequestParam(value = "max", required = false) Double max,
                        @RequestParam(value = "min", required = false) Double min) {
        if (principal!=null){
            User user = userService.findByUsername(principal.getName());
//            System.out.println(user.getUsername());
            model.addAttribute("user", user);
        }
        if (page == null) {
            page = 1;
        }
        Specification<Product> specification = Specification.where(null);
        if (word != null) {
            specification = specification.and(ProductSpecs.titleContains(word));
        }
        if (max != null) {
            specification = specification.and(ProductSpecs.priceGreaterThanOrEq(max));
        }
        if (min != null) {
            specification = specification.and(ProductSpecs.priceLesserThanOrEq(min));
        }
        model.addAttribute("products",
                productsService.getProductsWithPagingAdnFiltering(specification, PageRequest.of(page-1, 5)).getContent());
        model.addAttribute("product", new Product());
        model.addAttribute("page", page);
        model.addAttribute("word", word);
        model.addAttribute("max", max);
        model.addAttribute("min", min);
        return "/index";
    }

    @PostMapping()
    public String addNewProduct(@ModelAttribute("product") Product product) {
        productsService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productsService.findById(id));
        return "/show";
    }

    @GetMapping("/{id}/edit")
    public String showEditProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productsService.findById(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id,
                              @ModelAttribute("updatedProduct") Product updatedProduct) {
        productsService.editProduct(id, updatedProduct);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productsService.deleteProduct(id);
        return "redirect:/products";
    }

}
