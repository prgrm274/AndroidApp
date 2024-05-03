package com.programmer270487.dansandroid.data.mappers

import com.programmer270487.dansandroid.domain.Position
import com.programmer270487.dansandroid.data.remote.PositionDto
import com.programmer270487.dansandroid.data.local.PositionEntity

/**
 * DTO => ENTITY
 */
fun PositionDto.toPositionEntity(): PositionEntity {
    return PositionEntity(
        id = id,
        company = company,
        company_logo = company_logo,
        company_url = company_url,
        created_at = created_at,
        description = description,
        how_to_apply = how_to_apply,
        location = location,
        title = title,
        type = type,
        url = url
    )
}

/**
 * ENTITY => Position
 */
fun PositionEntity.toPosition(): Position {
    return Position(
        id = id,
        company = company,
        company_logo = company_logo,
        company_url = company_url,
        created_at = created_at,
        description = description,
        how_to_apply = how_to_apply,
        location = location,
        title = title,
        type = type,
        url = url
    )
}