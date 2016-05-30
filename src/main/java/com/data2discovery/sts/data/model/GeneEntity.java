package com.data2discovery.sts.data.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mike on 11/22/15.
 */
@Entity
@Table(name = "GENE", schema = "SEMANTIC", catalog = "")
public class GeneEntity {
    private String geneId;
    private String geneSymbol;
    private String geneName;
    private List<TissueEntity> tissues;

    @ManyToMany
    @JoinTable(name = "GENE_TISSUE"
            , joinColumns = @JoinColumn(name = "geneId", referencedColumnName = "GENE_ID")
            , inverseJoinColumns = @JoinColumn(name = "tissueId", referencedColumnName = "TISSUE_ID"))
    public List<TissueEntity> getTissues() {
        return tissues;
    }

    public void setTissues(List<TissueEntity> tissues) {
        this.tissues = tissues;
    }

    @Id
    @Basic
    @Column(name = "GENE_ID")
    public String getGeneId() {
        return geneId;
    }

    public void setGeneId(String geneId) {
        this.geneId = geneId;
    }

    @Basic
    @Column(name = "GENE_SYMBOL")
    public String getGeneSymbol() {
        return geneSymbol;
    }

    public void setGeneSymbol(String geneSymbol) {
        this.geneSymbol = geneSymbol;
    }

    @Basic
    @Column(name = "GENE_NAME")
    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeneEntity that = (GeneEntity) o;

        if (geneId != null ? !geneId.equals(that.geneId) : that.geneId != null) return false;
        if (geneSymbol != null ? !geneSymbol.equals(that.geneSymbol) : that.geneSymbol != null) return false;
        return !(geneName != null ? !geneName.equals(that.geneName) : that.geneName != null);

    }

    @Override
    public int hashCode() {
        int result = geneId != null ? geneId.hashCode() : 0;
        result = 31 * result + (geneSymbol != null ? geneSymbol.hashCode() : 0);
        result = 31 * result + (geneName != null ? geneName.hashCode() : 0);
        return result;
    }
}
