import java.io.*;
import java.util.*;


class Project{
    public static void main(String[]args) throws IOException{
        Random rand = new Random();
        double disease_strength = 0.5; //input parameter for how deadly/infectious the disease is
        int total_cycles = 70;
        int event_cycle = 60;
        int starting_typeA = 10;
        int starting_typeB = 10;
        int starting_typeC = 10;
        int starting_typeD = 10;
        int starting_typeE = 10;
        int starting_typeF = 10;

        // int current_cycle = 0;

        File csvFile = new File("output_data.csv");
        FileWriter fw = new FileWriter(csvFile);
        String firstline = "Cycle, TypeA, TypeB, TypeC, TypeD, TypeE, TypeF\n";
        fw.write(firstline);
        // StringBuilder sb = new StringBuilder();

        ArrayList<TypeA> typeA_list = new ArrayList<TypeA>();
        ArrayList<TypeB> typeB_list = new ArrayList<TypeB>();
        ArrayList<TypeC> typeC_list = new ArrayList<TypeC>();
        ArrayList<TypeD> typeD_list = new ArrayList<TypeD>();
        ArrayList<TypeE> typeE_list = new ArrayList<TypeE>();
        ArrayList<TypeF> typeF_list = new ArrayList<TypeF>();

        for(int i = 0; i < starting_typeA; i++){
            typeA_list.add(new TypeA(1, 1,  1, 0.5, 15, 1));
        }
        for(int i = 0; i < starting_typeB; i++){
            typeB_list.add(new TypeB(1, 1,  0.5, 1, 15, 1));
        }
        for(int i = 0; i < starting_typeC; i++){
            typeC_list.add(new TypeC(1, 1, 0.5, 1, 15, 1));
        }
        for(int i = 0; i < starting_typeD; i++){
            typeD_list.add(new TypeD(1, 1, 0.5, 1, 15, 1));
        }
        for(int i = 0; i < starting_typeE; i++){
            typeE_list.add(new TypeE(1, 1, 0.5, 1, 15, 1));
        }
        for(int i = 0; i < starting_typeF; i++){
            typeF_list.add(new TypeF(1, 1, 0.5, 1, 15, 1));
        }


        for(int current_cycle = 1; current_cycle <= total_cycles; current_cycle++){

            //reproduction

            //get ready to reproduce
            ArrayList<TypeA> repro_readyA = new ArrayList<TypeA>();
            for (TypeA i : typeA_list) {
                if(i.last_repro >= i.reproduction_rate){
                    repro_readyA.add(i);
                }
            }
            ArrayList<TypeB> repro_readyB = new ArrayList<TypeB>();
            for (TypeB i : typeB_list) {
                if(i.last_repro >= i.reproduction_rate){
                    repro_readyB.add(i);
                }
            }
            ArrayList<TypeC> repro_readyC = new ArrayList<TypeC>();
            for (TypeC i : typeC_list) {
                if(i.last_repro >= i.reproduction_rate){
                    repro_readyC.add(i);
                }
            }
            ArrayList<TypeD> repro_readyD = new ArrayList<TypeD>();
            for (TypeD i : typeD_list) {
                if(i.last_repro >= i.reproduction_rate){
                    repro_readyD.add(i);
                }
            }
            ArrayList<TypeE> repro_readyE = new ArrayList<TypeE>();
            for (TypeE i : typeE_list) {
                if(i.last_repro >= i.reproduction_rate){
                    repro_readyE.add(i);
                }
            }
            ArrayList<TypeF> repro_readyF = new ArrayList<TypeF>();
            for (TypeF i : typeF_list) {
                if(i.last_repro >= i.reproduction_rate){
                    repro_readyF.add(i);
                }
            }


            int totalIterations = -1;
            totalIterations += repro_readyA.size();
            totalIterations += repro_readyB.size();
            totalIterations += repro_readyC.size();
            totalIterations += repro_readyD.size();
            totalIterations += repro_readyE.size();
            totalIterations += repro_readyF.size();

            //pair up and 

            int iterations = reproA(repro_readyA, typeA_list, 0, totalIterations);
            iterations = reproB(repro_readyB, typeB_list, iterations, totalIterations);
            iterations = reproC(repro_readyC, typeC_list, iterations, totalIterations);
            iterations = reproD(repro_readyD, typeD_list, iterations, totalIterations);
            iterations = reproE(repro_readyE, typeE_list, iterations, totalIterations);
            reproF(repro_readyF, typeF_list, iterations, totalIterations);



            //tell objects a cycle has passed
            for (TypeA i : typeA_list) {
                i.timeUpdate();
            }
            for (TypeB i : typeB_list) {
                i.timeUpdate();
            }
            for (TypeC i : typeC_list) {
                i.timeUpdate();
            }
            for (TypeD i : typeD_list) {
                i.timeUpdate();
            }
            for (TypeE i : typeE_list) {
                i.timeUpdate();
            }
            for (TypeF i : typeF_list) {
                i.timeUpdate();
            }

            //kill old
            Iterator<TypeA> iteratorA = typeA_list.iterator();
            while (iteratorA.hasNext()) {
                TypeA i = iteratorA.next();
                if (i.age > i.lifetime) {
                    iteratorA.remove();
                }
            }

            Iterator<TypeB> iteratorB = typeB_list.iterator();
            while (iteratorB.hasNext()) {
                TypeB i = iteratorB.next();
                if (i.age > i.lifetime) {
                    iteratorB.remove();
                }
            }

            Iterator<TypeC> iteratorC = typeC_list.iterator();
            while (iteratorC.hasNext()) {
                TypeC i = iteratorC.next();
                if (i.age > i.lifetime) {
                    iteratorC.remove();
                }
            }

            Iterator<TypeD> iteratorD = typeD_list.iterator();
            while (iteratorD.hasNext()) {
                TypeD i = iteratorD.next();
                if (i.age > i.lifetime) {
                    iteratorD.remove();
                }
            }

            Iterator<TypeE> iteratorE = typeE_list.iterator();
            while (iteratorE.hasNext()) {
                TypeE i = iteratorE.next();
                if (i.age > i.lifetime) {
                    iteratorE.remove();
                }
            }

            Iterator<TypeF> iteratorF = typeF_list.iterator();
            while (iteratorF.hasNext()) {
                TypeF i = iteratorF.next();
                if (i.age > i.lifetime) {
                    iteratorF.remove();
                }
            }




            // //THE EVENT
            if(current_cycle == event_cycle){
                Iterator<TypeA> iteratorA_event = typeA_list.iterator();
                while (iteratorA_event.hasNext()) {
                    TypeA i = iteratorA_event.next();
                    if (i.disease_sus > disease_strength) {
                        iteratorA_event.remove();
                    }
                }
                Iterator<TypeB> iteratorB_event = typeB_list.iterator();
                while (iteratorB_event.hasNext()) {
                    TypeB i = iteratorB_event.next();
                    if (i.disease_sus > disease_strength) {
                        iteratorB_event.remove();
                    }
                }
                Iterator<TypeC> iteratorC_event = typeC_list.iterator();
                while (iteratorC_event.hasNext()) {
                    TypeC i = iteratorC_event.next();
                    if (i.disease_sus > disease_strength) {
                        iteratorC_event.remove();
                    }
                }
                Iterator<TypeD> iteratorD_event = typeD_list.iterator();
                while (iteratorD_event.hasNext()) {
                    TypeD i = iteratorD_event.next();
                    if (i.disease_sus > disease_strength) {
                        iteratorD_event.remove();
                    }
                }
                Iterator<TypeE> iteratorE_event = typeE_list.iterator();
                while (iteratorE_event.hasNext()) {
                    TypeE i = iteratorE_event.next();
                    if (i.disease_sus > disease_strength) {
                        iteratorE_event.remove();
                    }
                }
                Iterator<TypeF> iteratorF_event = typeF_list.iterator();
                while (iteratorF_event.hasNext()) {
                    TypeF i = iteratorF_event.next();
                    if (i.disease_sus > disease_strength) {
                        iteratorF_event.remove();
                    }
                }
            }
            


            //write data to csv file
            String line = current_cycle + "," + typeA_list.size() + "," + typeB_list.size() + "," + typeC_list.size() + "," + typeD_list.size() + "," + typeE_list.size() + "," + typeF_list.size() + "\n";
            fw.write(line);
            System.out.println("Current Cycle: " + current_cycle);
            if(current_cycle == total_cycles){
                if(typeA_list.size() != 0){
                    System.out.println(average(typeA_list).toString());
                }else{
                    System.out.println("No type A Alive");
                }
            }
        }
        
        fw.close();
    }


