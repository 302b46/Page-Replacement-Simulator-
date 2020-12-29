/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page.replacement.simulator;


import java.util.Scanner;

/**
 *
 * @author yara
 */
public class PageReplacementSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int frames; //number of frames
        int length = 0; //length of reference string
        int []references; //reference string
     
        
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of frames: ");
        frames = sc.nextInt();
  
     
        
    
        System.out.print("Enter size of reference string: ");
        length=sc.nextInt();
       
        references = new int[length];
 
        
        System.out.println("Enter numbers for the reference string: ");
        for(int i= 0; i<length; i++)
        {
        references[i] = sc.nextInt();
        }
   
        System.out.println();
        
        
        
        System.out.println("Pick a number to choose an algorithm:");
        System.out.println("1. First In First Out (FIFO)");
        System.out.println("2. Least Recently Used (LRU)");
        System.out.println("3. Optimal");
        
        System.out.print("Your choice: ");
        int choice =sc.nextInt();
        System.out.println();

       switch(choice)
       {
          //-------------FIFO-------------//
           case 1:
              
               FIFO fifo = new FIFO(frames,references,length);
            break;
            
            //------------- LRU -------------//
           case 2:
              LRU lru = new LRU(frames,references,length);
            break;
            
           //-------------OPTIMAL-------------//
            
           case 3: 
               Optimal optimal = new Optimal(frames,references,length);
            
            break;
          
       }
    }

}
            
        
        


    
    



        
        
        
        
    
    

