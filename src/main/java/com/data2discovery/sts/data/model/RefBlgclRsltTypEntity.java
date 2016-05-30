package com.data2discovery.sts.data.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by mgarcia on 5/7/16.
 */
@Entity
@Table(name = "REF_BLGCL_RSLT_TYP", schema = "REFDB", catalog = "")
public class RefBlgclRsltTypEntity {

    private Integer refBlgclRsltTypId;
    private String blgclRsltTypNm = "";
    private String blgclRsltTypShrtNm = "";
    private String blgclRsltTypDescTxt ="";
    private Integer refStsId;
    private Time stsDt;
    private Time chngTmstmp;
    private String sumMdTxt = "";
    private Integer refRsltLvlId;

    @Id
    @Column(name = "REF_BLGCL_RSLT_TYP_ID", nullable = false, precision = 0)
    public Integer getRefBlgclRsltTypId() {
        return refBlgclRsltTypId;
    }

    public void setRefBlgclRsltTypId(Integer refBlgclRsltTypId) {
        this.refBlgclRsltTypId = refBlgclRsltTypId;
    }

    @Basic
    @Column(name = "BLGCL_RSLT_TYP_NM", nullable = false, length = 50)
    public String getBlgclRsltTypNm() {
        return blgclRsltTypNm;
    }

    public void setBlgclRsltTypNm(String blgclRsltTypNm) {
        this.blgclRsltTypNm = blgclRsltTypNm;
    }

    @Basic
    @Column(name = "BLGCL_RSLT_TYP_SHRT_NM", nullable = false, length = 25)
    public String getBlgclRsltTypShrtNm() {
        return blgclRsltTypShrtNm;
    }

    public void setBlgclRsltTypShrtNm(String blgclRsltTypShrtNm) {
        this.blgclRsltTypShrtNm = blgclRsltTypShrtNm;
    }

    @Basic
    @Column(name = "BLGCL_RSLT_TYP_DESC_TXT", nullable = false, length = 500)
    public String getBlgclRsltTypDescTxt() {
        return blgclRsltTypDescTxt;
    }

    public void setBlgclRsltTypDescTxt(String blgclRsltTypDescTxt) {
        this.blgclRsltTypDescTxt = blgclRsltTypDescTxt;
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

    @Basic
    @Column(name = "SUM_MD_TXT", nullable = true, length = 30)
    public String getSumMdTxt() {
        return sumMdTxt;
    }

    public void setSumMdTxt(String sumMdTxt) {
        this.sumMdTxt = sumMdTxt;
    }

    @Basic
    @Column(name = "REF_RSLT_LVL_ID", nullable = true, precision = 0)
    public Integer getRefRsltLvlId() {
        return refRsltLvlId;
    }

    public void setRefRsltLvlId(Integer refRsltLvlId) {
        this.refRsltLvlId = refRsltLvlId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefBlgclRsltTypEntity that = (RefBlgclRsltTypEntity) o;

        if (refBlgclRsltTypId != that.refBlgclRsltTypId) return false;
        if (refStsId != that.refStsId) return false;
        if (blgclRsltTypNm != null ? !blgclRsltTypNm.equals(that.blgclRsltTypNm) : that.blgclRsltTypNm != null)
            return false;
        if (blgclRsltTypShrtNm != null ? !blgclRsltTypShrtNm.equals(that.blgclRsltTypShrtNm) : that.blgclRsltTypShrtNm != null)
            return false;
        if (blgclRsltTypDescTxt != null ? !blgclRsltTypDescTxt.equals(that.blgclRsltTypDescTxt) : that.blgclRsltTypDescTxt != null)
            return false;
        if (stsDt != null ? !stsDt.equals(that.stsDt) : that.stsDt != null) return false;
        if (chngTmstmp != null ? !chngTmstmp.equals(that.chngTmstmp) : that.chngTmstmp != null) return false;
        if (sumMdTxt != null ? !sumMdTxt.equals(that.sumMdTxt) : that.sumMdTxt != null) return false;
        if (refRsltLvlId != null ? !refRsltLvlId.equals(that.refRsltLvlId) : that.refRsltLvlId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) refBlgclRsltTypId;
        result = 31 * result + (blgclRsltTypNm != null ? blgclRsltTypNm.hashCode() : 0);
        result = 31 * result + (blgclRsltTypShrtNm != null ? blgclRsltTypShrtNm.hashCode() : 0);
        result = 31 * result + (blgclRsltTypDescTxt != null ? blgclRsltTypDescTxt.hashCode() : 0);
        result = 31 * result + (int) refStsId;
        result = 31 * result + (stsDt != null ? stsDt.hashCode() : 0);
        result = 31 * result + (chngTmstmp != null ? chngTmstmp.hashCode() : 0);
        result = 31 * result + (sumMdTxt != null ? sumMdTxt.hashCode() : 0);
        result = 31 * result + (refRsltLvlId != null ? refRsltLvlId.hashCode() : 0);
        return result;
    }
}
