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
import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import entities.Don;
import entities.Reclamation;
import entities.Recommendation;
import java.util.List;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class ListeRecommendationForm extends BaseForm {
com.codename1.ui.util.Resources resourceObjectInstance;
    public ListeRecommendationForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public ListeRecommendationForm(com.codename1.ui.util.Resources resourceObjectInstance1) {
        resourceObjectInstance = resourceObjectInstance1;
        initGuiBuilderComponents(resourceObjectInstance);
        installSidemenu(resourceObjectInstance);
        
        List<Recommendation> recommendations = RecommendationService.getInstance().listAll();
        for (Recommendation r : recommendations) {
            addItem(r);
        }
    }

    private void addItem(Recommendation r) {
        com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
        addComponent(gui_Container_1);
        com.codename1.ui.Container gui_Container_3_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3_4);
        gui_Container_3_4.setName("Container_3_4");
        com.codename1.ui.Label gui_Label_3_4 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Label_3_4_5 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Label_3_4_6 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Label_3_4_7 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Label_6 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Label_2_4 = new com.codename1.ui.Label();
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button suppprimerBtn = new Button("Supprimer");
        suppprimerBtn.addActionListener(e -> {
            if (RecommendationService.getInstance().supprimerRecommendation(r.getId())) {
                Dialog.show("OK", "Recommendation supprimé avec succés", "OK", "Cancel");
                new ListeRecommendationForm( resourceObjectInstance).show();
            }

        });
         Button modifierBtn = new Button("Modifier");
        modifierBtn.addActionListener(e -> {
                new ModifierRecommendationForm(resourceObjectInstance,r).show();
        });
        
         gui_Label_3_4_5.addPointerPressedListener((ActionListener)(ActionEvent evt)->{
                 Dialog.show("user info","name : "+r.getUser().getName()+"\nfamily name : "+r.getUser().getFamilyName()+"\nemail : "+r.getUser().getEmail(), "OK", "Cancel");

        });
        
        
        
        
        c1.add(suppprimerBtn);
        c1.add(modifierBtn);
        gui_Container_3_4.addComponent(gui_Label_3_4);
        gui_Container_3_4.addComponent(gui_Label_3_4_5);
        gui_Container_3_4.addComponent(gui_Label_3_4_6);
        //gui_Container_3_4.addComponent(gui_Label_2_4);
        gui_Container_3_4.addComponent(gui_Label_3_4_7);
        gui_Container_3_4.addComponent(gui_Label_6);
        gui_Container_3_4.addComponent(c1);

        gui_Label_3_4.setText("Sujet : " + r.getSujet());
        gui_Label_3_4_5.setText("Comment : " + r.getComment());
        gui_Label_3_4_6.setText("Date : " + r.getDate());
        gui_Label_3_4_7.setText("Etat : " + r.getEtat());

        gui_Label_3_4.setName("Label_3_4");
        //gui_Label_2_4.setText("typeDons : " + d.getTypeDons());

        //gui_Label_2_4.setName("Label_2_4");

        gui_Label_6.setText("");
        gui_Label_6.setUIID("Separator");
        gui_Label_6.setName("Label_6");

    }
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Liste des recommendation");
        setName("ListeRecommendation");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    
    @Override
    protected boolean isCurrentlisterecommendationImage() {
        return true;
    }
    
    
    
}
