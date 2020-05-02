package top.dianmu.ccompiler.learn.day87.backend;
import top.dianmu.ccompiler.learn.day87.frontend.*;

public class ICodeFactory {
    
    public static ICodeNode createICodeNode(CTokenType type) {
    	return new ICodeNodeImpl(type);
    }
}
