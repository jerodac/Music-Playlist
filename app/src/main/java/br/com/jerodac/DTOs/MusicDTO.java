package br.com.jerodac.DTOs;

import com.google.gson.annotations.SerializedName;

/**
 * @author Jean Rodrigo Dalbon Cunha on 17/01/17.
 */
public class MusicDTO {
    @SerializedName("id")
    protected int id;

    @SerializedName("title")
    protected String title;

    @SerializedName("title_short")
    protected String titleShort;

    @SerializedName("preview")
    protected String urlPreview;

    @SerializedName("artist")
    protected ArtistDTO artist;


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

    public String getTitleShort() {
        return titleShort;
    }

    public void setTitleShort(String titleShort) {
        this.titleShort = titleShort;
    }

    public String getUrlPreview() {
        return urlPreview;
    }

    public void setUrlPreview(String urlPreview) {
        this.urlPreview = urlPreview;
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }
}
