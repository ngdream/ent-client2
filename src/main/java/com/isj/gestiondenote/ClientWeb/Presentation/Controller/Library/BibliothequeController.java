package com.isj.gestiondenote.ClientWeb.Presentation.Controller.Library;

import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.ModalWithHttpHeader;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class BibliothequeController {
    @GetMapping("/listeDocument")
    public String listeDocument(Model model, HttpSession session) {
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", accessToken);
        System.out.println(model);
        System.out.println("yaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        RestTemplate restTemplate = new RestTemplate();

        Object[] documents = restTemplate.getForObject(URL.BASE_URL_BIB + "/ouvrages", Object[].class);
        model.addAttribute("documents", documents);

        System.out.println(model);
        return "pages/gestion-bibliotheque/liste-des-documents";

    }

    @GetMapping("/supprimerDocument/{id}")
    public String supprimerDocument(@PathVariable Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URL.BASE_URL_BIB + "/ouvrages/" + id);
        System.out.println(restTemplate);
        return "redirect:/listeDocument";
    }

}
