package top.dianmu.ccompiler.learn.day84.backend;
import top.dianmu.ccompiler.learn.day84.frontend.*;

import java.util.ArrayList;


public class ArgsExecutor extends BaseExecutor {

	@Override
	public Object Execute(ICodeNode root) {
		int production = (Integer)root.getAttribute(ICodeKey.PRODUCTION);
		ArrayList<Object> argList = new ArrayList<Object>();
		ICodeNode child ;
		switch (production) {
		case CGrammarInitializer.NoCommaExpr_TO_Args:
			child = (ICodeNode)executeChild(root, 0);
			int val = (Integer)child.getAttribute(ICodeKey.VALUE);
			argList.add(val);
			break;
			
		case CGrammarInitializer.NoCommaExpr_Comma_Args_TO_Args:
			child = executeChild(root, 0);
			val = (Integer)child.getAttribute(ICodeKey.VALUE);
			argList.add(val);
			
			child = (ICodeNode)executeChild(root, 1);
			ArrayList<Object> list = (ArrayList<Object>)child.getAttribute(ICodeKey.VALUE);
			argList.addAll(list);
			break;
		}
		
		root.setAttribute(ICodeKey.VALUE, argList);
		return root;
	}

}
