package com.data2discovery.sts.data.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by mgarcia on 5/25/16.
 */
@Entity
@Table(name = "REF_BLGCL_MTHD_VRSN", schema = "REFDB", catalog = "")
public class RefBlgclMthdVrsnEntity {
    private Integer refBlgclMthdVrsnId;
    private Integer mthdVrsnNbr;
    private String mthdVrsnRptNm;
    private String mthdVrsnDescTxt;
    private Time vldtnDt;
    private Integer intrassyVrbltyNbr;
    private String prtclRfrncTxt;
    private Integer refBlgclMthdId;
    private Integer refBlgclMthdVrsnPrntId;
    private Integer refStsId;
    private Time stsDt;
    private Time chngTmstmp;
    private String mthdVrsnMdTxt;
    private String vldtnFlg;
    private String mthdVrsnDrvdNm;
    private String mthdVrsnDrvdShrtNm;
    private String assnRsrchEfrtFlg;
    private String ntbkRfrncTxt;

    @Id
    @Column(name = "REF_BLGCL_MTHD_VRSN_ID", nullable = false, precision = 0)
    public Integer getRefBlgclMthdVrsnId() {
        return refBlgclMthdVrsnId;
    }

    public void setRefBlgclMthdVrsnId(Integer refBlgclMthdVrsnId) {
        this.refBlgclMthdVrsnId = refBlgclMthdVrsnId;
    }

    @Basic
    @Column(name = "MTHD_VRSN_NBR", nullable = true, precision = 0)
    public Integer getMthdVrsnNbr() {
        return mthdVrsnNbr;
    }

    public void setMthdVrsnNbr(Integer mthdVrsnNbr) {
        this.mthdVrsnNbr = mthdVrsnNbr;
    }

    @Basic
    @Column(name = "MTHD_VRSN_RPT_NM", nullable = false, length = 63)
    public String getMthdVrsnRptNm() {
        return mthdVrsnRptNm;
    }

    public void setMthdVrsnRptNm(String mthdVrsnRptNm) {
        this.mthdVrsnRptNm = mthdVrsnRptNm;
    }

    @Basic
    @Column(name = "MTHD_VRSN_DESC_TXT", nullable = true, length = 500)
    public String getMthdVrsnDescTxt() {
        return mthdVrsnDescTxt;
    }

    public void setMthdVrsnDescTxt(String mthdVrsnDescTxt) {
        this.mthdVrsnDescTxt = mthdVrsnDescTxt;
    }

    @Basic
    @Column(name = "VLDTN_DT", nullable = true)
    public Time getVldtnDt() {
        return vldtnDt;
    }

    public void setVldtnDt(Time vldtnDt) {
        this.vldtnDt = vldtnDt;
    }

    @Basic
    @Column(name = "INTRASSY_VRBLTY_NBR", nullable = true, precision = 0)
    public Integer getIntrassyVrbltyNbr() {
        return intrassyVrbltyNbr;
    }

    public void setIntrassyVrbltyNbr(Integer intrassyVrbltyNbr) {
        this.intrassyVrbltyNbr = intrassyVrbltyNbr;
    }

    @Basic
    @Column(name = "PRTCL_RFRNC_TXT", nullable = true, length = 500)
    public String getPrtclRfrncTxt() {
        return prtclRfrncTxt;
    }

    public void setPrtclRfrncTxt(String prtclRfrncTxt) {
        this.prtclRfrncTxt = prtclRfrncTxt;
    }

    @Basic
    @Column(name = "REF_BLGCL_MTHD_ID", nullable = true, precision = 0)
    public Integer getRefBlgclMthdId() {
        return refBlgclMthdId;
    }

    public void setRefBlgclMthdId(Integer refBlgclMthdId) {
        this.refBlgclMthdId = refBlgclMthdId;
    }

    @Basic
    @Column(name = "REF_BLGCL_MTHD_VRSN_PRNT_ID", nullable = true, precision = 0)
    public Integer getRefBlgclMthdVrsnPrntId() {
        return refBlgclMthdVrsnPrntId;
    }

