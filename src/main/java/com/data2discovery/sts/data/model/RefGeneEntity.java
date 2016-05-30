package com.data2discovery.sts.data.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by mgarcia on 5/25/16.
 */
@Entity
@Table(name = "REF_GENE", schema = "REFDB", catalog = "")
public class RefGeneEntity {
    private Integer refGeneId;
    private String geneNm;
    private String geneShrtNm;
    private String geneDescTxt;
    private String srcGeneId;
    private String geneSymbl;
    private Time stsDt;
    private Integer refStsId;
    private Time chngTmstmp;
    private Integer refSpcsId;

    @Id
    @Column(name = "REF_GENE_ID", nullable = false, precision = 0)
    public Integer getRefGeneId() {
        return refGeneId;
    }

    public void setRefGeneId(Integer refGeneId) {
        this.refGeneId = refGeneId;
    }

    @Basic
    @Column(name = "GENE_NM", nullable = true, length = 100)
    public String getGeneNm() {
        return geneNm;
    }

    public void setGeneNm(String geneNm) {
        this.geneNm = geneNm;
    }

    @Basic
    @Column(name = "GENE_SHRT_NM", nullable = true, length = 35)
    public String getGeneShrtNm() {
        return geneShrtNm;
    }

    public void setGeneShrtNm(String geneShrtNm) {
        this.geneShrtNm = geneShrtNm;
    }

    @Basic
    @Column(name = "GENE_DESC_TXT", nullable = true, length = 500)
    public String getGeneDescTxt() {
        return geneDescTxt;
    }

    public void setGeneDescTxt(String geneDescTxt) {
        this.geneDescTxt = geneDescTxt;
    }

    @Basic
    @Column(name = "SRC_GENE_ID", nullable = false, length = 100)
    public String getSrcGeneId() {
        return srcGeneId;
    }

    public void setSrcGeneId(String srcGeneId) {
        this.srcGeneId = srcGeneId;
    }

    @Basic
    @Column(name = "GENE_SYMBL", nullable = false, length = 100)
    public String getGeneSymbl() {
        return geneSymbl;
    }

    public void setGeneSymbl(String geneSymbl) {
        this.geneSymbl = geneSymbl;
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
    public Integer getRefStsId() {
        return refStsId;
    }

    public void setRefStsId(Integer refStsId) {
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

    @Basic
    @Column(name = "REF_SPCS_ID", nullable = true, precision = 0)
    public Integer getRefSpcsId() {
        return refSpcsId;
    }

    public void setRefSpcsId(Integer refSpcsId) {
        this.refSpcsId = refSpcsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefGeneEntity that = (RefGeneEntity) o;

        if (refGeneId != that.refGeneId) return false;
        if (refStsId != that.refStsId) return false;
        if (geneNm != null ? !geneNm.equals(that.geneNm) : that.geneNm != null) return false;
        if (geneShrtNm != null ? !geneShrtNm.equals(that.geneShrtNm) : that.geneShrtNm != null) return false;
        if (geneDescTxt != null ? !geneDescTxt.equals(that.geneDescTxt) : that.geneDescTxt != null) return false;
        if (srcGeneId != null ? !srcGeneId.equals(that.srcGeneId) : that.srcGeneId != null) return false;
        if (geneSymbl != null ? !geneSymbl.equals(that.geneSymbl) : that.geneSymbl != null) return false;
        if (stsDt != null ? !stsDt.equals(that.stsDt) : that.stsDt != null) return false;
        if (chngTmstmp != null ? !chngTmstmp.equals(that.chngTmstmp) : that.chngTmstmp != null) return false;
        if (refSpcsId != null ? !refSpcsId.equals(that.refSpcsId) : that.refSpcsId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refGeneId;
        result = 31 * result + (geneNm != null ? geneNm.hashCode() : 0);
        result = 31 * result + (geneShrtNm != null ? geneShrtNm.hashCode() : 0);
        result = 31 * result + (geneDescTxt != null ? geneDescTxt.hashCode() : 0);
        result = 31 * result + (srcGeneId != null ? srcGeneId.hashCode() : 0);
        result = 31 * result + (geneSymbl != null ? geneSymbl.hashCode() : 0);
        result = 31 * result + (stsDt != null ? stsDt.hashCode() : 0);
        result = 31 * result + (int) refStsId;
        result = 31 * result + (chngTmstmp != null ? chngTmstmp.hashCode() : 0);
        result = 31 * result + (refSpcsId != null ? refSpcsId.hashCode() : 0);
        return result;
    }
}
