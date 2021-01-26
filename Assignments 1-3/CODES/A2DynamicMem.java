// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() {
        if(type==2){
        Dictionary X=new BSTree();
        Dictionary Y=freeBlk.getFirst();
        while(Y!=null){
            X.Insert(Y.address,Y.size,Y.address);
            Y=Y.getNext();   
        }
        Dictionary Z=null;
        Y=X.getFirst();
        while(Y!=null && Y.getNext()!=null){
            if(Y==null){
               return;
            }
            else if(Y.getNext()==null){
                return;
            }
            else if(Y.address+Y.size==Y.getNext().address){
                    Z=Y.getNext();
                    int a1=Y.address;
                    int a2=Y.size;
                    int a3=Z.size;
                    int a4=a2+a3;
                    Dictionary Y1=new BSTree(Y.address,Y.size,Y.size);
                    Dictionary Z1=new BSTree(Z.address,Z.size,Z.size);
                    X.Delete(Y);
                    X.Delete(Z);
                    freeBlk.Delete(Y1);
                    freeBlk.Delete(Z1);
                    X.Insert(a1,a4,a1);
                    freeBlk.Insert(a1,a4,a4);
                    Y=X.Find(a1,true);

                }
            else{
                Y=Y.getNext();
                }
               

            }
           
        }
        else if(type==3){
            Dictionary X=new AVLTree();
            Dictionary Y=freeBlk.getFirst();
            while(Y!=null){
                X.Insert(Y.address,Y.size,Y.address);
                Y=Y.getNext();   
            }
            Dictionary Z=null;
            Y=X.getFirst();
            while(Y!=null && Y.getNext()!=null){
                if(Y==null){
                   return;
                }
                else if(Y.getNext()==null){
                    return;
                }
                else if(Y.address+Y.size==Y.getNext().address){
                        Z=Y.getNext();
                        int a1=Y.address;
                        int a2=Y.size;
                        int a3=Z.size;
                        int a4=a2+a3;
                        Dictionary Y1=new AVLTree(Y.address,Y.size,Y.size);
                        Dictionary Z1=new AVLTree(Z.address,Z.size,Z.size);
                        X.Delete(Y);
                        X.Delete(Z);
                        freeBlk.Delete(Y1);
                        freeBlk.Delete(Z1);
                        X.Insert(a1,a4,a1);
                        freeBlk.Insert(a1,a4,a4);
                        Y=X.Find(a1,true);
    
                    }
                else{
                    Y=Y.getNext();
                    }
                   
    
                }
               
            }
        
    
        else{
         return;
     }
    
            
            
        }
        
    }

           
        
     
  
   


   
