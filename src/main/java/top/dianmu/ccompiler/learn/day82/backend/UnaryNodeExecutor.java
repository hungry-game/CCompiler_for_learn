package top.dianmu.ccompiler.learn.day82.backend;
import top.dianmu.ccompiler.learn.day82.frontend.*;


public class UnaryNodeExecutor extends BaseExecutor{
	
	@Override
    public Object Execute(ICodeNode root) {
		executeChildren(root);
		
    	int production = (Integer)root.getAttribute(ICodeKey.PRODUCTION); 
    	String text ;
    	Symbol symbol;
    	Object value;
    	ICodeNode child;
    	
    	switch (production) {
    	case CGrammarInitializer.Number_TO_Unary:
    		text = (String)root.getAttribute(ICodeKey.TEXT);
    		boolean isFloat = text.indexOf('.') != -1;
    		if (isFloat) {
    			value = Float.valueOf(text);
    			root.setAttribute(ICodeKey.VALUE, Float.valueOf(text));	
    		} else {
    			value = Integer.valueOf(text);
    			root.setAttribute(ICodeKey.VALUE, Integer.valueOf(text));
    		}
    		
    		break;
    				
    	case CGrammarInitializer.Name_TO_Unary:
    		symbol = (Symbol)root.getAttribute(ICodeKey.SYMBOL);
    		root.setAttribute(ICodeKey.VALUE, symbol.getValue());
    		root.setAttribute(ICodeKey.TEXT, symbol.getName());
    		break;
    		
    	case CGrammarInitializer.String_TO_Unary:
    		text = (String)root.getAttribute(ICodeKey.TEXT);
    		//symbol = (top.dianmu.ccompiler.day65.top.dianmu.ccompiler.day66.top.dianmu.ccompiler.day67.top.dianmu.ccompiler.day68.top.dianmu.ccompiler.day70.Symbol)root.getAttribute(ICodeKey.SYMBOL);
    		root.setAttribute(ICodeKey.VALUE, text);
    		//root.setAttribute(ICodeKey.TEXT, symbol.getName());
    		break;
    		
    	case CGrammarInitializer.Unary_LB_Expr_RB_TO_Unary:
    		child = root.getChildren().get(0);
    		symbol = (Symbol)child.getAttribute(ICodeKey.SYMBOL);
    		child = root.getChildren().get(1);
			int index = (Integer)child.getAttribute(ICodeKey.VALUE);
			
			Declarator declarator = symbol.getDeclarator(Declarator.ARRAY);
			try {
				Object val = declarator.getElement(index);
				root.setAttribute(ICodeKey.VALUE, val);
				ArrayValueSetter setter = new ArrayValueSetter(symbol, index);
				root.setAttribute(ICodeKey.SYMBOL, setter);
				root.setAttribute(ICodeKey.TEXT, symbol.getName());
			}catch (Exception e) {
				System.err.println(e.getMessage());
				System.exit(1);
			}
    		break;
    		
        case CGrammarInitializer.Unary_Incop_TO_Unary:
        	symbol = (Symbol)root.getChildren().get(0).getAttribute(ICodeKey.SYMBOL);
        	Integer val = (Integer)symbol.getValue();
        	IValueSetter setter;
    		setter = (IValueSetter)symbol;
    		try {
				setter.setValue(val + 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("Runtime Error: Assign Value Error");
			}
    		break;
    	}
    	
    	return root;
    }

	
}
