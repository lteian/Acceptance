package com.yanshou.lteian.acceptance;

public class loco_acceptance {
    private int id;
    private int loco_id;
    private String type;
    private String desc;
    private String pic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoco_id() {
        return loco_id;
    }

    public void setLoco_id(int loco_no) {
        this.loco_id = loco_no;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
