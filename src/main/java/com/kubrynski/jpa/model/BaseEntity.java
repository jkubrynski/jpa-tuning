package com.kubrynski.jpa.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Jakub Kubrynski
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class BaseEntity {

	@Id
	@GeneratedValue
	@Access(AccessType.PROPERTY)
	private Long id;

	@Version
	private Timestamp version;

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	@Column(length = 36, nullable = false, updatable = false)
	private String uuid = UUID.randomUUID().toString();

	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid);
	}
	@Override
	public boolean equals(Object that) {
		return this == that || that instanceof BaseEntity
				&& Objects.equals(uuid, ((BaseEntity) that).uuid);
	}

}
