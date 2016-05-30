package com.data2discovery.sts.data.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by mgarcia on 5/25/16.
 */
@Entity
@Table(name = "REF_TRGT_GENE_ASGNMNT", schema = "REFDB", catalog = "")
public class RefTrgtGeneAsgnmntEntity {
    private byte refTrgtGeneAsgnmntId;
    private byte refBlgclTrgtId;
    private byte refGeneId;
    private Time stsDt;
    private byte refStsId;
    private Time chngTmstmp;

    @Id
    @Column(name = "REF_TRGT_GENE_ASGNMNT_ID", nullable = false, precision = 0)
    public byte getRefTrgtGeneAsgnmntId() {
        return refTrgtGeneAsgnmntId;
    }

    public void setRefTrgtGeneAsgnmntId(byte refTrgtGeneAsgnmntId) {
        this.refTrgtGeneAsgnmntId = refTrgtGeneAsgnmntId;
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
    @Column(name = "REF_GENE_ID", nullable = false, precision = 0)
    public byte getRefGeneId() {
        return refGeneId;
    }

    public void setRefGeneId(byte refGeneId) {
        this.refGeneId = refGeneId;
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
    @Column(name = "REF_STS_ID", nullable = false, precision = 0)
    public byte getRefStsId() {
        return refStsId;
    }

    public void setRefStsId(byte refStsId) {
        this.refStsId = refStsId;
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

        RefTrgtGeneAsgnmntEntity that = (RefTrgtGeneAsgnmntEntity) o;

        if (refTrgtGeneAsgnmntId != that.refTrgtGeneAsgnmntId) return false;
        if (refBlgclTrgtId != that.refBlgclTrgtId) return false;
        if (refGeneId != that.refGeneId) return false;
        if (refStsId != that.refStsId) return false;
        if (stsDt != null ? !stsDt.equals(that.stsDt) : that.stsDt != null) return false;
        if (chngTmstmp != null ? !chngTmstmp.equals(that.chngTmstmp) : that.chngTmstmp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) refTrgtGeneAsgnmntId;
        result = 31 * result + (int) refBlgclTrgtId;
        result = 31 * result + (int) refGeneId;
        result = 31 * result + (stsDt != null ? stsDt.hashCode() : 0);
        result = 31 * result + (int) refStsId;
        result = 31 * result + (chngTmstmp != null ? chngTmstmp.hashCode() : 0);
        return result;
    }
}
