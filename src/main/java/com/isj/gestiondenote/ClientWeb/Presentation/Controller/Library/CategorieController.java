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
import com.isj.gestiondenote.ClientWeb.utils.test.ModalWithHttpHeader;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;

//controlleur  pour gerer les categories de livres dans une bibliothèque
@Controller
public class CategorieController {

    // cette route nous permettra de lister toutes les categories disponible dans la
    // bibliotheque
    @GetMapping("/listeCategorie")
    public String listeCategorie(Model model, HttpSession session) {
        ModalWithHttpHeader.model(model, session);
        String accessToken = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", accessToken);
        RestTemplate restTemplate = new RestTemplate();

        // we're getting all categories from the library service
        Object[] categories = restTemplate.getForObject(URL.BASE_URL_BIB + "/categories", Object[].class);
        model.addAttribute("categories", categories);

        return "pages/gestion-bibliotheque/liste-des-categories";
    }

    // nous supprimons une categorie
    @GetMapping("/supprimerCategorie/{id}")
    public String supprimerCategorie(@PathVariable Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URL.BASE_URL_BIB + "/categories/" + id);
        System.out.println(restTemplate);
        return "redirect:/listeCategorie";
    }

    // nous ajoutons une nouvelle catégorie
    @PostMapping("/ajoutCategorie")
    public String ajoutCategorie(Model model, @ModelAttribute Categorie object, HttpSession session) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.BASE_URL_BIB + "/categories", object, Object[].class);
        return "redirect:/listeCategorie";
    }
}