    public static double calculate_trait(double t1, double t2){
        double expected_value = 0.5*t1 + 0.5*t2;
        double variance = expected_value*expected_value + (0.5*t1*t1+0.5*t2*t2);
        double standard_deviation = Math.sqrt(variance);
        Random rand = new Random();
        return (expected_value - standard_deviation) + (2*standard_deviation*rand.nextDouble());
    }

    public static int calculate_trait(int t1, int t2) {
        double expected_value = 0.5 * t1 + 0.5 * t2;
        double variance = expected_value * expected_value + (0.5 * t1 * t1 + 0.5 * t2 * t2);
        double standard_deviation = Math.sqrt(variance);
        Random rand = new Random();
        return (int) ((expected_value - standard_deviation) + (2 * standard_deviation * rand.nextDouble()));
    }

    public static plant average(ArrayList<TypeA> typeAList){
        int size = typeAList.size();
        double avgLeafSize = 0;
        double avgHeight = 0;
        double avgReproductionRate = 0;
        double avgDiseaseSus = 0;
        int avgLifetime = 0;
        // double avgPropRepro = 0;
        int avgOffspring = 0;
    
        // Calculate average values
        for (TypeA typeA : typeAList) {
            avgLeafSize += typeA.leaf_size;
            avgHeight += typeA.height;
            avgReproductionRate += typeA.reproduction_rate;
            avgDiseaseSus += typeA.disease_sus;
            avgLifetime += typeA.lifetime;
            // avgPropRepro += typeA.prop_repro;
            avgOffspring += typeA.offspring;
        }
    
        avgLeafSize /= size;
        avgHeight /= size;
        avgReproductionRate /= size;
        avgDiseaseSus /= size;
        avgLifetime /= size;
        // avgPropRepro /= size;
        avgOffspring /= size;

        return new TypeA(avgLeafSize, avgHeight, avgReproductionRate, avgDiseaseSus, avgLifetime, avgOffspring);

    }


