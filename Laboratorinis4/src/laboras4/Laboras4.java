
/*


Pauliaus Kupetčio IIF-13 2 papildoma uzduotis.
Panaudojau koda is zaidimo pacman, kur vaiduoklis turi rast trumpiausia kelia 
iki zaidejo. Teko pritaikyti plonasieniam labirintui ir viskas pavyko. 
*/
package laboras4;


import java.util.ArrayList;



public class Laboras4 {
    public static void main(String[] args) {
        maze lab = new maze(8,6);
        lab.keistiSienas(0, 0, true, true, true, true);
        lab.keistiSienas(0, 1, true, true, true, true);
        lab.keistiSienas(0, 2, true, true, true, true);
        lab.keistiSienas(0, 4, true, true, true, true);
        lab.keistiSienas(0, 5, true, true, true, true);
        lab.keistiSienas(0, 6, true, true, true, true);
        lab.keistiSienas(0, 7, true, false, true, true);
        
        lab.keistiSienas(1, 0, true, true, true, true);
        lab.keistiSienas(1, 1, true, true, true, true);
        lab.keistiSienas(1, 2, true, true, true, true);
        lab.keistiSienas(1, 3, true, true, true, true);
        lab.keistiSienas(1, 4, true, true, true, true);
        lab.keistiSienas(1, 5, true, true, true, true);
        lab.keistiSienas(1, 6, true, true, true, true);
        lab.keistiSienas(1, 7, true, true, true, true);
        
        
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
        
        lab.FindPath(lab.cells[0][0], lab.cells[3][3]);
        lab.print_walk_queue();
        
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
        System.out.println("tasko padetis: "+place.i+" "+place.j);
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

class walk {
    public int walk_count;
    public int x;
    public int y;
    public int back;
}

class target {
    public int x;
    public int y;
}

class maze {
    int width;
    int height;
    ArrayList<point> points;
    cell[][] cells;
    
    ArrayList<target> walk_queue;
    ArrayList<walk> BFSArray;
    
    public maze(int w, int h) {
       this.width = w;
       this.height = h;
       points = new ArrayList();
       BFSArray = new ArrayList();
       walk_queue = new ArrayList();
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
    
    void AddArray( int x, int y, int wc , int back ){

		walk tmp = new walk();
		tmp.x = x;
		tmp.y = y;
		tmp.walk_count = wc;
		tmp.back = back;
		BFSArray.add( tmp );
	
}
    
    public void FindPath(cell pradzia, cell pabaiga){
        
        //Nukopijuojame labirinta ir keiciame cell'iu reiksmes labirinto kopijoje,
        //pacio labirinto nelieciu as
        cell[][] tmp_cells = new cell[this.height][this.width];
        
        System.out.println("ilgis: "+this.cells.length);
        
        for(int i=0; i<this.cells.length; i++)
        for(int j=0; j<this.cells[i].length; j++)
            tmp_cells[i][j]=this.cells[i][j];
        
        BFSArray.clear();
        
        walk tmp = new walk();
        tmp.x = 0;
        
        tmp.x = pradzia.i;
        tmp.y = pradzia.j;
        tmp.walk_count = 0;
        tmp.back= -1;
        
        this.BFSArray.add(tmp);
        
        int i = 0;
        
        while(i < BFSArray.size()){
            //jei vektoriaus kurios dalies koordinates sutampa su zaidejo, reiskias suradome kelia iki zaidejo
		if( BFSArray.get(i).x == pabaiga.i && BFSArray.get(i).y == pabaiga.j ){
                    System.out.println("kelias rastas");
			//istrinam buvusi vektoriu kuris zymejo kaip turi eiti priesas
			walk_queue.clear();
			//sukuriame laikina kintamaji (struktura, kurioje yra koordinates x ir y)
			
			//kol nebuvo pasiektas ejimo skaiciu pabaiga tol darom cikla
			while( BFSArray.get(i).walk_count != 0 ){
                            target tmp2 = new target();
				//leiknajam kintamajam priskiriame koordinates
				tmp2.x = BFSArray.get(i).x;
				tmp2.y = BFSArray.get(i).y;
                                
				//pridedame prie vektoriaus, kuris rodo kaip eiti priesus
				walk_queue.add(tmp2);

				//nurodome kad ciklo numeris yra vektoriaus nurodytas ciklo numeris
				//taip mes tarsi atsekiam is kurio ciklo priskyreme naujas koordinates, ir taip suzinosime kokios tuometines buvo koordinates
				i = BFSArray.get(i).back;
			}
			break;
            }    
            
            	//jei is sitos esamos koordinates galime paeiti kuria nors kryptimi, pridedame dar vieną koordinate, kuria gali eiti vektorius. toje koordinateje issaugome issaugome ejimu skaiciu, 
		//ir sio ciklo numeri pagal kuri atsekame kokius vektoriaus elementus isimti is BFSArray vektorio

                //up
                int temp_x = BFSArray.get(i).x;
                int temp_y = BFSArray.get(i).y;
                if (temp_x > 0)
                if(tmp_cells[temp_x-1][temp_y].down == true){
                    AddArray( BFSArray.get(i).x-1, BFSArray.get(i).y, BFSArray.get(i).walk_count+1, i );
                    tmp_cells[temp_x-1][temp_y].top = false;
                    tmp_cells[temp_x-1][temp_y].down = false;
                    tmp_cells[temp_x-1][temp_y].right = false;
                    tmp_cells[temp_x-1][temp_y].left = false;
                    }
                //down
                if(temp_x+1 < this.height)
                if(tmp_cells[temp_x+1][temp_y].top == true){
                    AddArray( BFSArray.get(i).x+1, BFSArray.get(i).y, BFSArray.get(i).walk_count+1, i );
                    tmp_cells[temp_x+1][temp_y].top = false;
                    tmp_cells[temp_x+1][temp_y].down = false;
                    tmp_cells[temp_x+1][temp_y].right = false;
                    tmp_cells[temp_x+1][temp_y].left = false;
                    }
                //right
                if(temp_y+1 < this.width)
                if(tmp_cells[temp_x][temp_y+1].left == true){
                    AddArray( BFSArray.get(i).x, BFSArray.get(i).y+1, BFSArray.get(i).walk_count+1, i );
                    tmp_cells[temp_x][temp_y+1].top = false;
                    tmp_cells[temp_x][temp_y+1].down = false;
                    tmp_cells[temp_x][temp_y+1].right = false;
                    tmp_cells[temp_x][temp_y+1].left = false;
                    }
                //left
                if(temp_y > 0)
                if(tmp_cells[temp_x][temp_y-1].right == true){
                    AddArray( BFSArray.get(i).x, BFSArray.get(i).y-1, BFSArray.get(i).walk_count+1, i );
                    tmp_cells[temp_x][temp_y-1].top = false;
                    tmp_cells[temp_x][temp_y-1].down = false;
                    tmp_cells[temp_x][temp_y-1].right = false;
                    tmp_cells[temp_x][temp_y-1].left = false;
                    
                }

		i++;
        }  
        BFSArray.clear();
    }
    
    public void print_walk_queue(){
        
        System.out.println("ilgis: "+walk_queue.size());

        int i = 0;
        while(i < this.walk_queue.size()){
            System.out.println("step: "+walk_queue.get(i).x+" "+walk_queue.get(i).y);
            i++;
        }
    }
}
