package by.bsuir.iit.abramov.ppvis.calculator.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

	public static final int		defaultX		= 200;
	public static final int		defaultY		= 100;
	public static final int		defaultWidth	= 550;
	public static final int		defaultHeight	= 350;
	private final JPanel		contentPane;
	private static final String	TITLE			= "Калькулятор";

	public Window() {

		super(Window.TITLE);
		contentPane = new ContentPane(this);
		initialize();
	}

	private void initialize() {

		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Window.defaultX, Window.defaultY, Window.defaultWidth,
				Window.defaultHeight);
		final Menu menu = new Menu((ContentPane) contentPane);
		setJMenuBar(menu);

	}
}
