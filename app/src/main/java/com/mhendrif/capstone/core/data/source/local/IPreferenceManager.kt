package com.mhendrif.capstone.core.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.mhendrif.capstone.core.utils.SortOrder

interface IPreferenceManager {
    suspend fun updateSortOrder(sortOrder: SortOrder)
    fun getSortOrder(): DataStore<Preferences>
}