    public static int reproA(ArrayList<TypeA> repro_ready, ArrayList<TypeA> typeA_list, int currentIteration, int totalIterations){
        Random rand = new Random();
        while(repro_ready.size() > 1){
            //progress bar for longer simulations
            currentIteration++;
            int progress = (int) (((double) currentIteration / totalIterations) * 100)*2;
            System.out.print("\rProgress: [" + "=".repeat(progress) + " " + progress + "%]");

            int plant1 = rand.nextInt(repro_ready.size());
            int plant2 = rand.nextInt(repro_ready.size());

            //no self reproduction
            while(plant1 == plant2){
                plant2 = rand.nextInt(repro_ready.size());
            }

            TypeA p1 = repro_ready.get(plant1);
            TypeA p2 = repro_ready.get(plant2);

            //create children and add to list
            if(rand.nextDouble() < calculate_trait(p1.get_reproduction_rate(), p2.get_reproduction_rate())){
                for(int i = 0; i < calculate_trait(p1.offspring, p2.offspring); i++){
                    typeA_list.add(new TypeA(p1, p2));
                }
            }
            
            //remove parents from ready list
            repro_ready.remove(p1);
            repro_ready.remove(p2);
        }
        return (int) currentIteration;
    }

    public static int reproB(ArrayList<TypeB> repro_ready, ArrayList<TypeB> typeB_list, int currentIteration, int totalIterations){
        Random rand = new Random();
        while(repro_ready.size() > 1){
            //progress bar for longer simulations
            currentIteration++;
            int progress = (int) (((double) currentIteration / totalIterations) * 100)*2;
            System.out.print("\rProgress: [" + "=".repeat(progress) + " " + progress + "%]");

            int plant1 = rand.nextInt(repro_ready.size());
            int plant2 = rand.nextInt(repro_ready.size());

            //no self reproduction
            while(plant1 == plant2){
                plant2 = rand.nextInt(repro_ready.size());
            }

            TypeB p1 = repro_ready.get(plant1);
            TypeB p2 = repro_ready.get(plant2);

            //create children and add to list
            if(rand.nextDouble() < calculate_trait(p1.get_reproduction_rate(), p2.get_reproduction_rate())){
                for(int i = 0; i < calculate_trait(p1.offspring, p2.offspring); i++){
                    typeB_list.add(new TypeB(p1, p2));
                }
            }
            
            //remove parents from ready list
            repro_ready.remove(p1);
            repro_ready.remove(p2);
        }
        return (int) currentIteration;
    }

