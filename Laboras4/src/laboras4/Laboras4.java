
/*


Pauliaus Kupetčio IIF-13, 4 laboratorinis darbas.
*/
package laboras4;


import java.util.ArrayList;



public class Laboras4 {
    public static void main(String[] args) {
        maze lab = new maze(8,6);
        lab.keistiSienas(0, 0, false, true, true, true);
        lab.keistiSienas(0, 1, false, false, false, true);
        lab.keistiSienas(0, 2, true, true, false, false);
        lab.keistiSienas(0, 4, false, true, true, true);
        lab.keistiSienas(0, 5, false, true, false, true);
        lab.keistiSienas(0, 6, false, true, true, true);
        lab.keistiSienas(0, 7, false, false, true, true);
        
        lab.keistiSienas(1, 0, true, false, true, false);
        lab.keistiSienas(1, 1, false, true, true, false);
        lab.keistiSienas(1, 2, false, false, true, true);
        lab.keistiSienas(1, 3, true, false, true, false);
        lab.keistiSienas(1, 4, false, true, true, false);
        lab.keistiSienas(1, 5, false, false, false, true);
        lab.keistiSienas(1, 6, true, false, true, true);
        lab.keistiSienas(1, 7, true, false, true, false);
        
        
        lab.keistiSienas(2, 0, true, false, true, false);
        lab.keistiSienas(2, 1, true, false, true, false);
        lab.keistiSienas(2, 2, true, true, true, false);
        lab.keistiSienas(2, 3, true, false, false, true);
        lab.keistiSienas(2, 4, true, false, true, false);
        lab.keistiSienas(2, 5, false, false, true, false);
        lab.keistiSienas(2, 6, true, false, false, false);
        lab.keistiSienas(2, 7, true, false, true, false);
        
        lab.keistiSienas(3, 0, true, false, true, false);
        lab.keistiSienas(3, 1, true, false, true, false);
        lab.keistiSienas(3, 2, true, true, false, false);
        lab.keistiSienas(3, 3, false, false, true, true);
        lab.keistiSienas(3, 4, true, false, true, false);
        lab.keistiSienas(3, 5, true, false, true, false);
        lab.keistiSienas(3, 6, false, true, true, false);
        lab.keistiSienas(3, 7, true, false, false, true);
        
        lab.keistiSienas(4, 0, true, true, true, false);
        lab.keistiSienas(4, 1, true, true, false, true);
        lab.keistiSienas(4, 2, false, false, false, true);
        lab.keistiSienas(4, 3, true, false, false, false);
        lab.keistiSienas(4, 4, true, true, true, false);
        lab.keistiSienas(4, 5, true, true, false, true);
        lab.keistiSienas(4, 6, true, false, false, true);
        lab.keistiSienas(4, 7, false, false, true, false);
        
        lab.keistiSienas(5, 0, true, false, false, false);
        lab.keistiSienas(5, 1, false, true, false, false);
        lab.keistiSienas(5, 2, false, true, false, true);
        lab.keistiSienas(5, 3, false, true, false, true);
        lab.keistiSienas(5, 4, true, true, false, true);
        lab.keistiSienas(5, 5, false, true, false, true);
        lab.keistiSienas(5, 6, false, true, false, true);
        lab.keistiSienas(5, 7, true, false, false, true);
        
        point taskas = new point("veikejas",lab.cells[0][0]);
        
        taskas.goRight();
        taskas.goDown();
        
        taskas.WhereAreYou();
        
        lab.spausdinti();
    }
    
}

class point {
    public String title;
    public cell place;
    public point(String n, cell p){
        this.title = n;
        this.place = p;
    }
    public void goUp(){
        if(place.top != false)
        {
            place = place.totop;
        }
    }
    public void goDown(){
        if(place.down != false)
        {
            place = place.todown;
        }
    }
    public void goLeft(){
        if(place.left != false)
        {
            place = place.toleft;
        }
    }
    public void goRight(){
        if(place.right != false)
        {
            place = place.toright;
        }
    }
    public void WhereAreYou(){
        System.out.println(place.i+" "+place.j);
    }
}

class cell {
    public boolean top = true;
    public boolean down = true;
    public boolean right = true;
    public boolean left = true;
    public cell totop;
    public cell todown;
    public cell toleft;
    public cell toright;
    int i;
    int j;
    
    public cell(int a, int b){
        i = a;
        j=b;
    }
    
    @Override
    public String toString(){   
        String walls ="";
        if(this.left == false){
            walls += "|";
        }
        if(this.down == false){
            walls += "_";
        } 
        if(this.top == false){
            walls += "¯";
        }
        if(this.top == true && this.down == true){
            walls += " ";
        }
        if(this.right == false){
            walls += "|";
        }
        
        return walls;
    }
}


class maze {
    int width;
    int height;
    ArrayList<point> points;
    cell[][] cells;
    public maze(int w, int h) {
       this.width = w;
       this.height = h;
       points = new ArrayList();
       cells = new cell[h][w];
       for(int i = 0; i < h; i++)
       {
           for(int j = 0; j<w; j++)
           {
               //Is anksto visi laukai salia krastu tures sienas.
               cells[i][j] = new cell(i,j);
               if( i == 0){
                   cells[0][j].top = false;
                   
               }
               if(i == h-1){
                   cells[h-1][j].down = false;
               }
           }
           cells[i][0].left = false;
           cells[i][w-1].right = false;
       }
       for(int i = 0; i < h; i++)
       {
           for(int j = 0; j<w; j++){
               if(j<w-1){
               cells[i][j].toright=cells[i][j+1];
               } else {
                   cells[i][j].toright=cells[0][0];
               }
               if(j>0){
               cells[i][j].toleft=cells[i][j-1];
               } else {
                   cells[i][j].toleft=cells[0][0];
               }
               
               if(i>0){
               cells[i][j].totop=cells[i-1][j];
               } else {
                   cells[i][j].totop=cells[0][0];
               }
               
               if(i<h-1){
               cells[i][j].todown=cells[i+1][j];
               } else {
                   cells[i][j].todown=cells[0][0];
               }
               
           }
       }
    }
    public void spausdinti(){
           for(int i = 0; i < this.height; i++)
       {
           for(int j = 0; j<this.width; j++)
           {
               System.out.print(cells[i][j].toString());
           }
           System.out.println();
       }
       }    
    public boolean keistiSienas(int x,int y,boolean top, boolean right, boolean down, boolean left){
        if(x > this.width-1 || y > this.height-1){
            return false;
        } else{
        cells[x][y].left = left;
        cells[x][y].right = right;
        cells[x][y].top = top;
        cells[x][y].down = down;
        return true;
        }
    }
}
