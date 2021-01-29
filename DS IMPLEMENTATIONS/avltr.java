import java.util.*;

public class avltr{
    public static void main(String[] args) {
        AVLTree<Integer> b=new AVLTree<Integer>();
        for(int i=1;i<15;i++){
            b.insert(i);
        }
        for(int i=1;i<14;i++){
            System.out.println(b.getSuccessorinpartialorder(i).data); 
            System.out.println(b.getSuccessorinstrictpartialorder(i).data); 
        }
      
          
    }

}

class AVLNode<T extends Comparable<T>>{
    T data;
    AVLNode<T> left;
    AVLNode<T> right;
    AVLNode<T> parent;
    int height;
    public AVLNode(T data){
        this.data=data;
        this.height=-1;
    }
   
}


class AVLTree<T extends Comparable<T>> {
    AVLNode<T> root;

    public AVLTree(){
        this.root=null;
    }

    public void inOrderTraversal(AVLNode<T> n){
        if(n==null){
            return;
        }
        inOrderTraversal(n.left);
        System.out.println(n.data);
        inOrderTraversal(n.right);
    }

    public void preOrderTraversal(AVLNode<T> n){
        if(n==null){
            return;
        }
        System.out.println(n.data);
        preOrderTraversal(n.left);
        preOrderTraversal(n.right);
    }

    public void postOrderTraversal(AVLNode<T> n){
        if(n==null){
            return;
        }
        postOrderTraversal(n.left);
        postOrderTraversal(n.right);
        System.out.println(n.data);
    }

    public void preOrderTraversal(){
        preOrderTraversal(root);
    }
    public void postOrderTraversal(){
        postOrderTraversal(root);
    }
    public void inOrderTraversal(){
        inOrderTraversal(root);
    }

    public AVLNode<T> searchkey(T k){
       AVLNode<T> n=root;
       while(n!=null){
           if(n.data.compareTo(k)==0){
               return n;
           }
           else{
               if(n.data.compareTo(k)<0){
                   n=n.right;
               }
               if(n.data.compareTo(k)>0){
                   n=n.left;
               }
           }
       }
       return n;
       
    }

    public void levelOrderTraversal(){       
        Queue<AVLNode<T>> q=new LinkedList<AVLNode<T>>();
        if(root==null){
            return;
        }
        q.add(root);
        while(q.size()!=0){
            AVLNode<T> temp=q.peek();
            System.out.println(q.poll().data);
            if(temp.left!=null){
                q.add(temp.left);
            }
            if(temp.right!=null){
                q.add(temp.right);
            }

        }

    }

   
    public AVLNode<T> getSuccessorinpartialorder(T k){
        AVLNode<T> lead=root;
        AVLNode<T> lag=null;
        while(lead!=null && lead.data.compareTo(k)!=0){
            lag=lead;
            if(lead.data.compareTo(k)>0){
                lead=lead.left;
            }
            else{
                lead=lead.right;
            }

        }
        if(lead!=null){
            return lead;
        }             
        else{
            if(lag.data.compareTo(k)<0){
                lead=lag;
                while(lead.parent!=null && lead.parent.data.compareTo(lead.data)<=0){
                    lead=lead.parent;
                }
                return lead.parent;
            }
            else{
                return lag;
            }
           
        }
        
    }

    public AVLNode<T> getSuccessorinstrictpartialorder(T k){
        AVLNode<T> lead=root;
        AVLNode<T> lag=null;
        while(lead!=null && lead.data.compareTo(k)!=0){
            lag=lead;
            if(lead.data.compareTo(k)>0){
                lead=lead.left;
            }
            else{
                lead=lead.right;
            }

        }
        if(lead!=null && lead.right!=null){           
               lead=lead.right;
               while(lead.left!=null){
                   lead=lead.left;
               }
               return lead;

            }
            else{
                if(lead!=null){
                    while(lead.parent!=null && lead.parent.data.compareTo(lead.data)<=0){
                        lead=lead.parent;
                    }
                    return lead.parent;

                } 
                else{
                    if(lag.data.compareTo(k)<0){
                        lead=lag;
                        while(lead.parent!=null && lead.parent.data.compareTo(lead.data)<=0){
                            lead=lead.parent;
                        }
                        return lead.parent;
                    }
                    else{
                        return lag;
                    }
                   
                }
              

            }

      }
        
    


    
      public int getHeight(AVLNode<T> X){
        if(X==null){
            return 0;
        }
        else{
            return X.height;
        }

    }
    
