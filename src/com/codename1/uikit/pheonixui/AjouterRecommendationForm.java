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
import entities.Don;
import entities.Reclamation;
import entities.Recommendation;
import java.util.Date;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class AjouterRecommendationForm extends BaseForm {

    public AjouterRecommendationForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public AjouterRecommendationForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        installSidemenu(resourceObjectInstance);
        // Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField sujetTxt = new TextField();
        sujetTxt.setHint("Sujet");
        TextField commentTxt = new TextField();
        commentTxt.setHint("Comment");
        Button ajouterBtn = new Button("Ajouter");
        ajouterBtn.addActionListener(e -> {
            if (ValidateFields(sujetTxt.getText(), commentTxt.getText())) {
                Recommendation r = new Recommendation( 12345677,  sujetTxt.getText(), commentTxt.getText());
                RecommendationService.getInstance().addRecommendation(r);
                Dialog.show("OK", "Recommendation ajout?? avec succ??s", "OK", "Cancel");
            }
        });
        add(sujetTxt);
        add(commentTxt);
        add(ajouterBtn);

    }

    private boolean ValidateFields(String sujetTxt, String commentTxt) {
        if (sujetTxt.isEmpty() || commentTxt.isEmpty() ) {
            Dialog.show("ERREUR", "sil vous plait de remplir tous les champs", "OK", "Cancel");
            return false;
        }
        return true;
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Ajouter Recommendation");
        setName("Recommendation");
    }// </editor-fold>

    
    
//-- DON'T EDIT ABOVE THIS LINE!!!
    @Override
    protected boolean isCurrentAjouterRecommendationImage() {
        return true;
    }
}
