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
import entities.Recommendation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import entities.Reclamation;
import entities.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ALL TEC
 */
public class RecommendationService {

    public static RecommendationService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    ArrayList<Recommendation> listReclamation = new ArrayList<>();
    private String response;

    public RecommendationService() {
        req = new ConnectionRequest();
    }

    public static RecommendationService getInstance() {
        if (instance == null) {
            instance = new RecommendationService();
        }
        return instance;
    }

    public ArrayList<Recommendation> listAll() {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL1+"recommendation/";
        con.setUrl(url);
        con.addResponseListener((NetworkEvent evt) -> {
            RecommendationService ser = new RecommendationService();
            listReclamation = ser.parseListReclamationJson(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReclamation;
    }

    public ArrayList<Recommendation> parseListReclamationJson(String json) {
        ArrayList<Recommendation> recommendations = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> recs = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) recs.get("root");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Recommendation r = new Recommendation();
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int) id);
                r.setSujet(obj.get("sujet").toString());
                r.setComment(obj.get("comment").toString());
                r.setEtat(obj.get("etat").toString());
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
                //r.setIdUtilisateur((int) idUser);
                recommendations.add(r);
            }

        } catch (IOException ex) {
        }

        return recommendations;

    }

    public boolean supprimerRecommendation(int id) {
        String url = Statics.BASE_URL1 +"recommendation/"+"delete/" + id;
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

    public boolean addRecommendation(Recommendation r) {
        String url = Statics.BASE_URL1 +"recommendation/"+"add?sujet=" + r.getSujet() + "&comment=" + r.getComment() + "&idUser=" + r.getIdUtilisateur();
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

    public boolean modifierRecommendation(Recommendation r) {

        String url = Statics.BASE_URL1 +"recommendation/"+"edit?sujet=" + r.getSujet() + "&comment=" + r.getComment() + "&idUser=" + r.getIdUtilisateur() + "&id=" + r.getId();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(url);
        con.addResponseListener((NetworkEvent evt) -> {
            RecommendationService ser = new RecommendationService();
            response = new String(con.getResponseData());
            System.out.println("response : " + response);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return response.contains("recommendation modifier");
    }

}
