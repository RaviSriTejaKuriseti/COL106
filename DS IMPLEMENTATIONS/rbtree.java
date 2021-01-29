import java.util.*;

public class rbtree{
    public static void main(String[] args) {
        RedBlackTree<Integer> b=new RedBlackTree<Integer>();
        int arr[]={10,12,8,11,14};
        for(int i=0;i<5;i++){
           
           b.insert(arr[i]);
        }
        System.out.println(b.root.right.right.red);
       
                         
    }

}

class RBTNode<T extends Comparable<T>>{
    T data;
    boolean red;
    RBTNode<T> left;
    RBTNode<T> right;
    RBTNode<T> parent;
    public RBTNode(T data){
        this.data=data;
        this.red=true;
    }
     
}


class RedBlackTree<T extends Comparable<T>> {
    RBTNode<T> root;

    public RedBlackTree(){
        this.root=null;
    }

    public boolean isLeft(RBTNode<T> n){
        if(n.parent==null || n.parent.right==n){
            return false;
        }
        else{
            return true;
        }
    }

    public RBTNode<T> getUncle(RBTNode<T> n){
        if(n.parent==null){
            return null;
        }
        else{
            if(isLeft(n.parent)){
                return n.parent.parent.right;
            }
            else{
                return n.parent.parent.left;

            }
        }

       
    }

    public void inOrderTraversal(RBTNode<T> n){
        if(n==null){
            return;
        }
        inOrderTraversal(n.left);
        System.out.println(n.data);
        inOrderTraversal(n.right);
    }

    public void preOrderTraversal(RBTNode<T> n){
        if(n==null){
            return;
        }
        System.out.println(n.data);
        preOrderTraversal(n.left);
        preOrderTraversal(n.right);
    }

    public void postOrderTraversal(RBTNode<T> n){
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

    public RBTNode<T> searchkey(T k){
       RBTNode<T> n=root;
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
        Queue<RBTNode<T>> q=new LinkedList<RBTNode<T>>();
        if(root==null){
            return;
        }
        q.add(root);
        while(q.size()!=0){
            RBTNode<T> temp=q.peek();
            System.out.println(temp.red);
            System.out.println(q.poll().data);

            if(temp.left!=null){
                q.add(temp.left);
            }
            if(temp.right!=null){
                q.add(temp.right);
            }

        }

    }

    public RBTNode<T> insert(RBTNode<T> p, T x) {
        if(p.data.compareTo(x)<0){
            if(p.right==null){
                RBTNode<T> n=new RBTNode<T>(x);
                p.right=n;
                n.parent=p;
                if(p.red && n.red){
                    return colourcheck(n);
                }
                else{
                    return n;
                }
               
            }
            else{
                return insert(p.right,x);              

            }
           
        }
        else{
            if(p.left==null){
                RBTNode<T> n=new RBTNode<T>(x);
                p.left=n;
                n.parent=p;
                if(p.red && n.red){
                   return colourcheck(n);
                }
                else{
                    return n;
                }
            }
            else{
                return insert(p.left,x);
               
            }

        }

    }

    public RBTNode<T> insert(T x) {
        RBTNode<T> n=new RBTNode<T>(x);
        if(root==null){
            n.red=false;
            root=n;
            return root;
        }
        else{
            return insert(root,x);
        }
       
        }

    public RBTNode<T> leftRotate(RBTNode<T> n){
        RBTNode<T> temp=n.right;
        RBTNode<T> temp1=temp.left;        
        temp.parent=n.parent;
        temp.left=n;
        n.parent=temp;
        n.right=temp1;
       if(temp.parent!=null){
           if(temp.parent.data.compareTo(temp.data)<0){
            temp.parent.right=temp;
           }
           else{
            temp.parent.left=temp;
           }
           
       }
        if(temp1!=null){
            temp1.parent=n;
        }
        if(n==root){
            root=temp;
        }
        return temp;
        
    }
    public RBTNode<T> rightRotate(RBTNode<T> n){
        RBTNode<T> temp=n.left;
        RBTNode<T> temp1=temp.right;
        temp.parent=n.parent;
        temp.right=n;
        n.parent=temp;             
        n.left=temp1;
        if(temp.parent!=null){
            if(temp.parent.data.compareTo(temp.data)<0){
                temp.parent.right=temp;
            }
            else{
                temp.parent.left=temp;
            }
        }
        if(temp1!=null){
            temp1.parent=n;
        }
        if(n==root){
            root=temp;
        }
        return temp;  
       
    }

    public RBTNode<T> leftRightRotate(RBTNode<T> n){
        RBTNode<T> tem=n.parent;
        RBTNode<T> x=n.left.right;
        RBTNode<T> y=x.left;
        RBTNode<T> z=x.right;
        x.parent=tem;
        x.left=n.left;
        n.left.parent=x;
        x.right=n;
        n.parent=x;
        if(z!=null){
            z.parent=n;
        }
        n.left=z;
        if(y!=null){
            y.parent=x.left;
        }       
        x.left.right=y;
        if(x.parent!=null){
            if(x.parent.data.compareTo(x.data)<0){
             x.parent.right=x;
            }
            else{
             x.parent.left=x;
            }
        }
        else{
            x=root;
        }
        return x;
    }


    public RBTNode<T> rightLeftRotate(RBTNode<T> n){
        RBTNode<T> tem=n.parent;
        RBTNode<T> x=n.right.left;
        RBTNode<T> y=x.right;
        RBTNode<T> z=x.left;
        x.parent=tem;
        x.right=n.right;
        n.right.parent=x;
        x.left=n;
        n.parent=x;
        if(z!=null){
            z.parent=n;
        }
        n.right=z;
        if(y!=null){
            y.parent=x.right;
        }       
        x.right.left=y;
       
        if(x.parent!=null){
            if(x.parent.data.compareTo(x.data)<0){
             x.parent.right=x;
            }
            else{
             x.parent.left=x;
            }
        }
        else{
            x=root;
        }
        return x;
        
    }


    public RBTNode<T> colourcheck(RBTNode<T> n){
        if(n.parent==null){
            n.red=false;
            return n;
        }
        else if (getUncle(n)==null || getUncle(n).red==false){
            if(!isLeft(n) && !isLeft(n.parent)){
                RBTNode<T> t=leftRotate(n.parent.parent);
              
                t.red=false;
                t.left.red=true;
                return t;

            }
            else if(isLeft(n) && isLeft(n.parent)){
                RBTNode<T> t=rightRotate(n.parent.parent);
                
                t.red=false;
                t.right.red=true;
                return t;

            }
            else if(isLeft(n) && !isLeft(n.parent)){
                RBTNode<T> t=rightLeftRotate(n.parent.parent);
               
                 t.red=false;
                 t.left.red=true;
                 return t;

            }
            else if(!isLeft(n) && isLeft(n.parent)) {
                RBTNode<T> t=leftRightRotate(n.parent.parent);
              
                 t.red=false;
                 t.right.red=true;
                 return t;

            }
            else{
                return n;
            }
        }

        else if(getUncle(n).red){
            getUncle(n).red=false;
            n.parent.red=false;
            if(n.parent.parent!=null){
                n.parent.parent.red=true;
                return colourcheck(n.parent.parent);
            }
        }    
        return n;      

        }              
}

        

    

   
    

