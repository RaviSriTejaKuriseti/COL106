// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }
    
    public A1List Insert(int address, int size, int key)
    {    A1List n=new A1List(address,size,key);
        A1List n1=this;
        while(n1.prev!=null){
            n1=n1.prev;
        }
         A1List x=n1.next;
          x.prev=n;
          n1.next=n;
          n.prev=n1;
          n.next=x;
        return n;
    }

    private boolean compare(Dictionary X){
        if(X.key==this.key && X.address==this.address && X.size==this.size){
            return true;
        }
        return false;
    }
       
 
    public boolean Delete(Dictionary d) 
    {
        boolean ans=false;
        A1List x=this;
        while(x.prev!=null){
            x=x.prev;
       }
        while(x.next!=null && x.compare(d)!=true){
            x=x.next;
        }
        if(x.next==null){
            return ans;
        }
        else{

            A1List y=x.next;
            x.prev.next=y;
            y.prev=x.prev;
            //y.prev.next=y;
            return true;

        }            
        
    }
        
  
    public A1List Find(int k, boolean exact)
    { 
        A1List x=this;
        while(x.prev!=null){
            x=x.prev;
            
      }
      if(exact==true){
        while(x.next!=null && x.key!=k){
            x=x.next;
          }
          if(x.key==k){
              return x;
          }
          else{
              return null;
          }

      }
      
     else{
        while(x.next!=null && x.key<k){
            x=x.next;
          }
          if(x.key>=k){
            return x;
        }
        else{
            return null;
        }


      }
       
        
    }

    public A1List getFirst()
    { 
        A1List x=this;
        while(x.prev!=null){
            x=x.prev;
        }
        if(x.next.next!=null){
            return x.next;
        }
        else{
            return null;
        }
     
              
    }
    
    public A1List getNext() 
    {
     return this.next;
    }

    public boolean sanity()
    { 
       return nodeAlignment();

    }
       
      
  
    private boolean hasCycle()
    {
        
        boolean s1=false;
        A1List temp=this;
        A1List x=this.next;
        while(temp!=null && x!=null && (x.next)!=null && temp!=x){
            temp=temp.next;
            x=x.next.next;
        }
        if(temp==x){
            s1=true;
        }
        else{
            temp=this;
            x=this.prev;
            while((temp)!=null && x!=null && (x.prev)!=null && temp!=x){
                temp=temp.prev;
                x=x.prev.prev;
            }
            if(temp==x){
                s1=true;
            }

        }
        return s1;
       
    }

    private boolean sentinelCheck()
    {
        boolean s1=false;
        boolean s2=false;
        boolean s3=hasCycle();
        if(s3==false){
            A1List temp=this;
            while(temp.next!=null){
                temp=temp.next;
            }
            if(temp.address==-1 && temp.key==-1 && temp.size==-1){
                s1=true;
            }
            temp=this;
            while(temp.prev!=null){
                temp=temp.prev;
            }
            if(temp.address==-1 && temp.key==-1 && temp.size==-1){
                s2=true;
            }

        }
        return s1&&s2;
        
    }

    private boolean nodeAlignment()
    {   boolean ans=false;
        boolean s1=sentinelCheck();
        if(s1==false){
            return ans;
        }
        else{
            A1List temp=this;
            A1List te=this;
            while(temp.next!=null && temp.next.prev==temp){
                temp=temp.next;
            }
            if(temp.next==null){
                while(temp.prev!=null && temp.prev.next==temp){
                    temp=temp.prev;
                }
                if(temp.prev==null){
                    while(temp!=te){
                        temp=temp.next;
                        if(temp.next.prev!=temp){
                            return ans;
                        }
                    }
                    return true;
                }
                else{
                    return ans;
                }
               

            }
            else{
                return ans;
            }
        }
      
        
    }

    


   
}
    




   


