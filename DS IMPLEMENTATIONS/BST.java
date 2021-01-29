import java.util.*;

public class Bst{
    public static void main(String[] args) {
        BinarySearchTree<Integer> b=new BinarySearchTree<Integer>();
        int arr[]={1,2,3,4,5,6,7,8,13,15};
        for(int i=0;i<arr.length;i++){
            b.insert(arr[i]);
        }
        for(int i=1;i<=15;i++){
            System.out.println(b.getSuccessorinpartialorder(i).data); 
            System.out.println(b.getSuccessorinstrictpartialorder(i).data); 
        }
       
                 
             
    }

}

class BSTNode<T extends Comparable<T>>{
    T data;
    BSTNode<T> left;
    BSTNode<T> right;
    BSTNode<T> parent;
    public BSTNode(T data){
        this.data=data;
    }
   
}


class BinarySearchTree<T extends Comparable<T>> {
    BSTNode<T> root;

    public BinarySearchTree(){
        this.root=null;
    }

    public void inOrderTraversal(BSTNode<T> n){
        if(n==null){
            return;
        }
        inOrderTraversal(n.left);
        System.out.println(n.data);
        inOrderTraversal(n.right);
    }

    public void preOrderTraversal(BSTNode<T> n){
        if(n==null){
            return;
        }
        System.out.println(n.data);
        preOrderTraversal(n.left);
        preOrderTraversal(n.right);
    }

    public void postOrderTraversal(BSTNode<T> n){
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

    public BSTNode<T> searchkey(T k){
       BSTNode<T> n=root;
       while(n!=null){
           if(n.data.compareTo(k)==0){
               return n;
           }
           else{
               if(n.data.compareTo(k)<0){
                   n=n.right;
               }
               else{
                   n=n.left;
               }
           }
       }
       return n;
       
    }

    public void levelOrderTraversal(){       
        Queue<BSTNode<T>> q=new LinkedList<BSTNode<T>>();
        if(root==null){
            return;
        }
        q.add(root);
        while(q.size()!=0){
            BSTNode<T> temp=q.peek();
            System.out.println(q.poll().data);
            if(temp.left!=null){
                q.add(temp.left);
            }
            if(temp.right!=null){
                q.add(temp.right);
            }

        }

    }

    public void insert(T k) {
        BSTNode<T> n=new BSTNode<T>(k);
        if(this.root==null){
            root=n;
        }
        else{
            BSTNode<T> lead=root;
            BSTNode<T> lag=null;
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

    }

    public BSTNode<T> getSuccessorinpartialorder(T k){
        BSTNode<T> lead=root;
        BSTNode<T> lag=null;
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

    public BSTNode<T> getSuccessorinstrictpartialorder(T k){
        BSTNode<T> lead=root;
        BSTNode<T> lag=null;
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
    
    public void delete(T k) {
        BSTNode<T> lead = root;
        BSTNode<T> lag = null;
        while(lead != null && lead.data.compareTo(k) != 0) {
            lag = lead;
            if(lead.data.compareTo(k) > 0) {
                lead = lead.left;
            } 
            else{
                lead = lead.right;
            }
        }

        if(lead == null) {
            System.out.println("Invalid Argument");
        }
        else {
            T x = lag.data;
            if (lead.left == null && lead.right == null) {
                if (x.compareTo(k) > 0) {
                    lag.left = null;
                } 
                else {
                    lag.right = null;
                }
                lead = null;
            } 
            else if (lead.left == null) {
                if (x.compareTo(k) > 0) {
                    lag.left = lead.right;
                    lead.right.parent = lag;
                } 
                else {
                    lag.right = lead.right;
                    lead.right.parent = lag;
                }

            } 
            else if (lead.right == null) {
                if (x.compareTo(k) > 0) {
                    lag.left=lead.left;
                    lead.left.parent=lag;                    
                }
                else{
                    lag.right=lead.left;
                    lead.left.parent=lag;             
                }
            }                    
            else{
                BSTNode<T> temp=lead.left;    
                while(temp.right!=null){
                    temp=temp.right;
                }
                lead.data=temp.data;
                lag=temp.parent;
                if(temp.right==null && temp.left==null){
                    if(lag.data.compareTo(temp.data)>0){
                        lag.left=null;
                    }
                    else{
                        lag.right=null;
                    }
                   
                }
                else if(temp.right==null){
                    if(lag.data.compareTo(temp.data)>0){
                        lag.left=temp.left;
                        temp.left.parent=lag;
                    }
                    else{
                        lag.right=temp.left;
                        temp.left.parent=lag;
                    }
                }
                else if(temp.left==null){
                    if(lag.data.compareTo(temp.data)>0){
                        lag.left=temp.right;
                        temp.right.parent=lag;
                    }
                    else{
                        lag.right=temp.right;
                        temp.right.parent=lag;
                    }

                }
                else{
                    return;
                }
                
    }
        }
    }
}
  