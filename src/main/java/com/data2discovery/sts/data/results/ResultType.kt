package com.data2discovery.sts.data.results

/**
 * Created by mgarcia on 5/29/16.
 */
class ResultType(
        val refBlgclRsltTypId: Int?,
        val blgclRsltTypNm: String?,
        val blgclRsltTypShrtNm: String?,
        val blgclRsltTypDescTxt: String?,
        val refStsId: Int?,
        val refRsltLvlId: Int?
) {

    companion object {
        @Transient val qlString = "select new com.data2discovery.sts.data.results.ResultType(" +
                "e.refBlgclRsltTypId, " +
                "e.blgclRsltTypNm, " +
                "e.blgclRsltTypShrtNm, " +
                "e.blgclRsltTypDescTxt, " +
                "e.refStsId, e.refRsltLvlId) " +
                "from RefBlgclRsltEntity as e"
    }
}
