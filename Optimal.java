/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page.replacement.simulator;

/**
 *
 * @author yara
 */
public class Optimal {
        private int faults=0; //number of faults
        private int frames; //number of frames
        private int hits=0; //number of hits
        private int length = 0; //length of reference string
        private int marker = 0; // to check if = frames
        private int [] memory;
        private int []references; //reference string
        private int [][]memoryDisplay; 
        private boolean checkFull=false; // check if memory is full
        
     public Optimal(int frames, int []references, int length){
        this.frames=frames;
        this.references=references;
        this.length=length;
         memoryDisplay = new int[length][frames];
         memory= new int[frames];
         displayMemory();
    }
    
    
    private void calculateOptimal()
    {
        for(int j = 0; j < frames; j++)
                memory[j] = -1;
              
  
            for(int i = 0; i < length; i++)
            {
             int search = -1;
             for(int j = 0; j < frames; j++)
             {
              if(memory[j] == references[i])
              {
               search = j;
               hits++;
               break;
              } 
             }
             if(search == -1)
             {
                 if(checkFull)
              {
               int[] temp = new int[frames];
               boolean tempMarker[] = new boolean[frames];
            
               for(int j = i + 1; j < length; j++)
               {
                for(int k = 0; k < frames; k++)
                {
                 if((references[j] == memory[k])&& (tempMarker[k] == false))
                 {
                  temp[k] = j;
                  tempMarker[k] = true;
                  break;
                 }
                }
               }
               int position = temp[0]; //last number in reference string, if exists
               marker = 0;
               if(position == 0)
                position = 200;
               for(int j = 0; j < frames; j++)
               {
                if(temp[j] == 0)
                 temp[j] = 200;
                if(temp[j] > position)
                {
                 position = temp[j];
                 marker = j;
                }
               }
              }
              memory[marker] = references[i];
              faults++;
              if(!checkFull)
              {
               marker++;
                  if(marker == frames)
                  {
                   marker = 0;
                   checkFull = true;
                  }
              }
             }
                //copying memory elements into the memory display array 
               System.arraycopy(memory, 0, memoryDisplay[i], 0, frames);
           }
    }
    
     private void displayMemory()
    {
         
        //-------------MEMORY DISPLAY-------------//
       

        calculateOptimal();
    
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
