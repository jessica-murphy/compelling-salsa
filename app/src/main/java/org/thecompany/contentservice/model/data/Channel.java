/*
 * Copyright 2024-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.thecompany.contentservice.model.data;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "channel")
@AllArgsConstructor
@NoArgsConstructor
public class Channel {

	/**
	 * Unique string representing the channelName. Use camelCase.
	 */
	@Id
	@NotBlank
	@Column(name = "channel_id")
	public String channelId;

	/**
	 * String recording who initiated the most recent update to the channelName.
	 */
	@Column(name = "updated_by", nullable = false)
	public String updated_by;

	/**
	 * UTC timestamp recording when the most recent update to the channelName occurred.
	 */
	@Column(name = "last_updated", nullable = false)
	public Instant last_updated;
}
