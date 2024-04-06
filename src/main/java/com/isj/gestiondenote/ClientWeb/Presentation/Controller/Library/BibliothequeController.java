package com.isj.gestiondenote.ClientWeb.Presentation.Controller.Library;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.isj.gestiondenote.ClientWeb.Model.ModelBiblio.Ouvrage;
import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.ModalWithHttpHeader;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;

@Controller
public class BibliothequeController {
    @GetMapping("/listeDocument")
    public String listeDocument(Model model, HttpSession session) {
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", accessToken);
        RestTemplate restTemplate = new RestTemplate();
        Object[] documents = restTemplate.getForObject(URL.BASE_URL_BIB + "/ouvrages", Object[].class);
        model.addAttribute("documents", documents);
        return "pages/gestion-bibliotheque/liste-des-documents";
    }

    @GetMapping("/supprimerDocument/{id}")
    public String supprimerDocument(@PathVariable String id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URL.BASE_URL_BIB + "/ouvrages/" + id);
        return "redirect:/listeDocument";
    }

    @PostMapping("/ajoutDocument")
    public String ajoutCategorie(Model model, @ModelAttribute Ouvrage book, @RequestParam MultipartFile file,
            @RequestParam MultipartFile image, HttpSession session) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        body.add("image", image.getResource());
        body.add("nom", book.getNom());
        body.add("quantite", book.getQuantite());
        body.add("pricing", book.getPricing());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.BASE_URL_BIB + "/ouvrages", requestEntity, Object[].class);

        return "redirect:/listeDocument";
    }
}
