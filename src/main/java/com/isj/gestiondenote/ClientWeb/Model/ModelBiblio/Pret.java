package com.isj.gestiondenote.ClientWeb.Model.ModelBiblio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pret implements Serializable {

    private String motif;

    private String booksList;

    private String limitBackDate;

    private String BackDate;

}
