package com.cristiandpt.device_emitter.repository

import com.cristiandpt.device_emitter.entity.BloodPressureEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BloodPressureRepository : JpaRepository<BloodPressureEntity, Long> {

    @Query(
            """
            SELECT
                *
            FROM
                measurement_records
            ORDER BY
                id ASC
            LIMIT 10
        """
    )
    fun findTop10RecordsNativeSql(): List<BloodPressureEntity>
}
