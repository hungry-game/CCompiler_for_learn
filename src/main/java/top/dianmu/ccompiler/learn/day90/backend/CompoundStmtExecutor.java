package top.dianmu.ccompiler.learn.day90.backend;

public class CompoundStmtExecutor extends BaseExecutor {

	@Override
	public Object Execute(ICodeNode root) {
		
		return executeChild(root, 0);
	}

}
