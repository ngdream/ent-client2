package com.isj.gestiondenote.ClientWeb.Presentation.Controller.Library;

import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.ModalWithHttpHeader;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PretController {
    @GetMapping("/listePret")
    public ModelAndView listePret(Model model, HttpSession session) {
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", accessToken);
        RestTemplate restTemplate = new RestTemplate();
        Object[] prets = restTemplate.getForObject(URL.BASE_URL_BIB + "/prets", Object[].class);
        model.addAttribute("prets", prets);
        System.out.println(model);
        return new ModelAndView("pages/gestion-bibliotheque/liste-des-prets");

    }

    @GetMapping("/supprimerPret/{id}")
    public String supprimerPret(@PathVariable Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URL.BASE_URL_BIB + "/prets/" + id);
        System.out.println(restTemplate);
        return "redirect:/listePret";
    }
}
