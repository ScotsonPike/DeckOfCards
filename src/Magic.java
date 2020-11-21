public enum Magic {

	startAgain("Start again", false), seeThrough("See-through", false), missAGo("Miss a go", true),
	playBelow("Play below", true), burn("Burn", false);

	private String rule;
	private boolean sequential;

	Magic(String rule, boolean sequential) {
		this.rule = rule;
		this.sequential = sequential;
	}

	public String getRule() {
		return rule;
	}

	public boolean cardIsSequential() {
		return sequential;
	}
}
