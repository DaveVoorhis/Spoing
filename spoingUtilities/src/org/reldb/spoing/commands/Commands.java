package org.reldb.spoing.commands;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class Commands {

	private static Map<Integer, MenuItem> menuDoMapping = new HashMap<>();
	private static Map<Integer, HashSet<ToolBar>> commandDoMapping = new HashMap<>();
	
	public static void addCommandActivator(CommandActivator activator) {
		synchronized (commandDoMapping) {
			HashSet<ToolBar> toolbars = commandDoMapping.get(activator.getCommand());
			if (toolbars == null)
				toolbars = new HashSet<>();
			toolbars.add(activator.getToolBar());
			commandDoMapping.put(activator.getCommand(), toolbars);
		}
	}
	
	public static void removeCommandActivator(CommandActivator activator) {
		synchronized (commandDoMapping) {
			HashSet<ToolBar> toolbars = commandDoMapping.get(activator.getCommand());
			if (toolbars == null)
				return;
			toolbars.remove(activator.getToolBar());
		}
	}
	
	public static CommandActivator getCommandActivator(Integer command) {
		synchronized (commandDoMapping) {
			HashSet<ToolBar> toolbars = commandDoMapping.get(command);
			if (toolbars == null)
				return null;
			for (ToolBar toolbar: toolbars) {
				if (!toolbar.isDisposed() && toolbar.isVisible())
					for (ToolItem toolItem: toolbar.getItems())
						if (toolItem instanceof CommandActivator && !toolItem.isDisposed())
							if (((CommandActivator) toolItem).getCommand() == command)
								return (CommandActivator)toolItem;
			}
			return null;
		}
	}
	
	public static void linkCommand(Integer command, AcceleratedMenuItem menuItem) {
		menuDoMapping.put(command, menuItem);
		menuItem.getParent().addMenuListener(new MenuAdapter() {
			@Override
			public void menuShown(MenuEvent arg0) {
				CommandActivator activator = getCommandActivator(command);
				if (activator != null && activator.isFullyEnabled()) {
					menuItem.setEnabled(true);
					// remove old listeners
					Listener[] oldListeners = menuItem.getListeners(SWT.Selection);
					for (Listener oldListener: oldListeners)
						menuItem.removeListener(SWT.Selection, oldListener);
					// handle CHECK and RADIO menu items
					if ((menuItem.getStyle() & (SWT.CHECK | SWT.RADIO)) != 0) {
						menuItem.setSelection(activator.getSelection());
						menuItem.addListener(SWT.Selection, e -> activator.setSelection(!activator.getSelection()));
					}
					// add new listener
					menuItem.addListener(SWT.Selection, e -> activator.click());
					// acquire the CommandActivator's tooltip
					menuItem.setToolTipText(activator.getToolTipText());
				} else
					menuItem.setEnabled(false);
			}
		});
		menuItem.setEnabled(false);
	}

	public static MenuItem getMenuItem(Integer command, CommandActivator toolitem) {
		return menuDoMapping.get(command);
	}

}
