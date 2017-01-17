package br.com.jerodac.business;

import java.util.List;

import br.com.jerodac.DTOs.MusicDTO;
import br.com.jerodac.DTOs.RadioDTO;

/**
 * @author Jean Rodrigo Dalbon Cunha on 14/01/17.
 */
public class ModelPresenter {

    private List<RadioDTO> radios;

    private List<MusicDTO> musics;

    public List<RadioDTO> getRadios() {
        return radios;
    }

    public void setRadios(List<RadioDTO> radios) {
        this.radios = radios;
    }

    public List<MusicDTO> getMusics() {
        return musics;
    }

    public void setMusics(List<MusicDTO> musics) {
        this.musics = musics;
    }
}
