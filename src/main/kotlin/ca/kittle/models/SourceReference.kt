package ca.kittle.models

import kotlinx.serialization.Serializable

@Serializable
data class SourceReference(val sourceId: Int, val pageNumber: Int) {
    override fun toString(): String {
        return "${Sources.getSourceById(sourceId).label}, page $pageNumber"
    }
}
