package com.data2discovery.sts.data.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by mgarcia on 5/25/16.
 */
@Entity
@Table(name = "REF_EXPCTD_RSLT_TYP", schema = "REFDB", catalog = "")
public class RefExpctdRsltTypEntity {
    private byte refExpctdRsltTypId;
    private byte refBlgclMthdVrsnId;
    private byte refBlgclRsltTypId;
    private Time chngTmstmp;
    private byte refStsId;
    private Time stsDt;

    @Id
    @Column(name = "REF_EXPCTD_RSLT_TYP_ID", nullable = false, precision = 0)
    public byte getRefExpctdRsltTypId() {
        return refExpctdRsltTypId;
    }

    public void setRefExpctdRsltTypId(byte refExpctdRsltTypId) {
        this.refExpctdRsltTypId = refExpctdRsltTypId;
    }

    @Basic
    @Column(name = "REF_BLGCL_MTHD_VRSN_ID", nullable = false, precision = 0)
    public byte getRefBlgclMthdVrsnId() {
        return refBlgclMthdVrsnId;
    }

    public void setRefBlgclMthdVrsnId(byte refBlgclMthdVrsnId) {
        this.refBlgclMthdVrsnId = refBlgclMthdVrsnId;
    }

    @Basic
    @Column(name = "REF_BLGCL_RSLT_TYP_ID", nullable = false, precision = 0)
    public byte getRefBlgclRsltTypId() {
        return refBlgclRsltTypId;
    }

    public void setRefBlgclRsltTypId(byte refBlgclRsltTypId) {
        this.refBlgclRsltTypId = refBlgclRsltTypId;
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
    @Column(name = "REF_STS_ID", nullable = false, precision = 0)
    public byte getRefStsId() {
        return refStsId;
    }

    public void setRefStsId(byte refStsId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefExpctdRsltTypEntity that = (RefExpctdRsltTypEntity) o;

        if (refExpctdRsltTypId != that.refExpctdRsltTypId) return false;
        if (refBlgclMthdVrsnId != that.refBlgclMthdVrsnId) return false;
        if (refBlgclRsltTypId != that.refBlgclRsltTypId) return false;
        if (refStsId != that.refStsId) return false;
        if (chngTmstmp != null ? !chngTmstmp.equals(that.chngTmstmp) : that.chngTmstmp != null) return false;
        if (stsDt != null ? !stsDt.equals(that.stsDt) : that.stsDt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) refExpctdRsltTypId;
        result = 31 * result + (int) refBlgclMthdVrsnId;
        result = 31 * result + (int) refBlgclRsltTypId;
        result = 31 * result + (chngTmstmp != null ? chngTmstmp.hashCode() : 0);
        result = 31 * result + (int) refStsId;
        result = 31 * result + (stsDt != null ? stsDt.hashCode() : 0);
        return result;
    }
}
