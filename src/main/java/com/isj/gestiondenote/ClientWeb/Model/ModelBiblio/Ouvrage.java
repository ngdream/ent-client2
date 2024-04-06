package com.isj.gestiondenote.ClientWeb.Model.ModelBiblio;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Ouvrage implements Serializable {
    public String nom;
    public Double Pricing;
    public String description;
    public Date edition;
    public Integer quantite;
}
