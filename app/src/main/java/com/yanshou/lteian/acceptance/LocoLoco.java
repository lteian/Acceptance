package com.yanshou.lteian.acceptance;

public class LocoLoco {
    public int _id;
    public String locoType;
    public String locoNumber;
    public String locoDate;
    public String locoClassification;

    public LocoLoco(){
        this.locoType = "NoType";
        this.locoNumber = "NoNumber";
        this.locoDate = "NoDate";
        this.locoClassification = "NoClassification";
    }

  public LocoLoco(String locoType,String locoNumber,String locoDate,String locoClassification){
        this.locoType = locoType;
        this.locoNumber = locoNumber;
        this.locoDate = locoDate;
        this.locoClassification = locoClassification;
  }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getLocoNumber() {
        return locoNumber;
    }

    public void setLocoNumber(String locoNumber) {
        this.locoNumber = locoNumber;
    }

    public String getLocoDate() {
        return locoDate;
    }

    public void setLocoDate(String locoDate) {
        this.locoDate = locoDate;
    }

    public String getLocoClassification() {
        return locoClassification;
    }

    public void setLocoClassification(String locoClassification) {
        this.locoClassification = locoClassification;
    }

    public String getLocoType() {
        return locoType;
    }

    public void setLocoType(String locoType) {
        this.locoType = locoType;
    }
}
