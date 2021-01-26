// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.

    private int compare(Dictionary x){
        if(this.key> x.key){
            return 1;
        }
        else if(this.key<x.key){        
            return -1;
        }
        else{
            if(this.address>x.address){
                return 1;
            }
            else if(this.address<x.address){            //True if current object is greater than other. 
                return -1;
            }                                         //Key-Address-Size as tiebreakers.
                                                       //For deletion
            else{
                if(this.size>x.size){
                    return 1;
                }
                else if(this.size<x.size){        
                    return -1;
                }
                else{
                    return 0;
                    }
                }
            }


        }
        private int getHeight(AVLTree X){
            if(X==null){
                return 0;
            }
            else{
                return X.height;
            }
    
        }
        
        private void adjustHeight(AVLTree X){
            if(X.parent==null){
                return;
            }
            else{
                while(X.parent!=null){
                    setHeight(X);
                    X=X.parent;
                }

            }            
    
        }

        private void setHeight(AVLTree n){
            if(n.parent==null){
                return;
            }
            n.height= 1+Math.max(getHeight(n.right),getHeight(n.left));

        }
    
   
    public AVLTree Insert(int address, int size, int key) 
    { 
        AVLTree n=this;  
        while(n.parent!=null){
            n=n.parent;   //Sentinel-Node
        }
        AVLTree lead=n.right;  //Root Node
        AVLTree x=new AVLTree(address,size,key);
        if(lead==null){
            n.right=x;
            x.parent=n;            
        }
        else{
            AVLTree lag=null;
            while(lead!=null){
                lag=lead;
                if(lead.compare(x)>0){
                    lead=lead.left;
                }                                         //LEAF AT HEIGHT=1;
                else{
                    lead=lead.right;
                }
            }
            x.parent=lag;
            if(lag.compare(x)>0){
                lag.left=x;
            }
            else{
                lag.right=x;            
                
            }

        }
        AVLTree y=x;
        while(y.parent!=null){
            y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
            y=y.parent;

        } 
        rebalanceInsert(x,x);
        return x;
       
    }
 
   
    
    public boolean Delete(Dictionary e)
    { 
         AVLTree n=this;  
        while(n.parent!=null){
            n=n.parent;   //Sentinel-Node
        }
        AVLTree lead=n.right; //Root-Node
        AVLTree lag=null;
        if(lead==null){
            return false;
        }
        else{
            while(lead!=null && lead.compare(e)!=0){
                lag=lead;
                if(lead.compare(e)>0){
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
                    AVLTree y=lag;
                    while(y.parent!=null){
                        y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
                        y=y.parent;
            
                    } 
                    rebalanceDelete(lag);
                    return true;
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
                    AVLTree y=lag;
                    while(y.parent!=null){
                        y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
                        y=y.parent;
            
                    } 
                    
                    rebalanceDelete(lag);
                    return true;
    
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
                    AVLTree y=lag;
                    while(y.parent!=null){
                        y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
                        y=y.parent;
            
                    } 
                    
                    rebalanceDelete(lag);
                    return true;
    
                }
                else{
                    AVLTree m=lead;
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
                        AVLTree y=lag;
                        while(y.parent!=null){
                            y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
                            y=y.parent;
                
                        } 
                        
                        rebalanceDelete(lag);
                        return true;
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
                        AVLTree y=lag;
                        while(y.parent!=null){
                            y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
                            y=y.parent;
                
                        } 
                        
                        rebalanceDelete(lag);
                        return true;
    
    
                    }
                    else if(m.right!=null && m.left==null){
                        if(lag.left==m){
                            lag.left=m.right;
                            m.right.parent=lag;
                            m.parent=null;
                            m.left=null;
                            
                        }
                        else{
                            lag.right=m.right;
                            m.right.parent=lag;
                            m.parent=null;
                            m.left=null;
                        }
                        AVLTree y=lag;
                        while(y.parent!=null){
                            y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
                            y=y.parent;
                
                        } 
                       
                        rebalanceDelete(lag);
                        return true;
    
    
                    }

                    else{
                        return true;
                    }
                }
            }
        }
                         

        }
       
 
        public AVLTree Find(int key, boolean exact)

        {  
            AVLTree n=this;  
            while(n.parent!=null){
                n=n.parent;   //Sentinel-Node
            }
            AVLTree lead=n.right;  //Root Node
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
   
    private AVLTree rebalanceDelete(AVLTree n){
        if(n.parent==null){
            return n;
        }
        else{
            int z=getHeight(n.left)-getHeight(n.right);
            AVLTree S=null;
            if(z>1){               
                  if(getHeight(n.left.left)>=getHeight(n.left.right)){         
                       S=rightRotate(n);                                   
                    }
                   else{
                        S=leftRightRotate(n); 
                    }
                    if(S.right!=null){
                        adjustHeight(S.right);
                    }
                    if(S.left!=null){
                        adjustHeight(S.left);
                    }
                    return S; 
                }
            else if(z<-1){
                    if(getHeight(n.right.right)>=getHeight(n.right.left)){
                        S=leftRotate(n);
                    }
                    else{
                       S=rightLeftRotate(n);
                    }
                    if(S.right!=null){
                        adjustHeight(S.right);
                    }
                    if(S.left!=null){
                        adjustHeight(S.left);
                    }
                    return S; 
                       
                }
               
            
            else{
                return rebalanceDelete(n.parent);
            } 
        } 
    }
    
    private AVLTree rebalanceInsert(AVLTree n,AVLTree inserted){
        if(n.parent==null){
            return n;
        }
        else{
            int z=getHeight(n.left)-getHeight(n.right);
            AVLTree S=null;
            if(z>1){               
                  if(inserted.compare(n.left)<0){         
                       S=rightRotate(n);                                   
                    }
                   else{
                        S=leftRightRotate(n); 
                    }
                    if(S.right!=null){
                        adjustHeight(S.right);
                    }
                    if(S.left!=null){
                        adjustHeight(S.left);
                    }
                    return S; 
                }
            else if(z<-1){
                    if(inserted.compare(n.right)>0){
                        S=leftRotate(n);
                    }
                    else{
                       S=rightLeftRotate(n);
                    }
                    if(S.right!=null){
                        adjustHeight(S.right);
                    }
                    if(S.left!=null){
                        adjustHeight(S.left);
                    }
                    return S; 
                       
                }
               
            
            else{
                return n;
            } 
        } 
    } 
       
   private AVLTree rightRotate(AVLTree n){
        AVLTree temp=n.left;
        AVLTree temp1=temp.right;
        temp.parent=n.parent;
        temp.right=n;
        n.parent=temp;             
        n.left=temp1;
        if(temp1!=null){
            temp1.parent=n;
        }
        if(temp.parent.compare(temp)<0){
            temp.parent.right=temp;
         }
        else{
            temp.parent.left=temp;
        }
            
        return temp;   
        
    }

    private AVLTree leftRotate(AVLTree n){
        AVLTree temp=n.right;
        AVLTree temp1=temp.left;        
        temp.parent=n.parent;
        temp.left=n;
        n.parent=temp;
        n.right=temp1;
        if(temp1!=null){
            temp1.parent=n;
        } 
        if(temp.parent.compare(temp)<0){
            temp.parent.right=temp;
         }
        else{
            temp.parent.left=temp;
        }
        return temp;
        
}

    private AVLTree rightLeftRotate(AVLTree n){
        AVLTree temp=n.right;
        AVLTree temp1=temp.left; 
        AVLTree x=temp1.left;
        AVLTree y=temp1.right;       
        temp1.parent=n.parent;
        temp.parent=temp1;
        n.parent=temp1;
        temp1.left=n;
        temp1.right=temp;
        n.right=x;
        temp.left=y;
        if(x!=null){
            x.parent=n;
        }
        if(y!=null){
            y.parent=temp;
        }
        
        if(temp1.parent.compare(temp1)<0){
            temp1.parent.right=temp1;
        }
        else{
            temp1.parent.left=temp1;
        }
        return temp1;
        
    }

    private AVLTree leftRightRotate(AVLTree n){
        AVLTree temp=n.left;
        AVLTree temp1=temp.right; 
        AVLTree x=temp1.left;
        AVLTree y=temp1.right;       
        temp1.parent=n.parent;
        temp.parent=temp1;
        n.parent=temp1;
        temp1.left=temp;
        temp1.right=n;
        n.left=y;
        temp.right=x;
        if(x!=null){
            x.parent=temp;
        }
        if(y!=null){
            y.parent=n;
        }
        
        if(temp1.parent.compare(temp1)<0){
            temp1.parent.right=temp1;
        }
        else{
            temp1.parent.left=temp1;
        }
        return temp1;
        
    }

    
    public AVLTree getFirst()
    { 
        AVLTree n=this;  
        while(n.parent!=null){
            n=n.parent;   //Sentinel-Node
        }
        AVLTree lead=n.right;  //Root Node
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

    
    public AVLTree getNext()
    { 
        AVLTree lead=this;
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
                    if(lead.parent.compare(lead)>0){
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

    private boolean validPointers(AVLTree lead){
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

    private boolean SearchProperty(AVLTree X,AVLTree min,AVLTree max){
        if(X==null){
            return true;
        }
        else{
            if(X.right!=null){
                if(X.right.compare(X)<0){
                    return false;
                }
                else{
                    return true;
                }

            }
            if(X.left!=null){
                if(X.left.compare(X)>0){
                    return false;
                }
                else{
                    return true;
                }

            }
           
            if(min.compare(X)<0 || max.compare(X)>0){
                    return false;
                }
                else{
                    boolean s1=SearchProperty(X.left, min, X);
                    boolean s2=SearchProperty(X.right,X,max);
                    return s1&&s2;
                }         
           
        }
    }

    private boolean heightBalance(AVLTree X){
        if(X==null){
            return true;
        }
        else{
          int n=getHeight(X.right)-getHeight(X.left);
          if(n*n>1){
              return false;
          }
          else{
              boolean s1=heightBalance(X.left);
              if(s1==false){
                  return s1;
              }
              else{
                  boolean s2=heightBalance(X.right);
                  return s2;
              }
          }

        }
      }


    
    public boolean sanity()
    {  
        AVLTree n=this;  
        while(n.parent!=null){
            n=n.parent;   //Sentinel-Node
        }
        AVLTree lead=n.right;  //Root Node
        boolean s1=validPointers(lead);
        if(s1==false){
            return s1;
        }
        boolean s2=SearchProperty(lead,lead,lead);
        if(s2==false){
            return s2;
        }
        boolean s3=heightBalance(lead);
        return s3;
        
    }

    private void inorder(AVLTree X){
        if(X==null){
            return;
        }
        inorder(X.left);
        System.out.println(X.address+" "+X.size+" "+X.key+" "+X.height);
        inorder(X.right);
    }

   
}


