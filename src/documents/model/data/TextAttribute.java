package documents.model.data;

public class TextAttribute  extends Attribute {
	
	private String text;
	private String default_value;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDefault_value() {
		return default_value;
	}
	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}


}
