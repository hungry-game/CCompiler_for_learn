package top.dianmu.ccompiler.learn.day84.backend;
import top.dianmu.ccompiler.learn.day84.frontend.*;

public class ICodeFactory {
    
    public static ICodeNode createICodeNode(CTokenType type) {
    	return new ICodeNodeImpl(type);
    }
}
