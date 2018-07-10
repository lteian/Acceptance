package com.yanshou.lteian.acceptance;

public class LocoAcceptance {
    public long _id;
    public long locoId;
    public String acceptanceType;
    public String acceptanceDesc;
    public String acceptancePic;

    public LocoAcceptance(){
        this.locoId = 0;
        this.acceptanceType = "NoType";
        this.acceptanceDesc = "Nothing";
        this.acceptancePic = "NoPicture";
    }

    public LocoAcceptance(int locoId, String acceptanceType, String acceptanceDesc, String acceptancePic){
        this.locoId = locoId;
        this.acceptanceType = acceptanceType;
        this.acceptanceDesc = acceptanceDesc;
        this.acceptancePic = acceptancePic;
    }


    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getLocoId() {
        return locoId;
    }

    public void setLocoId(long locoId) {
        this.locoId = locoId;
    }

    public String getAcceptanceType() {
        return acceptanceType;
    }

    public void setAcceptanceType(String acceptanceType) {
        this.acceptanceType = acceptanceType;
    }

    public String getAcceptanceDesc() {
        return acceptanceDesc;
    }

    public void setAcceptanceDesc(String acceptanceDesc) {
        this.acceptanceDesc = acceptanceDesc;
    }

    public String getAcceptancePic() {
        return acceptancePic;
    }

    public void setAcceptancePic(String acceptancePic) {
        this.acceptancePic = acceptancePic;
    }
}
