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
import java.net.URISyntaxException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResourceLocationTransformer {
	public String retrieveChannelUri(String channelName) {
		String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		String path = "/channel/v1/" + channelName;

		URI location;
		try {
			location = new URI(baseUrl + path);
		}
		catch (URISyntaxException exception) {
			throw new ClientTransformerException(String.format("Failed to determine URI from host '%s' and path '%s'.", baseUrl, path), exception);
		}
		return location.toString();
	}
}
