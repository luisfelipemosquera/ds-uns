package sMySQLappTemplate.Core;

public abstract class Command <returnType> 
{
	protected FeatureTemplate receiver;
	
	public Command(FeatureTemplate feature)
	{
		receiver = feature;
	}
	
	public abstract returnType ExecCommand(Object... args);
}
