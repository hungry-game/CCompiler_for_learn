package top.dianmu.ccompiler.learn.day66;


import java.util.ArrayList;
import java.util.HashMap;


public class CGrammarInitializer {
	public static  final int TYPE_TO_TYPE_SPECIFIER = 11;
	public static  final int SPECIFIERS_TypeOrClass_TO_SPECIFIERS = 9;
	public static  final int NAME_TO_NewName = 12;
	public static  final int START_VarDecl_TO_VarDecl = 14;
	public static  final int ExtDeclList_COMMA_ExtDecl_TO_ExtDeclList = 5;
	public static  final int OptSpecifier_ExtDeclList_Semi_TO_ExtDef = 2;
	public static  final int TypeNT_VarDecl_TO_ParamDeclaration = 20;
	public static  final int VarList_COMMA_ParamDeclaration_TO_VarList = 19;

	public static  final int NewName_LP_VarList_RP_TO_FunctDecl = 16;
	public static  final int NewName_LP_RP_TO_FunctDecl = 17;
	
	private int productionNum = 0;
	
	private static CGrammarInitializer instance = null;
	private HashMap<Integer, ArrayList<Production>> productionMap = new HashMap<Integer, ArrayList<Production>>();
	private HashMap<Integer, Symbols> symbolMap = new HashMap<Integer, Symbols>();
    private ArrayList<Symbols> symbolArray = new ArrayList<Symbols>();
	public  static CGrammarInitializer getInstance() {
		if (instance == null) {
			instance = new CGrammarInitializer();
		}
		
		return instance;
		
	}
	private CGrammarInitializer() {
		initVariableDecalationProductions();
		initFunctionProductions();
		addTerminalToSymbolMapAndArray();
	}
	
	public HashMap<Integer, ArrayList<Production>> getProductionMap() {
		return productionMap;
	}
	
	public HashMap<Integer, Symbols> getSymbolMap() {
		return symbolMap;
	}
	
