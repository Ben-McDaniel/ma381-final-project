import java.io.*;
import java.util.*;


class Project extends Thread{
    public static void main(String[]args) throws IOException {
        Random rand = new Random();
        double disease_strength = 0.5; //input parameter for how deadly/infectious the disease is
        int total_cycles = 100;
        int event_cycle = 50;
        int starting_typeA = 5000;
        int starting_typeB = 5000;
        int starting_typeC = 5000;
        int starting_typeD = 5000;
        int starting_typeE = 5000;
        int starting_typeF = 5000;

        int maxPopulation = 50000;

        // int current_cycle = 0;

        File population_data = new File("./output/population_data.csv");
        File typeA_average = new File("./output/typeA_Average.csv");
        File typeB_average = new File("./output/typeB_Average.csv");
        File typeC_average = new File("./output/typeC_Average.csv");
        File typeD_average = new File("./output/typeD_Average.csv");
        File typeE_average = new File("./output/typeE_Average.csv");
        File typeF_average = new File("./output/typeF_Average.csv");

        FileWriter fw = new FileWriter(population_data);
        FileWriter fwA = new FileWriter(typeA_average);
        FileWriter fwB = new FileWriter(typeB_average);
        FileWriter fwC = new FileWriter(typeC_average);
        FileWriter fwD = new FileWriter(typeD_average);
        FileWriter fwE = new FileWriter(typeE_average);
        FileWriter fwF = new FileWriter(typeF_average);

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
            typeA_list.add(new TypeA(7, 5,  0.38, 0.32, 16, 1));
        }
        for(int i = 0; i < starting_typeB; i++){
            typeB_list.add(new TypeB(4, 9,  0.23, 0.52, 26, 3));
        }
        for(int i = 0; i < starting_typeC; i++){
            typeC_list.add(new TypeC(2, 7, 0.77, 0.47, 30, 2));
        }
        for(int i = 0; i < starting_typeD; i++){
            typeD_list.add(new TypeD(3, 2, 0.62, 0.2, 28, 3));
        }
        for(int i = 0; i < starting_typeE; i++){
            typeE_list.add(new TypeE(3, 4, 0.44, 0.28, 33, 1));
        }
        for(int i = 0; i < starting_typeF; i++){
            typeF_list.add(new TypeF(5, 5, 0.33, 0.69, 19, 4));
        }




