package org.reldb.spoing.commands;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.reldb.spoing.platform.IconLoader;

/*
 * A combination of a ToolItem for display on a ToolBar, and an optional reference to a MenuItem.
 * 
 * When the ToolItem is active, the associated MenuItem will be enabled and can launch the ToolItem.
 * 
 * When the ToolItem is inactive or disposed, the associated MenuItem will be inactive.
 * 
 */
public class CommandActivator extends ToolItem {
	private Integer command;
    private ToolBar toolbar;
	private MenuItem menuItem;
	private String iconName;
	private Listener listener;
    private boolean visible = false;

    private Timer stateTimer = new Timer();
    
    protected void notifyVisible() {
    	Commands.addCommandActivator(this);
    }
    
    protected void notifyHidden() {
    	Commands.removeCommandActivator(this);
    }
    
    private void visible() {
    	if (visible)
    		return;
    	visible = true;
    	notifyVisible();
    }
    
    private void hidden() {
    	if (!visible)
    		return;
    	visible = false;
    	notifyHidden();
    }

	public CommandActivator(Integer command, ToolBar toolBar, String iconName, int style, String tooltipText, Listener listener) {
		super(toolBar, style);
		this.command = command;
		this.toolbar = toolBar;
		this.iconName = iconName;
		this.listener = listener;
		setToolTipText(tooltipText);
		if (iconName != null)
			setImage(IconLoader.loadIcon(iconName));
		if (listener != null)
			addListener(SWT.Selection, listener);
		if (command != null) {
			menuItem = Commands.getMenuItem(command, this);
			addListener(SWT.Paint, e -> visible());
			stateTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					if (isDisposed() || getDisplay().isDisposed()) {
						stateTimer.cancel();
						hidden();
					} else
						getDisplay().asyncExec(() -> {
							if (isDisposed() || getParent().isDisposed()) {
								stateTimer.cancel();
								hidden();
							} else if (getParent().isVisible()) {
								visible();
							} else {
								hidden();
							}
						});
				}
			}, 250, 250);
		}
	}

	public void dispose() {
		if (command != null) {
			stateTimer.cancel();
			hidden();
		}
	}
	
	public void setEnabled(boolean enabled) {
		if (isDisposed() || super.isDisposed())
			return;
		super.setEnabled(enabled);
		if (menuItem != null && !menuItem.isDisposed())
			menuItem.setEnabled(true);
	}

	public void click() {
		var event = new Event();
		if (listener != null)
			listener.handleEvent(event);
		if (((getStyle() & SWT.CHECK) != 0) || ((getStyle() & SWT.RADIO) != 0))
			setSelection(!getSelection());
	}
	
	public boolean isVisible() {
		return visible;
	}

	public boolean isFullyEnabled() {
		return !isDisposed() && isVisible() && isEnabled();
	}

	public String getIconName() {
		return iconName;
	}
	
	public Integer getCommand() {
		return command;
	}

	public ToolBar getToolBar() {
		return toolbar;
	}
	
	public String toString() {
		return "CommandActivator [" + command + ", " + iconName + "]" + (isDisposed() ? " *disposed*" : "");
	}
	
	public void checkSubclass() {}
}
