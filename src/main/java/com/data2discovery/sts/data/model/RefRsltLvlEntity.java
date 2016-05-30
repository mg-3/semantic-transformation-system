package com.data2discovery.sts.data.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by mgarcia on 5/7/16.
 */
@Entity
@Table(name = "REF_RSLT_LVL", schema = "REFDB", catalog = "")
public class RefRsltLvlEntity {
    private Integer refRsltLvlId;
    private String rsltLvlNm;
    private String rsltLvlShrtNm;
    private String rsltLvlDescTxt;
    private Integer refStsId;
    private Time stsDt;
    private Time chngTmstmp;

    @Id
    @Column(name = "REF_RSLT_LVL_ID", nullable = false, precision = 0)
    public Integer getRefRsltLvlId() {
        return refRsltLvlId;
    }

    public void setRefRsltLvlId(Integer refRsltLvlId) {
        this.refRsltLvlId = refRsltLvlId;
    }

    @Basic
    @Column(name = "RSLT_LVL_NM", nullable = false, length = 50)
    public String getRsltLvlNm() {
        return rsltLvlNm;
    }

    public void setRsltLvlNm(String rsltLvlNm) {
        this.rsltLvlNm = rsltLvlNm;
    }

    @Basic
    @Column(name = "RSLT_LVL_SHRT_NM", nullable = false, length = 15)
    public String getRsltLvlShrtNm() {
        return rsltLvlShrtNm;
    }

    public void setRsltLvlShrtNm(String rsltLvlShrtNm) {
        this.rsltLvlShrtNm = rsltLvlShrtNm;
    }

    @Basic
    @Column(name = "RSLT_LVL_DESC_TXT", nullable = true, length = 500)
    public String getRsltLvlDescTxt() {
        return rsltLvlDescTxt;
    }

    public void setRsltLvlDescTxt(String rsltLvlDescTxt) {
        this.rsltLvlDescTxt = rsltLvlDescTxt;
    }

    @Basic
    @Column(name = "REF_STS_ID", nullable = false, precision = 0)
    public Integer getRefStsId() {
        return refStsId;
    }

    public void setRefStsId(Integer refStsId) {
        this.refStsId = refStsId;
    }

    @Basic
    @Column(name = "STS_DT", nullable = false)
    public Time getStsDt() {
        return stsDt;
    }

    public void setStsDt(Time stsDt) {
        this.stsDt = stsDt;
    }

    @Basic
    @Column(name = "CHNG_TMSTMP", nullable = false)
    public Time getChngTmstmp() {
        return chngTmstmp;
    }

    public void setChngTmstmp(Time chngTmstmp) {
        this.chngTmstmp = chngTmstmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefRsltLvlEntity that = (RefRsltLvlEntity) o;

        if (refRsltLvlId != that.refRsltLvlId) return false;
        if (refStsId != that.refStsId) return false;
        if (rsltLvlNm != null ? !rsltLvlNm.equals(that.rsltLvlNm) : that.rsltLvlNm != null) return false;
        if (rsltLvlShrtNm != null ? !rsltLvlShrtNm.equals(that.rsltLvlShrtNm) : that.rsltLvlShrtNm != null)
            return false;
        if (rsltLvlDescTxt != null ? !rsltLvlDescTxt.equals(that.rsltLvlDescTxt) : that.rsltLvlDescTxt != null)
            return false;
        if (stsDt != null ? !stsDt.equals(that.stsDt) : that.stsDt != null) return false;
        if (chngTmstmp != null ? !chngTmstmp.equals(that.chngTmstmp) : that.chngTmstmp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) refRsltLvlId;
        result = 31 * result + (rsltLvlNm != null ? rsltLvlNm.hashCode() : 0);
        result = 31 * result + (rsltLvlShrtNm != null ? rsltLvlShrtNm.hashCode() : 0);
        result = 31 * result + (rsltLvlDescTxt != null ? rsltLvlDescTxt.hashCode() : 0);
        result = 31 * result + (int) refStsId;
        result = 31 * result + (stsDt != null ? stsDt.hashCode() : 0);
        result = 31 * result + (chngTmstmp != null ? chngTmstmp.hashCode() : 0);
        return result;
    }
}
