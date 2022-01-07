public class Node {

        Node left, right , head ;
        int data;

        public Node(int data) {
            this.data = data;
        }
        
        public void insert( int a ,String string  ) {
            int value ;
            int b= 0  , t = 0 ;
            int c = 0 ;
            String x = null ;
            if(left.left == null){
                    left.insert( a  =  string.length() , string );//2
                x = string;
                string = null;
                    right.insert( b   =  string.length() , string );//3
                    head.data = a ;//2
                     t= b ;//t=2
                     c = a+ b ;//5
                     b=0;
                     x = x + string ;
                     string = null;
            }

                if (left.left.left == null) {
                    if (right == null) {
                        right.insert(b = string.length(), string);//5
                        head.data = c;//5
                        x = x + string ;
                        string = null;
                    }
                }

                    if (left.left.left.left==null){
                        left.data=c ;
                        head.data=c+b;
                        b=0;

                    }
                }


        public boolean contains(int value){
            if(value==data){
                return true;

            }else if(value <data){
                if(left==null)
                    return false;
                else return left.contains(value);
            }else
                if(right== null) return false;
                else return right.contains(value);
        }
        public void print(){
            if(left != null){
                left.print();
            }System.out.println(data);
            if(right != null){
                right.print();
            }System.out.println(data);
        }

    }


