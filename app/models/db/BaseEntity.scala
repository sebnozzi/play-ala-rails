package models.db

import org.squeryl.KeyedEntity

class BaseEntity extends KeyedEntity[Long] {
    val id: Long = 0
}