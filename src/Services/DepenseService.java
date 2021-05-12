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
import entities.Depense;
import entities.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ALL TEC
 */
public class DepenseService {

    public static DepenseService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    ArrayList<Depense> listDepenses = new ArrayList<>();
    private String response;

    public DepenseService() {
        req = new ConnectionRequest();
    }

    public static DepenseService getInstance() {
        if (instance == null) {
            instance = new DepenseService();
        }
        return instance;
    }

    public ArrayList<Depense> listAll() {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL1+"depense/";
        con.setUrl(url);
        con.addResponseListener((NetworkEvent evt) -> {
            DepenseService ser = new DepenseService();
            listDepenses = ser.parseListReclamationJson(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listDepenses;
    }
    
     public ArrayList<Depense> sortByDate() {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL1+"depense/sort";
        con.setUrl(url);
        con.addResponseListener((NetworkEvent evt) -> {
            DepenseService ser = new DepenseService();
            listDepenses = ser.parseListReclamationJson(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listDepenses;
    }

    public ArrayList<Depense> parseListReclamationJson(String json) {
        ArrayList<Depense> depenses = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> recs = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) recs.get("root");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Depense d = new Depense();
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                d.setId((int) id);
                d.setSource(obj.get("source").toString());
                d.setValeur(Double.parseDouble(obj.get("valeur").toString()));
                d.setDescription(obj.get("description").toString());
                d.setJustificatif(obj.get("justificatif").toString());
                SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date myDate = null;
                try {
                    myDate = myFormat.parse(obj.get("dateFormatted").toString());
                } catch (ParseException ex) {
                }
                d.setDate(myDate);
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
                d.setUser(u);
                Map<String, Object> Finance = (Map<String, Object>) obj.get("idFinance");
                float idFinanace = Float.parseFloat(Finance.get("id").toString());
                d.setId_finance((int)idFinanace);
                depenses.add(d);
            }

        } catch (IOException ex) {
        }

        return depenses;

    }

    public boolean supprimerDepense(int id) {
        String url = Statics.BASE_URL1 +"depense/"+"delete/" + id;
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

    public boolean addDepense(Depense d) {
        String url = Statics.BASE_URL1 +"depense/"+"add?valeur=" + d.getValeur()+ "&source=" + d.getSource()+ "&idUser=" + d.getUser().getId_User() + "&description=" + d.getDescription()+  "&justificatif=" + d.getJustificatif()+  "&idFinance=" + d.getId_finance();
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

    public boolean modifierDepense(Depense d) {

        String url = Statics.BASE_URL1 +"depense/"+"edit?valeur=" + d.getValeur()+ "&source=" + d.getSource()+ "&idUser=" + d.getUser().getId_User() + "&description=" + d.getDescription()+  "&justificatif=" + d.getJustificatif()+  "&idFinance=" + d.getId_finance()+"&id=" + d.getId();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(url);
        con.addResponseListener((NetworkEvent evt) -> {
            DepenseService ser = new DepenseService();
            response = new String(con.getResponseData());
            System.out.println("response : " + response);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return response.contains("depense modifier");
    }
    
    
    
 public void getRecu(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL1+"depense/getrecu/"+id;
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener((NetworkEvent evt) -> {
            DepenseService ser = new DepenseService();
            response = new String(con.getResponseData());
            System.out.println("response : " + response);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
