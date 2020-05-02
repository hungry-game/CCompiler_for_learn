package top.dianmu.ccompiler.learn.day96.backend;


import top.dianmu.ccompiler.learn.day96.frontend.CTokenType;

public class ICodeFactory {
    
    public static ICodeNode createICodeNode(CTokenType type) {
    	return new ICodeNodeImpl(type);
    }
}
