package no.ntnu.imt3281.yr_places;


import java.util.List;

public class Place {

    private int kommunenr;
    private String stedsnavn;
    private String stedsType;
    private String kommune;
    private String fylke;
    private float lat;
    private float lng;
    private String varselURL;

    public Place(List data) {
        this.kommunenr = Integer.parseInt(data.get(0).toString());
        this.stedsnavn = data.get(1).toString();
        this.stedsType = data.get(4).toString();
        this.kommune = data.get(6).toString();
        this.fylke = data.get(7).toString();
        this.lat = Float.parseFloat(data.get(8).toString());
        this.lng = Float.parseFloat(data.get(9).toString());
        String[] tmp;
        tmp = data.get(12).toString().split("/");
        if (tmp.length < 6){
            this.varselURL = data.get(11).toString();
        }
        else {
            this.varselURL = data.get(12).toString();
        }

    }

    public int getKommunenr(){
        return kommunenr;
    }

    public String getStedsnavn(){
        return stedsnavn;
    }

    public String getStedstype(){
        return stedsType;
    }

    public String getKommune(){
        return kommune;
    }

    public String getFylke(){
        return fylke;
    }

    public float getLat(){
        return lat;
    }

    public float getLng(){
        return lng;
    }

    public String getVarselURL(){
        return varselURL;
    }
}
