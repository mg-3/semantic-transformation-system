package com.data2discovery.sts.data.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by mgarcia on 5/25/16.
 */
@Entity
@Table(name = "REF_BLGCL_TRGT", schema = "REFDB", catalog = "")
public class RefBlgclTrgtEntity {
    private Integer refBlgclTrgtId;
    private String blgclTrgtDescTxt;
    private Integer refBlgclEntyId;
    private Integer refSpcsId;
    private Integer refStrnId;
    private Integer refStsId;
    private Time stsDt;
    private Time chngTmstmp;
    private String blgclTrgtDrvdNm;
    private String blgclTrgtDrvdShrtNm;
    private String trgtDscrptr;

    @Id
    @Column(name = "REF_BLGCL_TRGT_ID", nullable = false, precision = 0)
    public Integer getRefBlgclTrgtId() {
        return refBlgclTrgtId;
    }

    public void setRefBlgclTrgtId(Integer refBlgclTrgtId) {
        this.refBlgclTrgtId = refBlgclTrgtId;
    }

    @Basic
    @Column(name = "BLGCL_TRGT_DESC_TXT", nullable = true, length = 500)
    public String getBlgclTrgtDescTxt() {
        return blgclTrgtDescTxt;
    }

    public void setBlgclTrgtDescTxt(String blgclTrgtDescTxt) {
        this.blgclTrgtDescTxt = blgclTrgtDescTxt;
    }

    @Basic
    @Column(name = "REF_BLGCL_ENTY_ID", nullable = false, precision = 0)
    public Integer getRefBlgclEntyId() {
        return refBlgclEntyId;
    }

    public void setRefBlgclEntyId(Integer refBlgclEntyId) {
        this.refBlgclEntyId = refBlgclEntyId;
    }

    @Basic
    @Column(name = "REF_SPCS_ID", nullable = true, precision = 0)
    public Integer getRefSpcsId() {
        return refSpcsId;
    }

    public void setRefSpcsId(Integer refSpcsId) {
        this.refSpcsId = refSpcsId;
    }

    @Basic
    @Column(name = "REF_STRN_ID", nullable = true, precision = 0)
    public Integer getRefStrnId() {
        return refStrnId;
    }

    public void setRefStrnId(Integer refStrnId) {
        this.refStrnId = refStrnId;
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
    @Column(name = "BLGCL_TRGT_DRVD_NM", nullable = true, length = 305)
    public String getBlgclTrgtDrvdNm() {
        return blgclTrgtDrvdNm;
    }

    public void setBlgclTrgtDrvdNm(String blgclTrgtDrvdNm) {
        this.blgclTrgtDrvdNm = blgclTrgtDrvdNm;
    }

    @Basic
    @Column(name = "BLGCL_TRGT_DRVD_SHRT_NM", nullable = true, length = 150)
    public String getBlgclTrgtDrvdShrtNm() {
        return blgclTrgtDrvdShrtNm;
    }

    public void setBlgclTrgtDrvdShrtNm(String blgclTrgtDrvdShrtNm) {
        this.blgclTrgtDrvdShrtNm = blgclTrgtDrvdShrtNm;
    }

    @Basic
    @Column(name = "TRGT_DSCRPTR", nullable = true, length = 50)
    public String getTrgtDscrptr() {
        return trgtDscrptr;
    }

    public void setTrgtDscrptr(String trgtDscrptr) {
        this.trgtDscrptr = trgtDscrptr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefBlgclTrgtEntity that = (RefBlgclTrgtEntity) o;

        if (refBlgclTrgtId != that.refBlgclTrgtId) return false;
        if (refBlgclEntyId != that.refBlgclEntyId) return false;
        if (refStsId != that.refStsId) return false;
        if (blgclTrgtDescTxt != null ? !blgclTrgtDescTxt.equals(that.blgclTrgtDescTxt) : that.blgclTrgtDescTxt != null)
            return false;
        if (refSpcsId != null ? !refSpcsId.equals(that.refSpcsId) : that.refSpcsId != null) return false;
        if (refStrnId != null ? !refStrnId.equals(that.refStrnId) : that.refStrnId != null) return false;
        if (stsDt != null ? !stsDt.equals(that.stsDt) : that.stsDt != null) return false;
        if (chngTmstmp != null ? !chngTmstmp.equals(that.chngTmstmp) : that.chngTmstmp != null) return false;
        if (blgclTrgtDrvdNm != null ? !blgclTrgtDrvdNm.equals(that.blgclTrgtDrvdNm) : that.blgclTrgtDrvdNm != null)
            return false;
        if (blgclTrgtDrvdShrtNm != null ? !blgclTrgtDrvdShrtNm.equals(that.blgclTrgtDrvdShrtNm) : that.blgclTrgtDrvdShrtNm != null)
            return false;
        if (trgtDscrptr != null ? !trgtDscrptr.equals(that.trgtDscrptr) : that.trgtDscrptr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refBlgclTrgtId;
        result = 31 * result + (blgclTrgtDescTxt != null ? blgclTrgtDescTxt.hashCode() : 0);
        result = 31 * result + refBlgclEntyId;
        result = 31 * result + (refSpcsId != null ? refSpcsId.hashCode() : 0);
        result = 31 * result + (refStrnId != null ? refStrnId.hashCode() : 0);
        result = 31 * result + refStsId;
        result = 31 * result + (stsDt != null ? stsDt.hashCode() : 0);
        result = 31 * result + (chngTmstmp != null ? chngTmstmp.hashCode() : 0);
        result = 31 * result + (blgclTrgtDrvdNm != null ? blgclTrgtDrvdNm.hashCode() : 0);
        result = 31 * result + (blgclTrgtDrvdShrtNm != null ? blgclTrgtDrvdShrtNm.hashCode() : 0);
        result = 31 * result + (trgtDscrptr != null ? trgtDscrptr.hashCode() : 0);
        return result;
    }
}
