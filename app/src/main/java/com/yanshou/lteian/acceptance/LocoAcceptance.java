package com.yanshou.lteian.acceptance;

public class LocoAcceptance {
    public Long _id;
    public Long locoId;
    public String acceptanceType;
    public String acceptanceDesc;
    public String acceptancePic;

    public LocoAcceptance(){
        this.locoId = Long.valueOf(0);
        this.acceptanceType = "NoType";
        this.acceptanceDesc = "Nothing";
        this.acceptancePic = "NoPicture";
    }

    public LocoAcceptance(Long locoId, String acceptanceType, String acceptanceDesc, String acceptancePic){
        this.locoId = locoId;
        this.acceptanceType = acceptanceType;
        this.acceptanceDesc = acceptanceDesc;
        this.acceptancePic = acceptancePic;
    }


    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getLocoId() {
        return locoId;
    }

    public void setLocoId(Long locoId) {
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
