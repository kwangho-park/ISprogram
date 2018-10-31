package app;

/*
 * [GUI design] IS program [펼치기]
 *
 *  1 JFrame (menu + contentPane(=내용물 판))
 *    - contentPane : CardLayout
 *     
 *  2. login panel  
 *    - Layout Null
 *    
 *  3. information panel
 *    - Layout Null

 * */



import controller.Controller;

// application class //
public class ISprogramStarting {
	public static void main(String[] args) {
		
		// controller // 
		new Controller();
	}	
	
} // LoginGui END

