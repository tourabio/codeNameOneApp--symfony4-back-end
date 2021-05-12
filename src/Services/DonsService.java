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
import entities.Don;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus_pc
 */
public class DonsService {
      public static DonsService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
        ArrayList<Don> listDons = new ArrayList<>();

    private DonsService() {
        req = new ConnectionRequest();
    }
     public static DonsService getInstance() {
        if (instance == null) {
            instance = new DonsService();
        }
        return instance;
    }
     public boolean addDon(Don d) {
        String url = Statics.BASE_URL1 + "don/api/ajouter?nom=" + d.getNom() + "&prenom=" + d.getPrenom() + "&telephone=" + d.getTelephone() + "&adresse=" + d.getAdresse() + "&typeDons=" + d.getTypeDons()+ "&quantite=" + d.getQtt();
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
     
    public ArrayList<Don> listAll() {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL1 + "don/api/list";
        con.setUrl(url);
        con.addResponseListener((NetworkEvent evt) -> {
            DonsService ser = new DonsService();
            listDons = ser.parseListDonJson(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listDons;
    }

    public ArrayList<Don> parseListDonJson(String json) {
        ArrayList<Don> dons = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
     
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Don d = new Don();

                float id = Float.parseFloat(obj.get("id").toString());
                d.setId((int) id);
                d.setNom( obj.get("nom").toString());
                d.setPrenom(obj.get("prenom").toString());
                d.setAdresse(obj.get("adresse").toString());
                float tel = Float.parseFloat(obj.get("telephone").toString());
              d.setTelephone((int)tel);
              d.setTypeDons(obj.get("typeDons").toString());
                float qtt = Float.parseFloat(obj.get("quantite").toString());
                d.setQtt((int)qtt);
                dons.add(d);
            }

        } catch (IOException ex) {
        }

        return dons;

    }
  public boolean supprimerDon(int id) {
        String url = Statics.BASE_URL1 + "don/api/supprimer/" + id;
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
  
  
    public boolean receverDon(String qttDemande,int id ) {
        int idUserStatique = 1;
        String url = Statics.BASE_URL1 + "don/api/recever/"+id+"/"+qttDemande+"/"+idUserStatique;
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
      public ArrayList<Don> getListSearch(String type) {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL1 + "don/api/search/" + type;
        con.setUrl(url);
        con.addResponseListener((NetworkEvent evt) -> {
            DonsService ser = new DonsService();
            listDons = ser.parseListDonJson(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listDons;
    }
        public ArrayList<Don> getListsorted() {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL1 + "don/api/sort/";
        con.setUrl(url);
        con.addResponseListener((NetworkEvent evt) -> {
            DonsService ser = new DonsService();
            listDons = ser.parseListDonJson(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listDons;
    }
         public void sendMail(String qtt,String type) {
        ConnectionRequest con = new ConnectionRequest();
        String toStatique = "khalil.tourabi10@gmail.com";
        String url = Statics.BASE_URL1 + "don/api/sendMail/" +toStatique+"/"+type+"/"+qtt;
        con.setUrl(url);
        con.addResponseListener((NetworkEvent evt) -> {
            System.out.println(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
}
