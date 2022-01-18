package ru.sprng.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainControl {

    @GetMapping("/index")
    public String doSomething1(){
        return "index";
    }
//
//    @GetMapping("/name")
//    public String helloRequest(Model model){
//        model.addAttribute("name","Bob");
//        return "name";
//    }
//    @GetMapping("/hello")
//    public String hello(Model model, @RequestParam(value = "name") String name){
//        model.addAttribute("name",name);
//        return "hello";
//    }
//    @GetMapping("/hello1/{name}")
//    public String helloReg(Model model, @PathVariable(value = "name") String name) {
//        model.addAttribute("name", name);
//        return "hello1";
//    }

    @GetMapping("/form")
    public String showForm(){
        return "simple-form";
    }
    @PostMapping("/form")
    public String saveForm(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email){
        System.out.println(name);
        System.out.println(email);
        return "redirect:/index";
    }
    @GetMapping("/addcat")
    public String showAddCatForm(Model model){
        Cat cat = new Cat(1L, "Barsik", "black");
        model.addAttribute("cat", cat);
        return "cat-form";
    }
    @PostMapping("/addcat")
    public String showAddCatForm(@ModelAttribute (value = "cat") Cat cat){
        System.out.println(cat.getId() + " " + cat.getName() + " " + cat.getColor());
        return "redirect:/index";
    }
}
