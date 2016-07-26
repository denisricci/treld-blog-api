package br.com.treld.model;

import java.util.List;

/**
 * Created by thais on 25/07/2016.
 */
public class PostsPerPage {

    private List<Post> data;

    private long draw;
    private long recordsTotal;

    public List<Post> getData() {
        return data;
    }

    public void setData(List<Post> data) {
        this.data = data;
    }

    public long getDraw() {
        return draw;
    }

    public void setDraw(long draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
}
