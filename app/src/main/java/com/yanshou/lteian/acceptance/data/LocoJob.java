package com.yanshou.lteian.acceptance.data;

public class LocoJob {
    private Long _id;
    private Long locoId;
    private String jobType;
    private String jobDesc;
    private String jobPic;
    private String jobPosition;

    public LocoJob(){
        this.jobDesc = "Nothing";
        this.jobPic = "NoPicture";
    }

    public LocoJob(Long locoId, String jobType, String jobDesc, String acceptancePic){
        this.locoId = locoId;
        this.jobType = jobType;
        this.jobDesc = jobDesc;
        this.jobPic = acceptancePic;
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

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getJobPic() {
        return jobPic;
    }

    public void setJobPic(String acceptancePic) {
        this.jobPic = acceptancePic;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }
}
