package com.example.crudapp.controllers;

import com.example.crudapp.models.entities.ProductDTO;
import com.example.crudapp.models.services.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping()
@SessionAttributes("product")
public class ProductController {

    private final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @RequestMapping(value = {"index","/"})
    public String index(Model model) {
        model.addAttribute("title", "App Crud");
        return "redirect:/list";
    }

    @GetMapping(value="/list")
    public String toList(Model model){
        model.addAttribute("title", "Listado de Productos");
        model.addAttribute("products", productService.listAll());
        return "list";
    }

    @GetMapping(value = "/product/{id}")
    public String viewProduct(@PathVariable String id, Model model){
        model.addAttribute("title", "Vista de Producto");
        model.addAttribute("product", productService.findProduct(toInt(id)));
        model.addAttribute("mode","Ver");
        return "form";
    }

    @GetMapping(value = "/form")
    public String toForm(Model model) {
        ProductDTO product = new ProductDTO();
        model.addAttribute("title", "Formulario de registro");
        model.addAttribute("product", product);
        model.addAttribute("mode", "Guardar");
        return "form";
    }

    @GetMapping(value = "/form/{id}")
    public String toForm(@PathVariable String id, Model model){
        ProductDTO product = productService.findProduct(toInt(id));
        model.addAttribute("title", "Actualizar Datos");
        model.addAttribute("product", product);
        model.addAttribute("mode","Actualizar");
        return "form";
    }

    @PostMapping(value = "/form")
    public String save(ProductDTO product, @SessionAttribute("product") ProductDTO sesion, SessionStatus status){
        if(sesion.getProductId()>0)
            product.setProductId(sesion.getProductId());
        productService.saveProduct(product);
        status.setComplete();
        return "redirect:/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable String id){
        productService.deleteProduct(toInt(id));
        return "redirect:/list";
    }

    private int toInt(String id){
        return Integer.parseInt(id);
    }

}
