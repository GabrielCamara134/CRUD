
package com.gabriel.CRUD_Atividade.controllers;

import com.gabriel.CRUD_Atividade.model.Animatronic;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/animatronics")
public class AnimatronicController {

    // LISTAR ANIMATRONICS
    @GetMapping("/index")
    public String index(ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            List<Animatronic> animatronics =
                    new ArrayList<>(Animatronic.findAll());

            model.addAttribute("animatronics", animatronics);
            model.addAttribute("size", Animatronic.count());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "Animatronics/index";
    }

    // ABRIR FORMULÁRIO DE CADASTRO
    @GetMapping("/new")
    public String animatronicNew(ModelMap model) {
        model.addAttribute("animatronic", new Animatronic());

        return "Animatronics/new";
    }

    // CADASTRAR ANIMATRONIC
    @PostMapping("/create")
    public String animatronicCreate(
            @ModelAttribute Animatronic animatronic) {

        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            animatronic.saveIt();

            System.out.println("############ criando novo animatronic ##########################");
            System.out.println("ID: " + animatronic.getId());
            System.out.println("Nome: " + animatronic.getNome());
            System.out.println("Peso: " + animatronic.getPeso());
            System.out.println("Cor: " + animatronic.getCor());
            System.out.println("Preço: " + animatronic.getPreco());
            System.out.println("######################################");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/animatronics/index";
    }

    // EDITAR ANIMATRONIC
    @GetMapping("/edit/{id}")
    public String editAnimatronic(
            @PathVariable Long id,
            ModelMap model) {

        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            Animatronic animatronicEdit =
                    Animatronic.findById(id);

            if (animatronicEdit == null) {
                return "redirect:/animatronics/index";
            }

            model.addAttribute("animatronic", animatronicEdit);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "Animatronics/edit";
    }

    // ATUALIZAR ANIMATRONIC
    @PostMapping("/update/{id}")
    public String animatronicUpdate(
            @PathVariable Long id,
            @ModelAttribute Animatronic animatronic) {

        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            Animatronic animatronicEdit =
                    Animatronic.findById(id);

            if (animatronicEdit == null) {
                return "redirect:/animatronics/index";
            }

            animatronicEdit.setNome(animatronic.getNome());
            animatronicEdit.setPeso(animatronic.getPeso());
            animatronicEdit.setCor(animatronic.getCor());
            animatronicEdit.setPreco(animatronic.getPreco());

            animatronicEdit.saveIt();

            System.out.println("############ EDITANDO ANIMATRONIC ##########################");
            System.out.println("ID: " + animatronicEdit.getId());
            System.out.println("Nome: " + animatronicEdit.getNome());
            System.out.println("Peso: " + animatronicEdit.getPeso());
            System.out.println("Cor: " + animatronicEdit.getCor());
            System.out.println("Preço: " + animatronicEdit.getPreco());
            System.out.println("######################################");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/animatronics/index";
    }

    // VISUALIZAR DETALHES
    @GetMapping("/show/{id}")
    public String show(
            @PathVariable Long id,
            ModelMap model) {

        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            Animatronic animatronicShow =
                    Animatronic.findById(id);

            if (animatronicShow == null) {
                return "redirect:/animatronics/index";
            }

            model.addAttribute("animatronic", animatronicShow);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "Animatronics/show";
    }

    // DELETAR ANIMATRONIC
    @GetMapping("/delete/{id}")
    public String deleteAnimatronic(
            @PathVariable Long id) {

        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            Animatronic animatronicDelete =
                    Animatronic.findById(id);

            if (animatronicDelete != null) {
                animatronicDelete.delete();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/animatronics/index";
    }
}
