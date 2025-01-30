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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.thecompany.contentservice.model.internal.Channel;
import org.thecompany.contentservice.service.ChannelService;
import org.thecompany.contentservice.transformer.client.ChannelClientTransformer;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("channel/v1")
@RequiredArgsConstructor
public class ChannelController {
	private final ChannelService channelService;
	private final ChannelClientTransformer channelClientTransformer;

	@GetMapping("/{channelName}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(
			summary = "Retrieve a channel.",
			description = "Accepts a channel name and returns that channel resource."
	)
	public HttpEntity<org.thecompany.contentservice.model.client.Channel> getChannel(
			@PathVariable @Schema(example = "ShopSphere") String channelName
	) {
		Channel retrievedResource =  this.channelService.getChannel(channelName);
		return this.channelClientTransformer.toResponse(retrievedResource);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(
			summary = "Create a channel.",
			description = "Accepts a channel resource and returns the created channel resource."
	)
	public HttpEntity<org.thecompany.contentservice.model.client.Channel> createChannel(
			@RequestBody @Valid org.thecompany.contentservice.model.client.Channel channelJson,
			@RequestHeader @Schema(example = "David Beckham") @NotBlank String username
	) {
		Channel channel = this.channelClientTransformer.fromRequest(channelJson);
		Channel createdResource = this.channelService.createChannel(channel, username);
		return this.channelClientTransformer.toResponse(createdResource);
	}

	@DeleteMapping("/{channelName}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(
			summary = "Delete a channel.",
			description = "Accepts a channel name and removes that channel resource."
	)
	public void deleteChannel(
			@PathVariable @Schema(example = "ShopSphere") @NotBlank String channelName,
			@RequestHeader @Schema(example = "David Beckham") @NotBlank String username
	) {
		this.channelService.deleteChannel(channelName, username);
	}
}
