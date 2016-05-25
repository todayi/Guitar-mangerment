package dao;

public class GuitarSpec {
	public Builder builder;
	public String model;
	public Type type;
	public Wood backWood;
	public Wood topWood;
	public int StringNum;

	public GuitarSpec(Builder builder, String model, Type type, int StringNum, Wood backWood, Wood topWood) {
		this.builder = builder;
		this.model = model;
		this.type = type;
		this.StringNum = StringNum;
		this.backWood = backWood;
		this.topWood = topWood;
	}

	public Builder getBuilder() {
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Wood getBackWood() {
		return backWood;
	}

	public void setBackWood(Wood backWood) {
		this.backWood = backWood;
	}

	public Wood getTopWood() {
		return topWood;
	}

	public void setTopWood(Wood topWood) {
		this.topWood = topWood;
	}

	public int getStringNum() {
		return StringNum;
	}

	public void setStringNum(int stringNum) {
		this.StringNum = stringNum;
	}

}
