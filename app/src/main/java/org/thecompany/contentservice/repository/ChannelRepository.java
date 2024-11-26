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

package org.thecompany.contentservice.repository;

import org.thecompany.contentservice.model.data.Channel;

import org.springframework.data.repository.Repository;

public interface ChannelRepository extends Repository<Channel, String> {
	Channel findChannelByChannelId(String channelName);
	Channel save(Channel channel);
	void deleteChannelByChannelId(String channelName);
}
