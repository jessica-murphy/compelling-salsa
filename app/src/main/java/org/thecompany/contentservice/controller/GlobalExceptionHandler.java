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

import lombok.extern.slf4j.Slf4j;
import org.thecompany.contentservice.service.ResourceAlreadyExistsException;
import org.thecompany.contentservice.service.ResourceNotFoundException;
import org.thecompany.contentservice.service.ResourceRepositoryException;
import org.thecompany.contentservice.transformer.client.ClientTransformerException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public HttpEntity<String> handle(ResourceNotFoundException resourceNotFoundException) {
		log.warn(resourceNotFoundException.getMessage(), resourceNotFoundException);
		return new HttpEntity<>(resourceNotFoundException.getMessage());
	}

	@ExceptionHandler(ResourceRepositoryException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public HttpEntity<String> handle(ResourceRepositoryException resourceRepositoryException) {
		log.error(resourceRepositoryException.getMessage(), resourceRepositoryException);
		return new HttpEntity<>(resourceRepositoryException.getMessage());
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public HttpEntity<String> handle(ResourceAlreadyExistsException resourceAlreadyExistsException) {
		log.warn(resourceAlreadyExistsException.getMessage(), resourceAlreadyExistsException);
		return new HttpEntity<>(resourceAlreadyExistsException.getMessage());
	}
	@ExceptionHandler(ClientTransformerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public HttpEntity<String> handle(ClientTransformerException clientTransformerException) {
		log.error(clientTransformerException.getMessage(), clientTransformerException);
		return new HttpEntity<>(clientTransformerException.getMessage());
	}
}
