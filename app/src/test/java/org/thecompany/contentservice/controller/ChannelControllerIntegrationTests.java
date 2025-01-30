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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.thecompany.contentservice.IntegrationTests;
import org.thecompany.contentservice.model.client.Channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class ChannelControllerIntegrationTests extends IntegrationTests {
	@Autowired
	private TestRestTemplate restTemplate;
	private static final String CHANNEL_PATH = "channel/v1";
	private static final String CHANNEL = "myprotein";
	private static final String NOT_EXISTING_CHANNEL = "notExisting";
	private static final String USERNAME = "Arnold Schwarzenegger";
	@AfterEach
	void tearDown() {
		tearDownChannel(CHANNEL);
		tearDownChannel(NOT_EXISTING_CHANNEL);
	}
	@Test
	void shouldFailToRetrieveNonExistentChannel() {
		String getRequestUrl = getBaseUrl() + CHANNEL_PATH + "/" + NOT_EXISTING_CHANNEL;
		assertThat(this.restTemplate.getForEntity(getRequestUrl, String.class)
				.getStatusCode())
				.as("Expected retrieval of a non existent channel to return a not found response.")
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
		String getRequestUrl = getBaseUrl() + CHANNEL_PATH + "/" + CHANNEL;

		// resource can be created
		ResponseEntity<Channel> postResponseEntity = this.restTemplate.postForEntity(postRequestUrl, requestEntity, Channel.class);
		assertThat(postResponseEntity.getStatusCode())
				.as("Expected creation of a not-previously-existing channel to return a success response.")
				.isEqualTo(HttpStatus.CREATED);
		assertThat(postResponseEntity.getHeaders().get(HttpHeaders.LOCATION))
				.as("Expected the location of the created resource to be returned.")
				.containsExactly(getRequestUrl);
		assertThat(postResponseEntity.getBody())
				.as("Expected the body to contain the created resource for confirmation.")
				.isEqualTo(channelJson);
		// resource can be retrieved after creation
		ResponseEntity<Channel> getResponseEntity = this.restTemplate.getForEntity(getRequestUrl, Channel.class);
		assertThat(getResponseEntity.getStatusCode())
				.as("Expected retrieval of existing channel to return a success response.")
				.isEqualTo(HttpStatus.OK);
	}
	@Test
	void shouldFailToCreateExistingChannel() {
		restfulChannelCreation();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("username", USERNAME);
		Channel channelJson = new Channel(CHANNEL);
		HttpEntity<Channel> requestEntity = new HttpEntity<>(channelJson, headers);
		String postRequestUrl = getBaseUrl() + CHANNEL_PATH + "/";

		// resource is rejected because it already exists
		ResponseEntity<String> postResponseEntity = this.restTemplate.postForEntity(postRequestUrl, requestEntity, String.class);
		assertThat(postResponseEntity.getStatusCode())
				.as("Expected creation of an already-existing channel to return a bad request response.")
				.isEqualTo(HttpStatus.BAD_REQUEST);
	}
	@Test
	void shouldDeleteExistingChannel() {
		restfulChannelCreation();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("username", USERNAME);
		HttpEntity<Channel> requestEntity = new HttpEntity<>(headers);
		String deleteRequestUrl = getBaseUrl() + CHANNEL_PATH + "/" + CHANNEL;
		ResponseEntity<Void> deleteResponseEntity = this.restTemplate.exchange(deleteRequestUrl, HttpMethod.DELETE, requestEntity, Void.class);
		assertThat(deleteResponseEntity.getStatusCode())
				.as("Expected deletion of an existing channel to return a success response.")
				.isEqualTo(HttpStatus.NO_CONTENT);
	}
	@Test
	void shouldFailToDeleteNonExistentChannel() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("username", USERNAME);
		HttpEntity<Channel> requestEntity = new HttpEntity<>(headers);

		assertThat(this.restTemplate.exchange(getBaseUrl() + CHANNEL_PATH + "/" + NOT_EXISTING_CHANNEL, HttpMethod.DELETE, requestEntity, Void.class)
				.getStatusCode())
				.as("Expected deletion of a non-existent channel to return a not found response.")
				.isEqualTo(HttpStatus.NOT_FOUND);
	}
	private void tearDownChannel(String channelName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("username", "tearDown");
		HttpEntity<Channel> requestEntity = new HttpEntity<>(headers);
		String deleteRequestUrl = getBaseUrl() + CHANNEL_PATH + "/" + channelName;
		ResponseEntity<Void> deleteResponseEntity = this.restTemplate.exchange(deleteRequestUrl, HttpMethod.DELETE, requestEntity, Void.class);
		assertThat(deleteResponseEntity.getStatusCode())
				.as("Expected either channel did not exist or delete returns a success response.")
				.isIn(HttpStatus.NO_CONTENT, HttpStatus.NOT_FOUND);
	}
}
