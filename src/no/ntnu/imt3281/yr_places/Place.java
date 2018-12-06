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

    /**
     * Constructor setting all the data for a place.
     * @param data
     */
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
        if (tmp.length < 6){            //incase the bokmaal URL is invalid
            this.varselURL = data.get(11).toString();
        }
        else {
            this.varselURL = data.get(12).toString();
        }

    }

    /**
     * Returns the kommuneNr.
     * @return
     */
    public int getKommunenr(){
        return kommunenr;
    }

    /**
     * Returns the place name.
     * @return
     */
    public String getStedsnavn(){
        return stedsnavn;
    }

    /**
     * Returns the place type.
     * @return
     */
    public String getStedstype(){
        return stedsType;
    }

    /**
     * Returns the kommune the place is in.
     * @return
     */
    public String getKommune(){
        return kommune;
    }

    /**
     * Returns the fylke the place is in.
     * @return
     */
    public String getFylke(){
        return fylke;
    }

    /**
     * Returns the latitude of the place.
     * @return
     */
    public float getLat(){
        return lat;
    }

    /**
     * Returns the longitude of the place.
     * @return
     */
    public float getLng(){
        return lng;
    }

    /**
     * Returns the forecast URL for the place.
     * @return
     */
    public String getVarselURL(){
        return varselURL;
    }
}
