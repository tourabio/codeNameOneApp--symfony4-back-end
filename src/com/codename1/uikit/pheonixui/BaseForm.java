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

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * Utility methods common to forms e.g. for binding the side menu
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {
    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");
        
        Image inboxImage = null;
        if(isCurrentInbox()) inboxImage = selection;
        
         Image listeReclamationImage = null;
        if(isCurrentlisteReclamationImage()) listeReclamationImage = selection;
        Image AjouterReclamationImage = null;
        if(isCurrentAjouterReclamation()) AjouterReclamationImage = selection;
        
        Image calendarImage = null;
        if(isCurrentCalendar()) calendarImage = selection;
        
        Image listerecommendationImage = null;
        if(isCurrentlisterecommendationImage()) listerecommendationImage = selection;
        
        Image AjouterRecommendationImage = null;
        if(isCurrentAjouterRecommendationImage()) AjouterRecommendationImage = selection;
        
        Image ListeDepenseImage = null;
        if(isCurrentListeDepenseImage()) ListeDepenseImage = selection;
        
          Image AjouterDepenseImage = null;
        if(isCurrentAjouterDepenseImage()) AjouterDepenseImage = selection;
        
        Image ListeRevenueImage = null;
        if(isCurrentlisteRevenueImage()) ListeRevenueImage = selection;
        
        Image AjouterRevenuImage = null;
        if(isCurrentAjouterRevenuImage()) AjouterRevenuImage = selection;
        
        
        
        
        //getToolbar().addCommandToSideMenu("Stats", statsImage, e ->{});
        getToolbar().addCommandToSideMenu("Calendar", calendarImage, e -> new CalendarForm(res).show());
        getToolbar().addCommandToSideMenu("Map", null, e -> {});
        getToolbar().addCommandToSideMenu("Ajouter Reclamation", AjouterReclamationImage, e -> new AjouterReclamationForm(res).show());
        getToolbar().addCommandToSideMenu("liste des Reclamations", listeReclamationImage, e -> new ListeReclamationForm(res).show());
        
        getToolbar().addCommandToSideMenu("Ajouter Recommendation", AjouterRecommendationImage, e -> new AjouterRecommendationForm(res).show());
        getToolbar().addCommandToSideMenu("liste des Recommendations", listerecommendationImage, e -> new ListeRecommendationForm(res).show());
        
        getToolbar().addCommandToSideMenu("Ajouter Depenses", AjouterDepenseImage, e -> new AjouterDepenseForm(res).show());
        getToolbar().addCommandToSideMenu("liste des Depenses", ListeDepenseImage, e -> new ListeDepenseForm(res).show());
        
        getToolbar().addCommandToSideMenu("Ajouter Revenu", AjouterRevenuImage, e -> new AjouterRevenuForm(res).show());
        getToolbar().addCommandToSideMenu("liste des Revenue", ListeRevenueImage, e -> new ListeRevenueForm(res).show());
        
        getToolbar().addCommandToSideMenu("Settings", null, e -> {});
    }

        
    protected boolean isCurrentInbox() {
        return false;
    }
     protected boolean isCurrentlisteReclamationImage() {
        return false;
    }
    
    protected boolean isCurrentAjouterReclamation() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentlisterecommendationImage() {
        return false;
    }
    
    protected boolean isCurrentAjouterRecommendationImage() {
        return false;
    }
    
    protected boolean isCurrentListeDepenseImage() {
        return false;
    }
    
    protected boolean isCurrentAjouterDepenseImage() {
        return false;
    }
    
    protected boolean isCurrentlisteRevenueImage() {
        return false;
    }
    
     protected boolean isCurrentAjouterRevenuImage() {
        return false;
    }
}
