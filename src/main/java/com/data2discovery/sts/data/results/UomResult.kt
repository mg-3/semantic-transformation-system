package com.data2discovery.sts.data.results

/**
 * The resulting type for UOM results
 *
 * Created by mgarcia on 4/4/16.
 */
class UomResult(
        val id: Int,
        val stsShortName: String?,
        val stsDesc: String?,
        var uomId: Int,
        var uomName: String?,
        var uomShortName: String?,
        var uomDesc: String?
                ) {

    fun setUomDescription(desc: String) {
        uomDesc = desc
    }

}
