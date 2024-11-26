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

import org.junit.jupiter.api.Test;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

class ChannelClientTransformerTests {
	private final ChannelClientTransformer channelClientTransformer = new ChannelClientTransformer();
	private static final String CHANNEL = "myprotein";
	private static final String LOCATION = "test/location";

	@Test
	void fromRequest() {
		org.thecompany.contentservice.model.client.Channel channelJson = new org.thecompany.contentservice.model.client.Channel(CHANNEL);
		org.thecompany.contentservice.model.internal.Channel expected = new org.thecompany.contentservice.model.internal.Channel(CHANNEL);
		assertThat(this.channelClientTransformer.fromRequest(channelJson))
				.as("Expected client model to be transformed into internal model.")
				.isEqualTo(expected);
	}

	@Test
	void toResponse() {
		org.thecompany.contentservice.model.internal.Channel internalModel = new org.thecompany.contentservice.model.internal.Channel(CHANNEL);
		org.thecompany.contentservice.model.client.Channel responseBody = new org.thecompany.contentservice.model.client.Channel(CHANNEL);

		URI location = URI.create("http://localhost:8080/channel/v1/" + CHANNEL);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(HttpHeaders.LOCATION, location.toString());
		HttpEntity<org.thecompany.contentservice.model.client.Channel> expected = new HttpEntity<>(responseBody, headers);
		assertThat(this.channelClientTransformer.toResponse(internalModel))
				.as("Expected internal model to be transformed into client model.")
				.isEqualTo(expected);
	}
}
