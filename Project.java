import java.io.*;
import java.util.*;


class Project{
    public static void main(String[]args) throws IOException{
        Random rand = new Random();
        int total_cycles = 110;
        int event_cycle = 75;
        // int current_cycle = 0;

        File csvFile = new File("output_data.csv");
        FileWriter fw = new FileWriter(csvFile);
        String firstline = "Cycle, TypeA, TypeB, TypeC, TypeD, TypeE\n";
        fw.write(firstline);
        // StringBuilder sb = new StringBuilder();

        ArrayList<TypeA> typeA_list = new ArrayList<TypeA>();
        for(int i = 0; i < 10; i++){
            typeA_list.add(new TypeA(1, 1, 1, rand.nextInt(15)+10, 1, 15, 1, 1));
        }
        








        for(int current_cycle = 1; current_cycle <= total_cycles; current_cycle++){

            //reproduction

            //get ready to reproduce
            ArrayList<TypeA> repro_ready = new ArrayList<TypeA>();
            for (TypeA i : typeA_list) {
                if(i.last_repro >= i.reproduction_rate){
                    repro_ready.add(i);
                }
            }

            //pair up and reproduce
            while(repro_ready.size() > 1){
                int plant1 = rand.nextInt(repro_ready.size());
                int plant2 = rand.nextInt(repro_ready.size());

                //no self reproduction
                while(plant1 == plant2){
                    plant2 = rand.nextInt(repro_ready.size());
                }

                TypeA p1 = repro_ready.get(plant1);
                TypeA p2 = repro_ready.get(plant2);

                //create children and add to list [MATH NOT RIGHT HERE]
                for(int i = 0; i < rand.nextInt(3); i++){
                    typeA_list.add(new TypeA(1, 1, 1, rand.nextInt(15)+10, 1, 100, 1, 1));
                }


                //remove parents from ready list
                repro_ready.remove(p1);
                repro_ready.remove(p2);
                // System.out.println(repro_ready.size());
            }





            //tell objects a cycle has passed
            for (TypeA i : typeA_list) {
                i.timeUpdate();
            }

            //kill old
            Iterator<TypeA> iterator = typeA_list.iterator();
            while (iterator.hasNext()) {
                TypeA i = iterator.next();
                if (i.age > i.lifetime) {
                    iterator.remove();
                }
            }




            // //THE EVENT
            // if(current_cycle == event_cycle){
            //     for(TypeA i : typeA_list){

            //     }
            // }


            //write data to csv file
            String line = current_cycle + "," + typeA_list.size() + ",0,0,0,0\n";
            fw.write(line);
            System.out.println("Current Cycle: " + current_cycle);
        }

        fw.close();
    }
}