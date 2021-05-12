/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.codename1.uikit.pheonixui;

import Services.DepenseService;
import Services.DonsService;
import Services.ReclamationService;
import Services.RecommendationService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import entities.Depense;
import entities.Don;
import entities.Reclamation;
import entities.Recommendation;
import entities.User;
import java.util.Date;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class AjouterDepenseForm extends BaseForm {

    public AjouterDepenseForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public AjouterDepenseForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        installSidemenu(resourceObjectInstance);
        // Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField sourceTxt = new TextField();
        sourceTxt.setHint("Source");
        TextField descriptionTxt = new TextField();
        descriptionTxt.setHint("Description");
        TextField justificatifTxt = new TextField();
        justificatifTxt.setHint("Justificatif");
        TextField valeurTxt = new TextField();
        valeurTxt.setHint("Valeur");
        
        Button ajouterBtn = new Button("Ajouter");
        ajouterBtn.addActionListener(e -> {
            if (ValidateFields(sourceTxt.getText(), descriptionTxt.getText(), justificatifTxt.getText(), valeurTxt.getText())) {
                User u  = new User();
                u.setId_User(12345661);
                Depense d = new Depense(12345661, u, Double.parseDouble(valeurTxt.getText()), sourceTxt.getText(), descriptionTxt.getText(), justificatifTxt.getText());
                DepenseService.getInstance().addDepense(d);
                Dialog.show("OK", "Depense ajouté avec succés", "OK", "Cancel");
            }
        });
        add(sourceTxt);
        add(descriptionTxt);
        add(justificatifTxt);
        add(valeurTxt);
        add(ajouterBtn);

    }

    private boolean ValidateFields(String sourceTxt, String descriptionTxt, String justificatifTxt, String valeurTxt) {
        if (sourceTxt.isEmpty() || descriptionTxt.isEmpty()|| justificatifTxt.isEmpty()|| valeurTxt.isEmpty() ) {
            Dialog.show("ERREUR", "sil vous plait de remplir tous les champs", "OK", "Cancel");
            return false;
        }
        
        try {
            double d = Double.parseDouble(valeurTxt);
        } catch (NumberFormatException nfe) {
            Dialog.show("Error", "valeur doit etre numerique !", "OK", "Cancel");
            return false;
        }
        return true;
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Ajouter Depense");
        setName("Depense");
    }// </editor-fold>

    
    
//-- DON'T EDIT ABOVE THIS LINE!!!
    @Override
    protected boolean isCurrentAjouterDepenseImage() {
        return true;
    }
}
