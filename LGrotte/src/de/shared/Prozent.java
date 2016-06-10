package de.shared;

public class Prozent {
	private int aehnlichkeit;

	public Prozent(int aehnlichkeit) {
		if (aehnlichkeit < 0 || aehnlichkeit > 100) {
			throw new IllegalArgumentException();
		} else {
			this.aehnlichkeit = aehnlichkeit;
		}
	}

	public int getHealthAehnlichkeit() {
		return aehnlichkeit;
	}

	public void setHealthAehnlichkeit(int aehnlichkeit) {
		if (aehnlichkeit < 0 || aehnlichkeit > 100) {
			throw new IllegalArgumentException();
		} else {
			this.aehnlichkeit = aehnlichkeit;
		}
	}

}
