package br.com.jerodac.DTOs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Jean Rodrigo Dalbon Cunha on 17/01/17.
 */
public class TracksVO extends ResponseVO {

    @SerializedName("data")
    private List<MusicDTO> musics;

    public List<MusicDTO> getMusics() {
        return musics;
    }

    public void setMusics(List<MusicDTO> musics) {
        this.musics = musics;
    }
}
