package com.example.tp2_lddm;

import java.util.Iterator;

class Arvore {
    public int n;
    public Node root;

    public Arvore(){
        this.n = 1;
        this.root = new Node( null ,"root" ,false );
    }

    public void insert ( String dad, String content ){
        Node nDad = buscar( dad );
        if(nDad == null){
            System.out.println( dad + " nao encontrado");
        }
        insert( nDad ,content );
    }

    public void insert( Node dad, String content ){
        Node children = new Node( dad,content , true );
        dad.childrens.add( children );
        dad.leaf = false;
        n++;
    }

    public void alterar( String atual, String novo ){
        Node node = buscar( atual );
        node.content = novo;
    }

    public void remove( String content ){
        Node node = buscar( content );
        if( node == null ){
            System.out.println( node + " nao encontrado" );
        }
        remove( node.dad, node );
    }

    public void remove( Node dad , Node children ){
        Iterator itr = dad.childrens.iterator();
        while ( itr.hasNext() ){
            Node x = ( Node )itr.next();
            if ( x == children ){
                itr.remove();
            }
        }
        if( dad.childrens.size() == 0 ){
            dad.leaf = true;
        }
        n--;
    }

    public Node buscar( String content ){
        return buscar( root ,content,0,null);
    }

    public Node buscar( Node node ,String content , int i , Node children ){
        if(i < n){
            if(content.equals(node.content)){
                children = node;
            }
            else if( !node.leaf ){
                Iterator itr = node.childrens.iterator();
                while ( itr.hasNext() ){
                    Node x = ( Node )itr.next();
                    children = buscar( x ,content ,i+1 ,children );
                }
            }
        }
        return children;
    }

    public void showThree(){
        showThree(root,0);
    }

    public Node showThree( Node no, int i ){
        if( i < n ){
            System.out.println( no.content );
            if( !no.leaf ){
                Iterator itr = no.childrens.iterator();
                while ( itr.hasNext() ){
                    Node x = ( Node )itr.next();
                    showThree( x ,i+1 );
                }
            }
        }
        return no;
    }
}