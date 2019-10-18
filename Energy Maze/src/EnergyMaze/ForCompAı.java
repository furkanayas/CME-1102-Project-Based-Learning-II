package EnergyMaze;

public class ForCompAı {

}
//Unicod gibi basit bir açıklama sadece , oyunda computer hareket etmediği için , en azından düşündüğümüzü gösterme için yazdık
/* public void AI() {
 * has 9 situation 0 for end , 8 for move
 * Use kordinant system
 * 
 * 		   Human (10,10)
 *            C(5,10)5
 *  C(5,5)2      |      C(15,5)1         (x de ilerde olduğundan sola kayacak , y de yukarda olduğundan aşağı kayacak)
 *               |						 (2 tarafta doluysa 3. ye ve 4. ye de bakacak)
 *  C(10,5)6 ----H----  C(10,15)8
 *               |
 *  C(5,15)3     |      C(15,15)4
 *           C(15,10)7
 *           
 *           and if C(10,10) Game Over , 0. situation
 *         
 *           boolean isgameover = false;
 *           int tempx; //temp x
 *           int tempy; //temp y
 *           boolean move =false;
 *           //0.situation
 *           //kordinatı bulup hangi sıralama ile hareket etmesi gerektiğini seçiyor
 *           //Hareket true olursa break yapıp kesiyor ama false sa diğer seçenekleri deniyor .
           */ 
      /**     While(isgameover != true){
 *           tempx = C.getx(); tempy = C.gety();
 *           
 *           //end situation
 *     if(H.getx() == C.getx() && H.gety() == C.gety())
 *     {
 *     		GameoverText();
 *     		isgameover = true;
 *     }
 *     //first situation
 *     else if (C.getx() > H.getx() && C.gety() < H.getx())
 *     {
 *      
 *      solbosmu();
 *       isgameover = false;
 *       if(move == true){
 *     	 break;
 *     	 }
 *     else{
 *      altbosmu();
 *       isgameover = false;
 *       if(move == true){
 *      break;}
 *      else{
 *      sagbosmu();
 *       isgameover = false;
 *       if(move == true){
 *      break;
 *      }
 *      else{
 *      üstbosmu();
 *       isgameover = false;
 *      break;
 *      }
 *      }
 *      }
 *     }*/
       /*second situation sağ alt sol üst
 *     else if (C.getx() < H.getx() && C.gety() < H.getx())
 *     {
 *     	   
 *      sağbosmu();
 *       isgameover = false;
 *       if(move == true){
 *     	 break;
 *     	 }
 *     else{
 *      altbosmu();
 *       isgameover = false;
 *       if(move == true){
 *      break;}
 *      else{
 *      solbosmu();
 *       isgameover = false;
 *       if(move == true){
 *      break;
 *      }
 *      else{
 *      üstbosmu();
 *       isgameover = false;
 *      break;
 *      }
 *      }
 *      }
 *     }*/
      
       /**fifth situation x ler aynı y de yukarda alt sol sağ üst
 *     else if(H.getx() == C.getx() && H.gety() > C.getx()){
 *        altbosmu();
 *       isgameover = false;
 *       if(move == true){
 *     	 break;
 *     	 }
 *     else{
 *      solbosmu();
 *       isgameover = false;
 *       if(move == true){
 *      break;}
 *      else{
 *      sağbosmu();
 *       isgameover = false;
 *       if(move == true){
 *      break;
 *      }
 *      else{
 *      üstbosmu();
 *       isgameover = false;
 *      break;
 *      }
 *      }
 *      }
 *     }
 * }*/

//gibi gibi devam ediyor 4 durumu yukarda yazdık 

/*
 * public void GameoverText(){
 * 		cn.getTextWindow().setCursorPosition(5, 28);
		       cn.getTextWindow().output("Game Over");
 * }
 * 
 * public void solbosmu(){
 * 
 * 	if (C.getx-1 != '#'){ //sol boş mu diye bakıyor boşsa sola geçiyor
 *      	C.setx = tempx-1; C.sety = tempy;
 *      move = true;
 *      isgameover = false;
 *       return;
 
 *      }
 * }
 * 
 * public void sagbosmu(){
 * 
 * 	if (C.getx+1 != '#'){ //sağ boş mu diye bakıyor boşsa sağa geçiyor
 *      	C.setx = tempx+1; C.sety = tempy;
 *      move = true;
 *      isgameover = false;
 *       return;
 
 *      }
 * }
 * 
 * public void ustbosmu(){
 * 
 * 	if (C.gety-1 != '#'){ //üst boş mu diye bakıyor boşsa üste geçiyor
 *      	C.setx = tempx; C.sety = tempy-1;
 *      move = true;
 *      isgameover = false;
 *       return;
 
 *      }
 * }
 *  public void altbosmu(){
 * 
 * 	if (C.gety+1 != '#'){ //alt boş mu diye bakıyor boşsa alta geçiyor
 *      	C.setx = tempx; C.sety = tempy+1;
 *     	move = true;
 *      isgameover = false;
 *       return;
 
 *      }
 * }
 * }
 * 
 * */





