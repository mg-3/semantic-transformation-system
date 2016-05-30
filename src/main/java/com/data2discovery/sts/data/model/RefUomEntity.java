package com.data2discovery.sts.data.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mgarcia on 4/3/16.
 */
@Entity
@Table(name = "REF_UOM", schema = "REFDB", catalog = "")
public class RefUomEntity {
    private Integer refUomId;
    private String uomNm;
    private String uomShrtNm;
    private String uomDescTxt;
    private Integer refStsId;
    private Date stsDt;
    private Date chngTmstmp;
    private RefStsEntity refStsEntity;

    @ManyToOne
    @JoinColumn(referencedColumnName = "REF_STS_ID")
    public RefStsEntity getRefStsEntity() {
        return refStsEntity;
    }

    public void setRefStsEntity(RefStsEntity refStsEntity) {
        this.refStsEntity = refStsEntity;
    }


    @Id
    @Column(name = "REF_UOM_ID", nullable = false, precision = 0)
    public Integer getRefUomId() {
        return refUomId;
    }

    public void setRefUomId(Integer refUomId) {
        this.refUomId = refUomId;
    }

    @Basic
    @Column(name = "UOM_NM", nullable = false, length = 50)
    public String getUomNm() {
        return uomNm;
    }

    public void setUomNm(String uomNm) {
        this.uomNm = uomNm;
    }

    @Basic
    @Column(name = "UOM_SHRT_NM", nullable = false, length = 15)
    public String getUomShrtNm() {
        return uomShrtNm;
    }

    public void setUomShrtNm(String uomShrtNm) {
        this.uomShrtNm = uomShrtNm;
    }

    @Basic
    @Column(name = "UOM_DESC_TXT", nullable = true, length = 500)
    public String getUomDescTxt() {
        return uomDescTxt;
    }

    public void setUomDescTxt(String uomDescTxt) {
        this.uomDescTxt = uomDescTxt;
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
    public Date getStsDt() {
        return stsDt;
    }

    public void setStsDt(Date stsDt) {
        this.stsDt = stsDt;
    }

    @Basic
    @Column(name = "CHNG_TMSTMP", nullable = false)
    public Date getChngTmstmp() {
        return chngTmstmp;
    }

    public void setChngTmstmp(Date chngTmstmp) {
        this.chngTmstmp = chngTmstmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefUomEntity that = (RefUomEntity) o;

        if (refUomId != that.refUomId) return false;
        if (refStsId != that.refStsId) return false;
        if (uomNm != null ? !uomNm.equals(that.uomNm) : that.uomNm != null) return false;
        if (uomShrtNm != null ? !uomShrtNm.equals(that.uomShrtNm) : that.uomShrtNm != null) return false;
        if (uomDescTxt != null ? !uomDescTxt.equals(that.uomDescTxt) : that.uomDescTxt != null) return false;
        if (stsDt != null ? !stsDt.equals(that.stsDt) : that.stsDt != null) return false;
        if (chngTmstmp != null ? !chngTmstmp.equals(that.chngTmstmp) : that.chngTmstmp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) refUomId;
        result = 31 * result + (uomNm != null ? uomNm.hashCode() : 0);
        result = 31 * result + (uomShrtNm != null ? uomShrtNm.hashCode() : 0);
        result = 31 * result + (uomDescTxt != null ? uomDescTxt.hashCode() : 0);
        result = 31 * result + (int) refStsId;
        result = 31 * result + (stsDt != null ? stsDt.hashCode() : 0);
        result = 31 * result + (chngTmstmp != null ? chngTmstmp.hashCode() : 0);
        return result;
    }
}
