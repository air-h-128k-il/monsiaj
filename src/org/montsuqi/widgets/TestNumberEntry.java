package org.montsuqi.widgets;

import org.montsuqi.widgets.Window;
import org.montsuqi.widgets.NumberEntry;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import junit.extensions.jfcunit.finder.FrameFinder;

import junit.textui.TestRunner;

public class TestNumberEntry extends JFCTestCase {
	private transient TestHelper m_helper;
	private transient Window m_window;
	private transient NumberEntry m_numberEntry;

	public TestNumberEntry(final String name) {
		super(name);
	}

	public void createNumberEntry() {
		final Window window = new Window();
		window.setTitle("NumberEntry Test"); //$NON-NLS-1$
		window.setBounds(100 , 100 , 200 , 50);
		NumberEntry numberEntry = new NumberEntry();
		numberEntry.setName("NumberEntery1"); //$NON-NLS-1$
		window.getContentPane().add(numberEntry);
		window.setVisible(true);
	}

	public void setUp() throws Exception{
		m_helper = new JFCTestHelper();
		createNumberEntry();
		m_window = (Window) TestHelper.getWindow(new FrameFinder("NumberEntry Test")); //$NON-NLS-1$
		NamedComponentFinder f = new NamedComponentFinder(
			NumberEntry.class, "NumberEntery1"); //$NON-NLS-1$

		m_numberEntry = (NumberEntry) f.find(m_window, 0);
	}

	public void tearDown() throws Exception{
		m_window.setVisible(false);
	}


	public void test9format() throws Exception {
		String text;
		m_numberEntry.setFormat("99.9"); //$NON-NLS-1$
		m_helper.sendString(new StringEventData(this,
							m_numberEntry, "0")); //$NON-NLS-1$
		text = m_numberEntry.getText();
		assertEquals("99.9 format ", "00.0", text); //$NON-NLS-1$//$NON-NLS-2$
		m_helper.sendString(new StringEventData(this,
							m_numberEntry, "1.0")); //$NON-NLS-1$
		text = m_numberEntry.getText();
		assertEquals("99.9 format ", "01.0", text); //$NON-NLS-1$//$NON-NLS-2$
	}

	public void testZformat() throws Exception {
		String text;
		m_numberEntry.setFormat("ZZ.Z"); //$NON-NLS-1$

		m_helper.sendString(new StringEventData(this,
							m_numberEntry, "AA")); //$NON-NLS-1$
		text = m_numberEntry.getText();
		assertEquals("ZZ.Z format ", "", text); //$NON-NLS-1$//$NON-NLS-2$

		m_helper.sendString(new StringEventData(this,
							m_numberEntry, "0")); //$NON-NLS-1$
		text = m_numberEntry.getText();
		assertEquals("ZZ.Z format ", "", text); //$NON-NLS-1$//$NON-NLS-2$

		m_helper.sendString(new StringEventData(this,
							m_numberEntry, "1.0")); //$NON-NLS-1$
		text = m_numberEntry.getText();
		assertEquals("ZZ.Z format ", "1", text); //$NON-NLS-1$//$NON-NLS-2$

	}

	public void testZZZ9format() throws Exception {
		String text;
		m_numberEntry.setFormat("ZZZ9"); //$NON-NLS-1$
		m_helper.sendString(new StringEventData(this,
							m_numberEntry, "0")); //$NON-NLS-1$
		text = m_numberEntry.getText();
		assertEquals("99.9 format ", "0", text); //$NON-NLS-1$ //$NON-NLS-2$
		m_helper.sendString(new StringEventData(this,
							m_numberEntry, "101")); //$NON-NLS-1$
		text = m_numberEntry.getText();
		assertEquals("ZZZ9 format ", "101", text); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void testHyphenformat() throws Exception {
		String text;
		m_numberEntry.setFormat("---,---,---"); //$NON-NLS-1$
		m_helper.sendString(new StringEventData(this,
							m_numberEntry, "0")); //$NON-NLS-1$
		text = m_numberEntry.getText();
		assertEquals("Hypthen and comma format ", "", text); //$NON-NLS-1$ //$NON-NLS-2$
		m_helper.sendString(new StringEventData(this,
							m_numberEntry, "10000")); //$NON-NLS-1$
		text = m_numberEntry.getText();
		assertEquals("Hypthen and comma format ", "10,000", text); //$NON-NLS-1$ //$NON-NLS-2$

	}

	public static void main (String[] args){
		TestRunner.run(TestNumberEntry.class);
	}


}