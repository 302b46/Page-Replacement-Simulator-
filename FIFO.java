public class FIFO {
    
        private int faults=0; //number of faults
        private int frames; //number of frames
        private int hits=0; //number of hits
        private int length = 0; //length of reference string
        private int marker = 0; // to check if = frames
        private int [] memory;
        private int []references; //reference string
        private int [][]memoryDisplay; 
        private boolean checkFull=false; // check if memory is full
        
        
    public FIFO(int frames, int []references, int length){
        this.frames=frames;
        this.references=references;
        this.length=length;
         memoryDisplay = new int[length][frames];
         memory= new int[frames];
         displayMemory();
    }
    
    public void calculateFIFO(){
         for(int j = 0; j < frames; j++)
                memory[j] = -1;
          
            for(int i=0; i<length;i++)
            {
                checkFull=false;

                for(int j=0; j<frames;j++)
                {
                    if(memory[j]==references[i])
                    {
                        checkFull=true;
                        hits++;
                        break;
                    }
                }
                if(checkFull==false)
                { 
                    memory[marker] = references[i];
                    faults++;
                    marker++;
                    if(marker>=frames)
                        marker=0; 
                }
                    //copying memory elements into the memory display array 
                     System.arraycopy(memory, 0, memoryDisplay[i], 0, frames);
            }
        
    }
    
    private void displayMemory()
    {
         
        //-------------MEMORY DISPLAY-------------//
       

        calculateFIFO();
    
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
