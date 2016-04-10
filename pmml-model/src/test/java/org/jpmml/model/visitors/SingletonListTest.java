/*
 * Copyright (c) 2016 Villu Ruusmann
 */
package org.jpmml.model.visitors;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SingletonListTest extends AbstractImmutableListTest {

	@Test
	public void readContract(){
		List<String> list = new SingletonList<>("value");

		assertTrue(list instanceof Serializable);

		assertEquals(1, list.size());
		assertFalse(list.isEmpty());

		assertEquals("value", list.get(0));

		try {
			list.get(1);

			fail();
		} catch(IndexOutOfBoundsException ioobe){
			// Ignored
		}

		assertEquals(0, list.indexOf("value"));
		assertEquals(-1, list.indexOf(null));

		assertEquals(0, list.lastIndexOf("value"));
		assertEquals(-1, list.lastIndexOf(null));

		assertEquals(Arrays.asList("value"), list.subList(0, 1));
		assertEquals(Collections.emptyList(), list.subList(1, 1));

		assertEquals(Arrays.asList("value"), toList(list.iterator()));

		assertEquals(Arrays.asList("value"), toList(list.listIterator(0)));
		assertEquals(Collections.emptyList(), toList(list.listIterator(1)));
	}
}