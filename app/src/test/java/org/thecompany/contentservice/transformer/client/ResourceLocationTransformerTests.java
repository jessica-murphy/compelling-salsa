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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import static org.assertj.core.api.Assertions.assertThat;

class ResourceLocationTransformerTests {
	private static final String CHANNEL_NAME = "channelName";
	private static final String SCHEME = "http";
	private static final String HOST = "localhost";
	private static final Integer PORT = 12345;

	@BeforeEach
	void setup() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setScheme(SCHEME);
		request.setServerName(HOST);
		request.setServerPort(PORT);
		RequestAttributes requestAttributes = new ServletWebRequest(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);
	}
	@Test
	void retrieveChannelUri() {
		URI expected = URI.create("http://localhost:12345/channel/v1/channelName");
		ResourceLocationTransformer resourceLocationTransformer = new ResourceLocationTransformer();
		assertThat(resourceLocationTransformer.retrieveChannelUri(CHANNEL_NAME))
				.as("Expected the resource's location to be returned.")
				.isEqualTo(expected.toString());
	}
}
