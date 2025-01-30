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

package org.thecompany.contentservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thecompany.contentservice.model.internal.Channel;
import org.thecompany.contentservice.repository.ChannelRepository;
import org.thecompany.contentservice.transformer.data.ChannelDataTransformer;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChannelService {
	private final ChannelRepository channelRepository;
	private final ChannelDataTransformer channelDataTransformer;

	@Transactional
	public Channel getChannel(String channelName) {
		try {
			org.thecompany.contentservice.model.data.Channel channel = this.channelRepository.findChannelByChannelId(channelName);

			if (channel == null) {
				throw new ResourceNotFoundException(String.format("No channel found with name '%s'.", channelName));
			}
			return this.channelDataTransformer.toInternalRepresentation(channel);
		}
		catch (DataAccessException exception) {
			throw new ResourceRepositoryException(String.format("Failed to retrieve channel '%s'.", channelName), exception);
		}
	}
	@Transactional
	public Channel createChannel(Channel channel, String username) {
		try {
			if (this.channelRepository.existsChannelByChannelId(channel.channelName())) {
				throw new ResourceAlreadyExistsException(String.format("There already exists a channel with channel name '%s'.", channel.channelName()));
			}
			return this.channelDataTransformer.toInternalRepresentation(
					this.channelRepository.save(
							this.channelDataTransformer.toDatabaseRepresentation(channel, username)));
		}
		catch (DataAccessException exception) {
			throw new ResourceRepositoryException(String.format("Failed to save channel '%s'.", channel), exception);
		}
	}
	@Transactional
	public void deleteChannel(String channelName, String username) {
		log.info("User {} attempting to delete channelName {}.", username, channelName);
		try {
			if (!this.channelRepository.existsChannelByChannelId(channelName)) {
				throw new ResourceNotFoundException(String.format("No channel with channel name '%s'.", channelName));
			}
			this.channelRepository.deleteChannelByChannelId(channelName);
		}
		catch (DataAccessException exception) {
			throw new ResourceRepositoryException(String.format("Failed to delete channel '%s'.", channelName), exception);
		}
	}
}
