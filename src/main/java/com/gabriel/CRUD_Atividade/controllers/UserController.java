
package com.gabriel.CRUD_Atividade.controllers;

import com.gabriel.CRUD_Atividade.model.User;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    // LISTAR USUÁRIOS
    @GetMapping("/index")
    public String index(ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            List<User> users = new ArrayList<>(User.findAll());

            model.addAttribute("users", users);
            model.addAttribute("size", User.count());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "users/index";
    }

    // ABRIR FORMULÁRIO DE CADASTRO
    @GetMapping("/new")
    public String userNew(ModelMap model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    // CADASTRAR USUÁRIO
    @PostMapping("/create")
    public String userCreate(@ModelAttribute User user) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            user.saveIt();

            System.out.println("############ criando novo user ##########################");
            System.out.println("ID: " + user.getId());
            System.out.println("Nome: " + user.getName());
            System.out.println("Arroba: " + user.getArroba());
            System.out.println("CPF: " + user.getCpf());
            System.out.println("######################################");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/users/index";
    }

    // EDITAR USUÁRIO
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            User userEdit = User.findById(id);

            if (userEdit == null) {
                return "redirect:/users/index";
            }

            model.addAttribute("user", userEdit);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "users/edit";
    }

    // ATUALIZAR USUÁRIO
    @PostMapping("/update/{id}")
    public String userUpdate(
            @PathVariable Long id,
            @ModelAttribute User user,
            ModelMap model) {

        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            User userEdit = User.findById(id);

            if (userEdit == null) {
                return "redirect:/users/index";
            }

            userEdit.setName(user.getName());
            userEdit.setArroba(user.getArroba());
            userEdit.setCpf(user.getCpf());

            userEdit.saveIt();

            System.out.println("############ EDITANDO user ##########################");
            System.out.println("ID: " + userEdit.getId());
            System.out.println("Nome: " + userEdit.getName());
            System.out.println("Arroba: " + userEdit.getArroba());
            System.out.println("CPF: " + userEdit.getCpf());
            System.out.println("######################################");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/users/index";
    }

    // VISUALIZAR DETALHES
    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            User userShow = User.findById(id);

            if (userShow == null) {
                return "redirect:/users/index";
            }

            model.addAttribute("user", userShow);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "users/show";
    }

    // DELETAR USUÁRIO
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            User userDelete = User.findById(id);

            if (userDelete != null) {
                userDelete.delete();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/users/index";
    }
}
