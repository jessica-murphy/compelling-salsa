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

package org.thecompany.contentservice.transformer.client;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ChannelClientTransformer {
	public org.thecompany.contentservice.model.internal.Channel fromRequest(org.thecompany.contentservice.model.client.Channel clientModel) {
		try {
			String channelName = clientModel.channelName();
			return new org.thecompany.contentservice.model.internal.Channel(channelName);
		}
		catch (Exception exception) {
			throw new ClientTransformerException(String.format("Failed to transform channel '%s' from client to internal representation.", clientModel), exception);
		}
	}
	public HttpEntity<org.thecompany.contentservice.model.client.Channel> toResponse(org.thecompany.contentservice.model.internal.Channel internalModel) {
		try {
			String channel = internalModel.channelName();
			org.thecompany.contentservice.model.client.Channel responseBody = new org.thecompany.contentservice.model.client.Channel(internalModel.channelName());
			// todo: make dynamic
			URI location = URI.create("http://localhost:8080/channel/v1/" + channel);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(HttpHeaders.LOCATION, location.toString());
			return new HttpEntity<>(responseBody, headers);
		}
		catch (Exception exception) {
			throw new ClientTransformerException(String.format("Failed to transform channel '%s' from internal to client representation.", internalModel), exception);
		}
	}
}