    public void adjustHeight(AVLNode<T> X){
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

    public void setHeight(AVLNode<T> n){
        if(n.parent==null){
            return;
        }
        n.height= 1+Math.max(getHeight(n.right),getHeight(n.left));

    }


    public AVLNode<T> insert(T k) 
    { 
        AVLNode<T> n=new AVLNode<T>(k);
        if(root==null){
           root=n;            
        }
        else{
            AVLNode<T> lead=root;
            AVLNode<T> lag=null;
            while(lead!=null){
                lag=lead;
                if(lead.data.compareTo(k)>0){
                    lead=lead.left;
                }
                else{
                    lead=lead.right;
                }
                
            }
            n.parent=lag;            
            if(lag.data.compareTo(k)<0){
                lag.right=n;
            }
            else{
                lag.left=n;

            }
        }
        AVLNode<T> y=n;
        while(y.parent!=null){
            y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
            y=y.parent;

        } 
        rebalanceInsert(n,n);
        return n;
    
    }



    public void Delete(T k)
    { 
        
        AVLNode<T> lead=root; 
        AVLNode<T> lag=null;
        if(lead==null){
            return;
        }
        else{
            while(lead!=null && lead.data.compareTo(k)!=0){
                lag=lead;
                if(lead.data.compareTo(k)>0){
                    lead=lead.left;
                }
                else{
                    lead=lead.right;
                }
            }
            if(lead==null){
                return;
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
                    AVLNode<T> y=lag;
                    while(y.parent!=null){
                        y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
                        y=y.parent;
            
                    } 
                    rebalanceDelete(lag);
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
                    AVLNode<T> y=lag;
                    while(y.parent!=null){
                        y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
                        y=y.parent;
            
                    } 
                    
                    rebalanceDelete(lag);

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
                    AVLNode<T> y=lag;
                    while(y.parent!=null){
                        y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
                        y=y.parent;
            
                    } 
                    
                    rebalanceDelete(lag);
                }
                else{
                    AVLNode<T> m=lead;
                    m=lead.right;
                    while(m.left!=null){
                        m=m.left;
                    }
                    lead.data=m.data;
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
                        AVLNode<T> y=lag;
                        while(y.parent!=null){
                            y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
                            y=y.parent;
                
                        } 
                        
                        rebalanceDelete(lag);
                        
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
                        AVLNode<T> y=lag;
                        while(y.parent!=null){
                            y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
                            y=y.parent;
                
                        } 
                        
                        rebalanceDelete(lag);
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
                        AVLNode<T> y=lag;
                        while(y.parent!=null){
                            y.height=1+Math.max(getHeight(y.left),getHeight(y.right));
                            y=y.parent;
                
                        } 
                    
                        rebalanceDelete(lag);

                    }

                    else{
                        return;
                    }
                }
            }
        }
                        

        }

        public AVLNode<T> rebalanceDelete(AVLNode<T> n){
            if(n.parent==null){
                return n;
            }
            else{
                int z=getHeight(n.left)-getHeight(n.right);
                AVLNode<T> S=null;
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
        
        public AVLNode<T> rebalanceInsert(AVLNode<T> n,AVLNode<T> inserted){
            if(n.parent==null){
                return n;
            }
            else{
                int z=getHeight(n.left)-getHeight(n.right);
                AVLNode<T> S=null;
                if(z>1){               
                      if(inserted.data.compareTo(n.left.data)<0){         
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
                        if(inserted.data.compareTo(n.right.data)>0){
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
           
       public AVLNode<T> rightRotate(AVLNode<T> n){
            AVLNode<T> temp=n.left;
            AVLNode<T> temp1=temp.right;
            temp.parent=n.parent;
            temp.right=n;
            n.parent=temp;             
            n.left=temp1;
            if(temp1!=null){
                temp1.parent=n;
            }
            if(temp.parent.data.compareTo(temp.data)<0){
                temp.parent.right=temp;
             }
            else{
                temp.parent.left=temp;
            }
                
            return temp;   
            
        }
    
        public AVLNode<T> leftRotate(AVLNode<T> n){
            AVLNode<T> temp=n.right;
            AVLNode<T> temp1=temp.left;        
            temp.parent=n.parent;
            temp.left=n;
            n.parent=temp;
            n.right=temp1;
            if(temp1!=null){
                temp1.parent=n;
            } 
            if(temp.parent.data.compareTo(temp.data)<0){
                temp.parent.right=temp;
             }
            else{
                temp.parent.left=temp;
            }
            return temp;
            
    }
    
        public AVLNode<T> rightLeftRotate(AVLNode<T> n){
            AVLNode<T> temp=n.right;
            AVLNode<T> temp1=temp.left; 
            AVLNode<T> x=temp1.left;
            AVLNode<T> y=temp1.right;       
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
            
            if(temp1.parent.data.compareTo(temp1.data)<0){
                temp1.parent.right=temp1;
            }
            else{
                temp1.parent.left=temp1;
            }
            return temp1;
            
        }
    
        public AVLNode<T> leftRightRotate(AVLNode<T> n){
            AVLNode<T> temp=n.left;
            AVLNode<T> temp1=temp.right; 
            AVLNode<T> x=temp1.left;
            AVLNode<T> y=temp1.right;       
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
            
            if(temp1.parent.data.compareTo(temp1.data)<0){
                temp1.parent.right=temp1;
            }
            else{
                temp1.parent.left=temp1;
            }
            return temp1;
            
        }
}
    