package com.epam.learn.java.ad.gallery.model;

/**
 * rejected for now
 * @author Administrator
 *
 */
public enum ExpositionState {

	NONE(0), NEW(1), ACTIVE(2), DISABLED(3);

	private int id;

	ExpositionState(int i) {
		this.id = i;
	}

	public int getId() {
		return id;
	}

	public boolean isEmpty() {
		return this.equals(ExpositionState.NONE);
	}

	public boolean compare(int i) {
		return id == i;
	}

	public static ExpositionState getValue(int _id) {
		ExpositionState[] As = ExpositionState.values();
		for (int i = 0; i < As.length; i++) {
			if (As[i].compare(_id))
				return As[i];
		}
		return ExpositionState.NONE;
	}

}
