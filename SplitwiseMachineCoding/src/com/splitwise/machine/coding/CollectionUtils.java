package com.splitwise.machine.coding;

import java.util.Collection;

public class CollectionUtils<E> {

	public E get(Collection<E> collection, E object) {
		for (E e : collection) {
			if (e.hashCode() == object.hashCode() && e.equals(object)) {
				return e;
			}
		}
		return null;
	}
}
