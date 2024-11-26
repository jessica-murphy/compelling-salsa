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

package org.thecompany.contentservice.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.thecompany.contentservice.IntegrationTests;
import org.thecompany.contentservice.model.client.Channel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

class ChannelControllerIntegrationTests extends IntegrationTests {
	@Autowired
	private TestRestTemplate restTemplate;
	private static final String CHANNEL_PATH = "channel/v1";
	private static final String CHANNEL = "myprotein";
	private static final String USERNAME = "Arnold Schwarzenegger";

//	@Test
//	void shouldRetrieveAnExistingChannel() {
//		assertThat(this.restTemplate.getForEntity(getBaseUrl() + CHANNEL_PATH + "/" + CHANNEL, String.class)
//				.getStatusCode())
//				.as("Expected retrieval of an existing channelName to return a success response.")
//				.isEqualTo(HttpStatus.OK);
//	}

	@Test
	void shouldFailToRetrieveNonExistentChannel() {
		assertThat(this.restTemplate.getForEntity(getBaseUrl() + CHANNEL_PATH + "/notExisting", String.class)
				.getStatusCode())
				.as("Expected retrieval of a non existent channelName to return a not found response.")
				.isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	void restfulChannelCreation() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("username", USERNAME);
		Channel channelJson = new Channel(CHANNEL);
		HttpEntity<Channel> requestEntity = new HttpEntity<>(channelJson, headers);
		String postRequestUrl = getBaseUrl() + CHANNEL_PATH + "/";
		URI responseLocation = this.restTemplate.postForLocation(postRequestUrl, requestEntity);
		Channel responseBody = this.restTemplate.postForObject(postRequestUrl, requestEntity, Channel.class);

		String getRequestUrl = getBaseUrl() + CHANNEL_PATH + "/" + CHANNEL;
//		assertThat(String.valueOf(responseLocation))
//				.isEqualTo(getRequestUrl);

		assertThat(this.restTemplate.postForEntity(postRequestUrl, requestEntity, Channel.class)
				.getStatusCode())
				.as("Expected creation of a non existent channelName to return a success response.")
				.isEqualTo(HttpStatus.CREATED);

		assertThat(this.restTemplate.getForEntity(getBaseUrl() + CHANNEL_PATH + "/" + CHANNEL, String.class)
				.getStatusCode())
				.as("Expected retrieval of existing channelName to return a success response.")
				.isEqualTo(HttpStatus.OK);
	}
//
//	@Test
//	void shouldFailToCreateExistingChannel() {
//		assertThat(this.restTemplate.getForEntity(getBaseUrl() + CHANNEL_PATH + "/notExisting" , String.class)
//				.getStatusCode())
//				.as("Expected creation of an existing channelName to return a bad request response.")
//				.isEqualTo(HttpStatus.NOT_FOUND);
//		Channel channelJson = new Channel(CHANNEL);
//
//		assertThatThrownBy(() -> this.restTemplate.put(getBaseUrl() + CHANNEL_PATH + "/notExisting", channelJson))
//				.as("Expected creation of an existing channelName to return a success response.")
//				.isInstanceOf(RestClientException.class)
//				.hasMessageContaining(HttpStatus.BAD_REQUEST.getReasonPhrase());
//	}
//
//	@Test
//	void shouldDeleteExistingChannel() {
//		assertThatThrownBy(() -> this.restTemplate.delete(getBaseUrl() + CHANNEL_PATH + "/notExisting"))
//				.as("Expected creation of an existing channelName to return a success response.")
//				.hasMessageContaining(HttpStatus.BAD_REQUEST.getReasonPhrase());
//	}
//
//	@Test // todo: fails because no way to tell if the thing previously existed
//	void shouldFailToDeleteNonExistentChannel() {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.add("username", USERNAME);
//		HttpEntity<Channel> requestEntity = new HttpEntity<>(headers);
//
//		assertThat(this.restTemplate.exchange(getBaseUrl() + CHANNEL_PATH + "/notExisting", HttpMethod.DELETE, requestEntity, Void.class)
//				.getStatusCode())
//				.as("Expected deletion of a non-existent channelName to return a not found response.")
//				.isEqualTo(HttpStatus.NOT_FOUND);
//	}
}
