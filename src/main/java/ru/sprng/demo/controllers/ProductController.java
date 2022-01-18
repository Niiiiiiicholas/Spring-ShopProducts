package ru.sprng.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sprng.demo.entities.Product;
import ru.sprng.demo.repositories.specification.ProductsSpecs;
import ru.sprng.demo.services.ProductService;

import java.math.BigDecimal;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductsList(Model model,
                                   @RequestParam(value = "page", required = false)Integer page,
                                   @RequestParam(value = "word", required = false) String word,
                                   @RequestParam(value = "minPrice", required = false)BigDecimal minPrice,
                                   @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice
    ){
        if(page == null){
            page = 1;
        }
        Specification<Product> specification = Specification.where(null);
        if(word != null){
            specification = specification.and(ProductsSpecs.titleContains(word));
        }
        if(minPrice != null){
            specification = specification.and(ProductsSpecs.priceGreaterThanOrEq(minPrice));
        }
        if(maxPrice != null){
            specification = specification.and(ProductsSpecs.priceLesserThanOrEq(maxPrice));
        }

        Product product = new Product();
        model.addAttribute("products", productService.getProductWithPagingAndFiltering(specification, PageRequest.of(page - 1,5)).getContent());
        model.addAttribute("product", product);
        model.addAttribute("word", word);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "products";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-edit";
    }
    @GetMapping("/edit/{id}")
    public String showEditProductForm(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/edit")
    public String addProduct(@ModelAttribute(value = "product") Product product){
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }
    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id){
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(value = "id") Long id){
        productService.deleteById(id);
        return "redirect:/products";
    }
}
