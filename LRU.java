/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page.replacement.simulator;

import java.util.ArrayList;

/**
 *
 * @author yara
 */
public class LRU {
    
     private int faults=0; //number of faults
        private int frames; //number of frames
        private int hits=0; //number of hits
        private int length = 0; //length of reference string
        private int marker = 0; // to check if = frames
        private int [] memory;
        private int []references; //reference string
        private int [][]memoryDisplay; 
        private boolean checkFull=false; // check if memory is full
        ArrayList<Integer> lru = new ArrayList<Integer>();
        
        
    public LRU(int frames, int []references, int length){
        this.frames=frames;
        this.references=references;
        this.length=length;
         memoryDisplay = new int[length][frames];
         memory= new int[frames];
         displayMemory();
    }
    
    public void calculateLRU(){
         for(int j = 0; j < frames; j++)
                memory[j] = -1;
            for(int i=0; i<length;i++)
            {
                if(lru.contains(references[i]))
                {
                    lru.remove(lru.indexOf(references[i]));
                }

                lru.add(references[i]); 
                int search= -1;
                for(int j=0; j<frames;j++)
                {
                    //check for hit (i.e.: check if reference is already in memory)
                    
                    if(memory[j]==references[i])
                    {
                        search = j;
                        hits++; 
                        break;
                    }
                }

                //check for page fault (i.e.: check if reference is not in memory then add it)

                if(search == -1)
                {
                    if(checkFull)
                        { 
                           int minimumLocation = length;
                           for(int k = 0;k<frames;k++)
                           {
                               if(lru.contains(memory[k]))
                               {
                                    int temp = lru.indexOf(memory[k]) ;

                                    if(temp < minimumLocation)
                                    {
                                        minimumLocation = temp;
                                        marker = k;
                                    }
                               }


                            }
                        }
                memory[marker] = references[i];
                faults++;
                marker++;
                if(marker==frames)
                {
                    marker=0;
                    checkFull=true;
                }
            }
                //copying memory elements into the memory display array 
                
                System.arraycopy(memory, 0, memoryDisplay[i], 0, frames);
            }
        
    }
    
    private void displayMemory()
    {
         
        //-------------MEMORY DISPLAY-------------//
       

        calculateLRU();
    
        for(int i =0; i<frames; i++)
        { 
          
            for(int j=0; j<length;j++)
                System.out.printf(" %3d |", memoryDisplay[j][i]);
            System.out.println();
        
        }
        
        
       //-------------OUTPUT-------------//
        
        System.out.println("Number of frames: "+frames);
        System.out.print("Reference String: ");
        for(int i=0; i<length;i++)
            System.out.print(references[i]+" ");
        System.out.println();
        System.out.println("Number of hits: "+hits);
        System.out.println("Number of faults: "+faults);
    }
    
}
