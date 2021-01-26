import java.util.Vector;
import java.util.Scanner;
import java.io.*;
import java.util.HashMap;


class Pair<U extends Comparable<U>,V extends Comparable<V>>{
    private U first;
    private V second;

    public Pair(U x,V y){
        this.first=x;
        this.second=y;
    }

    public void setFirst(U x){
        this.first=x;
    }
    public void setSecond(V x){
        this.second=x;
    }

    public U getFirst(){
        return this.first;
    }

    public V getSecond(){
        return this.second;
    }

    public int compare(Pair<U,V>p){
        int x=this.getFirst().compareTo(p.getFirst());
        if(x!=0){
         return x;
        }
        else{
            int y=this.getSecond().compareTo(p.getSecond());
            return y;
        }

    }

    
}


class Sorting {
    public static <T extends Comparable<T>> Vector<T> genericmergeSort(Vector<T> arr) {
        if(arr.size()>1){
            int mid=(arr.size())/2;
            Vector<T>L=new Vector<T>();
            Vector<T>M=new Vector<T>(); 
            for(int i=0;i<arr.size();i++){
                if(i<mid){
                    L.add(arr.get(i));
                }
                else{
                    M.add(arr.get(i));
                }
            }
                       
            return genericmerge(genericmergeSort(L),genericmergeSort(M));

        }
        
        else{
           return arr;
        }

    }
     
    public static <T extends Comparable<T>> Vector<T> genericmerge(Vector<T> L, Vector<T> M) {
        Vector<T>ans=new Vector<T>();
        int l=L.size();
        int m=M.size();
        int i=0;
        int j=0;
        while(i<l && j<m){
            int comp=L.get(i).compareTo(M.get(j));
            if(comp>=0){
                ans.add(L.get(i));
                i+=1;
            }
            else{
                ans.add(M.get(j));
                j+=1;
            }          

        }
        while(i<l){
            ans.add(L.get(i));
            i+=1;
        }
        while(j<m){
            ans.add(M.get(j));
            j+=1;    
        }
                
                
        return ans;

    }


    public static <T extends Comparable<T>> Vector<Vector<T>> vectorsort(Vector<Vector<T>> arr){
        if(arr.size()>1){
            int mid=(arr.size())/2;
            Vector<Vector<T>>L=new Vector<Vector<T>>();
            Vector<Vector<T>>M=new Vector<Vector<T>>(); 
            for(int i=0;i<arr.size();i++){
                if(i<mid){
                    L.add(arr.get(i));
                }
                else{
                    M.add(arr.get(i));
                }
            }
                       
            return vectormerge(vectorsort(L),vectorsort(M));

        }
        
        else{
           return arr;
        }

    }

    public static  <T extends Comparable<T>> int compare(Vector<T> A,Vector<T> B){
        int n1=A.size();
        int n2=B.size();
        if(n1!=n2){
            return n1-n2;
        }
        else{
            for(int i=0;i<A.size();i++){
                int z=A.get(i).compareTo(B.get(i));
                if(z!=0){
                    return z;
                }
            }
        }
        return 0;        
    }

    public static <T extends Comparable<T>> Vector<Vector<T>> vectormerge(Vector<Vector<T>> A,Vector<Vector<T>> B){
        Vector<Vector<T>>ans=new Vector<Vector<T>>();  
        int i=0;
        int j=0;
        int l=A.size();
        int m=B.size();
        while(i<l && j<m){
            int comp=compare(A.get(i),B.get(j));
            if(comp>=0){
                ans.add(A.get(i));
                i+=1;
            }
            else{
                ans.add(B.get(j));
                j+=1;
            }
    
        }
        while(i<l){
            ans.add(A.get(i));
            i+=1;
        }
        while(j<m){
            ans.add(B.get(j));
            j+=1;    
        }
        return ans;
         
    }

    
    public static <U extends Comparable<U>,V extends Comparable<V>> Vector<Pair<U,V>> pairsort(Vector<Pair<U,V>> arr){
        if(arr.size()>1){
            int mid=(arr.size())/2;
            Vector<Pair<U,V>>L=new Vector<Pair<U,V>>();
            Vector<Pair<U,V>>M=new Vector<Pair<U,V>>(); 
            for(int i=0;i<arr.size();i++){
                if(i<mid){
                    L.add(arr.get(i));
                }
                else{
                    M.add(arr.get(i));
                }
            }
                       
            return pairmerge(pairsort(L),pairsort(M));

        }
        
        else{
           return arr;
        }
       

    }

    

public static <U extends Comparable<U>,V extends Comparable<V>> Vector<Pair<U,V>> pairmerge(Vector<Pair<U,V>>A,Vector<Pair<U,V>>B){
        Vector<Pair<U,V>>ans=new Vector<Pair<U,V>>();  
        int i=0;
        int j=0;
        int l=A.size();
        int m=B.size();
        while(i<l && j<m){
            int comp=A.get(i).compare(B.get(j));
            if(comp>=0){
                ans.add(A.get(i));
                i+=1;
            }
            else{
                ans.add(B.get(j));
                j+=1;
            }
    
        }
        while(i<l){
            ans.add(A.get(i));
            i+=1;
        }
        while(j<m){
            ans.add(B.get(j));
            j+=1;    
        }
        return ans;
         
    }


}



