/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.schauderhaft.ddd.jdbc;

import lombok.val;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jens Schauder
 */
public class LegoModel {

	@Id
	private Long id;
	private String name;
	private List<Manual> manuals = new ArrayList<>();

	public LegoModel(String name) {
		this.name = name;
	}

	/**
	 * maps the id of a brick to the number of pieces contained.
	 */
	private Set<BrickContentItem> content = new HashSet<>();

	public void add(Brick brick, Integer amount) {

		Assert.notNull(brick, "We can't add <null> bricks to a model");
		Assert.notNull(brick.Id, "Save the brick before adding it to a model so that it has a valid ID");

		val brickId = brick.Id;

		content.add(new BrickContentItem(brickId, amount));
	}

	public int totalBrickCount() {

		return content.stream().mapToInt(br -> br.amount).sum();
	}

	public int distinctBrickCount() {
		return content.size();
	}

	public void add(Manual manual) {
		manuals.add(manual);
	}
}
