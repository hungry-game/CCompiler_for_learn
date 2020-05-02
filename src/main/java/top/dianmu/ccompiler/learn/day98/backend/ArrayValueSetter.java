package top.dianmu.ccompiler.learn.day98.backend;
import top.dianmu.ccompiler.learn.day98.frontend.*;

public class ArrayValueSetter implements IValueSetter{
	private Symbol symbol;
	private int index = 0;
    @Override
    public void setValue(Object obj) {
    	Declarator declarator = symbol.getDeclarator(Declarator.ARRAY);
    	try {
			declarator.addElement(index, obj);
			
			System.out.println("Set Value of " + obj.toString() + " to Array of name " + symbol.getName() + " with index of " + index);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
    	
    }
    
    public ArrayValueSetter(Symbol symbol, int index) {
    	this.symbol = symbol;
    	this.index = index;
    }

	@Override
	public Symbol getSymbol() {
		// TODO Auto-generated method stub
		return symbol;
	}
}
