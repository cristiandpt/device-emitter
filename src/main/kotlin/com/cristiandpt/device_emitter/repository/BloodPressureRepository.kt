package com.cristiandpt.device_emitter.repository

import com.cristiandpt.device_emitter.entity.BloodPressureEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BloodPressureRepository : JpaRepository<BloodPressureEntity, Long>
