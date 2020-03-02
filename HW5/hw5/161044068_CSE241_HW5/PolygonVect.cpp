#include "PolygonVect.h"
namespace polyVect{
    PolygonVect::PolygonVect(){
    }
    PolygonVect::PolygonVect(double x, double y){
        Point2D p;
        p.setCoord(x,y);
        v.push_back(p);
    }
    vector<Polygon::Point2D> PolygonVect::convert(Shape* shape){ // Gelen shape'in köşe noktalarını vektöre push back eder.
        if(dynamic_cast<Rectangle*>(shape)){  
            Point2D a,b,c,d;
            a.setCoord(dynamic_cast<Rectangle*>(shape)->getCoordX(),dynamic_cast<Rectangle*>(shape)->getCoordY());
            b.setCoord(dynamic_cast<Rectangle*>(shape)->getCoordX()+dynamic_cast<Rectangle*>(shape)->getSize(),dynamic_cast<Rectangle*>(shape)->getCoordY());
            c.setCoord(dynamic_cast<Rectangle*>(shape)->getCoordX()+dynamic_cast<Rectangle*>(shape)->getSize(),dynamic_cast<Rectangle*>(shape)->getCoordY()+dynamic_cast<Rectangle*>(shape)->getHeight());
            d.setCoord(dynamic_cast<Rectangle*>(shape)->getCoordX(),dynamic_cast<Rectangle*>(shape)->getCoordY()+dynamic_cast<Rectangle*>(shape)->getHeight());
            v.push_back(a);
            v.push_back(b);
            v.push_back(c);
            v.push_back(d);    
        }
        else if(dynamic_cast<Circle*>(shape)){
            for(int i=0; i<100; i++){
                Point2D a;
                double angle=3.6*i*(PI/180);       
                a.setCoord(dynamic_cast<Circle*>(shape)->getCoordX()+dynamic_cast<Circle*>(shape)->getSize()*cos(angle),dynamic_cast<Circle*>(shape)->getCoordY()+dynamic_cast<Circle*>(shape)->getSize()*sin(angle));
                v.push_back(a);
            }
        }
        else if(dynamic_cast<Triangle*>(shape)){
            Point2D a,b,c;
            a.setCoord(dynamic_cast<Triangle*>(shape)->getCoordX(),dynamic_cast<Triangle*>(shape)->getCoordY());
            b.setCoord(dynamic_cast<Triangle*>(shape)->getCoordX2(),dynamic_cast<Triangle*>(shape)->getCoordY2());
            c.setCoord(dynamic_cast<Triangle*>(shape)->getCoordX3(),dynamic_cast<Triangle*>(shape)->getCoordY3());
            v.push_back(a);
            v.push_back(b);
            v.push_back(c);
        }
        return v;
    }
    Polygon::Point2D& PolygonVect::operator[](int index){
        if(index<0 || index>v.size())
            throw IndexOutOfRangeException();
        return v[index];
    }
    void PolygonVect::printPolygon(ostream& out,vector<Shape*> v){
        string str="xmlns=\"http://wwww.w3.org/2000/svg\">";
        out<<"<svg version=\"1.1\" "<<"baseProfile=\"full\"";    
        out<<" width=\""<<v[0]->getSize()+50<<"\" height=\""<<v[0]->getSize()+50<<"\" ";
        out<<str;

        for(int i=0; i<v.size(); ++i){      
            PolygonVect a;
            a.convert(v[i]);  
            out<<"<polygon points=\"";
            for(int j=0; j<a.getVect().size(); ++j){        
                out<<a.getVect()[j].getCoordX()<<",";       
                out<<a.getVect()[j].getCoordY()<<" ";
            }
            if(i != 0){
                out<<"\" fill=\"green\" />"; 
            }          
            else{
                out<<"\" fill=\"red\" />"; 
            }  
        }
        out<<"</svg>";
    }
}