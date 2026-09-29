package com.gabriel.CRUD_Atividade.controllers;

import com.gabriel.CRUD_Atividade.model.Pizzaria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pizzarias")
public class PizzariaController {

    private List<Pizzaria> listPizzaria = new ArrayList<Pizzaria>();

    @GetMapping("/index")
    public String index(ModelMap model) {
        model.addAttribute("Pizzaria", listPizzaria);
        model.addAttribute("size", listPizzaria.size());

        return "pizzarias/index";
    }

    @GetMapping("/new")
    public String pizzariaNew(ModelMap model) {
        model.addAttribute("pizzaria", new Pizzaria());

        return "pizzarias/new";
    }

    @PostMapping("/create")
    public String pizzariaCreate(
            @ModelAttribute Pizzaria pizzaria,
            ModelMap model) {

        System.out.println("############ criando nova pizzaria ##########################");
        System.out.println("Nome: " + pizzaria.getNome());
        System.out.println("CNPJ: " + pizzaria.getCnpj());
        System.out.println("Telefone: " + pizzaria.getTelefone());
        System.out.println("E-mail: " + pizzaria.getEmail());
        System.out.println("Endereço: " + pizzaria.getEndereco());
        System.out.println("######################################");

        long id = listPizzaria.size() + 1;

        listPizzaria.add(
                new Pizzaria(
                        id,
                        pizzaria.getNome(),
                        pizzaria.getCnpj(),
                        pizzaria.getTelefone(),
                        pizzaria.getEmail(),
                        pizzaria.getEndereco()
                )
        );

        model.addAttribute("pizzaria", pizzaria);

        return "redirect:/pizzarias/index";
    }

    @GetMapping("/edit/{id}")
    public String editPizzaria(
            @PathVariable Long id,
            ModelMap model) {

        int idInt = Math.toIntExact(id - 1L);

        Pizzaria pizzariaEdit = listPizzaria.get(idInt);

        model.addAttribute("pizzaria", pizzariaEdit);

        return "pizzarias/edit";
    }

    @PostMapping("/update/{id}")
    public String pizzariaUpdate(
            @PathVariable Long id,
            @ModelAttribute Pizzaria pizzaria,
            ModelMap model) {

        System.out.println("############ EDITANDO PIZZARIA ##########################");
        System.out.println("ID: " + pizzaria.getId());
        System.out.println("Nome: " + pizzaria.getNome());
        System.out.println("CNPJ: " + pizzaria.getCnpj());
        System.out.println("Telefone: " + pizzaria.getTelefone());
        System.out.println("E-mail: " + pizzaria.getEmail());
        System.out.println("Endereço: " + pizzaria.getEndereco());
        System.out.println("######################################");

        int idInt = Math.toIntExact(id - 1L);

        Pizzaria pizzariaEdit = listPizzaria.get(idInt);

        pizzariaEdit.setNome(pizzaria.getNome());
        pizzariaEdit.setCnpj(pizzaria.getCnpj());
        pizzariaEdit.setTelefone(pizzaria.getTelefone());
        pizzariaEdit.setEmail(pizzaria.getEmail());
        pizzariaEdit.setEndereco(pizzaria.getEndereco());

        model.addAttribute("pizzaria", pizzaria);

        return "redirect:/pizzarias/index";
    }

    @GetMapping("/show/{id}")
    public String show(
            @PathVariable Long id,
            ModelMap model) {

        int idInt = Math.toIntExact(id - 1L);

        Pizzaria pizzariaShow = listPizzaria.get(idInt);

        model.addAttribute("pizzaria", pizzariaShow);

        return "/pizzarias/show";
    }

    @GetMapping("/delete/{id}")
    public String deletePizzaria(
            @PathVariable Long id) {

        int idInt = Math.toIntExact(id - 1L);

        listPizzaria.remove(idInt);

        return "redirect:/pizzarias/index";
    }
}
