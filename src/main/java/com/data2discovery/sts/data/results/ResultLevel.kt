package com.data2discovery.sts.data.results

/**
 * Created by mgarcia on 5/29/16.
 */
class ResultLevel(
        val refRsltLvlId: Int?,
        val rsltLvlNm: String?,
        val rsltLvlShrtNm: String?,
        val rsltLvlDescTxt: String?,
        val refStsId: Int?
)
//{
//    companion object {
//        @Transient val qlString = "select new com.data2discovery.sts.data.results.ResultLevel(" +
//                "e.refRsltLvlId, " +
//                "e.rsltLvlNm, " +
//                "e.rsltLvlShrtNm, " +
//                "e.rsltLvlDescTxt, " +
//                "e.refStsId) " +
//                "from RefRsltLvlEntity as e"
//    }
//}