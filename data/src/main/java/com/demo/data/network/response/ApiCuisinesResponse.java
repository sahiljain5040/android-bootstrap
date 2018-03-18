package com.demo.data.network.response;

import com.demo.domain.model.CuisineWrapper;

import java.util.ArrayList;

/**
 * Created by sahil on 10/16/17.
 */

public class ApiCuisinesResponse {

    private ArrayList<CuisineWrapper> cuisines;

    public ArrayList<CuisineWrapper> getCuisines() {
        return cuisines;
    }

    public void setCuisines(ArrayList<CuisineWrapper> cuisines) {
        this.cuisines = cuisines;
    }

    @Override
    public String toString() {
        return "ApiCuisinesResponse{" +
                "cuisines=" + cuisines +
                '}';
    }
}
