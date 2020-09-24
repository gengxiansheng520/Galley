package com.example.galley.http.module;

import java.util.List;

public class PixaPhoto {
    private String total;
    private String totalHits;
    private List<Hit> hits;

    @Override
    public String toString() {
        return "PixaPhoto{" +
                "total='" + total + '\'' +
                ", totalHits='" + totalHits + '\'' +
                ", hits=" + hits +
                '}';
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(String totalHits) {
        this.totalHits = totalHits;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }
}
