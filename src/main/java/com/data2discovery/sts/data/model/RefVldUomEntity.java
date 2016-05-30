package com.data2discovery.sts.data.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by mgarcia on 5/7/16.
 */
@Entity
@Table(name = "REF_VLD_UOM", schema = "REFDB", catalog = "")
public class RefVldUomEntity {
    private short refVldUomId;
    private short refUomId;
    private short refBlgclRsltTypId;
    private Time chngTmstmp;
    private short refStsId;
    private Time stsDt;

    @Id
    @Column(name = "REF_VLD_UOM_ID", nullable = false, precision = 0)
    public short getRefVldUomId() {
        return refVldUomId;
    }

    public void setRefVldUomId(short refVldUomId) {
        this.refVldUomId = refVldUomId;
    }

    @Basic
    @Column(name = "REF_UOM_ID", nullable = false, precision = 0)
    public short getRefUomId() {
        return refUomId;
    }

    public void setRefUomId(short refUomId) {
        this.refUomId = refUomId;
    }

    @Basic
    @Column(name = "REF_BLGCL_RSLT_TYP_ID", nullable = false, precision = 0)
    public short getRefBlgclRsltTypId() {
        return refBlgclRsltTypId;
    }

    public void setRefBlgclRsltTypId(short refBlgclRsltTypId) {
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
    public short getRefStsId() {
        return refStsId;
    }

    public void setRefStsId(short refStsId) {
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

        RefVldUomEntity that = (RefVldUomEntity) o;

        if (refVldUomId != that.refVldUomId) return false;
        if (refUomId != that.refUomId) return false;
        if (refBlgclRsltTypId != that.refBlgclRsltTypId) return false;
        if (refStsId != that.refStsId) return false;
        if (chngTmstmp != null ? !chngTmstmp.equals(that.chngTmstmp) : that.chngTmstmp != null) return false;
        if (stsDt != null ? !stsDt.equals(that.stsDt) : that.stsDt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) refVldUomId;
        result = 31 * result + (int) refUomId;
        result = 31 * result + (int) refBlgclRsltTypId;
        result = 31 * result + (chngTmstmp != null ? chngTmstmp.hashCode() : 0);
        result = 31 * result + (int) refStsId;
        result = 31 * result + (stsDt != null ? stsDt.hashCode() : 0);
        return result;
    }
}
