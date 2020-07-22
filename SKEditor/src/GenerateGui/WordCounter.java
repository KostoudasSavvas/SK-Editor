package GenerateGui;

import javax.swing.JLabel;
import javax.swing.JTextPane;

public class WordCounter {
	public void countWordsChars(JTextPane area,JLabel labelW,JLabel labelCh) {
		int countW=0;
		String contents = area.getText();
		String lines[] = contents.split("\\r?\\n+");
		String result[] = new String[5000];
		for (int i=0;i<lines.length;i++){
			String[] sa = lines[i].split(" ");
			for (int j=0;j<sa.length;j++) {
				result[countW] = sa[j];
				countW++;
			}
		}

		if (contents.isEmpty()) {
			labelW.setText("Words"+" "+"0");
		}else {
			labelW.setText("Words"+" "+countW);
		}
		labelCh.setText("Characters"+" "+contents.length());
	}
}