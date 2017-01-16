package br.com.jerodac.DTOs;

import com.google.gson.annotations.SerializedName;

/**
 * @author Jean Rodrigo Dalbon Cunha on 14/01/17.
 */
public class RadioDTO {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("picture")
    private String picture;

    @SerializedName("picture_small")
    private String pictureSmall;

    @SerializedName("picture_medium")
    private String pictureMedium;

    @SerializedName("picture_big")
    private String picturBig;

    @SerializedName("picture_xl")
    private String picturXlarge;

    @SerializedName("tracklist")
    private String urlTrackList;

    @SerializedName("type")
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPictureSmall() {
        return pictureSmall;
    }

    public void setPictureSmall(String pictureSmall) {
        this.pictureSmall = pictureSmall;
    }

    public String getPictureMedium() {
        return pictureMedium;
    }

    public void setPictureMedium(String pictureMedium) {
        this.pictureMedium = pictureMedium;
    }

    public String getPicturBig() {
        return picturBig;
    }

    public void setPicturBig(String picturBig) {
        this.picturBig = picturBig;
    }

    public String getPicturXlarge() {
        return picturXlarge;
    }

    public void setPicturXlarge(String picturXlarge) {
        this.picturXlarge = picturXlarge;
    }

    public String getUrlTrackList() {
        return urlTrackList;
    }

    public void setUrlTrackList(String urlTrackList) {
        this.urlTrackList = urlTrackList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
