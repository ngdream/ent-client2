package com.isj.gestiondenote.ClientWeb.Presentation.Controller.Library;

import java.awt.PageAttributes;
import java.util.ArrayList;
import java.util.List;

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

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.isj.gestiondenote.ClientWeb.Model.ModelBiblio.Pret;

@Controller
public class PretController {
    @GetMapping("/listePret")
    public String listePret(Model model, HttpSession session) {
        ModalWithHttpHeader.model(model, session);
        String accessToken = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", accessToken);
        RestTemplate restTemplate = new RestTemplate();
        Object[] prets = restTemplate.getForObject(URL.BASE_URL_BIB + "/prets", Object[].class);
        model.addAttribute("prets", prets);
        return "pages/gestion-bibliotheque/liste-des-prets";

    }

    @GetMapping("/supprimerPret/{id}")
    public String supprimerPret(@PathVariable Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URL.BASE_URL_BIB + "/prets/" + id);
        return "redirect:/listePret";
    }

    @PostMapping("/ajoutPret")
    public String ajouterPret(@ModelAttribute Pret object, HttpSession session) {
        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        List<String> books = new ArrayList<>();
        books.add(object.getBooksList());

        body.add("books", books);
        body.add("limitBackDate", object.getLimitBackDate());
        body.add("backDate", object.getBackDate());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.BASE_URL_BIB + "/prets", requestEntity,
                Object[].class);
        return "redirect:/listePret";
    }
}