    public void setRefBlgclMthdVrsnPrntId(Integer refBlgclMthdVrsnPrntId) {
        this.refBlgclMthdVrsnPrntId = refBlgclMthdVrsnPrntId;
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
    @Column(name = "MTHD_VRSN_MD_TXT", nullable = true, length = 30)
    public String getMthdVrsnMdTxt() {
        return mthdVrsnMdTxt;
    }

    public void setMthdVrsnMdTxt(String mthdVrsnMdTxt) {
        this.mthdVrsnMdTxt = mthdVrsnMdTxt;
    }

    @Basic
    @Column(name = "VLDTN_FLG", nullable = false, length = 1)
    public String getVldtnFlg() {
        return vldtnFlg;
    }

    public void setVldtnFlg(String vldtnFlg) {
        this.vldtnFlg = vldtnFlg;
    }

    @Basic
    @Column(name = "MTHD_VRSN_DRVD_NM", nullable = true, length = 2000)
    public String getMthdVrsnDrvdNm() {
        return mthdVrsnDrvdNm;
    }

    public void setMthdVrsnDrvdNm(String mthdVrsnDrvdNm) {
        this.mthdVrsnDrvdNm = mthdVrsnDrvdNm;
    }

    @Basic
    @Column(name = "MTHD_VRSN_DRVD_SHRT_NM", nullable = true, length = 1000)
    public String getMthdVrsnDrvdShrtNm() {
        return mthdVrsnDrvdShrtNm;
    }

    public void setMthdVrsnDrvdShrtNm(String mthdVrsnDrvdShrtNm) {
        this.mthdVrsnDrvdShrtNm = mthdVrsnDrvdShrtNm;
    }

    @Basic
    @Column(name = "ASSN_RSRCH_EFRT_FLG", nullable = false, length = 1)
    public String getAssnRsrchEfrtFlg() {
        return assnRsrchEfrtFlg;
    }

    public void setAssnRsrchEfrtFlg(String assnRsrchEfrtFlg) {
        this.assnRsrchEfrtFlg = assnRsrchEfrtFlg;
    }

    @Basic
    @Column(name = "NTBK_RFRNC_TXT", nullable = true, length = 50)
    public String getNtbkRfrncTxt() {
        return ntbkRfrncTxt;
    }

    public void setNtbkRfrncTxt(String ntbkRfrncTxt) {
        this.ntbkRfrncTxt = ntbkRfrncTxt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefBlgclMthdVrsnEntity that = (RefBlgclMthdVrsnEntity) o;

        if (refBlgclMthdVrsnId != that.refBlgclMthdVrsnId) return false;
        if (refStsId != that.refStsId) return false;
        if (mthdVrsnNbr != null ? !mthdVrsnNbr.equals(that.mthdVrsnNbr) : that.mthdVrsnNbr != null) return false;
        if (mthdVrsnRptNm != null ? !mthdVrsnRptNm.equals(that.mthdVrsnRptNm) : that.mthdVrsnRptNm != null)
            return false;
        if (mthdVrsnDescTxt != null ? !mthdVrsnDescTxt.equals(that.mthdVrsnDescTxt) : that.mthdVrsnDescTxt != null)
            return false;
        if (vldtnDt != null ? !vldtnDt.equals(that.vldtnDt) : that.vldtnDt != null) return false;
        if (intrassyVrbltyNbr != null ? !intrassyVrbltyNbr.equals(that.intrassyVrbltyNbr) : that.intrassyVrbltyNbr != null)
            return false;
        if (prtclRfrncTxt != null ? !prtclRfrncTxt.equals(that.prtclRfrncTxt) : that.prtclRfrncTxt != null)
            return false;
        if (refBlgclMthdId != null ? !refBlgclMthdId.equals(that.refBlgclMthdId) : that.refBlgclMthdId != null)
            return false;
        if (refBlgclMthdVrsnPrntId != null ? !refBlgclMthdVrsnPrntId.equals(that.refBlgclMthdVrsnPrntId) : that.refBlgclMthdVrsnPrntId != null)
            return false;
        if (stsDt != null ? !stsDt.equals(that.stsDt) : that.stsDt != null) return false;
        if (chngTmstmp != null ? !chngTmstmp.equals(that.chngTmstmp) : that.chngTmstmp != null) return false;
        if (mthdVrsnMdTxt != null ? !mthdVrsnMdTxt.equals(that.mthdVrsnMdTxt) : that.mthdVrsnMdTxt != null)
            return false;
        if (vldtnFlg != null ? !vldtnFlg.equals(that.vldtnFlg) : that.vldtnFlg != null) return false;
        if (mthdVrsnDrvdNm != null ? !mthdVrsnDrvdNm.equals(that.mthdVrsnDrvdNm) : that.mthdVrsnDrvdNm != null)
            return false;
        if (mthdVrsnDrvdShrtNm != null ? !mthdVrsnDrvdShrtNm.equals(that.mthdVrsnDrvdShrtNm) : that.mthdVrsnDrvdShrtNm != null)
            return false;
        if (assnRsrchEfrtFlg != null ? !assnRsrchEfrtFlg.equals(that.assnRsrchEfrtFlg) : that.assnRsrchEfrtFlg != null)
            return false;
        if (ntbkRfrncTxt != null ? !ntbkRfrncTxt.equals(that.ntbkRfrncTxt) : that.ntbkRfrncTxt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refBlgclMthdVrsnId;
        result = 31 * result + (mthdVrsnNbr != null ? mthdVrsnNbr.hashCode() : 0);
        result = 31 * result + (mthdVrsnRptNm != null ? mthdVrsnRptNm.hashCode() : 0);
        result = 31 * result + (mthdVrsnDescTxt != null ? mthdVrsnDescTxt.hashCode() : 0);
        result = 31 * result + (vldtnDt != null ? vldtnDt.hashCode() : 0);
        result = 31 * result + (intrassyVrbltyNbr != null ? intrassyVrbltyNbr.hashCode() : 0);
        result = 31 * result + (prtclRfrncTxt != null ? prtclRfrncTxt.hashCode() : 0);
        result = 31 * result + (refBlgclMthdId != null ? refBlgclMthdId.hashCode() : 0);
        result = 31 * result + (refBlgclMthdVrsnPrntId != null ? refBlgclMthdVrsnPrntId.hashCode() : 0);
        result = 31 * result + refStsId;
        result = 31 * result + (stsDt != null ? stsDt.hashCode() : 0);
        result = 31 * result + (chngTmstmp != null ? chngTmstmp.hashCode() : 0);
        result = 31 * result + (mthdVrsnMdTxt != null ? mthdVrsnMdTxt.hashCode() : 0);
        result = 31 * result + (vldtnFlg != null ? vldtnFlg.hashCode() : 0);
        result = 31 * result + (mthdVrsnDrvdNm != null ? mthdVrsnDrvdNm.hashCode() : 0);
        result = 31 * result + (mthdVrsnDrvdShrtNm != null ? mthdVrsnDrvdShrtNm.hashCode() : 0);
        result = 31 * result + (assnRsrchEfrtFlg != null ? assnRsrchEfrtFlg.hashCode() : 0);
        result = 31 * result + (ntbkRfrncTxt != null ? ntbkRfrncTxt.hashCode() : 0);
        return result;
    }
}
