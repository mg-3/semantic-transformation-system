package com.data2discovery.sts.data.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by mgarcia on 4/3/16.
 */
@Entity
@Table(name = "REF_STS", schema = "REFDB", catalog = "")
public class RefStsEntity {
    private Integer refStsId;
    private String stsNm;
    private String stsShrtNm;
    private String stsDescTxt;
    private Date chngTmstmp;
    private Set<RefUomEntity> uomEntitySet;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "REF_STS_ID")
    public Set<RefUomEntity> getUomEntitySet() {
        return uomEntitySet;
    }

    public void setUomEntitySet(Set<RefUomEntity> uomEntitySet) {
        this.uomEntitySet = uomEntitySet;
    }

    @Id
    @Column(name = "REF_STS_ID", nullable = false, precision = 0)
    public Integer getRefStsId() {
        return refStsId;
    }

    public void setRefStsId(Integer refStsId) {
        this.refStsId = refStsId;
    }

    @Basic
    @Column(name = "STS_NM", nullable = false, length = 50)
    public String getStsNm() {
        return stsNm;
    }

    public void setStsNm(String stsNm) {
        this.stsNm = stsNm;
    }

    @Basic
    @Column(name = "STS_SHRT_NM", nullable = false, length = 15)
    public String getStsShrtNm() {
        return stsShrtNm;
    }

    public void setStsShrtNm(String stsShrtNm) {
        this.stsShrtNm = stsShrtNm;
    }

    @Basic
    @Column(name = "STS_DESC_TXT", nullable = true, length = 500)
    public String getStsDescTxt() {
        return stsDescTxt;
    }

    public void setStsDescTxt(String stsDescTxt) {
        this.stsDescTxt = stsDescTxt;
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

        RefStsEntity that = (RefStsEntity) o;

        if (refStsId != that.refStsId) return false;
        if (stsNm != null ? !stsNm.equals(that.stsNm) : that.stsNm != null) return false;
        if (stsShrtNm != null ? !stsShrtNm.equals(that.stsShrtNm) : that.stsShrtNm != null) return false;
        if (stsDescTxt != null ? !stsDescTxt.equals(that.stsDescTxt) : that.stsDescTxt != null) return false;
        if (chngTmstmp != null ? !chngTmstmp.equals(that.chngTmstmp) : that.chngTmstmp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) refStsId;
        result = 31 * result + (stsNm != null ? stsNm.hashCode() : 0);
        result = 31 * result + (stsShrtNm != null ? stsShrtNm.hashCode() : 0);
        result = 31 * result + (stsDescTxt != null ? stsDescTxt.hashCode() : 0);
        result = 31 * result + (chngTmstmp != null ? chngTmstmp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RefStsEntity{" +
                "refStsId=" + refStsId +
                ", stsNm='" + stsNm + '\'' +
                ", stsShrtNm='" + stsShrtNm + '\'' +
                ", stsDescTxt='" + stsDescTxt + '\'' +
                ", chngTmstmp=" + chngTmstmp +
                '}';
    }
}
