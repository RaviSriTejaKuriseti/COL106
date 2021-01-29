import java.util.*;

class DSNode<T extends Comparable<T>>{
    int count;
    DSNode<T> parent;
    T data;
    public DSNode(T data,int count){
        this.count=count;
        this.data=data;
        this.parent=this;
    }
}

class DisjointSetUnion<T extends Comparable<T>>{
    Vector<DSNode<T>>data;
    public DisjointSetUnion(Vector<T>content){
        for(int i=0;i<content.size();i++){
            DSNode<T> d=new DSNode<T>(content.get(i),1);
            data.set(i,d);
        }
        
    }

    public boolean Find(DSNode<T> x,DSNode<T>y){
        DSNode<T> x1=Find(x);
        DSNode<T> y1=Find(y);
        if(x1.data.compareTo(y1.data)==0){
            return true;
        }
        return false;
    }

    public DSNode<T> Find(DSNode<T>x){
        while(x.parent!=x){
            x=x.parent;
        }
        return x;
    }

    public DSNode<T> Union(DSNode<T> x,DSNode<T> y){
        DSNode<T> x1=Find(x);
        DSNode<T> y1=Find(y);
        if(x1.count<=y1.count){
            x1.parent=y1;
            y1.count+=x1.count;
            return y1;
        }
        else{
            y1.parent=x1;
            x1.count+=y1.count;
            return x1;
        }
       


    }
}

public class dsu {
    public static void main(String[] args) {
        
    }
    
}
