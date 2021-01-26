// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).


    public int Allocate(int blockSize) {
        if(blockSize<=0){
            return -1;
        }
        Dictionary m = freeBlk.Find(blockSize,false);
        
        if(m==null){
            return -1;                                      
        }
        int x=m.address;
        int y=m.size;
        allocBlk.Insert(x,blockSize,x);
        freeBlk.Delete(m);
        if(m.size>blockSize){
            freeBlk.Insert(x+blockSize,y-blockSize,y-blockSize);
        }            
        
        return x;
    }
    
      
    public int Free(int startAddr) {
        if(startAddr<0){
            return -1;
        }
        Dictionary m = allocBlk.Find(startAddr,true);
        if(m==null){
            return -1;
        }
        else{
            freeBlk.Insert(startAddr,m.size,m.size);
            allocBlk.Delete(m);
            return 0;
        }
        
        }
            
        
    }

    

   