public class A4_2019CS10369 {
        
    public static void main(String[] args) {
        if (args.length!=3) {
            System.out.println("Invalid Commands");
        }
        else{
            HashMap<String,Integer>content=new HashMap<>();
            int length=0;
            Vector<String>names=new Vector<String>();            
            try{
                File f = new File(args[0]);
                Scanner scan=new Scanner(f);
                String s=scan.nextLine();
                String t="";
                while(scan.hasNextLine()){
                    s=scan.nextLine();
                    t=resurrectnode(s);                 
                    names.add(t);
                    content.put(t,length);
                    length+=1;
                }
                scan.close();

            }
            catch(FileNotFoundException e){
                System.out.println("File doesn't exist");                
            }

            Vector<Vector<Pair<Integer,Integer>>> G=new Vector<Vector<Pair<Integer,Integer>>>();
            for(int j=0;j<length;j++){
                Vector<Pair<Integer,Integer>> array=new Vector<Pair<Integer,Integer>>();
                G.add(array);

            }

            try{
                File f = new File(args[1]);
                Scanner scan=new Scanner(f);
                String s=scan.nextLine();
                while(scan.hasNextLine()){
                    s=scan.nextLine(); 
                    resurrectedge(s,content,G);           
                   
                }
                scan.close();

            }
            catch(FileNotFoundException e){
                System.out.println("File doesn't exist");                
            }
            if(args[2].equals("average")){
                average(G);              

            }
            else if(args[2].equals("rank")){
                rank(G,names);                
               
            }
            else if(args[2].equals("independent_storylines_dfs")){
                independent_storylines_dfs(G,names);              
            }
            else{
                System.out.println("Invalid Inputs");
            }

        }
        
            
       
    }


   public static void average(Vector<Vector<Pair<Integer,Integer>>>G){
        double f=0;
        double z=G.size();
        for(int i=0;i<G.size();i++){
            f+=G.get(i).size();
        }
        double ans=f/z;
        System.out.println(String.format("%.2f",ans));

    }

