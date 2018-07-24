package com.yanshou.lteian.acceptance;

import java.util.Calendar;

public class LocoLoco {
    public Long _id;
    public String locoType;
    public String locoNumber;
    public Long locoDate = Calendar.getInstance().getTimeInMillis();
    public String locoClassification;

    public LocoLoco(){
        this.locoType = "NoType";
        this.locoNumber = "NoNumber";
        this.locoClassification = "NoClassification";
    }

  public LocoLoco(long id,String locoType,String locoNumber,String locoClassification){
        this._id = id;
        this.locoType = locoType;
        this.locoNumber = locoNumber;
        this.locoClassification = locoClassification;
  }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getLocoNumber() {
        return locoNumber;
    }

    public void setLocoNumber(String locoNumber) {
        this.locoNumber = locoNumber;
    }

    public Long getLocoDate() {
        return locoDate;
    }

    public void setLocoDate(Long locoDate) {
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
