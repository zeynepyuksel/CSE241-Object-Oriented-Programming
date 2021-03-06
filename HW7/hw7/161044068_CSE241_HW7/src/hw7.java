import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class hw7 {
	private JFrame frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hw7 window = new hw7();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public hw7() {
		tests();
		initialize();
	}
	private void initialize() {
		
		Rectangle outer = new Rectangle(500,450,0,0);
		Rectangle inner = new Rectangle(50,45,220,195);
		Circle cOuter = new Circle(450,0,0);
		Circle cInner = new Circle(50,220,198);
		Triangle tOuter = new Triangle(20,250,0,0,450,500,450);
		Triangle tInner = new Triangle(20,250,200,225,250,275,250);
		
		//Frame olusturulur.
		frame = new JFrame();
		frame.setTitle("HW7 - SHAPES");
		frame.setBounds(350, 150, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//Panel..
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(50, 70,500,450);
		frame.getContentPane().add(panel);
		
		//İnner yazısı icin
		JLabel innerLb = new JLabel("Inner shape");
		innerLb.setHorizontalAlignment(SwingConstants.CENTER);
		innerLb.setBounds(180, 10, 100, 14);
		frame.getContentPane().add(innerLb);
		
		//Inner shape lerinin butonu
		JComboBox<String> innerCb = new JComboBox<String>();
		innerCb.setSelectedIndex(-1);
		innerCb.setMaximumRowCount(3);
		innerCb.setBounds(180, 30, 100, 20);
		innerCb.addItem("Rectangle");
		innerCb.addItem("Triangle");
		innerCb.addItem("Circle");
		frame.getContentPane().add(innerCb);
		
		
		//Outer yazısı icin
		JLabel outerlb = new JLabel("Outer shape");
		outerlb.setHorizontalAlignment(SwingConstants.CENTER);
		outerlb.setBounds(50, 10, 99, 14);
		frame.getContentPane().add(outerlb);
		
		//Outer shape lerin butonu
		JComboBox<String> outerCb = new JComboBox<String>();
		outerCb.setSelectedIndex(-1);
		outerCb.setMaximumRowCount(3);
		outerCb.setBounds(55, 30, 100, 20);
		outerCb.addItem("Rectangle");
		outerCb.addItem("Triangle"); 
		outerCb.addItem("Circle");    
		frame.getContentPane().add(outerCb);

		JButton draw = new JButton("Draw");
		draw.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Graphics g = panel.getGraphics();
				g.setColor(Color.PINK);
				if(outerCb.getSelectedItem() == "Rectangle") {
					outer.Draw(g);
				}
				else if(outerCb.getSelectedItem() == "Circle") {
					cOuter.Draw(g);
				}
				else {
					tOuter.Draw(g);
				}
				
				g.setColor(Color.CYAN);
				if(innerCb.getSelectedItem() == "Rectangle") {
					inner.Draw(g);
				}
				else if(innerCb.getSelectedItem() == "Circle") {
					cInner.Draw(g);
				}  
				else {
					tInner.Draw(g);
				}
				panel.paintComponents(g);
			}
		});
		draw.setBounds(350, 28, 75, 23);
		frame.getContentPane().add(draw);
		
		JButton clear = new JButton("Clear");
		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Graphics g = panel.getGraphics(); 
				g.setColor(Color.WHITE); 
				g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
				panel.paintComponents(g);
			}
		});
		clear.setBounds(450, 28, 75, 23);
		frame.getContentPane().add(clear);
		
	}
	public static void sortAll(ArrayList<Shape> shape){
	    Shape temp;
	    for(int i=0; i<shape.size(); ++i){
	        for(int j=i+1; j<shape.size(); ++j){
	            if(shape.get(i).area() > shape.get(j).area()){
	                temp=shape.get(i);
	                shape.set(i,shape.get(j));
	                shape.set(j,temp);
	            }
	        }
	    }
	}
	public static ArrayList<Polygon> convertAll(ArrayList<Shape> shape){
	    ArrayList<Polygon> v = new ArrayList<Polygon>();
	    for(int i=0; i<shape.size(); ++i){
	        Polygon a = new PolygonVect();
	        if(shape.get(i) instanceof Rectangle){
	            a.setWidth(((Rectangle)shape.get(i)).getWidth());
	            a.setHeight(((Rectangle)shape.get(i)).getHeight());
	            a.setCoord1(((Rectangle)shape.get(i)).getCoordx(),((Rectangle)shape.get(i)).getCoordy());     
	        }
	        else if(shape.get(i) instanceof Circle){
	            a.setWidth(((Circle)shape.get(i)).getSize());
	            a.setCoord1(((Circle)shape.get(i)).getCoordx(),((Circle)shape.get(i)).getCoordy());
	        }
	        else if(shape.get(i) instanceof Triangle){
	            a.setWidth(((Triangle)shape.get(i)).getSize());
	            a.setCoord1(((Triangle)shape.get(i)).getCoordx1(),((Triangle)shape.get(i)).getCoordy1());
	            a.setCoord2(((Triangle)shape.get(i)).getCoordx2(),((Triangle)shape.get(i)).getCoordy2());
	            a.setCoord3(((Triangle)shape.get(i)).getCoordx3(),((Triangle)shape.get(i)).getCoordy3());
	        }
	        v.add(a);
	    }
	    return v;
	}
	private static void tests(){
		try {
			// Rectangle area(),perimeter(),increment(),decrement() Function Testing
			System.out.println("Rectangle area(),perimeter(),increment(),decrement() Function Testing");
			Rectangle rect = new Rectangle(100,50,2,4);
			System.out.printf("rect's area is %.2f\n",rect.area());
			System.out.printf("rect's perimeter is %.2f\n",rect.perimeter());
			rect.increment();
			System.out.printf("After increment() function, rect'coords x-> %.2f y-> %.2f\n",rect.getCoordx(),rect.getCoordy());
			rect.decrement();
			System.out.printf("After decrement() function, rect'coords x-> %.2f y-> %.2f\n",rect.getCoordx(),rect.getCoordy());
			// Circle area(),perimeter(),increment(),decrement() Function Testing
			System.out.println();
			System.out.println();
			System.out.println("Circle area(),perimeter(),increment(),decrement() Function Testing");
			Circle circ = new Circle(10,5,3);
			System.out.printf("circ's area is %.2f\n",circ.area());
			System.out.printf("circ's perimeter is %.2f\n",circ.perimeter());
			circ.increment();
			System.out.printf("After increment() function, circ's coords x-> %.2f y-> %.2f\n",circ.getCoordx(),circ.getCoordy());
			circ.decrement();
			System.out.printf("After increment() function, circ's coords x-> %.2f y-> %.2f\n",circ.getCoordx(),circ.getCoordy());
			// Triangle area(),perimeter(),increment(),decrement() Function Testing
			System.out.println();
			System.out.println();
			System.out.println("Triangle area(),perimeter(),increment(),decrement() Function Testing");
			Triangle tri = new Triangle(10,5,0,0,10,10,10);
			System.out.printf("tri's area is %.2f\n",tri.area());
			System.out.printf("tri's perimeter is %.2f\n",tri.perimeter());
			tri.increment();
			System.out.printf("After increment() function, tri's coords :\n");
			System.out.printf("x1-> %.2f y1-> %.2f\n",tri.getCoordx1(),tri.getCoordy1());
			System.out.printf("x2-> %.2f y2-> %.2f\n",tri.getCoordx2(),tri.getCoordy2());
			System.out.printf("x3-> %.2f y3-> %.2f\n",tri.getCoordx3(),tri.getCoordy3());
			tri.decrement();
			System.out.printf("After decrement() function, tri's coords :\n");
			System.out.printf("x1-> %.2f y1-> %.2f\n",tri.getCoordx1(),tri.getCoordy1());
			System.out.printf("x2-> %.2f y2-> %.2f\n",tri.getCoordx2(),tri.getCoordy2());
			System.out.printf("x3-> %.2f y3-> %.2f\n",tri.getCoordx3(),tri.getCoordy3());
			//compareTo() Function Testing
			System.out.println();
			System.out.println();
			System.out.println("compareTo() Function Testing");
			if(rect.compareTo(circ) == 0)
				System.out.println("rect's area is same circ's area");
			else if(rect.compareTo(circ) == 1)
				System.out.println("rect's area is bigger than circ's area");
			else
				System.out.println("rect's area is less than tri's area");
			if(rect.compareTo(tri) == 0)
				System.out.println("rect's area is same tri's area");
			else if(rect.compareTo(tri) == 1)
				System.out.println("rect's area is bigger than tri's area");
			else
				System.out.println("rect's area is less than circ's area");
			}
			catch(IllegalArgumentException e) {
				System.out.println("Entered Wrong Argument!");
			}
			//SortAll Function Testing
			System.out.println();
			System.out.println();
			System.out.println("SortAll() Function Testing");
			ArrayList<Shape> s = new ArrayList<Shape>();
			Rectangle a = new Rectangle(10,5);
			Rectangle b = new Rectangle(9,5);
			Rectangle c = new Rectangle(8,5);
		    s.add(a); 
		    s.add(b);
		    s.add(c); 
		    for(int i=0; i<s.size(); ++i) {
		    	System.out.printf("s[%d]'s Area = %.2f ",i,s.get(i).area());
		    }
		    sortAll(s);
		    System.out.println("\nAfter sort..");
		    for(int i=0; i<s.size(); ++i)
		    	System.out.printf("s[%d]'s Area = %.2f ",i,s.get(i).area());
		    System.out.println();
		  //convertAll Function Testing
		    System.out.println("\n\nconvertAll() Function Testing");
		    Rectangle shape1 = new Rectangle(10,10);
		    Rectangle shape2 = new Rectangle(30,30);
		    ComposedShape compo = new ComposedShape(shape2,shape1);
		    System.out.println("ArrayList<Shape>'s coordXs and coordYs : ");
		    for(int i=0; i<compo.getArrayList().size(); ++i)
		        System.out.printf("v[%d]'s coordX= %.2f v[%d]'s coordY= %.2f\n",i,((Rectangle)compo.getArrayList().get(i)).getCoordx(),i,((Rectangle)compo.getArrayList().get(i)).getCoordy());
		    ArrayList<Polygon> p = convertAll(compo.getArrayList());
		    System.out.printf("\nAfter convert from vector<Shape> to vector<Polygon>\n");
		    System.out.printf("vector<Polygon>'s coordXs and coordYs : \n");
		    for(int i=0; i<p.size(); ++i)
		    	System.out.printf("p[%d]'s coordX= %.2f p[%d]'s coordY= %.2f\n",i,p.get(i).getCoordX1(),i,p.get(i).getCoordY1());
	}
    
}
