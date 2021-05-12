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
public class ModifierDepenseForm extends BaseForm {

    public ModifierDepenseForm(Depense d1) {
        this(com.codename1.ui.util.Resources.getGlobalResources(), d1);
    }

    public ModifierDepenseForm(com.codename1.ui.util.Resources resourceObjectInstance, Depense d1) {
        initGuiBuilderComponents(resourceObjectInstance);
        installSidemenu(resourceObjectInstance);
        TextField sourceTxt = new TextField(d1.getSource());
        TextField descriptionTxt = new TextField(d1.getDescription());
        TextField justificatifTxt = new TextField(d1.getJustificatif());
        TextField valeurTxt = new TextField(Double.toString(d1.getValeur()));

        Button modifierBtn = new Button("Modifier");
        modifierBtn.addActionListener(e -> {
            if (ValidateFields(sourceTxt.getText(), descriptionTxt.getText(), justificatifTxt.getText(), valeurTxt.getText())) {
                User u = new User();
                u.setId_User(12345661);
                Depense d = new Depense(d1.getId(), 12345661, u, Double.parseDouble(valeurTxt.getText()), sourceTxt.getText(), descriptionTxt.getText(), justificatifTxt.getText());
                DepenseService.getInstance().modifierDepense(d);
                System.out.println("Depense : " + d);
                Dialog.show("OK", "Depense modifiée avec succés", "OK", "Cancel");
                new ListeDepenseForm(resourceObjectInstance).show();

            }
        });
        add(sourceTxt);
        add(descriptionTxt);
        add(justificatifTxt);
        add(valeurTxt);
        add(modifierBtn);
    }

    private boolean ValidateFields(String sourceTxt, String descriptionTxt, String justificatifTxt, String valeurTxt) {
        if (sourceTxt.isEmpty() || descriptionTxt.isEmpty() || justificatifTxt.isEmpty() || valeurTxt.isEmpty()) {
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
        setTitle("Modifier Depense");
        setName("Modifier_Depense");

    }// </editor-fold>

    
}
