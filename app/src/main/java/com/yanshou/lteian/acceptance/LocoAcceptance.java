package com.yanshou.lteian.acceptance;

public class LocoAcceptance {
    private int id;
    private int loco_id;
    private String acceptance_type;
    private String acceptance_desc;
    private String acceptance_pic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoco_id() {
        return loco_id;
    }

    public void setLoco_id(int loco_id) {
        this.loco_id = loco_id;
    }

    public String getAcceptance_type() {
        return acceptance_type;
    }

    public void setAcceptance_type(String acceptance_type) {
        this.acceptance_type = acceptance_type;
    }

    public String getAcceptance_desc() {
        return acceptance_desc;
    }

    public void setAcceptance_desc(String acceptance_desc) {
        this.acceptance_desc = acceptance_desc;
    }

    public String getAcceptance_pic() {
        return acceptance_pic;
    }

    public void setAcceptance_pic(String acceptance_pic) {
        this.acceptance_pic = acceptance_pic;
    }
}
