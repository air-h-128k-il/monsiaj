/*      PANDA -- a simple transaction monitor

Copyright (C) 1998-1999 Ogochan.
              2000-2003 Ogochan & JMA (Japan Medical Association).

This module is part of PANDA.

		PANDA is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY.  No author or distributor accepts responsibility
to anyone for the consequences of using it or for whether it serves
any particular purpose or works at all, unless he says so in writing.
Refer to the GNU General Public License for full details.

		Everyone is granted permission to copy, modify and redistribute
PANDA, but only under the conditions described in the GNU General
Public License.  A copy of this license is supposed to have been given
to you along with PANDA so you can know your rights and
responsibilities.  It should be in a file named COPYING.  Among other
things, the copyright notice and this notice must be preserved on all
copies.
*/

package org.montsuqi.widgets;

import java.io.ByteArrayOutputStream;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;

public class TextPaneOutputStream extends ByteArrayOutputStream {

	private final JTextPane target;
	private final MutableAttributeSet attributeSet;

	public TextPaneOutputStream(JTextPane target) {
		super();
		this.target = target;
		attributeSet = new SimpleAttributeSet(target.getCharacterAttributes());
	}

	public TextPaneOutputStream(JTextPane target, int size) {
		super(size);
		this.target = target;
		attributeSet = new SimpleAttributeSet(target.getCharacterAttributes());
	}

	public MutableAttributeSet getAttributeSet() {
		return attributeSet;
	}

	public synchronized void write(byte[] b, int off, int len)  {
		String s = new String(b, off, len);
		Document doc = target.getDocument();
		try {
			doc.insertString(doc.getLength(), s, attributeSet);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
	}
}