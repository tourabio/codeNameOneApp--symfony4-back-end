/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Revenue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import entities.Depense;
import entities.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ALL TEC
 */
public class RevenueService {

    public static RevenueService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    ArrayList<Revenue> listRevenues = new ArrayList<>();
    private String response;

    public RevenueService() {
        req = new ConnectionRequest();
    }

    public static RevenueService getInstance() {
        if (instance == null) {
            instance = new RevenueService();
        }
        return instance;
    }

    public ArrayList<Revenue> listAll() {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL1+"revenue/";
        con.setUrl(url);
        con.addResponseListener((NetworkEvent evt) -> {
            RevenueService ser = new RevenueService();
            listRevenues = ser.parseListReclamationJson(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRevenues;
    }
    
     

    public ArrayList<Revenue> parseListReclamationJson(String json) {
        ArrayList<Revenue> revenues = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> recs = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) recs.get("root");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Revenue r = new Revenue();
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int) id);
                r.setSource(obj.get("source").toString());
                r.setValeur(Double.parseDouble(obj.get("valeur").toString()));
                r.setDescription(obj.get("description").toString());
                r.setJustificatif(obj.get("justificatif").toString());
                SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date myDate = null;
                try {
                    myDate = myFormat.parse(obj.get("dateFormatted").toString());
                } catch (ParseException ex) {
                }
                r.setDate(myDate);
                Map<String, Object> User = (Map<String, Object>) obj.get("idutilisateur");
                float idUser = Float.parseFloat(User.get("idUser").toString());
                u.setId_User((int) idUser);
                u.setName(User.get("name").toString());
                u.setFamilyName(User.get("familyname").toString());
                u.setType(User.get("type").toString());
                u.setEmail(User.get("email").toString());
                u.setPassword(User.get("password").toString());
                u.setSubject(User.get("subject").toString());
                u.setClasse(User.get("class").toString());
                r.setUser(u);
                Map<String, Object> Finance = (Map<String, Object>) obj.get("idFinance");
                float idFinanace = Float.parseFloat(Finance.get("id").toString());
                r.setId_finance((int)idFinanace);
                revenues.add(r);
            }

        } catch (IOException ex) {
        }

        return revenues;

    }

    public boolean supprimerRevenue(int id) {
        String url = Statics.BASE_URL1 +"revenue/"+"delete/" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean addRevenue(Revenue r) {
        String url = Statics.BASE_URL1 +"revenue/"+"add?valeur=" + r.getValeur()+ "&source=" + r.getSource()+ "&idUser=" + r.getUser().getId_User() + "&description=" + r.getDescription()+  "&justificatif=" + r.getJustificatif()+  "&idFinance=" + r.getId_finance();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean modifierRevenue(Revenue r) {

        String url = Statics.BASE_URL1 +"revenue/"+"edit?valeur=" + r.getValeur()+ "&source=" + r.getSource()+ "&idUser=" + r.getUser().getId_User() + "&description=" + r.getDescription()+  "&justificatif=" + r.getJustificatif()+  "&idFinance=" + r.getId_finance()+"&id=" + r.getId();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(url);
        con.addResponseListener((NetworkEvent evt) -> {
            RevenueService ser = new RevenueService();
            response = new String(con.getResponseData());
            System.out.println("response : " + response);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return response.contains("revenue modifier");
    }
    
    

}
