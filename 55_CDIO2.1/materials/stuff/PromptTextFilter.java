package simulator;

import javax.swing.text.*;

public class PromptTextFilter extends DocumentFilter {
	private static final String PROMPT = "> "; // LINE-START-DESIGN
	
	@Override
	public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
		if(string == null) {
			return;
		}
		else {
			replace(fb, offset, 0, string, attr);
		}
	}
	
	@Override
	public void remove(DocumentFilter.FilterBypass fb, int offset,int length) throws BadLocationException {
		replace(fb, offset, length, "", null);
	}
	
	@Override
	public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		Document doc = fb.getDocument();
		Element root = doc.getDefaultRootElement();
		
		int count = root.getElementCount();
		int index = root.getElementIndex(offset);
		Element cur = root.getElement(index);
		
		int promptPosition = cur.getStartOffset() + PROMPT.length();
		
		if(index == count - 1 && offset - promptPosition >= 0) {
			if(text.equals("\n")) {
				String cmd = doc.getText(promptPosition, offset - promptPosition);
				
				if(cmd.isEmpty()) {
					text = "\n" + PROMPT;
				}
				else {
					text = "\n" + PROMPT;
				}
			}
			
			fb.replace(offset, length, text, attrs);
		}
	}
}
