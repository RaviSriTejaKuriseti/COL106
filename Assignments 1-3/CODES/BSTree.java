// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    

    private int shallowCompare(Dictionary e) {
        if (this.key > e.key) {
            return 1;
        } 
        else if (this.key < e.key) {
            return -1;
        }
        else {
            if (this.address > e.address) {
                return 1;
            }
             else if (this.address < e.address) { // True if current object is greater than other.
                return -1;
            }                                        // Key-Address-Size as tiebreakers.
                                                    // For deletion
            else {
                if (this.size > e.size) {
                    return 1;
                }
                 else if (this.size < e.size) {
                    return -1;
                }
                else{
                    return 0;
                    }
                }
            }


        } 
        
 
    public BSTree Insert(int address, int size, int key) 
    { 
        BSTree n=this;  
        while(n.parent!=null){
            n=n.parent;   //Sentinel-Node
        }
        BSTree lead=n.right;  //Root Node
        BSTree x=new BSTree(address,size,key);
        if(lead==null){
            n.right=x;
            x.parent=n;            
        }
        else{
            BSTree lag=null;
            while(lead!=null){
                lag=lead;
                if(lead.shallowCompare(x)>0){
                    lead=lead.left;
                }
                else{
                    lead=lead.right;
                }
            }
            x.parent=lag;
            if(lag.shallowCompare(x)>0){
                lag.left=x;
            }
            else{
                lag.right=x;            
                
            }

        }
        return x;
       
    }

    public boolean Delete(Dictionary e)
    { 
         BSTree n=this;  
        while(n.parent!=null){
            n=n.parent;   //Sentinel-Node
        }
        BSTree lead=n.right; //Root-Node
        BSTree lag=null;
        if(lead==null){
            return false;
        }
        else{
            while(lead!=null && lead.shallowCompare(e)!=0){
                lag=lead;
                if(lead.shallowCompare(e)>0){
                    lead=lead.left;
                }
                else{
                    lead=lead.right;
                }
            }
            if(lead==null){
                return false;
            }
            else{
               
                if(lead.right==null && lead.left==null){
                    lag=lead.parent;
                    if(lag.left==lead){
                        lag.left=null;
                        lead.parent=null;
                    }
                    else{
                        lag.right=null;
                        lead.parent=null;
                    }
                }
                else if(lead.right==null && lead.left!=null){
                    lag=lead.parent;
                    if(lag.left==lead){
                        lag.left=lead.left;
                        lead.left.parent=lag;
                        lead.parent=null;
                        lead.left=null;
                        
                    }
                    else{
                        lag.right=lead.left;
                        lead.left.parent=lag;
                        lead.parent=null;
                        lead.left=null;
                    }
    
                }
                else if(lead.right!=null && lead.left==null){
                    lag=lead.parent;
                    if(lag.left==lead){
                        lag.left=lead.right;
                        lead.right.parent=lag;
                        lead.parent=null;
                        lead.right=null;
                        
                    }
                    else{
                        lag.right=lead.right;
                        lead.right.parent=lag;
                        lead.parent=null;
                        lead.right=null;
                    }
    
                }
                else{
                    BSTree m=lead;
                    m=lead.right;
                    while(m.left!=null){
                        m=m.left;
                    }
                    lead.address=m.address;
                    lead.key=m.key;
                    lead.size=m.size;
                    lag=m.parent;
    
                    if(m.right==null && m.left==null){                   
                        if(lag.right==m){
                            m.parent=null;
                            lag.right=null;
                        }
                        else{
                            m.parent=null;
                            lag.left=null;
                        }
                    }
                    else if(m.right==null && m.left!=null){
                        if(lag.left==m){
                            lag.left=m.left;
                            m.left.parent=lag;
                            m.parent=null;
                            m.left=null;
                            
                        }
                        else{
                            lag.right=m.left;
                            m.left.parent=lag;
                            m.parent=null;
                            m.left=null;
                        }
    
    
                    }
                    else if(m.right!=null && m.left==null){
                        if(lag.left==m){
                            lag.left=m.right;
                            m.right.parent=lag;
                            m.parent=null;
                            m.right=null;
                            
                        }
                        else{
                            lag.right=m.right;
                            m.right.parent=lag;
                            m.parent=null;
                            m.right=null;
                        }
    
    
                    }

                    else{
                        return false;
                    }
                   
                
                }

        }
        return true;  
    }
       
            
        }
      
       


    public BSTree Find(int key, boolean exact)

    {  
        BSTree n=this;  
        while(n.parent!=null){
            n=n.parent;   //Sentinel-Node
        }
        BSTree lead=n.right;  //Root Node
        if(lead==null){
            return null;
        }
        lead=this.getFirst();
        if(lead==null){
            return null;
        }
        else{
            while(lead!=null && lead.key!=key){
                lead=lead.getNext();
            }
            if(lead==null){
                if(exact==true){
                    return lead;
                }
                else{
                    lead=this.getFirst();
                    while(lead!=null && lead.key<key){
                         lead=lead.getNext();
                    }
                    return lead;

                }
            }
            else{
                return lead;

            }
           
        }
                
    }
            
         
    


    public BSTree getFirst()
    { 
        BSTree n=this;  
        while(n.parent!=null){
            n=n.parent;   //Sentinel-Node
        }
        BSTree lead=n.right;  //Root Node
        if(lead==null){
            return null;
        }
        else{
            while(lead.left!=null){
                lead=lead.left;
            }
            return lead;
        }
    }

    public BSTree getNext()
    { 
        BSTree lead=this;
        if(this.parent==null){
            return null;
        }
        else{
            if(lead.right!=null){
                lead=lead.right;
                while(lead.left!=null){
                    lead=lead.left;
                }
                return lead;
            }
            else{
                while (lead.parent.parent!= null){
                    if(lead.parent.shallowCompare(lead)>0){
                        return lead.parent;
                    }
                    else{
                        lead=lead.parent;
                        }
                   
                }
               return null;
                
            }
        }
       
    }
        
  
    private boolean validPointers(BSTree lead){
        if(lead==null){
            return true;
        }  
        else{
            if(lead.right!=null && lead.right.parent!=lead || lead.left!=null && lead.left.parent!=lead ){
                return false;
            }
            boolean s1=validPointers(lead.right);
            if(s1==false){
                return false;
            }
            boolean s2=validPointers(lead.left);
            return s2;
            }

        }

    
        private boolean SearchProperty(BSTree X,BSTree min,BSTree max){
            if(X==null){
                return true;
            }
            else{
                if(X.right!=null){
                    if(X.right.shallowCompare(X)<0){
                        return false;
                    }
                    else{
                        return true;
                    }

                }
                if(X.left!=null){
                    if(X.left.shallowCompare(X)>0){
                        return false;
                    }
                    else{
                        return true;
                    }

                }
               
                if(min.shallowCompare(X)<0 || max.shallowCompare(X)>0){
                        return false;
                    }
                    else{
                        boolean s1=SearchProperty(X.left, min, X);
                        boolean s2=SearchProperty(X.right,X,max);
                        return s1&&s2;
                    }
            }         
        }

    
    public boolean sanity()
    {  BSTree n=this;  
        while(n.parent!=null){
            n=n.parent;   //Sentinel-Node
        }
        BSTree lead=n.right;  //Root Node
        boolean s1=validPointers(lead);
        if(s1==false){
            return s1;
        }
        boolean s2=SearchProperty(lead,lead,lead);
        return s2;
    }

    private void inorder(BSTree X){
        if(X==null){
            return;
        }
        inorder(X.left);
        System.out.println(X.address+" "+X.size+" "+X.key);
        inorder(X.right);
    }

    public static void main(String[] args) {
        
    }
}

 
   
  

       
    

    




 


