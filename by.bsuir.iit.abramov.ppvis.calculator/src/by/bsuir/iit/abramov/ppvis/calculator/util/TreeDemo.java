/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package by.bsuir.iit.abramov.ppvis.calculator.util;

/**
 * This application that requires the following additional files:
 *   TreeDemoHelp.html
 *    arnold.html
 *    bloch.html
 *    chan.html
 *    jls.html
 *    swingtutorial.html
 *    tutorial.html
 *    tutorialcont.html
 *    vm.html
 */
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class TreeDemo extends JPanel {
	private class BookInfo {
		public String	bookName;
		public URL		bookURL;

		public BookInfo(final String book, final String filename) {

			bookName = book;
		}

		@Override
		public String toString() {

			return bookName;
		}
	}

	private final JEditorPane	htmlPane;
	private final JTree			tree;
	private URL					helpURL;

	private static boolean		DEBUG					= false;
	// Optionally play with line styles. Possible values are
	// "Angled" (the default), "Horizontal", and "None".
	private static boolean		playWithLineStyle		= false;

	private static String		lineStyle				= "Horizontal";

	// Optionally set the look and feel.
	private static boolean		useSystemLookAndFeel	= false;

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread.
	 */
	private static void createAndShowGUI() {

		// Create and set up the window.
		final JFrame frame = new JFrame("TreeDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new TreeDemo());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(final String[] args) {

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				TreeDemo.createAndShowGUI();
			}
		});
	}

	public TreeDemo() {

		super(new GridLayout(1, 0));

		// Create the nodes.
		final DefaultMutableTreeNode top = new DefaultMutableTreeNode("The Java Series");
		createNodes(top);

		// Create a tree that allows one selection at a time.
		tree = new JTree(top);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Create the scroll pane and add the tree to it.
		final JScrollPane treeView = new JScrollPane(tree);

		// Create the HTML viewing pane.
		htmlPane = new JEditorPane();

		// Add the scroll panes to a split pane.
		final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(treeView);
		// splitPane.setBottomComponent(htmlView);

		final Dimension minimumSize = new Dimension(100, 50);
		treeView.setMinimumSize(minimumSize);
		splitPane.setDividerLocation(100);
		splitPane.setPreferredSize(new Dimension(500, 300));

		// Add the split pane to this panel.
		add(splitPane);
	}

	private void createNodes(final DefaultMutableTreeNode top) {

		DefaultMutableTreeNode category = null;
		DefaultMutableTreeNode book = null;

		category = new DefaultMutableTreeNode("Books for Java Programmers");
		top.add(category);

		// original Tutorial
		book = new DefaultMutableTreeNode(new BookInfo(
				"The Java Tutorial: A Short Course on the Basics", "tutorial.html"));
		category.add(book);

		// Tutorial Continued
		book = new DefaultMutableTreeNode(new BookInfo(
				"The Java Tutorial Continued: The Rest of the JDK", "tutorialcont.html"));
		category.add(book);

		// JFC Swing Tutorial
		book = new DefaultMutableTreeNode(new BookInfo(
				"The JFC Swing Tutorial: A Guide to Constructing GUIs",
				"swingtutorial.html"));
		category.add(book);

		// Bloch
		book = new DefaultMutableTreeNode(new BookInfo(
				"Effective Java Programming Language Guide", "bloch.html"));
		category.add(book);

		// Arnold/Gosling
		book = new DefaultMutableTreeNode(new BookInfo("The Java Programming Language",
				"arnold.html"));
		category.add(book);

		// Chan
		book = new DefaultMutableTreeNode(new BookInfo("The Java Developers Almanac",
				"chan.html"));
		category.add(book);

		category = new DefaultMutableTreeNode("Books for Java Implementers");
		top.add(category);

		// VM
		book = new DefaultMutableTreeNode(new BookInfo(
				"The Java Virtual Machine Specification", "vm.html"));
		category.add(book);

		// Language Spec
		book = new DefaultMutableTreeNode(new BookInfo("The Java Language Specification",
				"jls.html"));
		category.add(book);
	}
}
