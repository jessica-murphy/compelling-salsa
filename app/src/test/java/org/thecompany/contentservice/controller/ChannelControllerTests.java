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

import org.junit.jupiter.api.Test;
import org.thecompany.contentservice.service.ChannelService;
import org.thecompany.contentservice.transformer.client.ChannelClientTransformer;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ChannelControllerTests {
	private final ChannelService channelService = mock(ChannelService.class);
	private final ChannelClientTransformer channelClientTransformer = mock(ChannelClientTransformer.class);
	private final ChannelController channelController = new ChannelController(this.channelService, this.channelClientTransformer);
	private static final String CHANNEL = "myprotein";
	private static final String USERNAME = "Arnold Schwarzenegger";
	@Test
	void getChannelShouldCallChannelService() {
		this.channelController.getChannel(CHANNEL);
		verify(this.channelService, times(1)).getChannel(CHANNEL);
	}
	@Test
	void createChannelShouldCallChannelService() {
		org.thecompany.contentservice.model.client.Channel channelJson = new org.thecompany.contentservice.model.client.Channel(CHANNEL);
		org.thecompany.contentservice.model.internal.Channel expected = new org.thecompany.contentservice.model.internal.Channel(CHANNEL);
		doReturn(expected).when(this.channelClientTransformer).fromRequest(channelJson);
		this.channelController.createChannel(channelJson, USERNAME);
		verify(this.channelService, times(1)).createChannel(expected, USERNAME);
	}
	@Test
	void deleteChannelShouldCallChannelService() {
		this.channelController.deleteChannel(CHANNEL, USERNAME);
		verify(this.channelService, times(1)).deleteChannel(CHANNEL, USERNAME);
	}
}
