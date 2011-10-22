package sMySQLappTemplate.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import sMySQLappTemplate.Exceptions.DuplicatedActionName;

public class ExtensionPoint 
{
	private HashMap<String, Action> actions;	
	private HashMap<String, Object> returns;
	private ArrayList<String> actionOrder;
	
	public ExtensionPoint()
	{
		actions = new HashMap<String, Action>();
		returns = new HashMap<String, Object>();		
		actionOrder = new ArrayList<String>();
	}
	
	public void addAction (Command<Object> action, String actionName)
	throws DuplicatedActionName
	{
		
		if (actions.get(actionName) == null)
		{
			actions.put(actionName, new Action(action, actionName));
			actionOrder.add(actionName);
		}
		else throw new DuplicatedActionName();
	}
	
	public void removeAction(String actionName)
	{
		Action a;
		if ((a = actions.get(actionName)) != null) 
		{
			actions.remove(a);
			actionOrder.remove(actionName);
		}
	}
	
	public Object execExtensionPoint()
	{
		Object result = null;
		for(String act: actionOrder)
		{
			result = actions.get(act).getAction().ExecCommand(this);
			returns.put(act, result);
		}
		return result;
	}
	
	public Object getResult(String actionName)
	{
		return returns.get(actionName);		
	}
	
	private class Action
	{
		private Command<Object> action;
		private String actionName;
		
		public Action (Command<Object> action, String actionName)
		{
			this.action = action;
			this.actionName = actionName;
		}

		public Command<Object> getAction() {
			return action;
		}

		public String getActionName() {
			return actionName;
		}
	}

}
