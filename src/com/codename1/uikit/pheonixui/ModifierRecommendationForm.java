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
public class ModifierRecommendationForm extends BaseForm {

    public ModifierRecommendationForm(Recommendation r1) {
        this(com.codename1.ui.util.Resources.getGlobalResources(),r1);
    }

    public ModifierRecommendationForm(com.codename1.ui.util.Resources resourceObjectInstance, Recommendation r1) {
        initGuiBuilderComponents(resourceObjectInstance);
        installSidemenu(resourceObjectInstance);
        // Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField sujetTxt = new TextField(r1.getSujet());
        TextField commentTxt = new TextField(r1.getComment());
        Button modifierBtn = new Button("Modifier");
        modifierBtn.addActionListener(e -> {
            if (ValidateFields(sujetTxt.getText(), commentTxt.getText())) {
                Recommendation r = new Recommendation(r1.getId(),12345677,sujetTxt.getText(), commentTxt.getText());
                RecommendationService.getInstance().modifierRecommendation(r);
                System.out.println("Recommendation : "+r);
                Dialog.show("OK", "Recommendation modifiée avec succés", "OK", "Cancel");
                new ListeRecommendationForm( resourceObjectInstance).show();

            }
        });
        add(sujetTxt);
        add(commentTxt);
        add(modifierBtn);

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
        setTitle("Modifier Recommendation");
        setName("Modifier_Recommendation");
        
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    @Override
    protected boolean isCurrentAjouterReclamation() {
        return true;
    }
}
