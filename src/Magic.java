public enum Magic {
	
	startAgain ("Start again"),
	seeThrough ("See through"),
	missAGo ("Miss a go"),
	playBelow ("Play below"),
	burn ("Burn");
	
	private String rule;
	
	Magic(String rule){
		this.rule = rule;
	}
	
	public String getRule() {
		return rule;
	}
}