	public ArrayList<Symbols> getSymbolArray() {
		return symbolArray;
	}
	
	
	private void initVariableDecalationProductions() {
		
		productionMap.clear();
		
		
		/*LB: { RB:}
    	 * 
    	 * C variable declaration grammar
    	 *  PROGRAM -> EXT_DEF_LIST
    	 *  
    	 *  EXT_DEF_LIST -> EXT_DEF_LIST EXT_DEF
    	 *  
    	 *  EXT_DEF -> OPT_SPECIFIERS EXT_DECL_LIST  SEMI 
    	 *             | OPT_SPECIFIERS SEMI
    	 *             
    	 *             
    	 *  EXT_DECL_LIST ->   EXT_DECL  
    	 *                   | EXT_DECL_LIST COMMA EXT_DECL
    	 *                   
    	 *  EXT_DECL -> VAR_DECL
    	 *  
    	 *  OPT_SPECIFIERS -> CLASS TTYPE
    	 *                   | TTYPE
    	 *                   | SPECIFIERS
    	 *                   | EMPTY?
    	 *                   
    	 *  SPECIFIERS -> TYPE_OR_CLASS
    	 *                | SPECIFIERS TYPE_OR_CLASS
    	 * 
    	 *           
    	 *  TYPE_OR_CLASS -> TYPE_SPECIFIER 
    	 *                   | CLASS
    	 *  
    	 *  TYPE_SPECIFIER ->  TYPE
    	 *                   
    	 *  NEW_NAME -> NAME
    	 *  
    	 *  NAME_NT -> NAME
    	 *  
    	 *  VAR_DECL -> | NEW_NAME
    	 *             
    	 *              | START VAR_DECL
    	 *              
    	 */
    	
		
    	//PROGRAM -> EXT_DEF_LIST
    	ArrayList<Integer> right = null;
    	right = getProductionRight( new int[]{CTokenType.EXT_DEF_LIST.ordinal() });
    	Production production = new Production(productionNum,CTokenType.PROGRAM.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, true);
    
    	
    	//EXT_DEF_LIST -> EXT_DEF_LIST EXT_DEF 
    	right = getProductionRight(new int[]{CTokenType.EXT_DEF_LIST.ordinal(), CTokenType.EXT_DEF.ordinal()});
    	production = new Production(productionNum, CTokenType.EXT_DEF_LIST.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, true);
    	
    	
    		
    	//EXT_DEF -> OPT_SPECIFIERS EXT_DECL_LIST  SEMI
    	right = getProductionRight(new int[]{CTokenType.OPT_SPECIFIERS.ordinal(), CTokenType.EXT_DECL_LIST.ordinal(), CTokenType.SEMI.ordinal()});
    	production = new Production(productionNum, CTokenType.EXT_DEF.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, false);
    	
    	
    	
    	
    	//EXT_DEF -> OPT_SPECIFIERS  SEMI
    	right = getProductionRight(new int[]{CTokenType.OPT_SPECIFIERS.ordinal(),  CTokenType.SEMI.ordinal()});
    	production = new Production(productionNum, CTokenType.EXT_DEF.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, false);
    	
    	
    
    	//EXT_DECL_LIST ->   EXT_DECL
    	right = getProductionRight(new int[]{CTokenType.EXT_DECL.ordinal()});
    	production = new Production(productionNum, CTokenType.EXT_DECL_LIST.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, false);
    	
    	///EXT_DECL_LIST ->EXT_DECL_LIST COMMA EXT_DECL
    	right = getProductionRight(new int[]{CTokenType.EXT_DECL_LIST.ordinal(), CTokenType.COMMA.ordinal(), CTokenType.EXT_DECL.ordinal()});
    	production = new Production(productionNum, CTokenType.EXT_DECL_LIST.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, false);
    	
    	//EXT_DECL -> VAR_DECL
    	right = getProductionRight(new int[]{CTokenType.VAR_DECL.ordinal()});
    	production = new Production(productionNum,CTokenType.EXT_DECL.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, false);
    	
    	//OPT_SPECIFIERS -> SPECIFIERS
    	right = getProductionRight(new int[]{CTokenType.SPECIFIERS.ordinal()});
    	production = new Production(productionNum,CTokenType.OPT_SPECIFIERS.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, true);
    	
    	//SPECIFIERS -> TYPE_OR_CLASS
    	right = getProductionRight(new int[]{CTokenType.TYPE_OR_CLASS.ordinal()});
    	production = new Production(productionNum,CTokenType.SPECIFIERS.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, false);
    	
    	//SPECIFIERS -> SPECIFIERS TYPE_OR_CLASS
    	right = getProductionRight(new int[]{CTokenType.SPECIFIERS.ordinal(), CTokenType.TYPE_OR_CLASS.ordinal()});
    	production = new Production(productionNum,CTokenType.SPECIFIERS.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, false);
    	
    	
    
    	//TYPE_OR_CLASS -> TYPE_SPECIFIER 
    	right = getProductionRight(new int[]{CTokenType.TYPE_SPECIFIER.ordinal()});
    	production = new Production(productionNum,CTokenType.TYPE_OR_CLASS.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, false);
    	
    	
    	
    	//TYPE_SPECIFIER ->  TYPE
    	right = getProductionRight(new int[]{CTokenType.TYPE.ordinal()});
    	production = new Production(productionNum,CTokenType.TYPE_SPECIFIER.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, false);
    	
    	//NEW_NAME -> NAME
    	right = getProductionRight(new int[]{CTokenType.NAME.ordinal()});
    	production = new Production(productionNum,CTokenType.NEW_NAME.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, false);
    	
   
    	//VAR_DECL ->  NEW_NAME
    	right = getProductionRight(new int[]{CTokenType.NEW_NAME.ordinal()});
    	production = new Production(productionNum,CTokenType.VAR_DECL.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, false);
    	
    	
    	///VAR_DECL ->START VAR_DECL
    	right = getProductionRight(new int[]{CTokenType.STAR.ordinal(), CTokenType.VAR_DECL.ordinal()});
    	production = new Production(productionNum,CTokenType.VAR_DECL.ordinal(), 0, right);
    	productionNum++;
    	addProduction(production, false);
    	
		
	}
	
