package com.data2discovery.sts.data.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by mgarcia on 5/25/16.
 */
@Entity
@Table(name = "REF_BLGCL_MTHD_TRGT", schema = "REFDB", catalog = "")
public class RefBlgclMthdTrgtEntity {
    private byte refBlgclMthdTrgtId;
    private byte refBlgclTrgtId;
    private byte refBlgclMthdId;
    private Time chngTmstmp;
    private byte refStsId;
    private Time stsDt;

    @Id
    @Column(name = "REF_BLGCL_MTHD_TRGT_ID", nullable = false, precision = 0)
    public byte getRefBlgclMthdTrgtId() {
        return refBlgclMthdTrgtId;
    }

    public void setRefBlgclMthdTrgtId(byte refBlgclMthdTrgtId) {
        this.refBlgclMthdTrgtId = refBlgclMthdTrgtId;
    }

    @Basic
    @Column(name = "REF_BLGCL_TRGT_ID", nullable = false, precision = 0)
    public byte getRefBlgclTrgtId() {
        return refBlgclTrgtId;
    }

    public void setRefBlgclTrgtId(byte refBlgclTrgtId) {
        this.refBlgclTrgtId = refBlgclTrgtId;
    }

    @Basic
    @Column(name = "REF_BLGCL_MTHD_ID", nullable = false, precision = 0)
    public byte getRefBlgclMthdId() {
        return refBlgclMthdId;
    }

    public void setRefBlgclMthdId(byte refBlgclMthdId) {
        this.refBlgclMthdId = refBlgclMthdId;
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

        RefBlgclMthdTrgtEntity that = (RefBlgclMthdTrgtEntity) o;

        if (refBlgclMthdTrgtId != that.refBlgclMthdTrgtId) return false;
        if (refBlgclTrgtId != that.refBlgclTrgtId) return false;
        if (refBlgclMthdId != that.refBlgclMthdId) return false;
        if (refStsId != that.refStsId) return false;
        if (chngTmstmp != null ? !chngTmstmp.equals(that.chngTmstmp) : that.chngTmstmp != null) return false;
        if (stsDt != null ? !stsDt.equals(that.stsDt) : that.stsDt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) refBlgclMthdTrgtId;
        result = 31 * result + (int) refBlgclTrgtId;
        result = 31 * result + (int) refBlgclMthdId;
        result = 31 * result + (chngTmstmp != null ? chngTmstmp.hashCode() : 0);
        result = 31 * result + (int) refStsId;
        result = 31 * result + (stsDt != null ? stsDt.hashCode() : 0);
        return result;
    }
}
