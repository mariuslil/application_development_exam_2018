package no.ntnu.imt3281.yr_places;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Place {

    private int kommunenr;
    private String stedsnavn;
    private String stedsType;
    private String kommune;
    private String fylke;
    private double lat;
    private double lng;
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

    public Place(ResultSet res) throws SQLException {
        kommunenr = res.getInt(1);
        stedsnavn = res.getString(2);
        stedsType = res.getString(3);
        kommune = res.getString(4);
        fylke = res.getString(5);
        lat = res.getDouble(6);
        lng = res.getDouble(7);
        varselURL = res.getString(8);
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
    public double getLat(){
        return lat;
    }

    /**
     * Returns the longitude of the place.
     * @return
     */
    public double getLng(){
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
