package com.isj.gestiondenote.ClientWeb.Presentation.Controller.Library;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.isj.gestiondenote.ClientWeb.Model.ModelBiblio.Categorie;
import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.ModalWithHttpHeader;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;

@Controller
public class CategorieController {
    @GetMapping("/listeCategorie")
    public String listeCategorie(Model model, HttpSession session) {
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", accessToken);
        RestTemplate restTemplate = new RestTemplate();

        // we're getting all categories from the library service
        Object[] categories = restTemplate.getForObject(URL.BASE_URL_BIB + "/categories", Object[].class);
        model.addAttribute("categories", categories);

        return "pages/gestion-bibliotheque/liste-des-categories";

    }

    @GetMapping("/supprimerCategorie/{id}")
    public String supprimerCategorie(@PathVariable Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URL.BASE_URL_BIB + "/categories/" + id);
        System.out.println(restTemplate);
        return "redirect:/listeCategorie";
    }

    @PostMapping("/ajoutCategorie")
    public String ajoutCategorie(Model model, @ModelAttribute Categorie object, HttpSession session) {
        Modal.model(model);
        RestTemplate restTemplate = new RestTemplate();

        Object[] categories = restTemplate.postForObject(URL.BASE_URL_BIB + "/categories", object, Object[].class);
        System.out.print(categories);
        model.addAttribute("categories", categories);
        return "redirect:/listeCategorie";
    }
}
