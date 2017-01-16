package br.com.jerodac.DTOs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Jean Rodrigo Dalbon Cunha on 14/01/17.
 */
public class RadiosResponseVO extends ResponseVO {

    @SerializedName("data")
    private List<RadioDTO> data;

    public List<RadioDTO> getData() {
        return data;
    }

    public void setData(List<RadioDTO> data) {
        this.data = data;
    }
}
