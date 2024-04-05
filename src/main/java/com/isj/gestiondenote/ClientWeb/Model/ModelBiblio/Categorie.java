package com.isj.gestiondenote.ClientWeb.Model.ModelBiblio;

import com.isj.gestiondenote.ClientWeb.Model.entities.Securite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Categorie  extends Securite implements Serializable {
    private String nom;
    private String couleur;
}