    public static int reproC(ArrayList<TypeC> repro_ready, ArrayList<TypeC> typeC_list, int currentIteration, int totalIterations){
        Random rand = new Random();
        while(repro_ready.size() > 1){
            //progress bar for longer simulations
            currentIteration++;
            int progress = (int) (((double) currentIteration / totalIterations) * 100)*2;
            System.out.print("\rProgress: [" + "=".repeat(progress) + " " + progress + "%]");

            int plant1 = rand.nextInt(repro_ready.size());
            int plant2 = rand.nextInt(repro_ready.size());

            //no self reproduction
            while(plant1 == plant2){
                plant2 = rand.nextInt(repro_ready.size());
            }

            TypeC p1 = repro_ready.get(plant1);
            TypeC p2 = repro_ready.get(plant2);

            //create children and add to list
            if(rand.nextDouble() < calculate_trait(p1.get_reproduction_rate(), p2.get_reproduction_rate())){
                for(int i = 0; i < calculate_trait(p1.offspring, p2.offspring); i++){
                    typeC_list.add(new TypeC(p1, p2));
                }
            }
            
            //remove parents from ready list
            repro_ready.remove(p1);
            repro_ready.remove(p2);
        }
        return (int) currentIteration;
    }

    public static int reproD(ArrayList<TypeD> repro_ready, ArrayList<TypeD> typeD_list, int currentIteration, int totalIterations){
        Random rand = new Random();
        while(repro_ready.size() > 1){
            //progress bar for longer simulations
            currentIteration++;
            int progress = (int) (((double) currentIteration / totalIterations) * 100)*2;
            System.out.print("\rProgress: [" + "=".repeat(progress) + " " + progress + "%]");

            int plant1 = rand.nextInt(repro_ready.size());
            int plant2 = rand.nextInt(repro_ready.size());

            //no self reproduction
            while(plant1 == plant2){
                plant2 = rand.nextInt(repro_ready.size());
            }

            TypeD p1 = repro_ready.get(plant1);
            TypeD p2 = repro_ready.get(plant2);

            //create children and add to list
            if(rand.nextDouble() < calculate_trait(p1.get_reproduction_rate(), p2.get_reproduction_rate())){
                for(int i = 0; i < calculate_trait(p1.offspring, p2.offspring); i++){
                    typeD_list.add(new TypeD(p1, p2));
                }
            }
            
            //remove parents from ready list
            repro_ready.remove(p1);
            repro_ready.remove(p2);
        }
        return (int) currentIteration;
    }

    public static int reproE(ArrayList<TypeE> repro_ready, ArrayList<TypeE> typeE_list, int currentIteration, int totalIterations){
        Random rand = new Random();
        while(repro_ready.size() > 1){
            //progress bar for longer simulations
            currentIteration++;
            int progress = (int) (((double) currentIteration / totalIterations) * 100)*2;
            System.out.print("\rProgress: [" + "=".repeat(progress) + " " + progress + "%]");

            int plant1 = rand.nextInt(repro_ready.size());
            int plant2 = rand.nextInt(repro_ready.size());

            //no self reproduction
            while(plant1 == plant2){
                plant2 = rand.nextInt(repro_ready.size());
            }

            TypeE p1 = repro_ready.get(plant1);
            TypeE p2 = repro_ready.get(plant2);

            //create children and add to list
            if(rand.nextDouble() < calculate_trait(p1.get_reproduction_rate(), p2.get_reproduction_rate())){
                for(int i = 0; i < calculate_trait(p1.offspring, p2.offspring); i++){
                    typeE_list.add(new TypeE(p1, p2));
                }
            }
            
            //remove parents from ready list
            repro_ready.remove(p1);
            repro_ready.remove(p2);
        }
        return (int) currentIteration;
    }

    public static int reproF(ArrayList<TypeF> repro_ready, ArrayList<TypeF> typeF_list, int currentIteration, int totalIterations){
        Random rand = new Random();
        while(repro_ready.size() > 1){
            //progress bar for longer simulations
            currentIteration++;
            int progress = (int) (((double) currentIteration / totalIterations) * 100)*2;
            System.out.print("\rProgress: [" + "=".repeat(progress) + " " + progress + "%]");

            int plant1 = rand.nextInt(repro_ready.size());
            int plant2 = rand.nextInt(repro_ready.size());

            //no self reproduction
            while(plant1 == plant2){
                plant2 = rand.nextInt(repro_ready.size());
            }

            TypeF p1 = repro_ready.get(plant1);
            TypeF p2 = repro_ready.get(plant2);

            //create children and add to list
            if(rand.nextDouble() < calculate_trait(p1.get_reproduction_rate(), p2.get_reproduction_rate())){
                for(int i = 0; i < calculate_trait(p1.offspring, p2.offspring); i++){
                    typeF_list.add(new TypeF(p1, p2));
                }
            }
            
            //remove parents from ready list
            repro_ready.remove(p1);
            repro_ready.remove(p2);
        }
        return (int) currentIteration;
    }


}