	private void initFunctionProductions() {
		/*production num begin with 15
		 * 
		 * EXT_DEF -> OPT_SPECIFIERS FUNCT_DECL SEMI
		 * 
		 * FUNCT_DECL -> NEW_NAME LP VAR_LIST RP
		 *              | NEW_NAME LP RP
		 * VAR_LIST ->  PARAM_DECLARATION
		 *              | VAR_LIST COMMA PARAM_DECLARATION
		 * PARAM_DECLARATION -> TYPE_NT VAR_DECL
		 * 
		 * TYPE_NT -> TYPE_SPECIFIER
		 *            | TYPE TYPE_SPECIFIER
		 */
		
		//EXT_DEF -> OPT_SPECIFIERS FUNCT_DECL
		ArrayList<Integer> right = null;
		right = getProductionRight( new int[]{CTokenType.OPT_SPECIFIERS.ordinal(), CTokenType.FUNCT_DECL.ordinal(),
				CTokenType.SEMI.ordinal()});
		Production production = new Production(productionNum,CTokenType.EXT_DEF.ordinal(),0, right);
		productionNum++;
		addProduction(production, false);
		
		//FUNCT_DECL -> NEW_NAME LP VAR_LIST RP
		right = null;
		right = getProductionRight( new int[]{CTokenType.NEW_NAME.ordinal(), CTokenType.LP.ordinal(),
				CTokenType.VAR_LIST.ordinal(), CTokenType.RP.ordinal() });
		production = new Production(productionNum,CTokenType.FUNCT_DECL.ordinal(), 0, right);
		productionNum++;
		addProduction(production, false);
		
		//FUNCT_DECL ->  NEW_NAME LP RP
		right = null;
		right = getProductionRight( new int[]{CTokenType.NEW_NAME.ordinal(),CTokenType.LP.ordinal(), CTokenType.RP.ordinal()});
		production = new Production(productionNum,CTokenType.FUNCT_DECL.ordinal(), 0, right);
		productionNum++;
		addProduction(production, false);
		
	
		//VAR_LIST ->  PARAM_DECLARATION
		right = null;
		right = getProductionRight( new int[]{CTokenType.PARAM_DECLARATION.ordinal()});
		production = new Production(productionNum,CTokenType.VAR_LIST.ordinal(), 0, right);
		productionNum++;
		addProduction(production, false);
		
		//VAR_LIST -> VAR_LIST COMMA PARAM_DECLARATION
		right = null;
		right = getProductionRight( new int[]{CTokenType.VAR_LIST.ordinal(), CTokenType.COMMA.ordinal(),
				CTokenType.PARAM_DECLARATION.ordinal() });
		production = new Production(productionNum,CTokenType.VAR_LIST.ordinal(), 0, right);
		productionNum++;
		addProduction(production, false);
		
		//PARAM_DECLARATION -> TYPE_NT VAR_DECL
		right = null;
		right = getProductionRight( new int[]{CTokenType.TYPE_NT.ordinal(), CTokenType.VAR_DECL.ordinal() });
		production = new Production(productionNum,CTokenType.PARAM_DECLARATION.ordinal(), 0, right);
		productionNum++;
		addProduction(production, false);
		
		//TYPE_NT -> TYPE_SPECIFIER
		right = null;
		right = getProductionRight( new int[]{CTokenType.TYPE_SPECIFIER.ordinal() });
		production = new Production(productionNum,CTokenType.TYPE_NT.ordinal(), 0, right);
		productionNum++;
		addProduction(production, false);
		
		//TYPE_NT -> TYPE TYPE_SPECIFIER
		right = null;
		right = getProductionRight( new int[]{CTokenType.TYPE.ordinal(), CTokenType.TYPE_SPECIFIER.ordinal() });
		production = new Production(productionNum,CTokenType.TYPE_NT.ordinal(), 0, right);
		productionNum++;
		addProduction(production, false);
		
	}
	
	private void addProduction(Production production,boolean nullable) {
	    	
	    	ArrayList<Production> productionList = productionMap.get(production.getLeft());
			
			if (productionList == null) {
				productionList = new ArrayList<Production>();
				productionMap.put(production.getLeft(), productionList);
			}
			
			if (productionList.contains(production) == false) {
				productionList.add(production);
			}
			
			addSymbolMapAndArray(production, nullable);
		
	    }
	 
	 private void addSymbolMapAndArray(Production production, boolean nullable) {
		//add symbol array and symbol map
			int[] right = new int[production.getRight().size()];
			for (int i = 0; i < right.length; i++) {
				right[i] = production.getRight().get(i);
			}
			
			if (symbolMap.containsKey(production.getLeft())) {
				symbolMap.get(production.getLeft()).addProduction(right);
			} else {
				ArrayList<int[]> productions = new ArrayList<int[]>();
				productions.add(right);
				Symbols symObj = new Symbols(production.getLeft(), nullable, productions);
				symbolMap.put(production.getLeft(), symObj);
				symbolArray.add(symObj);
			}
	 }
	 
	 private void addTerminalToSymbolMapAndArray() {
		 for (int i = CTokenType.FIRST_TERMINAL_INDEX; i <= CTokenType.LAST_TERMINAL_INDEX; i++) {
			 Symbols symObj = new Symbols(i, false, null);
			 symbolMap.put(i, symObj);
			 symbolArray.add(symObj);
		 }
	 }
	 
	 private ArrayList<Integer> getProductionRight(int[] arr) {
	    	ArrayList<Integer> right = new ArrayList<Integer>();
	    	for (int i = 0; i < arr.length; i++) {
	    		right.add(arr[i]);
	    	}
	    	
	    	return right;
	    }
	 
	
}
