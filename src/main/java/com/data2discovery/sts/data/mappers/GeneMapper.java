package com.data2discovery.sts.data.mappers;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created by mgarcia on 2/9/16.
 */
@RequiredArgsConstructor
@Data
public class GeneMapper {

    private final int geneId;
    private final String geneName;
    private final String geneSymbol;

}
