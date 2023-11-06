public class TypeF extends plant{
    double leaf_size;
    double height;
    double reproduction_rate;
    double disease_sus;
    int lifetime;
    int offspring;

    int age; //cycles
    int last_repro; //# of cycles since last reproduction
    int half_age;

    public TypeF(double leaf_size, double height, double reproduction_rate, double disease_sus, int lifetime, int offspring) {
        this.leaf_size = leaf_size;
        this.height = height;
        this.reproduction_rate = reproduction_rate;
        this.disease_sus = disease_sus;
        this.lifetime = lifetime;
        this.offspring = offspring;

        this.age = 0;
        this.half_age = lifetime/2+1;
        this.last_repro = 0;
    }

    public TypeF(TypeF parent1, TypeF parent2){
        this.leaf_size = calculate_trait(parent1.leaf_size, parent2.leaf_size);
        this.height = calculate_trait(parent1.height, parent2.height);
        this.reproduction_rate = calculate_trait(parent1.reproduction_rate, parent2.reproduction_rate);
        this.disease_sus = calculate_trait(parent1.disease_sus, parent2.disease_sus);
        this.lifetime = calculate_trait(parent1.lifetime, parent2.lifetime);
        this.offspring = calculate_trait(parent1.offspring, parent2.offspring);
    
        this.age = 0;
        this.half_age = lifetime/2+1;
        this.last_repro = 0;
    }

    public void timeUpdate(){
        this.age++;
        this.last_repro++;
    }


    //triange wave reproduction bonus
    public double get_reproduction_rate(){
        if(age < half_age){
            return reproduction_rate + 0.15*(age/half_age);
        }

        if(age > half_age){
            return reproduction_rate + 0.15 - 0.15*((age-half_age)/half_age);
        }

        return reproduction_rate + 0.15;
    }

    @Override
    public String toString() {
        return "TypeE{" +
                "leaf_size=" + leaf_size +
                ", height=" + height +
                ", reproduction_rate=" + reproduction_rate +
                ", disease_sus=" + disease_sus +
                ", lifetime=" + lifetime +
                ", offspring=" + offspring +
                ", age=" + age +
                ", half_age=" + half_age +
                ", last_repro=" + last_repro +
                '}';
    }

    
}
