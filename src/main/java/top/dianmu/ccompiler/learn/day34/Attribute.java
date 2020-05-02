package top.dianmu.ccompiler.learn.day34;

public class Attribute {
    Object  left;
    Object  right;
    
    private String parseGrammar = "";
    
    private Attribute() {
    	
    }
    
    public static Attribute getAttribute(Object attrVal) {
    	Attribute obj = new Attribute();
    	obj.left = attrVal;
    	obj.right = attrVal;
    	
    	return obj;
    }
    
    public void setGrammar(String s) {
    	parseGrammar = s;
    }
    
    public String getGrammar() {
    	return parseGrammar;
    }
}
