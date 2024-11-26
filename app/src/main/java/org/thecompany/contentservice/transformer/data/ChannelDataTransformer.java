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

package org.thecompany.contentservice.transformer.data;

import java.time.Instant;

import org.springframework.stereotype.Component;

@Component
public class ChannelDataTransformer {
	public org.thecompany.contentservice.model.data.Channel toDatabaseRepresentation(org.thecompany.contentservice.model.internal.Channel internalModel, String username) {
		try {
			return new org.thecompany.contentservice.model.data.Channel(
					internalModel.channelName(),
					username,
					Instant.now()
			);
		}
		catch (Exception exception) {
			throw new DataTransformerException(String.format("Failed to transform channel '%s' from internal to database representation.", internalModel), exception);
		}
	}
	public org.thecompany.contentservice.model.internal.Channel toInternalRepresentation(org.thecompany.contentservice.model.data.Channel databaseModel) {
		try {
			return new org.thecompany.contentservice.model.internal.Channel(
					databaseModel.channelId
			);
		}
		catch (Exception exception) {
			throw new DataTransformerException(String.format("Failed to transform channel '%s' from database to internal representation.", databaseModel), exception);
		}
	}
}
