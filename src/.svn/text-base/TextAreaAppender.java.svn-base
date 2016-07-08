package roguelike;

import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;
import javax.swing.JTextArea;

public class TextAreaAppender extends AppenderSkeleton {
	private JTextArea tarea;
	
	public TextAreaAppender(JTextArea t) {
		tarea = t;
	}
	
	protected void append(LoggingEvent levent) {
		if(tarea != null) {
			if(tarea.getLineCount() > 9) {
				String text = tarea.getText();
				//get rid of the first three lines
				int idx = text.indexOf("\n", text.indexOf("\n", text.indexOf("\n", text.indexOf("\n", text.indexOf("n")+1)+1)+1)+1);
				tarea.setText(text.substring(idx));
			}
			tarea.append(levent.getRenderedMessage() + "\n");
			//tarea.setRows(30);
		}
	}
	
	public boolean requiresLayout() { return false; }
	
	public void close() {
		tarea = null;
	}
}