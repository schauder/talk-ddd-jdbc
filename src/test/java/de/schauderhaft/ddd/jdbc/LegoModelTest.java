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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.*;

/**
 * @author Jens Schauder
 */
@RunWith(SpringRunner.class)
@DataJdbcTest
public class LegoModelTest {

	@Autowired
	LegoModels models;

	@Autowired
	Bricks bricks;


	@Test
	public void modelReferencingManual() {

		LegoModel womenOfNasa = new LegoModel("Women of NASA");
		womenOfNasa.add(new Manual("English", "blah, blah, ... assembly ... blah"));

		models.save(womenOfNasa);
	}



	@Test
	public void modelReferencingBricks() {

		Brick thin_2x2 = new Brick("2x2 - thin");
		Brick thin_2x4 = new Brick("2x2 - normal");
		Brick normal_2x2 = new Brick("2x4 - normal");

		bricks.saveAll(asList(thin_2x2, thin_2x4, normal_2x2));

		LegoModel womenOfNasa = new LegoModel("Women of NASA");
		womenOfNasa.add(thin_2x2, 6);
		womenOfNasa.add(thin_2x4, 3);
		womenOfNasa.add(normal_2x2, 4);

		models.save(womenOfNasa);
	}

	@Test
	public void modelManualAndBricks() {

		Brick thin_2x2 = new Brick("2x2 - thin");
		Brick thin_2x4 = new Brick("2x2 - normal");
		Brick normal_2x2 = new Brick("2x4 - normal");

		bricks.saveAll(asList(thin_2x2, thin_2x4, normal_2x2));

		LegoModel womenOfNasa = new LegoModel("Women of NASA");
		womenOfNasa.add(new Manual("English", "blah, blah, ... assembly ... blah"));

		womenOfNasa.add(thin_2x2, 6);
		womenOfNasa.add(thin_2x4, 3);
		womenOfNasa.add(normal_2x2, 4);

		models.save(womenOfNasa);
	}
}
