public enum LevelEnum {
	LEVEL1(1), LEVEL2(2), LEVEL3(3);

	private int level;

	LevelEnum(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

};