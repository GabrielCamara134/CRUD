package com.gabriel.CRUD_Atividade.controllers;

import com.gabriel.CRUD_Atividade.model.Animatronic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/animatronics")
public class AnimatronicController {

    private List<Animatronic> listAnimatronic = new ArrayList<>();

    @GetMapping("/index")
    public String index(ModelMap model) {
        model.addAttribute("animatronics", listAnimatronic);
        model.addAttribute("size", listAnimatronic.size());

        return "Animatronics/index";
    }

    @GetMapping("/new")
    public String animatronicNew(ModelMap model) {
        model.addAttribute("animatronic", new Animatronic());

        return "Animatronics/new";
    }

    @PostMapping("/create")
    public String animatronicCreate(
            @ModelAttribute Animatronic animatronic) {

        long id = listAnimatronic.size() + 1;

        listAnimatronic.add(
                new Animatronic(
                        id,
                        animatronic.getNome(),
                        animatronic.getPeso(),
                        animatronic.getCor(),
                        animatronic.getPreco()
                )
        );

        return "redirect:/animatronics/index";
    }

    @GetMapping("/edit/{id}")
    public String editAnimatronic(
            @PathVariable Long id,
            ModelMap model) {

        int idInt = Math.toIntExact(id - 1L);

        Animatronic animatronic =
                listAnimatronic.get(idInt);

        model.addAttribute("animatronic", animatronic);

        return "Animatronics/edit";
    }

    @PostMapping("/update/{id}")
    public String animatronicUpdate(
            @PathVariable Long id,
            @ModelAttribute Animatronic animatronic) {

        int idInt = Math.toIntExact(id - 1L);

        Animatronic animatronicEdit =
                listAnimatronic.get(idInt);

        animatronicEdit.setNome(animatronic.getNome());
        animatronicEdit.setPeso(animatronic.getPeso());
        animatronicEdit.setCor(animatronic.getCor());
        animatronicEdit.setPreco(animatronic.getPreco());

        return "redirect:/animatronics/index";
    }

    @GetMapping("/show/{id}")
    public String show(
            @PathVariable Long id,
            ModelMap model) {

        int idInt = Math.toIntExact(id - 1L);

        Animatronic animatronic =
                listAnimatronic.get(idInt);

        model.addAttribute("animatronic", animatronic);

        return "Animatronics/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteAnimatronic(
            @PathVariable Long id) {

        int idInt = Math.toIntExact(id - 1L);

        listAnimatronic.remove(idInt);

        return "redirect:/animatronics/index";
    }
}
