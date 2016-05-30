package com.data2discovery.sts.data.model;

import javax.persistence.*;

/**
 * Created by mike on 11/22/15.
 */
@Entity
@Table(name = "TISSUE", schema = "SEMANTIC", catalog = "")
public class TissueEntity {
    private Integer tissueId;
    private String tissueName;

    @Id
    @Column(name = "TISSUE_ID")
    public Integer getTissueId() {
        return tissueId;
    }

    public void setTissueId(Integer tissueId) {
        this.tissueId = tissueId;
    }

    @Basic
    @Column(name = "TISSUE_NAME")
    public String getTissueName() {
        return tissueName;
    }

    public void setTissueName(String tissueName) {
        this.tissueName = tissueName;
    }

//    private List<GeneEntity> genes;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "GENE_TISSUE"
//            , joinColumns = @JoinColumn(name = "tissueId", referencedColumnName = "TISSUE_ID")
//            , inverseJoinColumns = @JoinColumn(name = "geneId", referencedColumnName = "GENE_ID"))
//    public List<GeneEntity> getGenes() {
//        return genes;
//    }
//
//    public void setGenes(List<GeneEntity> genes) {
//        this.genes = genes;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TissueEntity that = (TissueEntity) o;

        if (tissueId != null ? !tissueId.equals(that.tissueId) : that.tissueId != null) return false;
        return !(tissueName != null ? !tissueName.equals(that.tissueName) : that.tissueName != null);

    }

    @Override
    public int hashCode() {
        int result = tissueId != null ? tissueId.hashCode() : 0;
        result = 31 * result + (tissueName != null ? tissueName.hashCode() : 0);
        return result;
    }

}
