package no.ntnu.imt3281.yr_places;


import java.util.List;

public class Place {

    private int kommunenr;
    private String stedsnavn;
    private String stedsType;
    private String kommune;
    private String fylke;
    private int lat;
    private int lng;
    private String varselURL;

    public Place(List data) {
        this.kommunenr = Integer.parseInt(data.get(0).toString());
        this.stedsnavn = data.get(1).toString();
        this.stedsType = data.get(2).toString();
        this.kommune = data.get(3).toString();
        this.fylke = data.get(4).toString();
        this.lat = Integer.parseInt(data.get(5).toString());
        this.lng = Integer.parseInt(data.get(6).toString());
        this.varselURL = data.get(7).toString();

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

    public int getLat(){
        return lat;
    }

    public int getLng(){
        return lng;
    }

    public String getVarselURL(){
        return varselURL;
    }
}
