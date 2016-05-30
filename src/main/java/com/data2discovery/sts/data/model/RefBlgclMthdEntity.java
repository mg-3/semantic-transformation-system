package com.data2discovery.sts.data.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by mgarcia on 5/25/16.
 */
@Entity
@Table(name = "REF_BLGCL_MTHD", schema = "REFDB", catalog = "")
public class RefBlgclMthdEntity {
    private Integer refBlgclMthdId;
    private Integer blgclMthdNbr;
    private String blgclMthdRptNm;
    private String blgclMthdDescTxt;
    private Integer refBlgclMthdTypId;
    private Integer refStsId;
    private Time stsDt;
    private Time chngTmstmp;
    private Integer refBlgclMthdTypGrpngId;
    private String blgclMthdDrvdNm;
    private String blgclMthdDrvdShrtNm;
    private String vldtnFlg;

    @Id
    @Column(name = "REF_BLGCL_MTHD_ID", nullable = false, precision = 0)
    public Integer getRefBlgclMthdId() {
        return refBlgclMthdId;
    }

    public void setRefBlgclMthdId(Integer refBlgclMthdId) {
        this.refBlgclMthdId = refBlgclMthdId;
    }

    @Basic
    @Column(name = "BLGCL_MTHD_NBR", nullable = false, precision = 0)
    public Integer getBlgclMthdNbr() {
        return blgclMthdNbr;
    }

    public void setBlgclMthdNbr(Integer blgclMthdNbr) {
        this.blgclMthdNbr = blgclMthdNbr;
    }

    @Basic
    @Column(name = "BLGCL_MTHD_RPT_NM", nullable = false, length = 40)
    public String getBlgclMthdRptNm() {
        return blgclMthdRptNm;
    }

    public void setBlgclMthdRptNm(String blgclMthdRptNm) {
        this.blgclMthdRptNm = blgclMthdRptNm;
    }

    @Basic
    @Column(name = "BLGCL_MTHD_DESC_TXT", nullable = true, length = 500)
    public String getBlgclMthdDescTxt() {
        return blgclMthdDescTxt;
    }

    public void setBlgclMthdDescTxt(String blgclMthdDescTxt) {
        this.blgclMthdDescTxt = blgclMthdDescTxt;
    }

    @Basic
    @Column(name = "REF_BLGCL_MTHD_TYP_ID", nullable = true, precision = 0)
    public Integer getRefBlgclMthdTypId() {
        return refBlgclMthdTypId;
    }

    public void setRefBlgclMthdTypId(Integer refBlgclMthdTypId) {
        this.refBlgclMthdTypId = refBlgclMthdTypId;
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
    @Column(name = "REF_BLGCL_MTHD_TYP_GRPNG_ID", nullable = true, precision = 0)
    public Integer getRefBlgclMthdTypGrpngId() {
        return refBlgclMthdTypGrpngId;
    }

    public void setRefBlgclMthdTypGrpngId(Integer refBlgclMthdTypGrpngId) {
        this.refBlgclMthdTypGrpngId = refBlgclMthdTypGrpngId;
    }

    @Basic
    @Column(name = "BLGCL_MTHD_DRVD_NM", nullable = true, length = 2000)
    public String getBlgclMthdDrvdNm() {
        return blgclMthdDrvdNm;
    }

    public void setBlgclMthdDrvdNm(String blgclMthdDrvdNm) {
        this.blgclMthdDrvdNm = blgclMthdDrvdNm;
    }

    @Basic
    @Column(name = "BLGCL_MTHD_DRVD_SHRT_NM", nullable = true, length = 1000)
    public String getBlgclMthdDrvdShrtNm() {
        return blgclMthdDrvdShrtNm;
    }

    public void setBlgclMthdDrvdShrtNm(String blgclMthdDrvdShrtNm) {
        this.blgclMthdDrvdShrtNm = blgclMthdDrvdShrtNm;
    }

    @Basic
    @Column(name = "VLDTN_FLG", nullable = true, length = 1)
    public String getVldtnFlg() {
        return vldtnFlg;
    }

    public void setVldtnFlg(String vldtnFlg) {
        this.vldtnFlg = vldtnFlg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefBlgclMthdEntity that = (RefBlgclMthdEntity) o;

        if (refBlgclMthdId != that.refBlgclMthdId) return false;
        if (blgclMthdNbr != that.blgclMthdNbr) return false;
        if (refStsId != that.refStsId) return false;
        if (blgclMthdRptNm != null ? !blgclMthdRptNm.equals(that.blgclMthdRptNm) : that.blgclMthdRptNm != null)
            return false;
        if (blgclMthdDescTxt != null ? !blgclMthdDescTxt.equals(that.blgclMthdDescTxt) : that.blgclMthdDescTxt != null)
            return false;
        if (refBlgclMthdTypId != null ? !refBlgclMthdTypId.equals(that.refBlgclMthdTypId) : that.refBlgclMthdTypId != null)
            return false;
        if (stsDt != null ? !stsDt.equals(that.stsDt) : that.stsDt != null) return false;
        if (chngTmstmp != null ? !chngTmstmp.equals(that.chngTmstmp) : that.chngTmstmp != null) return false;
        if (refBlgclMthdTypGrpngId != null ? !refBlgclMthdTypGrpngId.equals(that.refBlgclMthdTypGrpngId) : that.refBlgclMthdTypGrpngId != null)
            return false;
        if (blgclMthdDrvdNm != null ? !blgclMthdDrvdNm.equals(that.blgclMthdDrvdNm) : that.blgclMthdDrvdNm != null)
            return false;
        if (blgclMthdDrvdShrtNm != null ? !blgclMthdDrvdShrtNm.equals(that.blgclMthdDrvdShrtNm) : that.blgclMthdDrvdShrtNm != null)
            return false;
        if (vldtnFlg != null ? !vldtnFlg.equals(that.vldtnFlg) : that.vldtnFlg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refBlgclMthdId;
        result = 31 * result + blgclMthdNbr;
        result = 31 * result + (blgclMthdRptNm != null ? blgclMthdRptNm.hashCode() : 0);
        result = 31 * result + (blgclMthdDescTxt != null ? blgclMthdDescTxt.hashCode() : 0);
        result = 31 * result + (refBlgclMthdTypId != null ? refBlgclMthdTypId.hashCode() : 0);
        result = 31 * result + refStsId;
        result = 31 * result + (stsDt != null ? stsDt.hashCode() : 0);
        result = 31 * result + (chngTmstmp != null ? chngTmstmp.hashCode() : 0);
        result = 31 * result + (refBlgclMthdTypGrpngId != null ? refBlgclMthdTypGrpngId.hashCode() : 0);
        result = 31 * result + (blgclMthdDrvdNm != null ? blgclMthdDrvdNm.hashCode() : 0);
        result = 31 * result + (blgclMthdDrvdShrtNm != null ? blgclMthdDrvdShrtNm.hashCode() : 0);
        result = 31 * result + (vldtnFlg != null ? vldtnFlg.hashCode() : 0);
        return result;
    }
}
