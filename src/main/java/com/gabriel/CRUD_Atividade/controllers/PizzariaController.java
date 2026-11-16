
package com.gabriel.CRUD_Atividade.controllers;

import com.gabriel.CRUD_Atividade.model.Pizzaria;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pizzarias")
public class PizzariaController {

    // LISTAR PIZZARIAS
    @GetMapping("/index")
    public String index(ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            List<Pizzaria> pizzarias = new ArrayList<>(Pizzaria.findAll());

            model.addAttribute("Pizzaria", pizzarias);
            model.addAttribute("size", Pizzaria.count());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "pizzarias/index";
    }

    // ABRIR FORMULÁRIO DE CADASTRO
    @GetMapping("/new")
    public String pizzariaNew(ModelMap model) {
        model.addAttribute("pizzaria", new Pizzaria());

        return "pizzarias/new";
    }

    // CADASTRAR PIZZARIA
    @PostMapping("/create")
    public String pizzariaCreate(@ModelAttribute Pizzaria pizzaria) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            pizzaria.saveIt();

            System.out.println("############ criando nova pizzaria ##########################");
            System.out.println("ID: " + pizzaria.getId());
            System.out.println("Nome: " + pizzaria.getNome());
            System.out.println("CNPJ: " + pizzaria.getCnpj());
            System.out.println("Telefone: " + pizzaria.getTelefone());
            System.out.println("E-mail: " + pizzaria.getEmail());
            System.out.println("Endereço: " + pizzaria.getEndereco());
            System.out.println("######################################");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/pizzarias/index";
    }

    // EDITAR PIZZARIA
    @GetMapping("/edit/{id}")
    public String editPizzaria(@PathVariable Long id, ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            Pizzaria pizzariaEdit = Pizzaria.findById(id);

            if (pizzariaEdit == null) {
                return "redirect:/pizzarias/index";
            }

            model.addAttribute("pizzaria", pizzariaEdit);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "pizzarias/edit";
    }

    // ATUALIZAR PIZZARIA
    @PostMapping("/update/{id}")
    public String pizzariaUpdate(
            @PathVariable Long id,
            @ModelAttribute Pizzaria pizzaria) {

        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            Pizzaria pizzariaEdit = Pizzaria.findById(id);

            if (pizzariaEdit == null) {
                return "redirect:/pizzarias/index";
            }

            pizzariaEdit.setNome(pizzaria.getNome());
            pizzariaEdit.setCnpj(pizzaria.getCnpj());
            pizzariaEdit.setTelefone(pizzaria.getTelefone());
            pizzariaEdit.setEmail(pizzaria.getEmail());
            pizzariaEdit.setEndereco(pizzaria.getEndereco());

            pizzariaEdit.saveIt();

            System.out.println("############ EDITANDO PIZZARIA ##########################");
            System.out.println("ID: " + pizzariaEdit.getId());
            System.out.println("Nome: " + pizzariaEdit.getNome());
            System.out.println("CNPJ: " + pizzariaEdit.getCnpj());
            System.out.println("Telefone: " + pizzariaEdit.getTelefone());
            System.out.println("E-mail: " + pizzariaEdit.getEmail());
            System.out.println("Endereço: " + pizzariaEdit.getEndereco());
            System.out.println("######################################");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/pizzarias/index";
    }

    // VISUALIZAR DETALHES
    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            Pizzaria pizzariaShow = Pizzaria.findById(id);

            if (pizzariaShow == null) {
                return "redirect:/pizzarias/index";
            }

            model.addAttribute("pizzaria", pizzariaShow);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "pizzarias/show";
    }

    // DELETAR PIZZARIA
    @GetMapping("/delete/{id}")
    public String deletePizzaria(@PathVariable Long id) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            Pizzaria pizzariaDelete = Pizzaria.findById(id);

            if (pizzariaDelete != null) {
                pizzariaDelete.delete();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/pizzarias/index";
    }
}