    public static String resurrectnode(String s){
        int count=0;
        int indexcomma=-1;
        int indexquot=-1;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==','){
                count+=1;
                indexcomma=i;
            }
            else if(s.charAt(i)=='"' && indexquot<=0){
                indexquot=i;
            }
        }
        if(count==1){
            return s.substring(indexcomma+1);
        }
        else{
            return s.substring(indexquot+3,s.length()-1);
        }
    }

    public static void resurrectedge(String s,HashMap<String,Integer> dict,Vector<Vector<Pair<Integer,Integer>>>G){
        int count=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='"'){
                count+=1;
            }
        }
            if(count==0){
               String arr1[]=s.split(",");
               int n1=dict.get(arr1[0]);
               int n2=dict.get(arr1[1]);
               Integer n3=Integer.parseInt(arr1[2]);  
               G.get(n1).add(new Pair<Integer,Integer>(n2,n3));
               G.get(n2).add(new Pair<Integer,Integer>(n1,n3));
            }
            else if(count==2){
                if(s.charAt(0)=='"'){
                    int i=1;        
                    while(i<s.length() && s.charAt(i)!='"'){
                       i+=1;
                    }
                    int c=i;
                    String s1=s.substring(1,c);
                    String s2=s.substring(c+2);
                    int n1=dict.get(s1);
                    String arr[]=s2.split(",");
                    int n2=dict.get(arr[0]);
                    Integer n3=Integer.parseInt(arr[1]);
                    G.get(n1).add(new Pair<Integer,Integer>(n2,n3));
                    G.get(n2).add(new Pair<Integer,Integer>(n1,n3));

                }
                else{
                int ind[]=new int[2];
                int j=0;
                for(int i=0;i<s.length();i++){
                    if(s.charAt(i)=='"'){
                        ind[j]=i;
                        j+=1;
                    }

                }
                String s1=s.substring(0,ind[0]-1);
                String s2=s.substring(ind[0]+1,ind[1]);
                int n1=dict.get(s1);
                int n2=dict.get(s2);
                String s3=s.substring(ind[1]+2);
                Integer n3=Integer.parseInt(s3);
                G.get(n1).add(new Pair<Integer,Integer>(n2,n3));
                G.get(n2).add(new Pair<Integer,Integer>(n1,n3));
                }

                             
            }
            else if(count==4){
                int ind[]=new int[4];
                int j=0;
                for(int i=0;i<s.length();i++){
                    if(s.charAt(i)=='"'){
                        ind[j]=i;
                        j+=1;
                    }

                }
                String s1=s.substring(1,ind[1]);
                String s2=s.substring(ind[2]+1,ind[3]);
                int n1=dict.get(s1);
                int n2=dict.get(s2);
                String s3=s.substring(ind[3]+2);
                Integer n3=Integer.parseInt(s3);
                G.get(n1).add(new Pair<Integer,Integer>(n2,n3));
                G.get(n2).add(new Pair<Integer,Integer>(n1,n3));



            }
            else{
                System.out.println("Invalid Input");
            }
            
    }

    


    public static void rank(Vector<Vector<Pair<Integer,Integer>>>G,Vector<String>names){
        Vector<Pair<Integer,String>>al=new Vector<Pair<Integer,String>>();
       
        for(int i=0;i<G.size();i++){
            int sum=0;
            for(int j=0;j<G.get(i).size();j++){
                sum+=G.get(i).get(j).getSecond();
            }
            Pair<Integer,String>p=new Pair<Integer,String>(sum,names.get(i));
            al.add(p);                  
        }    
       
        Vector<Pair<Integer,String>>alll=Sorting.pairsort(al);
        int l=al.size();
        for(int i=0;i<l-1;i++){
            String x=alll.get(i).getSecond();
            System.out.print(x); 
            System.out.print(",");       
        }
        System.out.print(alll.get(l-1).getSecond());
      
        System.out.println(" ");
       
    }

      

    public static void independent_storylines_dfs(Vector<Vector<Pair<Integer,Integer>>>G,Vector<String>hm){
        int n=G.size();
        Vector<Boolean>visited= new Vector<Boolean>();
        for(int i=0;i<n;i++){
            visited.add(false);
        }
        Vector<Vector<String>> vec=new Vector<Vector<String>>();
        for(int i=0;i<n;i++){
            if(!visited.get(i)){
               Vector<String>st=new Vector<>();
               st.add(hm.get(i));
                vec.add(st);

                DFS(i,visited,G,st,hm);
            }
        }
        int sz=vec.size();
        for(int k=0;k<sz;k++){
            Vector<String>tempvec=Sorting.genericmergeSort(vec.get(k));
            vec.set(k,tempvec);           
        }
        Vector<Vector<String>> finalans=Sorting.vectorsort(vec);
        for(int i=0;i<sz;i++){
            int arrsize=finalans.get(i).size();
            for(int j=0;j<arrsize;j++){
            String x=finalans.get(i).get(j);
            System.out.print(x);
            if(j!=arrsize-1){
                System.out.print(","); 
            } 
          
        }
            System.out.println(" ");
        }          
    }

   public static void DFS(int i,Vector<Boolean>visited,Vector<Vector<Pair<Integer,Integer>>>G,Vector<String>hs,Vector<String>hm){
        visited.set(i,true);
        for(int I=0;I<G.get(i).size();I++){
            int j=G.get(i).get(I).getFirst();
            if(!visited.get(j)){
                hs.add(hm.get(j));
                DFS(j,visited,G,hs,hm);
            }

        }

    }
        
}
