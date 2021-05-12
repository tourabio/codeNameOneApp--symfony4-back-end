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
import entities.Depense;
import entities.Don;
import entities.Reclamation;
import entities.Recommendation;
import java.util.List;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class ListeDepenseForm extends BaseForm {

    com.codename1.ui.util.Resources resourceObjectInstance;

    public ListeDepenseForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public ListeDepenseForm(com.codename1.ui.util.Resources resourceObjectInstance1) {
        resourceObjectInstance = resourceObjectInstance1;
        initGuiBuilderComponents(resourceObjectInstance);
        installSidemenu(resourceObjectInstance);

        Button trieParDateBtn = new Button("trier par date");
        add(trieParDateBtn);
        Label retourLigne = new Label("\n\n\n");
        add(retourLigne);
        
        
        
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        List<Depense> depenses = DepenseService.getInstance().listAll();
        for (Depense d : depenses) {
            c.add(addItem(d));
        }
        add(c);
        
          trieParDateBtn.addActionListener(e->{
                List<Depense> depensesSorted = DepenseService.getInstance().sortByDate();
                removeComponent(c);
                c.removeAll();
                for (Depense single : depensesSorted) {
                    c.add(addItem(single));
                }
                add(c);
                show();
        });
        
        
        
        
        
        
    }

    private Container addItem(Depense d) {
        com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
        //addComponent(gui_Container_1);
        com.codename1.ui.Container gui_Container_3_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3_4);
        gui_Container_3_4.setName("Container_3_4");
        com.codename1.ui.Label gui_Label_3_4 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Label_3_4_5 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Label_3_4_6 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Label_3_4_7 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Label_3_4_8 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Label_6 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Label_2_4 = new com.codename1.ui.Label();
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button suppprimerBtn = new Button("Supprimer");
        suppprimerBtn.addActionListener(e -> {
            if (DepenseService.getInstance().supprimerDepense(d.getId())) {
                Dialog.show("OK", "Depense supprimé avec succés", "OK", "Cancel");
                new ListeDepenseForm(resourceObjectInstance).show();
            }

        });
        Button modifierBtn = new Button("Modifier");
        modifierBtn.addActionListener(e -> {
            new ModifierDepenseForm(resourceObjectInstance, d).show();
        });
        
         Button getrecu = new Button("Recu");
        getrecu.addActionListener(e -> {
            DepenseService.getInstance().getRecu(d.getId());
            Dialog.show("OK", "Recu Depense generé avec succés verifier votre dossier D:\\base", "OK", "Cancel");
        });

        gui_Label_3_4_5.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            Dialog.show("user info", "name : " + d.getUser().getName() + "\nfamily name : " + d.getUser().getFamilyName() + "\nemail : " + d.getUser().getEmail(), "OK", "Cancel");

        });

        c1.add(suppprimerBtn);
        c1.add(modifierBtn);
        c1.add(getrecu);
        gui_Container_3_4.addComponent(gui_Label_3_4);
        gui_Container_3_4.addComponent(gui_Label_3_4_5);
        gui_Container_3_4.addComponent(gui_Label_3_4_6);
        //gui_Container_3_4.addComponent(gui_Label_2_4);
        gui_Container_3_4.addComponent(gui_Label_3_4_7);
        gui_Container_3_4.addComponent(gui_Label_3_4_8);
        gui_Container_3_4.addComponent(gui_Label_6);
        gui_Container_3_4.addComponent(c1);

        gui_Label_3_4.setText("Source : " + d.getSource());
        gui_Label_3_4_5.setText("Description : " + d.getDescription());
        gui_Label_3_4_6.setText("Justificatif : " + d.getJustificatif());
        gui_Label_3_4_7.setText("Date : " + d.getDate());
        gui_Label_3_4_8.setText("Valeur : " + d.getValeur());

        gui_Label_3_4.setName("Label_3_4");
        //gui_Label_2_4.setText("typeDons : " + d.getTypeDons());

        //gui_Label_2_4.setName("Label_2_4");
        gui_Label_6.setText("");
        gui_Label_6.setUIID("Separator");
        gui_Label_6.setName("Label_6");
        return gui_Container_1;
    }
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Liste des depenses");
        setName("ListeDepense");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    @Override
    protected boolean isCurrentListeDepenseImage() {
        return true;
    }

}