        for(int current_cycle = 1; current_cycle <= total_cycles; current_cycle++){


            final long startTime = System.currentTimeMillis();
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

            // int newA = typeA_list.size();
            // int newB = typeB_list.size();
            // int newC = typeC_list.size();
            // int newD = typeD_list.size();
            // int newE = typeE_list.size();
            // int newF = typeF_list.size();


            //pair up and 
            
            Thread rA = new Thread(reproA(repro_readyA, typeA_list, 0, totalIterations));
            Thread rB = new Thread(reproB(repro_readyB, typeB_list, 0, totalIterations));
            Thread rC = new Thread(reproC(repro_readyC, typeC_list, 0, totalIterations));
            Thread rD = new Thread(reproD(repro_readyD, typeD_list, 0, totalIterations));
            Thread rE = new Thread(reproE(repro_readyE, typeE_list, 0, totalIterations));
            Thread rF = new Thread(reproF(repro_readyF, typeF_list, 0, totalIterations));

            rA.run();
            rB.run();
            rC.run();
            rD.run();
            rE.run();
            rF.run();

            // newA = typeA_list.size() - newA;
            // newB = typeB_list.size() - newB;
            // newC = typeC_list.size() - newC;
            // newD = typeD_list.size() - newD;
            // newE = typeE_list.size() - newE;
            // newF = typeF_list.size() - newF;

            // int totalAdded = newA + newB + newC + newD + newE + newF;

            
            // if(totalAdded != 0){
            //     if (!typeA_list.isEmpty()) {
            //         double removeRatio = newA / totalAdded;
            //         int removalCount = (int) (removeRatio * typeA_list.size());
            //         for (int i = 0; i < removalCount; i++) {
            //             if (!typeA_list.isEmpty()) {
            //                 int index = rand.nextInt(typeA_list.size());
            //                 typeA_list.remove(index);
            //             }
            //         }
            //     }
                
            //     if (!typeB_list.isEmpty()) {
            //         double removeRatio = newB / totalAdded;
            //         int removalCount = (int) (removeRatio * typeB_list.size());
            //         for (int i = 0; i < removalCount; i++) {
            //             if (!typeB_list.isEmpty()) {
            //                 int index = rand.nextInt(typeB_list.size());
            //                 typeB_list.remove(index);
            //             }
            //         }
            //     }
                
            //     if (!typeC_list.isEmpty()) {
            //         double removeRatio = newC / totalAdded;
            //         int removalCount = (int) (removeRatio * typeC_list.size());
            //         for (int i = 0; i < removalCount; i++) {
            //             if (!typeC_list.isEmpty()) {
            //                 int index = rand.nextInt(typeC_list.size());
            //                 typeC_list.remove(index);
            //             }
            //         }
            //     }
                
            //     if (!typeD_list.isEmpty()) {
            //         double removeRatio = newD / totalAdded;
            //         int removalCount = (int) (removeRatio * typeD_list.size());
            //         for (int i = 0; i < removalCount; i++) {
            //             if (!typeD_list.isEmpty()) {
            //                 int index = rand.nextInt(typeD_list.size());
            //                 typeD_list.remove(index);
            //             }
            //         }
            //     }
                
            //     if (!typeE_list.isEmpty()) {
            //         double removeRatio = newE / totalAdded;
            //         int removalCount = (int) (removeRatio * typeE_list.size());
            //         for (int i = 0; i < removalCount; i++) {
            //             if (!typeE_list.isEmpty()) {
            //                 int index = rand.nextInt(typeE_list.size());
            //                 typeE_list.remove(index);
            //             }
            //         }
            //     }
                
            //     if (!typeF_list.isEmpty()) {
            //         double removeRatio = newF / totalAdded;
            //         int removalCount = (int) (removeRatio * typeF_list.size());
            //         for (int i = 0; i < removalCount; i++) {
            //             if (!typeF_list.isEmpty()) {
            //                 int index = rand.nextInt(typeF_list.size());
            //                 typeF_list.remove(index);
            //             }
            //         }
            //     }
            // }
            
            
                


            // int iterations = reproA(repro_readyA, typeA_list, 0, totalIterations);
            // iterations = reproB(repro_readyB, typeB_list, iterations, totalIterations);
            // iterations = reproC(repro_readyC, typeC_list, iterations, totalIterations);
            // iterations = reproD(repro_readyD, typeD_list, iterations, totalIterations);
            // iterations = reproE(repro_readyE, typeE_list, iterations, totalIterations);
            // reproF(repro_readyF, typeF_list, iterations, totalIterations);


            
            


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

            //we need a way to not have population go to infinity

            // while(typeA_list.size() + typeB_list.size() + typeC_list.size() + typeD_list.size() + typeE_list.size() + typeF_list.size() > maxPopulation){
            //     if (!typeA_list.isEmpty()) {
            //         int index = rand.nextInt(typeA_list.size());
            //         typeA_list.remove(index);
            //     }
            //     if (!typeB_list.isEmpty()) {
            //         int index = rand.nextInt(typeB_list.size());
            //         typeB_list.remove(index);
            //     }
            //     if (!typeC_list.isEmpty()) {
            //         int index = rand.nextInt(typeC_list.size());
            //         typeC_list.remove(index);
            //     }
            //     if (!typeD_list.isEmpty()) {
            //         int index = rand.nextInt(typeD_list.size());
            //         typeD_list.remove(index);
            //     }
            //     if (!typeE_list.isEmpty()) {
            //         int index = rand.nextInt(typeE_list.size());
            //         typeE_list.remove(index);
            //     }
            //     if (!typeF_list.isEmpty()) {
            //         int index = rand.nextInt(typeF_list.size());
            //         typeF_list.remove(index);
            //     }
            // }
            

            
            














    while (typeA_list.size() + typeB_list.size() + typeC_list.size() + typeD_list.size() + typeE_list.size() + typeF_list.size() > maxPopulation) {
                    int totalSize = typeA_list.size() + typeB_list.size() + typeC_list.size() + typeD_list.size() + typeE_list.size() + typeF_list.size();
                
                    if (!typeA_list.isEmpty()) {
                        double removalRatio = (double) typeA_list.size() / totalSize;
                        int removalCount = (int) (removalRatio * typeA_list.size());
                        for (int i = 0; i < removalCount; i++) {
                            if (!typeA_list.isEmpty()) {
                                int index = rand.nextInt(typeA_list.size());
                                typeA_list.remove(index);
                            }
                        }
                    }
                
                    if (!typeB_list.isEmpty()) {
                        double removalRatio = (double) typeB_list.size() / totalSize;
                        int removalCount = (int) (removalRatio * typeB_list.size());
                        for (int i = 0; i < removalCount; i++) {
                            if (!typeB_list.isEmpty()) {
                                int index = rand.nextInt(typeB_list.size());
                                typeB_list.remove(index);
                            }
                        }
                    }
                
                    if (!typeC_list.isEmpty()) {
                        double removalRatio = (double) typeC_list.size() / totalSize;
                        int removalCount = (int) (removalRatio * typeC_list.size());
                        for (int i = 0; i < removalCount; i++) {
                            if (!typeC_list.isEmpty()) {
                                int index = rand.nextInt(typeC_list.size());
                                typeC_list.remove(index);
                            }
                        }
                    }
                
                    if (!typeD_list.isEmpty()) {
                        double removalRatio = (double) typeD_list.size() / totalSize;
                        int removalCount = (int) (removalRatio * typeD_list.size());
                        for (int i = 0; i < removalCount; i++) {
                            if (!typeD_list.isEmpty()) {
                                int index = rand.nextInt(typeD_list.size());
                                typeD_list.remove(index);
                            }
                        }
                    }
                
                    if (!typeE_list.isEmpty()) {
                        double removalRatio = (double) typeE_list.size() / totalSize;
                        int removalCount = (int) (removalRatio * typeE_list.size());
                        for (int i = 0; i < removalCount; i++) {
                            if (!typeE_list.isEmpty()) {
                                int index = rand.nextInt(typeE_list.size());
                                typeE_list.remove(index);
                            }
                        }
                    }
                
                    if (!typeF_list.isEmpty()) {
                        double removalRatio = (double) typeF_list.size() / totalSize;
                        int removalCount = (int) (removalRatio * typeF_list.size());
                        for (int i = 0; i < removalCount; i++) {
                            if (!typeF_list.isEmpty()) {
                                int index = rand.nextInt(typeF_list.size());
                                typeF_list.remove(index);
                            }
                        }
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

            final long endTime = System.currentTimeMillis();
            System.out.print("Current Cycle: " + current_cycle + " | Generation Runtime: " + (endTime - startTime) + "ms");

            if (!typeA_list.isEmpty()) {
                fwA.write(averageA(typeA_list).toString() + "\n");
            } else {
                fwA.write("0,0,0,0,0,0,0,0,0\n");
            }
            
            if (!typeB_list.isEmpty()) {
                fwB.write(averageB(typeB_list).toString() + "\n");
            } else {
                fwB.write("0,0,0,0,0,0,0,0,0\n");
            }
            
            if (!typeC_list.isEmpty()) {
                fwC.write(averageC(typeC_list).toString() + "\n");
            } else {
                fwC.write("0,0,0,0,0,0,0,0,0\n");
            }
            
            if (!typeD_list.isEmpty()) {
                fwD.write(averageD(typeD_list).toString() + "\n");
            } else {
                fwD.write("0,0,0,0,0,0,0,0,0\n");
            }
            
            if (!typeE_list.isEmpty()) {
                fwE.write(averageE(typeE_list).toString() + "\n");
            } else {
                fwE.write("0,0,0,0,0,0,0,0,0\n");
            }
            
            if (!typeF_list.isEmpty()) {
                fwF.write(averageF(typeF_list).toString() + "\n");
            } else {
                fwF.write("0,0,0,0,0,0,0,0,0\n");
            }
            

        }
        
        fw.close();
        fwA.close();
        fwB.close();
        fwC.close();
        fwD.close();
        fwE.close();
        fwF.close();
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

   
    public static TypeA averageA(ArrayList<TypeA> typeAList){
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

    public static TypeB averageB(ArrayList<TypeB> typeBList){
        int size = typeBList.size();
        double avgLeafSize = 0;
        double avgHeight = 0;
        double avgReproductionRate = 0;
        double avgDiseaseSus = 0;
        int avgLifetime = 0;
        // double avgPropRepro = 0;
        int avgOffspring = 0;
    
        // Calculate average values
        for (TypeB typeA : typeBList) {
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

        return new TypeB(avgLeafSize, avgHeight, avgReproductionRate, avgDiseaseSus, avgLifetime, avgOffspring);

    }
    
    public static TypeC averageC(ArrayList<TypeC> typeAList){
        int size = typeAList.size();
        double avgLeafSize = 0;
        double avgHeight = 0;
        double avgReproductionRate = 0;
        double avgDiseaseSus = 0;
        int avgLifetime = 0;
        // double avgPropRepro = 0;
        int avgOffspring = 0;
    
        // Calculate average values
        for (TypeC typeA : typeAList) {
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

        return new TypeC(avgLeafSize, avgHeight, avgReproductionRate, avgDiseaseSus, avgLifetime, avgOffspring);

    }
    
    public static TypeD averageD(ArrayList<TypeD> typeAList){
        int size = typeAList.size();
        double avgLeafSize = 0;
        double avgHeight = 0;
        double avgReproductionRate = 0;
        double avgDiseaseSus = 0;
        int avgLifetime = 0;
        // double avgPropRepro = 0;
        int avgOffspring = 0;
    
        // Calculate average values
        for (TypeD typeA : typeAList) {
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

        return new TypeD(avgLeafSize, avgHeight, avgReproductionRate, avgDiseaseSus, avgLifetime, avgOffspring);

    }
    
    public static TypeE averageE(ArrayList<TypeE> typeAList){
        int size = typeAList.size();
        double avgLeafSize = 0;
        double avgHeight = 0;
        double avgReproductionRate = 0;
        double avgDiseaseSus = 0;
        int avgLifetime = 0;
        // double avgPropRepro = 0;
        int avgOffspring = 0;
    
        // Calculate average values
        for (TypeE typeA : typeAList) {
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

        return new TypeE(avgLeafSize, avgHeight, avgReproductionRate, avgDiseaseSus, avgLifetime, avgOffspring);

    }

    public static TypeF averageF(ArrayList<TypeF> typeAList){
        int size = typeAList.size();
        double avgLeafSize = 0;
        double avgHeight = 0;
        double avgReproductionRate = 0;
        double avgDiseaseSus = 0;
        int avgLifetime = 0;
        // double avgPropRepro = 0;
        int avgOffspring = 0;
    
        // Calculate average values
        for (TypeF typeA : typeAList) {
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

        return new TypeF(avgLeafSize, avgHeight, avgReproductionRate, avgDiseaseSus, avgLifetime, avgOffspring);

    }

    public static Runnable reproA(ArrayList<TypeA> repro_ready, ArrayList<TypeA> typeA_list, int currentIteration, int totalIterations){
        Random rand = new Random();
        while(repro_ready.size() > 1){
            //progress bar for longer simulations
            // currentIteration++;
            // int progress = (int) (((double) currentIteration / totalIterations) * 100)*2;
            // System.out.print("\rProgress: [" + "=".repeat(progress) + " " + progress + "%]");

            int plant1 = rand.nextInt(repro_ready.size());
            int plant2 = rand.nextInt(repro_ready.size());

            //no self reproduction
            while(plant1 == plant2){
                plant2 = rand.nextInt(repro_ready.size());
            }

            TypeA p1 = repro_ready.get(plant1);
            TypeA p2 = repro_ready.get(plant2);

            p1.last_repro = 0;

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
        // return (int) currentIteration;
        return null;
    }

    public static Runnable reproB(ArrayList<TypeB> repro_ready, ArrayList<TypeB> typeB_list, int currentIteration, int totalIterations){
        Random rand = new Random();
        while(repro_ready.size() > 1){
            // //progress bar for longer simulations
            // currentIteration++;
            // int progress = (int) (((double) currentIteration / totalIterations) * 100)*2;
            // System.out.print("\rProgress: [" + "=".repeat(progress) + " " + progress + "%]");

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
        return null;
    }

    public static Runnable reproC(ArrayList<TypeC> repro_ready, ArrayList<TypeC> typeC_list, int currentIteration, int totalIterations){
        Random rand = new Random();
        while(repro_ready.size() > 1){
            //progress bar for longer simulations
            // currentIteration++;
            // int progress = (int) (((double) currentIteration / totalIterations) * 100)*2;
            // System.out.print("\rProgress: [" + "=".repeat(progress) + " " + progress + "%]");

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
        return null;

    }

    public static Runnable reproD(ArrayList<TypeD> repro_ready, ArrayList<TypeD> typeD_list, int currentIteration, int totalIterations){
        Random rand = new Random();
        while(repro_ready.size() > 1){
            //progress bar for longer simulations
            // currentIteration++;
            // int progress = (int) (((double) currentIteration / totalIterations) * 100)*2;
            // System.out.print("\rProgress: [" + "=".repeat(progress) + " " + progress + "%]");

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
        return null;

    }

    public static Runnable reproE(ArrayList<TypeE> repro_ready, ArrayList<TypeE> typeE_list, int currentIteration, int totalIterations){
        Random rand = new Random();
        while(repro_ready.size() > 1){
            // //progress bar for longer simulations
            // currentIteration++;
            // int progress = (int) (((double) currentIteration / totalIterations) * 100)*2;
            // System.out.print("\rProgress: [" + "=".repeat(progress) + " " + progress + "%]");

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
        return null;

    }

    public static Runnable reproF(ArrayList<TypeF> repro_ready, ArrayList<TypeF> typeF_list, int currentIteration, int totalIterations){
        Random rand = new Random();
        while(repro_ready.size() > 1){
            //progress bar for longer simulations
            // currentIteration++;
            // int progress = (int) (((double) currentIteration / totalIterations) * 100)*2;
            // System.out.print("\rProgress: [" + "=".repeat(progress) + " " + progress + "%]");

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
        System.out.println(" ");
        return null;

    }


}