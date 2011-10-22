package sMySQLappTemplate.Core;

public abstract class FeatureTemplate
{
	protected AppCoreTemplate appCore;
	
	// CONSTRUCTOR
	// Debe incluir las llamadas para registrar componentes
	// ie: creacion y adicion de botones, menues, etc.
	
	public FeatureTemplate(AppCoreTemplate AppCore)
	{
		this.appCore = AppCore;		
	}
}
